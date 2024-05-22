package GUI.Data.DataPackage.Classes;

import GUI.Data.Enum.Classes.CourseTime;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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


}
