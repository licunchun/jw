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
    private static Teachers teacher;
    public static ClassesSet getTeacherClassesSet(String ID) {
        teacher = new Teachers(ID);
        ClassesSet classesSet = new ClassesSet();

        for (String code: teacher.codes){
            String[] classInfo = Courses.getInfo(code);
            Classes classes = Classes.fromArray(classInfo);
            classesSet.add(classes);
        }
        return classesSet;
    }

    public static CourseCodeSet getTeacherCourseCodeSet(String ID) {
        teacher = new Teachers(ID);
        CourseCodeSet courseCodeSet = new CourseCodeSet();
        for (String code: teacher.codes){
            String[] classInfo = Courses.getInfo(code);
            Classes classes = Classes.fromArray(classInfo);
            Courses courses = new Courses(code);
            for (String studentID:courses.studentIDs){
                int score = ClassesServ.getStudentScore(code,studentID);
                double GPA = ClassesServ.getStudentGPA(code,studentID);
                StudentCourseScoreTable studentCourseScoreTable = new StudentCourseScoreTable(classes,GPA,score);
                courseCodeSet.add(studentCourseScoreTable);
            }
        }
        return courseCodeSet;
    }
}
