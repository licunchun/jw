package GUI.Data.DataPackage.Classes;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.HashSet;
import java.util.Set;

public class CourseCodeSet {
    private Set<String> courseCodeSet=new HashSet<>();
    public CourseCodeSet(){

    }
    public void add(String ID){
        this.courseCodeSet.add(ID);
    }
    public ObservableList<CourseGradeForTable> toGradeObservableList(){
        return FXCollections.observableArrayList();
    }//TODO
}
