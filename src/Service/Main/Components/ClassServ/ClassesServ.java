package Service.Main.Components.ClassServ;

import GUI.Data.DataPackage.Classes.Classes;
import GUI.Data.DataPackage.Classes.CourseTimeSet;
import GUI.Data.DataPackage.Classes.IDSet;
import GUI.Data.Enum.Classes.*;
import GUI.Data.Enum.Error.Main.Components.ClassesServ.DeleteClassesError;
import GUI.Data.Enum.Error.Main.Components.ClassesServ.NewClassesError;
import GUI.Data.Enum.School;
import Service.Data.Tables.*;
import Service.Data.Utils.*;
import Service.Data.Utils.TimeUtil;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ClassesServ {
    public static Classes getClasses(String classesCode) {
        String[] classInfo = Courses.getInfo(classesCode);
        return Classes.fromArray(classInfo);
    }

    public static IDSet getStudentSet(String classesCode) {
        IDSet idSet = new IDSet();
        String[] IDs = CodeStudent.getStudentsID(classesCode);
        for (String ID:IDs){
            idSet.add(ID);
        }
        return idSet;
    }

    public static int getStudentScore(String classesCode, String ID) {
       String point = CodeStudent.getPoint(classesCode,ID);
       if(point.isEmpty())
           return -1;
       else
            return Integer.parseInt(point);
    }

    public static boolean setStudentScore(String classesCode, String ID, int grade) {
        CodeStudent.setPoint(classesCode,ID,String.valueOf(grade));
        return true;
    }

    public static double getStudentGPA(String classesCode, String ID) {
        String point = CodeStudent.getPoint(classesCode,ID);
        return PointUtil.pointToGPA(point);
    }


    //保留
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
        Courses courses = new Courses(classesCode);
        if(!courses.codeExist)
            return DeleteClassesError.ClassesCodeNotFind;
        //
        CodeStudent.deleteInfo(classesCode);
        CodeTeacher.deleteInfo(classesCode);
        //在学生表中把该课所占用的时间删除
        String courseDays = courses.times;
        for (String ID:courses.studentIDs){
            Students students = new Students(ID);
            String studentDays = students.days;
            String newStudentDays = DaysUtil.deleteDaysInDays(courseDays,studentDays);
            students.setDays(newStudentDays);
        }
        //
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
        if(code.isEmpty())
            return NewClassesError.CodeIsEmpty;
        if(!isCodeValid(code))
            return NewClassesError.CodeInvalid;

        if (name.isEmpty())
            return NewClassesError.NameIsEmpty;
        if (!isNameValid(name))
            return NewClassesError.NameInvalid;

        if (period.isEmpty())
            return NewClassesError.PeriodIsEmpty;
        if (!isPeriodValid(period))
            return NewClassesError.PeriodInvalid;

        if (credits.isEmpty())
            return NewClassesError.CreditsIsEmpty;
        if (!isCreditsValid(credits))
            return NewClassesError.CreditsInvalid;

        if (maxCount.isEmpty())
            return NewClassesError.MaxCountIsEmpty;
        if (!isMaxCountValid(maxCount))
            return NewClassesError.MaxCountInvalid;


        String[] IDs = IDUtil.getTeacherIDFromIDs(teachersID);
        if (teachersID.isEmpty()||IDs.length==0)
            return NewClassesError.TeacherIsEmpty;
        ArrayList<String> names = new ArrayList<>();
        for (String ID:IDs){
            UserUtil userUtil = new UserUtil(ID);
            if(userUtil.userType!=UserUtil.TEACHER)
                return NewClassesError.TeacherInvalid;
            names.add(userUtil.name);
        }
        String teacher = names.toString();
        String[] info = {
                code,
                name,
                period,
                credits,
                DaysUtil.getDaysFromCourseTimeSet(time),
                "0",
                maxCount,
                classType.toString(),
                courseType.toString(),
                school.toString(),
                campus.toString(),
                examMode.toString(),
                language.toString(),
                education.toString(),
                teacher,
                "未满",
                "待定"
        };
        Courses.addInfo(info);
        //将所有开这门课的老师数据处理
        for (String ID:IDs){
            String[] data = {code,ID};
            CodeTeacher.addInfo(data);
        }
        return NewClassesError.Success;
    }

    private static boolean isCodeValid(String code){
        Pattern pattern = Pattern.compile("[0-9A-Z.]{1,20}");
        Matcher managers = pattern.matcher(code);
        return managers.matches();
    }
    private static boolean isNameValid(String name){

        return true;
    }
    private static boolean isPeriodValid(String period){
        try {
            int peri = Integer.parseInt(period);
            return peri > 0 && peri <= 200;
        } catch (NumberFormatException numberFormatException){
            return false;
        }
    }
    private static boolean isCreditsValid(String credits){
        try {
            double cre = Double.parseDouble(credits);
            return cre > 0.0 && cre <= 6.0;
        } catch (NumberFormatException numberFormatException){
            return false;
        }
    }
    private static boolean isMaxCountValid(String maxCount){
        try {
            int max = Integer.parseInt(maxCount);
            return max > 0 && max <= 500;
        } catch (NumberFormatException numberFormatException){
            return false;
        }
    }
}

