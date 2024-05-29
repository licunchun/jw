package Service.Data.Utils;

import GUI.Data.Enum.User.UserType;

public class UserTypeTransformer {
    public static int fromUserType(UserType userType){
        return switch (userType){
            case UserType.Student -> UserUtil.STUDENT;
            case UserType.Teacher -> UserUtil.TEACHER;
            case UserType.Admin -> UserUtil.MANAGER;
            default -> UserUtil.INVALID;
        };
    }
    public static UserType fromString(String ID) {
        int userType = IDUtil.getUserType(ID);
        return switch (userType) {
            case UserUtil.STUDENT -> UserType.Student;
            case UserUtil.TEACHER -> UserType.Teacher;
            case UserUtil.MANAGER -> UserType.Admin;
            default -> UserType.None;
        };
    }
}
