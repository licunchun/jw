package Service.Data.Type;

public class Teacher {
    public String name;
    public String account;
    public String key;
    public String classes;

    public Teacher(String[] info) {
        name = info[0];
        account = info[1];
        key = info[2];
        classes = info[3];
    }
}
