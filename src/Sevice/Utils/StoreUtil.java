package Sevice.Utils;

import GUI.Data.Enum.User.Gender;
import GUI.Data.Enum.User.Grade;
import GUI.Data.Enum.School;
import GUI.Data.Enum.User.UserType;

public class StoreUtil {
    public static boolean storeStudent(UserType userType, String name, String ID, String password, School school, Gender gender, Grade grade) {
        return true;
    }

    public static boolean storeTeacher(UserType userType, String name, String ID, String password, School school, Gender gender) {
        return true;
    }

    public static boolean storeAdmin(UserType userType, String name, String ID, String password) {
        return true;
    }
}
