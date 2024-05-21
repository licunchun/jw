package Service.Main.Components.ClassServ;

import GUI.Data.DataPackage.Classes.Classes;
import GUI.Data.DataPackage.Classes.ClassesSet;
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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ClassesServ {
    public static String[] fromClasses(Classes classes){
        String code = classes.getCode()==null?"":classes.getCode();
        String name = classes.getName()==null?"":classes.getName();
        String period = classes.getPeriod()==null?"":classes.getPeriod().toString();
        String credits = classes.getCredits()==null?"":classes.getCredits().toString();
//        String time = classes.getTime().toString();
        String time = "";
        String stdCount = String.valueOf(classes.getStdCount());
        String limitCount = String.valueOf(classes.getLimitCount());
        String classType = classes.getClassType()==null?"":classes.getClassType().toString();
        String courseType = classes.getCourseType()==null?"":classes.getCourseType().toString();
        String school = classes.getSchool()==null?"":classes.getSchool().toString();
        String campus = classes.getCampus()==null?"":classes.getCampus().toString();
        String examMode = classes.getExamMode()==null?"":classes.getExamMode().toString();
        String language = classes.getLanguage()==null?"":classes.getLanguage().toString();
        String education = classes.getEducation()==null?"":classes.getEducation().toString();
//        String teachers = classes.getTeacher().toString();
        String teachers = "";
        String full = classes.getFull()==null?"":classes.getFull().toString();
        String place = classes.getPlace()==null?"":classes.getPlace();
        String[] info = {
                code,
                name,
                period,
                credits,
                time,
                stdCount,
                limitCount,
                classType,
                courseType,
                school,
                campus,
                examMode,
                language,
                education,
                teachers,
                full,
                place
        };
        return info;
    }
    public static Classes getClasses(String classesCode) {
        String[] classInfo = Courses.geInfo(classesCode);
        return Classes.fromArray(classInfo);
    }
    public static ClassesSet searchClasses(Classes classes) {
        String[] conditions = fromClasses(classes);
        String[] codes = Courses.findCode(conditions);
        ClassesSet cs = new ClassesSet();
        for (String code : codes) {
            String[] info = Courses.geInfo(code);
            cs.add(Classes.fromArray(info));
        }
        return cs;
    }

    public static IDSet getStudentSet(String classesCode) {
        IDSet idSet = new IDSet();
        for (String ID:Points.getAllID(classesCode)){
            idSet.add(ID);
        }
        return idSet;
    }

    public static int getStudentScore(String classesCode, String ID) {
       String score = Points.getScore(classesCode,ID);
       return Integer.parseInt(score);
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
        boolean week_flag = true;
        Week w_t = Week.Monday,w;
        ArrayList<Integer> sections = new ArrayList<>();
        for (CourseTime ct:courseTimeSet.getCourseTimeIterable()){
            if(week_flag){
                w_t = ct.getWeek();
                sections.add(ct.getSection());
                week_flag = false;
            }
            else {
                w = ct.getWeek();
                if(w_t==w)
                    sections.add(ct.getSection());
                else {
                    splice(w_t,sections,sb);
                    w_t = w;
                    sections.add(ct.getSection());
                }
            }
        }
        splice(w_t,sections,sb);
        return sb.toString();
    }
private static void splice(Week w,ArrayList<Integer> list,StringBuilder sb){
    sb.append(Week.formWeek(w)).append('(');
    boolean first_flag = true;
    int[] arr = new int[list.size()];
    for (int i = 0; i < list.size(); i++) {
        arr[i] = list.get(i);
    }
    Arrays.sort(arr);

    for (int i:arr){
        if(first_flag) {
            sb.append(i);
            first_flag = false;
        } else {
            sb.append(',').append(i);
        }
    }
    sb.append(')');
    list.clear();
}
    public static DeleteClassesError deleteClasses(String classesCode) {
        //将老师开课信息删除
        String teachers = Courses.geInfo(classesCode)[Courses.teachers_C];
        String regex = "[一-龥·]+"; // 匹配五个数字的正则表达式
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(teachers);
        while (matcher.find()) {
            String teacher = matcher.group();
            String teacherID = Teachers.getID(teacher);
            Teachers.deleteClasses(teacherID,classesCode);
        }
        //将所有选这门课的学生删除
        //Points表也对应删除
        String[] studentsID = Points.getAllID(classesCode);
        for (String studentID:studentsID){
            Points.deletePoints(classesCode,studentID);
            Students.courseWithdrawal(studentID,classesCode);
        }
        //将课程信息删除
        Courses.deleteInfo(classesCode);
        return DeleteClassesError.Success;
    }//TODO

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
            String teacher
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
        } else if (teacher.isEmpty()) {
            return NewClassesError.TeacherIsEmpty;
        } else if (!isTeacherValid(teacher)) {
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
                teacher,
                "未满",
                "待定"
        };
        Courses.addInfo(info);
        return NewClassesError.Success;
    }

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

