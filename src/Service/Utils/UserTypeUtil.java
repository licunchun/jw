package Service.Utils;

import Data.DataBase;
import GUI.Data.Enum.User.UserType;

public class UserTypeUtil {
    final private static int STUDENT_ACCOUNT_LENGTH = 10;
    final private static int TEACHER_ACCOUNT_LENGTH = 5;
    final private static int MANAGER_ACCOUNT_LENGTH = 1;

    public static int IDToAccount(String ID) {
        int length = ID.length();
        switch (length) {
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
}
