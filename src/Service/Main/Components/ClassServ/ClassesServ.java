package Service.Main.Components.ClassServ;

import Service.Data.DataBase;
import GUI.Data.DataPackage.Classes.Classes;
import GUI.Data.DataPackage.Classes.ClassesSet;
import GUI.Data.DataPackage.Classes.CourseTimeSet;
import GUI.Data.DataPackage.Classes.IDSet;
import GUI.Data.Enum.Classes.*;
import GUI.Data.Enum.Error.Main.Components.ClassesServ.DeleteClassesError;
import GUI.Data.Enum.Error.Main.Components.ClassesServ.NewClassesError;
import GUI.Data.Enum.School;

import java.util.ArrayList;
import java.util.Arrays;

public class ClassesServ {
    public static String[] fromClasses(Classes classes){
        String code = classes.getCode();
        String name = classes.getName();
        String period = classes.getPeriod().toString();
        String credits = classes.getCredits().toString();
//        String time = classes.getTime().toString();
        String time = "";
        String stdCount = String.valueOf(classes.getStdCount());
        String limitCount = String.valueOf(classes.getLimitCount());
        String classType = classes.getClassType().toString();
        String courseType = classes.getCourseType().toString();
        String school = classes.getSchool().toString();
        String campus = classes.getCampus().toString();
        String examMode = classes.getExamMode().toString();
        String language = classes.getLanguage().toString();
        String education = classes.getEducation().toString();
//        String teachers = classes.getTeacher().toString();
        String teachers = "";
        String full = classes.getFull().toString();
        String place = classes.getPlace();
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
        String[] classInfo = DataBase.selectTable(DataBase.COURSE,"code",classesCode);
        return Classes.fromArray(classInfo);
    }
    public static ClassesSet searchClasses(Classes classes) {
        String[] condition = fromClasses(classes);
        String[] codes = DataBase.getClassesCode(condition);
        ClassesSet cs = new ClassesSet();
        for (int i = 0; i < codes.length; i++) {
            String[] info = DataBase.getCourseInfo(codes[i]);
            cs.add(Classes.fromArray(info));
        }
        return cs;
    }

    public static IDSet getStudentSet(String classesCode) {
        IDSet idSet = new IDSet();
        for (String ID:DataBase.getStudentID(classesCode)){
            idSet.add(ID);
        }
        return idSet;
    }

    public static int getStudentScore(String classesCode, String ID) {
       String score = DataBase.getStudentScore(classesCode,ID);
       return Integer.parseInt(score);
    }

    public static boolean setStudentScore(String classesCode, String ID, int grade) {
        return DataBase.setStudentScore(classesCode,ID,String.valueOf(grade));
    }

    public static double getStudentGPA(String classesCode, String ID) {
        int grade = getStudentScore(classesCode,ID);
        if(grade>=95)
            return 4.3;
        else if (grade>=90)
            return 4.0;
        else if (grade>=85)
            return 3.7;
        else if (grade>=82)
            return 3.3;
        else if (grade>=78)
            return 3.0;
        else if (grade>=75)
            return 2.7;
        else if (grade>=72)
            return 2.3;
        else if (grade>=68)
            return 2.0;
        else if (grade>=65)
            return 1.7;
        else if (grade == 64)
            return 1.5;
        else if (grade>=61)
            return 1.3;
        else if (grade == 60)
            return 1.0;
        else
            return 0.0;
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
        DataBase.deleteTable(DataBase.COURSE,"code",classesCode);
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
        DataBase.insertTable(DataBase.COURSE,info);
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

