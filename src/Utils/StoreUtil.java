package Utils;

import Data.Enum.Gender;
import Data.Enum.Grade;
import Data.Enum.School;
import Data.Enum.UserType;

public class StoreUtil {
    public static boolean storeStudent(UserType userType, String name, String ID, String password, Grade grade, School school, Gender gender){
        return true;
    };
    public static boolean storeTeacher(UserType userType, String name, String ID, String password, School school, Gender gender){
        return true;
    };
    public static boolean storeAdmin(UserType userType, String name, String ID, String password){
        return true;
    };
}
