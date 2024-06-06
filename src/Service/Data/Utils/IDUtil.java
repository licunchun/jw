package Service.Data.Utils;

import Service.Data.Tables.Managers;
import Service.Data.Tables.Students;
import Service.Data.Tables.Teachers;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class IDUtil {
    public static final Pattern studentIDPattern = Pattern.compile("^PB[0-9]{8}$");
    public static final Pattern teacherIDPattern = Pattern.compile("^[0-9]{5}$");
    public static final Pattern managerIDPattern = Pattern.compile("^[0-9]{1}$");
    private static Matcher matcher;
    private String ID;
    public int IDType;
    public boolean IDEmpty;
    public boolean IDValid;
//    public boolean IDOverLength;
    public boolean IDExist;
    public String student20AvailableID;
    public String student21AvailableID;
    public String student22AvailableID;
    public String student23AvailableID;
    public String teacherAvailableID;
    public String managerAvailableID;

    public IDUtil() {
        student20AvailableID = getStudentAvailableID("PB20");
        student21AvailableID = getStudentAvailableID("PB21");
        student22AvailableID = getStudentAvailableID("PB22");
        student23AvailableID = getStudentAvailableID("PB23");
        teacherAvailableID = getTeacherAvailableID();
        managerAvailableID = getManagerAvailableID();
    }

    public IDUtil(String ID) {
        this.ID = ID;
        IDType = getIDType();
        IDEmpty = isIDEmpty();
        IDValid = isIDValid();
//        IDOverLength = isIDOverLength();
        IDExist = isIDExist();
    }

    public String getID() {
        return ID;
    }

    private int getIDType(){
        matcher = studentIDPattern.matcher(ID);
        if(matcher.matches())
            return UserUtil.STUDENT;
        matcher = teacherIDPattern.matcher(ID);
        if(matcher.matches())
            return UserUtil.TEACHER;
        matcher = managerIDPattern.matcher(ID);
        if(matcher.matches())
            return UserUtil.MANAGER;
        return UserUtil.INVALID;
    }
    public static int getIDType(String ID){
        matcher = studentIDPattern.matcher(ID);
        if(matcher.matches())
            return UserUtil.STUDENT;
        matcher = teacherIDPattern.matcher(ID);
        if(matcher.matches())
            return UserUtil.TEACHER;
        matcher = managerIDPattern.matcher(ID);
        if(matcher.matches())
            return UserUtil.MANAGER;
        return UserUtil.INVALID;
    }
    private boolean isIDValid(){
        return IDType != UserUtil.INVALID;
    }
    private boolean isIDEmpty(){
        if(ID==null||ID.isEmpty())
            return true;
        return false;
    }
    private boolean isIDExist(){
        UserUtil userUtil = new UserUtil(ID);
        return userUtil.userType != UserUtil.INVALID;
    }
    public static boolean isIDExist(String ID){
        UserUtil userUtil = new UserUtil(ID);
        return userUtil.userType != UserUtil.INVALID;
    }


    //获得可用ID
    private String getStudentAvailableID(String grade){

        for (int i = 0; i < 1000000; i++) {
            Students students = new Students(grade+String.format("%06d", i));
            if(!students.IDExist)
                return grade+String.format("%06d", i);
        }
        throw new RuntimeException("学生数据已满");
    }
    private String getTeacherAvailableID(){
        for (int i = 2000; i < 100000; i++) {
            Teachers teachers = new Teachers(String.format("%05d", i));
            if(!teachers.IDExist)
                return String.format("%05d", i);
        }
        throw new RuntimeException("教师数据已满");
    }
    private String getManagerAvailableID(){
        for (int i = 0; i < 10; i++) {
            Managers managers = new Managers(String.format("%01d", i));
            if(!managers.IDExist)
                return String.format("%01d", i);
        }
        throw new RuntimeException("管理员用户已满");
    }

    public static String[] getTeacherIDFromIDs(String teacherIDs){
        matcher = teacherIDPattern.matcher(teacherIDs);
        ArrayList<String> ID = new ArrayList<>();
        while (matcher.find()){
            ID.add(matcher.group());
        }
        return ID.toArray(new String[0]);
    }
}
