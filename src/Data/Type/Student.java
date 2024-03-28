package Data.Type;

import Data.Enum.School;
import Data.Enum.User.Gender;
import Data.Enum.User.Grade;

import java.util.ArrayList;
import java.util.Set;

public class Student {
    public String name;
    public String account;
    public String key;
    public String grade;
    public String gender;
    public String major;
    public String [] classes = null;
    public String money;
    public Student(String [] info) {
        name = info[0];
        account = info[1];
        key = info[2];
        grade = info[3];
        gender = info[4];
        major = info[5];
        if (!info[6].isEmpty()) {
            classes = info[6].split(", ");
        }
        money = info[7];
    }
}
