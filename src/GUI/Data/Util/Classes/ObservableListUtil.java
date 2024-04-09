package GUI.Data.Util.Classes;

import GUI.Data.DataPackage.Classes.Classes;
import GUI.Data.DataPackage.Classes.ClassesSet;
import GUI.Data.DataPackage.Classes.StudentCourseScoreTable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import static Sevice.Main.Components.ClassServ.ClassesServ.getStudentGPA;
import static Sevice.Main.Components.ClassServ.ClassesServ.getStudentGrade;
import static Sevice.Main.Student.ClassesServ.StudentClassesServ.getStudentClassesSet;

public class ObservableListUtil {
    public static ObservableList<StudentCourseScoreTable> getStudentScoreObservableList(String ID) {
        ObservableList<StudentCourseScoreTable> observableList = FXCollections.observableArrayList();

        ClassesSet classesSet = getStudentClassesSet(ID);
        Iterable<Classes> classesSetIterable = classesSet.getClassesIterable();

        for (Classes studentClass : classesSetIterable) {
            String code = studentClass.getCode();
            int score = getStudentGrade(code, ID);
            double GPA = getStudentGPA(code, ID);
            StudentCourseScoreTable newData = new StudentCourseScoreTable(studentClass, GPA, score);
            observableList.add(newData);
        }
        return FXCollections.observableArrayList();
    }
}
