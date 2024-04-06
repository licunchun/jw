package Data.Type;

import java.util.ArrayList;
import java.util.List;

public class StudentSet {
    List<Student> students;

    public StudentSet() {
        students = new ArrayList<>();
    }

    public Student get(int i) {
        return students.get(i);
    }

    public int length() {
        return students.size();
    }
}
