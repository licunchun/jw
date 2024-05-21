package Data.Type;

import java.util.ArrayList;
import java.util.List;

public class StudentSet {
    public List<Student> students;

    public StudentSet() {
        students = new ArrayList<>();
    }

    public Student get(int i) {
        return students.get(i);
    }

    public int length() {
        return students.size();
    }

    public StudentSet findName(String name) {
        if (name.isEmpty()) return this;

        for (int i = 0; i < this.length(); i++) {
            if (!this.get(i).name.contains(name)) students.remove(i--);
        }
        return this;
    }
}
