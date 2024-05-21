package Service.Data.Tables;

import Service.Data.Utils.IDUtil;


public class User {
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
            case STUDENT -> Students.getInfo(ID)[Students.password_C];
            case TEACHER -> Teachers.getInfo(ID)[Teachers.password_C];
            case MANAGER -> Managers.getInfo(ID)[Managers.password_C];
            default -> throw new RuntimeException();
        };
    }
    public static void setPassword(String ID, String password){
        int userType = IDUtil.getUserType(ID);
        switch (userType) {
            case STUDENT -> Students.setPassword(ID,password);
            case MANAGER -> Managers.setPassword(ID,password);
            case TEACHER -> Teachers.setPassword(ID,password);
            default -> throw new RuntimeException();
        }
    }
    public static String getName(String ID){
        int userType = IDUtil.getUserType(ID);
        return switch (userType){
            case STUDENT -> Students.getInfo(ID)[Students.name_C];
            case TEACHER -> Teachers.getInfo(ID)[Teachers.name_C];
            case MANAGER -> Managers.getInfo(ID)[Managers.name_C];
            default -> throw new RuntimeException();
        };
    }
    public static void setName(String ID, String name){
        int userType = IDUtil.getUserType(ID);
        switch (userType) {
            case STUDENT -> Students.setName(ID,name);
            case MANAGER -> Managers.setName(ID,name);
            case TEACHER -> Teachers.setName(ID,name);
            default -> throw new RuntimeException();
        }
    }
}
