package Data.Type;

import java.util.Set;

public class Student {
    public String name;
    public String account;
    public String gender;
    public String major;
    public String classes;
    public String money;
    public String key;
    public Student(String [] info) {
        name = info[0];
        account = info[1];
        gender = info[2];
        major = info[3];
        classes = info[4];
        money = info[5];
        classes = info[6];
    }
}
