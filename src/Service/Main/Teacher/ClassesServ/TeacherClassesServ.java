package Service.Main.Teacher.ClassesServ;

import GUI.Data.DataPackage.Classes.Classes;
import GUI.Data.DataPackage.Classes.ClassesSet;
import GUI.Data.DataPackage.Classes.CourseCodeSet;
import GUI.Data.DataPackage.Classes.StudentCourseScoreTable;
import Service.Data.Tables.Courses;
import Service.Data.Tables.Points;
import Service.Data.Tables.Teachers;
import Service.Data.Utils.CodeUtil;
import Service.Main.Components.ClassServ.ClassesServ;

public class TeacherClassesServ {
    private static final Teachers teacher = new Teachers();
    public static ClassesSet getTeacherClassesSet(String ID) {
        ClassesSet classesSet = new ClassesSet();
        String classes_t = teacher.getClasses(ID);
        String[] codes = CodeUtil.getCode(classes_t);
        for (String code:codes){
            String[] classInfo = Courses.getInfo(code);
            Classes classes = Classes.fromArray(classInfo);
            classesSet.add(classes);
        }
        return classesSet;
    }

    public static CourseCodeSet getTeacherCourseCodeSet(String ID) {
        CourseCodeSet courseCodeSet = new CourseCodeSet();
        String classes_t = teacher.getClasses(ID);
        String[] codes = CodeUtil.getCode(classes_t);
        for (String code:codes){
            String[] classInfo = Courses.getInfo(code);
            Classes classes = Classes.fromArray(classInfo);
            String[] studentsID = Points.getAllID(code);
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
