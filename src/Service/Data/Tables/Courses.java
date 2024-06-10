package Service.Data.Tables;

import Service.Data.SQLiteJDBC;

import java.util.ArrayList;

public class Courses {
    public static final String tableName = Tables.COURSES;
    public static final String[] CourseCol = Tables.CourseCol;
    public static final String PRIMARY_KEY = "code";
    private static final SQLiteJDBC coursesTable = new SQLiteJDBC(tableName);
    public boolean codeExist;
    private String code;

    public String name;
    public String period;
    public String credits;
    public String times;
    public String stdCount;
    public String limitCount;
    public String classType;
    public String courseType;
    public String school;
    public String campus;
    public String examMode;
    public String language;
    public String education;
    public String teachers;
    public String full;
    public String place;
    public String[] teacherIDs;
    public String[] studentIDs;

    public Courses(String code) {
        this.code = code;
        codeExist = isCodeExist();
        if(!codeExist)
            return;
        String[] info = getInfo();
        name = info[1];
        period = info[2];
        credits = info[3];
        times = info[4];
        stdCount = info[5];
        limitCount = info[6];
        classType = info[7];
        courseType = info[8];
        school = info[9];
        campus = info[10];
        examMode = info[11];
        language = info[12];
        education = info[13];
        teachers = info[14];
        full = info[15];
        place = info[16];
        teacherIDs = CodeTeacher.getTeachersID(code);
        studentIDs = CodeStudent.getStudentsID(code);
    }


    private boolean isCodeExist(){
        return coursesTable.isColValueExist("code",code);
    }
    private String[] getInfo(){
        return coursesTable.select(CourseCol,"code",code);
    }
    //部分属性的set方法
    public void setStdCount(String stdCount){
        coursesTable.update("stdCount",stdCount,PRIMARY_KEY,code);
    }
    public void setFull(String full){
        coursesTable.update("full",full,PRIMARY_KEY,code);
    }

    public static String[] getInfo(String code){
        return coursesTable.select(CourseCol,"code",code);
    }
    //
    public static String[] getAllCode(){
        return coursesTable.selectAll("code");
    }
    public static void addInfo(String[] info){
        coursesTable.insert(CourseCol,info);
    }
    public static void deleteInfo(String code){
        coursesTable.delete(PRIMARY_KEY,code);
    }

    public static String[] findCode(String[] conditions){
        ArrayList<String> valueName = new ArrayList<>();
        ArrayList<String> value = new ArrayList<>();
        for (int i = 0; i < conditions.length; i++) {
            if (conditions[i] != null && !conditions[i].isEmpty()) {
                valueName.add(CourseCol[i]);
                value.add(conditions[i]);
            }
        }
        return coursesTable.selectLike("code",valueName.toArray(new String[0]),value.toArray(new String[0]));
    }
}
