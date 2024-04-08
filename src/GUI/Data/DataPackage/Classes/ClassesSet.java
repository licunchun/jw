package GUI.Data.DataPackage.Classes;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.HashSet;
import java.util.Set;

import static Sevice.Main.Components.ClassServ.ClassesServ.getStudentGPA;
import static Sevice.Main.Components.ClassServ.ClassesServ.getStudentGrade;
import static Sevice.Main.Student.ClassesServ.StudentClassesServ.getStudentClassesSet;

public class ClassesSet {
    private final Set<Classes> classesSet = new HashSet<>();

    public ClassesSet() {

    }

    public void add(Classes classes) {
        this.classesSet.add(classes);
    }

    public ObservableList<ClassesForTable> toObservableList() {
        ObservableList<ClassesForTable> observableList = FXCollections.observableArrayList();
        if (classesSet == null) {
            return observableList;
        }
        for (Classes classes : classesSet) {
            observableList.add(new ClassesForTable(classes));
        }
        return observableList;
    }
    public Iterable<Classes> getClassesIterable() {
        return classesSet;
    }
    public ObservableList<StudentCourseScoreTable> toGradeObservableList(String ID) {
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
