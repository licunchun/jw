package Service.Data.Utils;

import Service.Data.Tables.Managers;
import Service.Data.Tables.Students;
import Service.Data.Tables.Teachers;
import Service.Data.Utils.IDUtil;


public class UserUtil {
    public int userType;
    public String name;
    public String password;
    public String school;
    private final Students student;
    private final Teachers teacher;
    private final Managers manager;
    public static final int INVALID = -1;
    public static final int STUDENT = 0;
    public static final int TEACHER = 1;
    public static final int MANAGER = 2;


    public UserUtil(String ID) {
        //处理三类用户的共有属性
        student = new Students(ID);
        teacher = new Teachers(ID);
        manager = new Managers(ID);
        userType = initUserType();
        if(userType==INVALID)
            return;
        name = initName();
        password = initPassword();
        school = initSchool();
    }
    private int initUserType(){
        if(student.IDExist)
            return STUDENT;
        if(teacher.IDExist)
            return TEACHER;
        if(manager.IDExist)
            return MANAGER;
        return INVALID;
    }
    private String initName(){
        return switch (userType){
            case STUDENT -> student.name;
            case TEACHER -> teacher.name;
            case MANAGER -> manager.name;
            default -> throw new RuntimeException();
        };
    }
    private String initPassword(){
        return switch (userType){
            case STUDENT -> student.password;
            case TEACHER -> teacher.password;
            case MANAGER -> manager.password;
            default -> throw new RuntimeException();
        };
    }
    private String initSchool(){
        return switch (userType) {
            case STUDENT -> student.school;
            case TEACHER -> teacher.school;
            case MANAGER -> "";
            default -> throw new RuntimeException();
        };
    }
    //set方法
    public void setName(String name){
        switch (userType) {
            case STUDENT -> student.setName(name);
            case TEACHER -> teacher.setName(name);
            case MANAGER -> manager.setName(name);
            default -> throw new RuntimeException();
        }
    }
    public void setPassword(String password){
        switch (userType) {
            case STUDENT -> student.setPassword(password);
            case TEACHER -> teacher.setPassword(password);
            case MANAGER -> manager.setPassword(password);
            default -> throw new RuntimeException();
        }
    }


    public void setSchool(String school) {
        switch (userType) {
            case STUDENT -> student.setSchool(school);
            case TEACHER -> teacher.setSchool(school);
            default -> throw new RuntimeException();
        }
    }
}
