package Sevice.Main.Components.ClassServ;

import Data.DataBase;
import Data.Type.*;
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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ClassesServ {
    public static Classes getClasses(String classesCode) {
        DataBase db = new DataBase();
        ClassInfoSet cis = db.check();
        cis.findCode(classesCode);
        return ClassInfoSet2Classes(cis);
    }//TODO
    private static CourseTimeSet fromClass(String times){
        CourseTimeSet cts = new CourseTimeSet();
        String pattern = "\\d\\(\\d(,\\d+)*\\)";
        Pattern p = Pattern.compile(pattern);
        Matcher m = p.matcher(times);
        while (m.find()) {
            String time = m.group();

            Pattern p_t = Pattern.compile("\\d+");
            Matcher m_t = p_t.matcher(time);
            int week = 0,section;
            while(m_t.find()){
                String s = m_t.group();
                if(week==0){
                    week = Integer.parseInt(s);
                }else{
                    section = Integer.parseInt(m_t.group());
                    cts.add(CourseTime.values()[(week-1)*13+(section-1)]);
                }
            }
        }
        return cts;
    }
    private static Classes ClassInfoSet2Classes(ClassInfoSet cis)
    {
        ClassInfo ci = cis.classInfos.getFirst();
        DataBase db = new DataBase();
        //时间处理
        CourseTimeSet cts = fromClass(ci.time);
        int stdCount = Integer.parseInt(ci.stdCount),limitCount = Integer.parseInt(ci.limitCount);
        Full full = (stdCount < limitCount)?Full.NotFull:Full.Full;
        IDSet idset = new IDSet();
        for (String name:ci.teachers)
        {
            String ID = db.accountOfTeacher(name);
            idset.add(ID);
        }
        //位置处理
        String place = "未初始化";
        String pattern = "周 (.*?):";
        Pattern p = Pattern.compile(pattern);
        Matcher m = p.matcher(ci.time);

        if(m.find())
        {
            String s = m.group();
            place = s.substring(1, s.length() - 1);
        }

        return new Classes(
                ci.code,
                ci.name,
                Integer.valueOf(ci.period),
                Double.valueOf(ci.credits),
                cts,
                stdCount,
                limitCount,
                ClassType.fromString(ci.courseType),
                CourseType.fromString(ci.classType),
                School.fromString(ci.department),
                Campus.fromString(ci.campus),
                ExamMode.fromString(ci.examMode),
                Language.fromString(ci.Language),
                Education.fromString(ci.education),
                idset,
                full,
                place
                );
    }
    public static ClassesSet searchClasses(Classes classes) {
        return new ClassesSet();
    }//TODO

    public static IDSet getStudentSet(String classesCode) {
        DataBase db = new DataBase();
        PointSet ps = db.points();
        ps.findCode(classesCode);
        IDSet idSet = new IDSet();
        for (Point p:ps.points) {
            idSet.add(p.account);
        }
        return idSet;
    }

    public static int getStudentScore(String classesCode, String ID) {
        DataBase db = new DataBase();
        PointSet ps = db.points();
        ps.findCode(classesCode);
        for (Point p:ps.points) {
            if(p.account.compareTo(ID)==0){
                return Integer.parseInt(p.point);
            }
        }
        return 0;
    }

    public static boolean setStudentScore(String classesCode, String ID, int grade) {
        DataBase db = new DataBase();
        return db.setPoint(classesCode,ID,String.valueOf(grade));
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
            return 1;
        else
            return 0;
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
    sb.append('(').append(w);
    int[] arr = new int[list.size()];
    for (int i = 0; i < list.size(); i++) {
        arr[i] = list.get(i);
    }
    Arrays.sort(arr);

    for (int i:arr){
        sb.append(',').append(i);
    }
    sb.append(')');
    list.clear();
}
    public static DeleteClassesError deleteClasses(String classesCode) {
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
        return NewClassesError.Success;
    }//TODO
}
