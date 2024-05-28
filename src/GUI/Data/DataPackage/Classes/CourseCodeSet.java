package GUI.Data.DataPackage.Classes;

import java.util.HashSet;
import java.util.Set;

public class CourseCodeSet {
    private final Set<StudentCourseScoreTable> courseCodeSet = new HashSet<>();

    public CourseCodeSet() {

    }

    public void add(StudentCourseScoreTable classes) {
        this.courseCodeSet.add(classes);
    }


}