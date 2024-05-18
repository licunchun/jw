package Service.Main.Teacher.ClassesServ;

import GUI.Data.DataPackage.Classes.Classes;
import GUI.Data.DataPackage.Classes.ClassesSet;
import GUI.Data.DataPackage.Classes.CourseCodeSet;
import GUI.Data.DataPackage.Classes.StudentCourseScoreTable;
import Service.Data.DataBase;
import Service.Main.Components.ClassServ.ClassesServ;
import Service.Utils.IDManager;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TeacherClassesServ {
    public static ClassesSet getTeacherClassesSet(String ID) {
        ClassesSet classesSet = new ClassesSet();
        String[] codes = DataBase.getUserInfo(ID);
        Pattern p = Pattern.compile("\"[\\\\dA-Za-z.]+");
        Matcher m = p.matcher(codes[3]);
        while (m.find()) {
            String code = m.group();
            String[] classInfo = DataBase.getClassInfo(code);
            Classes classes = Classes.fromArray(classInfo);
            classesSet.add(classes);
        }
        return classesSet;
    }

    public static CourseCodeSet getTeacherCourseCodeSet(String ID) {
//        if(!IDManager.isIDExist(ID)||ID==null)
//
        CourseCodeSet courseCodeSet = new CourseCodeSet();
        String[] codes = DataBase.getUserInfo(ID);
        Pattern p = Pattern.compile("\"[\\\\dA-Za-z.]+");
        Matcher m = p.matcher(codes[3]);
        while (m.find()){
            String code = m.group();
            String[] classInfo = DataBase.getClassInfo(code);
            Classes classes = Classes.fromArray(classInfo);
            String[] studentsID = DataBase.getStudentID(code);
            for (String studentID:studentsID){
                int score = ClassesServ.getStudentScore(code,studentID);
                double GPA = ClassesServ.getStudentGPA(code,studentID);
                StudentCourseScoreTable studentCourseScoreTable = new StudentCourseScoreTable(classes,GPA,score);
                courseCodeSet.add(studentCourseScoreTable);
            }
        }
        return courseCodeSet;
    }
}
