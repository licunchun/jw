package Service.Data.Utils;

import Service.Data.Tables.Managers;
import Service.Data.Tables.Students;
import Service.Data.Tables.Teachers;
import Service.Data.Utils.IDUtil;


public class UserUtil {
    private static final Students student = new Students();
    private static final Teachers teacher = new Teachers();
    private static final Managers manager = new Managers();
    public static final int INVALID = -1;
    public static final int STUDENT = 0;
    public static final int TEACHER = 1;
    public static final int MANAGER = 2;
    public static boolean isIDExist(String ID){
        int userType = IDUtil.getUserType(ID);
        return switch (userType){
            case STUDENT -> Students.isIDExist(ID);
            case TEACHER -> Teachers.isIDExist(ID);
            case MANAGER -> Managers.isIDExist(ID);
            default -> false;
        };
    }

    public static String getPassword(String ID){
        int userType = IDUtil.getUserType(ID);
        return switch (userType){
            case STUDENT -> student.getPassword(ID);
            case TEACHER -> teacher.getPassword(ID);
            case MANAGER -> manager.getPassword(ID);
            default -> throw new RuntimeException();
        };
    }
    public static void setPassword(String ID, String password){
        int userType = IDUtil.getUserType(ID);
        switch (userType) {
            case STUDENT -> student.setPassword(ID,password);
            case TEACHER -> teacher.setPassword(ID,password);
            case MANAGER -> manager.setPassword(ID,password);
            default -> throw new RuntimeException();
        }
    }
    public static String getName(String ID){
        int userType = IDUtil.getUserType(ID);
        return switch (userType){
            case STUDENT -> student.getName(ID);
            case TEACHER -> teacher.getName(ID);
            case MANAGER -> manager.getName(ID);
            default -> throw new RuntimeException();
        };
    }
    public static void setName(String ID, String name){
        int userType = IDUtil.getUserType(ID);
        switch (userType) {
            case STUDENT -> student.setName(ID,name);
            case TEACHER -> teacher.setName(ID,name);
            case MANAGER -> manager.setName(ID,name);
            default -> throw new RuntimeException();
        }
    }
    public static String getSchool(String ID){
        int userType = IDUtil.getUserType(ID);
        return switch (userType) {
            case STUDENT -> student.getSchool(ID);
            case TEACHER -> teacher.getSchool(ID);
            case MANAGER -> "";
            default -> throw new RuntimeException();
        };
    }
}
