package Service.Data.Type;

public class ClassInfo {
    public String code;
    public String name;
    public String period;
    public String credits;
    public String time;
    public String stdCount;
    public String limitCount;
    public String courseType;
    public String department;
    public String campus;
    public String examMode;
    public String Language;
    public String education;
    public String classType;
    public String[] teachers;

    public ClassInfo(String[] info) {
        code = info[0];
        name = info[1];
        period = info[2];
        credits = info[3];
        time = info[4];
        stdCount = info[5];
        limitCount = info[6];
        courseType = info[7];
        department = info[8];
        campus = info[9];
        examMode = info[10];
        Language = info[11];
        education = info[12];
        classType = info[13];
        teachers = info[14].split(", ");
    }
}
