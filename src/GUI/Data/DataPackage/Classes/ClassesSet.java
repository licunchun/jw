package GUI.Data.DataPackage.Classes;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.HashSet;
import java.util.Set;

import static Sevice.Main.Components.ClassServ.ClassesServ.getStudentGPA;
import static Sevice.Main.Components.ClassServ.ClassesServ.getStudentScore;
import static Sevice.Main.Student.ClassesServ.StudentClassesServ.getStudentClassesSet;
import static Sevice.Main.Teacher.ClassesServ.TeacherClassesServ.getTeacherClassesSet;

public class ClassesSet {
    private final Set<Classes> classesSet = new HashSet<>();

    public ClassesSet() {

    }

    public void add(Classes classes) {
        this.classesSet.add(classes);
    }

    public ObservableList<ClassesForTable> toObservableList() {
        ObservableList<ClassesForTable> observableList = FXCollections.observableArrayList();
        for (Classes classes : classesSet) {
            observableList.add(new ClassesForTable(classes));
        }
        return observableList;
    }
    public Iterable<Classes> getClassesIterable() {
        return classesSet;
    }

    public ObservableList<String> toListClassesObservableList(String ID) {
        ObservableList<String> observableList = FXCollections.observableArrayList();
        ClassesSet classesSet = getTeacherClassesSet(ID);
        Iterable<Classes> classesSetIterable = classesSet.getClassesIterable();
        for(Classes teacherClass : classesSetIterable) {
            observableList.add(teacherClass.getName());
        }
        return observableList;
    }//TODO
}
