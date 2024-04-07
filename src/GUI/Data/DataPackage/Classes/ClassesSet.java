package GUI.Data.DataPackage.Classes;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.HashSet;
import java.util.Set;

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
}
