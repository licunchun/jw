package Sevice.Utils;

import Data.Enum.User.Gender;
import Data.Enum.User.Grade;
import Data.Enum.School;
import Data.Enum.User.UserType;

public class StoreUtil {
    public static boolean storeStudent(UserType userType, String name, String ID, String password, School school, Gender gender,Grade grade){
        return true;
    };
    public static boolean storeTeacher(UserType userType, String name, String ID, String password, School school, Gender gender){
        return true;
    };
    public static boolean storeAdmin(UserType userType, String name, String ID, String password){
        return true;
    };
}
