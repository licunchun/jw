package GUI.Data.DataPackage.Classes;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.HashSet;
import java.util.Set;

import static Sevice.Main.Components.ClassServ.ClassesServ.getStudentGPA;
import static Sevice.Main.Components.ClassServ.ClassesServ.getStudentGrade;
import static Sevice.Main.Student.ClassesServ.StudentClassesServ.getStudentClassesSet;

public class CourseCodeSet {
    private final Set<StudentCourseScoreTable> courseCodeSet = new HashSet<>();

    public CourseCodeSet() {

    }

    public void add(StudentCourseScoreTable classes) {
        this.courseCodeSet.add(classes);
    }


}