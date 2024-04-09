package GUI.Data.Util.Classes;

import GUI.Data.DataPackage.Classes.Classes;
import GUI.Data.DataPackage.Classes.ClassesSet;
import GUI.Data.DataPackage.Classes.StudentCourseScoreTable;
import GUI.Data.DataPackage.Classes.TeacherScoreMainTable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import static Sevice.Main.Components.ClassServ.ClassesServ.getStudentGPA;
import static Sevice.Main.Components.ClassServ.ClassesServ.getStudentScore;
import static Sevice.Main.Student.ClassesServ.StudentClassesServ.getStudentClassesSet;
import static Sevice.Main.Teacher.ClassesServ.TeacherClassesServ.getTeacherClassesSet;

public class ObservableListUtil {
    public static ObservableList<StudentCourseScoreTable> getStudentScoreObservableList(String ID) {
        ObservableList<StudentCourseScoreTable> observableList = FXCollections.observableArrayList();

        ClassesSet classesSet = getStudentClassesSet(ID);
        Iterable<Classes> classesSetIterable = classesSet.getClassesIterable();

        for (Classes studentClass : classesSetIterable) {
            String code = studentClass.getCode();
            int score = getStudentScore(code, ID);
            double GPA = getStudentGPA(code, ID);
            StudentCourseScoreTable newData = new StudentCourseScoreTable(studentClass, GPA, score);
            observableList.add(newData);
        }
        return observableList;
    }
    public static ObservableList<TeacherScoreMainTable> getTeacherCourseObservableList(String ID) {
        ObservableList<TeacherScoreMainTable> observableList = FXCollections.observableArrayList();

        ClassesSet classesSet = getTeacherClassesSet(ID);
        Iterable<Classes> classesSetIterable = classesSet.getClassesIterable();

        for (Classes teacherClass : classesSetIterable) {
            TeacherScoreMainTable newData = new TeacherScoreMainTable(teacherClass);
            observableList.add(newData);
        }

        return observableList;
    }
}
