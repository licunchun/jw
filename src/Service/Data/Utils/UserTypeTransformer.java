package Service.Data.Utils;

import GUI.Data.Enum.User.UserType;
import Service.Data.Tables.User;

public class UserTypeTransformer {
    public static int fromUserType(UserType userType){
        return switch (userType){
            case UserType.Student -> User.STUDENT;
            case UserType.Teacher -> User.TEACHER;
            case UserType.Admin -> User.MANAGER;
            default -> User.INVALID;
        };
    }
    public static UserType fromString(String ID) {
        int userType = IDUtil.getUserType(ID);
        return switch (userType) {
            case User.STUDENT -> UserType.Student;
            case User.TEACHER -> UserType.Teacher;
            case User.MANAGER -> UserType.Admin;
            default -> UserType.None;
        };
    }
}
