package Interface;

import Data.Enum.User.Gender;
import Data.Enum.User.Grade;
import Data.Enum.School;
import Data.Enum.User.UserType;

public interface IStoreData {
    boolean storeUser(UserType userType, String name, String ID, String password, Grade grade, School school, Gender gender);
    boolean storeUser(UserType userType, String name, String ID, String password, School school, Gender gender);
    boolean storeUser(UserType userType, String name, String ID, String password);
}
