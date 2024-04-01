package Sevice.Utils;

import Data.DataBase;
import GUI.Data.Enum.User.UserType;

public class UserTypeUtil {
    final private static int STUDENT_ACCOUNT_LENGTH = 10;
    final private static int TEACHER_ACCOUNT_LENGTH = 5;
    final private static int MANAGER_ACCOUNT_LENGTH = 1;
    public static int IDToAccount(String ID){
        int length = ID.length();
        switch (length){
            case STUDENT_ACCOUNT_LENGTH -> {
                return DataBase.STUDENT;
            }
            case TEACHER_ACCOUNT_LENGTH -> {
                return DataBase.TEACHER;
            }
            case MANAGER_ACCOUNT_LENGTH -> {
                return DataBase.MANAGER;
            }
            default -> {
                return DataBase.INVALID;
            }
        }
    }
    public static int EnumToDataBase(UserType userType){
        return switch (userType){
            case UserType.Student -> DataBase.STUDENT;
            case UserType.Teacher -> DataBase.TEACHER;
            case UserType.Admin -> DataBase.MANAGER;
            default -> throw new RuntimeException("RegistServ.java(line 35)");
        };
    }
    public static UserType IDToEnum(String ID){
        int length = ID.length();
        switch (length){
            case STUDENT_ACCOUNT_LENGTH -> {
                return UserType.Student;
            }
            case TEACHER_ACCOUNT_LENGTH -> {
                return UserType.Teacher;
            }
            case MANAGER_ACCOUNT_LENGTH -> {
                return UserType.Admin;
            }
            default -> {
                return UserType.None;
            }
        }
    }

}
