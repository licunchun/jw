package GUI.Data.DataPackage.Classes;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.HashSet;
import java.util.Set;

public class ClassesSet {
    private Set<Classes> classesSet=new HashSet<>();

    public ClassesSet(){

    }

    public ObservableList<ClassesForTable> toOBservableList(){
        ObservableList<ClassesForTable> observableList = FXCollections.observableArrayList();
        if(classesSet==null){
            return observableList;
        }
        for(Classes classes:classesSet){
            observableList.add(new ClassesForTable(classes));
        }
        return observableList;
    }
}
