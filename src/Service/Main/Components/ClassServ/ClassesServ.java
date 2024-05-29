package Service.Main.Components.ClassServ;

import GUI.Data.DataPackage.Classes.Classes;
import GUI.Data.DataPackage.Classes.CourseTimeSet;
import GUI.Data.DataPackage.Classes.IDSet;
import GUI.Data.Enum.Classes.*;
import GUI.Data.Enum.Error.Main.Components.ClassesServ.DeleteClassesError;
import GUI.Data.Enum.Error.Main.Components.ClassesServ.NewClassesError;
import GUI.Data.Enum.School;
import Service.Data.Tables.Courses;
import Service.Data.Tables.Points;
import Service.Data.Tables.Students;
import Service.Data.Tables.Teachers;
import Service.Data.Utils.CodeUtil;
import Service.Data.Utils.IDUtil;
import Service.Data.Utils.TimeUtil;

import java.util.ArrayList;
import java.util.Arrays;

public class ClassesServ {
    private static final Students student = new Students();
    private static final Teachers teacher = new Teachers();
    private static final Courses course = new Courses();

    public static Classes getClasses(String classesCode) {
        String[] classInfo = Courses.getInfo(classesCode);
        return Classes.fromArray(classInfo);
    }

    public static IDSet getStudentSet(String classesCode) {
        IDSet idSet = new IDSet();
        for (String ID:Points.getAllID(classesCode)){
            idSet.add(ID);
        }
        return idSet;
    }

    public static int getStudentScore(String classesCode, String ID) {
       String point = Points.getScore(classesCode,ID);
       if(point.isEmpty())
           return -1;
       else
            return Integer.parseInt(point);
    }

    public static boolean setStudentScore(String classesCode, String ID, int grade) {
        Points.setScore(classesCode,ID,String.valueOf(grade));
        return true;
    }

    public static double getStudentGPA(String classesCode, String ID) {
        return Points.getGPA(classesCode,ID);
    }

    public static String toStringTime(CourseTimeSet courseTimeSet) {
        StringBuilder sb = new StringBuilder();
        String[] days = TimeUtil.getTimes();
        for (CourseTime ct:courseTimeSet.getCourseTimeIterable()){
            int[] section = CourseTimeSet.toArray(ct);
            String day = TimeUtil.getSetDay(section);
            TimeUtil.addDayInDays(day,days);
        }
        for (String day:days){
            int[] sec = TimeUtil.getSection(day);
            if(sec.length==1)
                continue;
            sb.append(day).append(" ");
        }
        return sb.toString();
    }
    public static DeleteClassesError deleteClasses(String classesCode) {
        if(!Courses.isCodeExist(classesCode))
            return DeleteClassesError.ClassesCodeNotFind;

        //将所有选这门课的学生删除
        String[] studentsID = Points.getAllID(classesCode);
        for (String studentID:studentsID){
            //Points表也对应删除
            Points.deletePoints(classesCode,studentID);
            //
            String classes = student.getClasses(studentID);
            CodeUtil.deleteCodeInClasses(classesCode,classes);
            student.setClasses(studentID,classes);
            //
            String studentTimes = student.getTimes(studentID);
            String courseTimes = course.getTimes(classesCode);
            String[] studentDays = TimeUtil.getDay(studentTimes);
            String[] courseDays = TimeUtil.getDay(courseTimes);
            //学生时间删除
            for (String day:courseDays){
                TimeUtil.deleteDayInDays(day,studentDays);
            }
            String newTimes = Arrays.toString(studentDays);
            student.setTimes(studentID,newTimes);
        }
        //将所有开这门课的老师数据处理
        String[] teachersID = Teachers.getIDWithCode(classesCode);
        for (String teacherID:teachersID){
            String classes = teacher.getClasses(teacherID);
            String newClasses = CodeUtil.deleteCodeInClasses(classesCode,classes);
            teacher.setClasses(teacherID,newClasses);
        }
        //将课程信息删除
        Courses.deleteInfo(classesCode);
        return DeleteClassesError.Success;
    }

    public static NewClassesError newClasses(
            String code,
            String name,
            String period,
            String credits,
            CourseTimeSet time,
            String maxCount,
            ClassType classType,
            CourseType courseType,
            School school,
            Campus campus,
            ExamMode examMode,
            Language language,
            Education education,
            String teachersID
    ) {
        if(code.isEmpty()){
            return NewClassesError.CodeIsEmpty;
        } else if(!isCodeValid(code)){
            return NewClassesError.CodeInvalid;
        } else if (name.isEmpty()) {
            return NewClassesError.NameIsEmpty;
        } else if (!isNameValid(name)) {
            return NewClassesError.NameInvalid;
        } else if (period.isEmpty()) {
            return NewClassesError.PeriodIsEmpty;
        } else if (!isPeriodValid(period)) {
            return NewClassesError.PeriodInvalid;
        } else if (credits.isEmpty()) {
            return NewClassesError.CreditsIsEmpty;
        } else if (!isCreditsValid(credits)) {
            return NewClassesError.CreditsInvalid;
        } else if (maxCount.isEmpty()) {
            return NewClassesError.MaxCountIsEmpty;
        } else if (!isMaxCountValid(maxCount)) {
            return NewClassesError.MaxCountInvalid;
        } else if (teachersID.isEmpty()) {
            return NewClassesError.TeacherIsEmpty;
        } else if (!isTeacherValid(teachersID)) {
            return NewClassesError.TeacherInvalid;
        }
        String[] info = {
                code,
                name,
                period,
                credits,
                toStringTime(time),
                "0",
                maxCount,
                classType.toString(),
                courseType.toString(),
                school.toString(),
                campus.toString(),
                examMode.toString(),
                language.toString(),
                education.toString(),
                teachersID,
                "未满",
                "待定"
        };
        Courses.addInfo(info);
        //将所有开这门课的老师数据处理
        String[] teacherID = IDUtil.getIDFromTeachers(teachersID);
        for (String ID:teacherID){
            String classes = teacher.getClasses(ID);
            String newClasses = CodeUtil.addCodeInClasses(code,classes);
            teacher.setClasses(ID,newClasses);
        }
        return NewClassesError.Success;
    }

    //TODO:
    private static boolean isCodeValid(String code){
        return true;
    }
    private static boolean isNameValid(String name){
        return true;
    }
    private static boolean isPeriodValid(String period){
        return true;
    }
    private static boolean isCreditsValid(String credits){
        return true;
    }
    private static boolean isMaxCountValid(String maxCount){
        return true;
    }
    private static boolean isTeacherValid(String teacher){
        return true;
    }
}

