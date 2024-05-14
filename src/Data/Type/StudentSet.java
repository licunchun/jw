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
    public void findName(String name) {
        if (name.isEmpty()) return;

        for (int i = 0; i < this.length(); i++) {
            if (!this.get(i).name.contains(name)) students.remove(i--);
        }
    }
}
