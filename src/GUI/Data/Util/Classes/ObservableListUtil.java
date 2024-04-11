package GUI.Data.Util.Classes;

import GUI.Controller.Main.Teacher.Classes.TeacherScoreSubPageController;
import GUI.Data.DataPackage.Classes.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import static Sevice.Main.Components.ClassServ.ClassesServ.*;
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
    public static ObservableList<TeacherScoreMainTable> getTeacherScoreMainPageObservableList(String ID) {
        ObservableList<TeacherScoreMainTable> observableList = FXCollections.observableArrayList();

        ClassesSet classesSet = getTeacherClassesSet(ID);
        Iterable<Classes> classesSetIterable = classesSet.getClassesIterable();

        for (Classes teacherClass : classesSetIterable) {
            TeacherScoreMainTable newData = new TeacherScoreMainTable(teacherClass);
            observableList.add(newData);
        }

        return observableList;
    }
    public static ObservableList<TeacherScoreSubTable> getTeacherScoreSubPageObservableList(String ID, Classes classes) {
        ObservableList<TeacherScoreSubTable> observableList = FXCollections.observableArrayList();

        String classesCode = classes.getCode();
        IDSet studentIDSet = getStudentSet(classesCode);
        Iterable<String> studentIDSetIterable = studentIDSet.getStudentIDSetIterable();

        for (String studentID : studentIDSetIterable) {
        //    TeacherScoreMainTable newData = new TeacherScoreMainTable(teacherClass);
            TeacherScoreSubTable newData = new TeacherScoreSubTable(classesCode, studentID);
            observableList.add(newData);
        }

        return observableList;
    }
}
