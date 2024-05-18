package Service.Utils;

import Service.Data.DataBase;
import GUI.Data.Enum.User.UserType;

public class UserTypeTransformer {
    public static int fromUserType(UserType userType){
        return switch (userType){
            case UserType.Student -> DataBase.STUDENT;
            case UserType.Teacher -> DataBase.TEACHER;
            case UserType.Admin -> DataBase.MANAGER;
            default -> DataBase.INVALID;
        };
    }
    public static UserType fromString(String ID) {
        int userType = IDManager.getUserType(ID);
        return switch (userType) {
            case DataBase.STUDENT -> UserType.Student;
            case DataBase.TEACHER -> UserType.Teacher;
            case DataBase.MANAGER -> UserType.Admin;
            default -> UserType.None;
        };
    }
}
