package Interface;

import Data.Enum.Gender;
import Data.Enum.Grade;
import Data.Enum.School;
import Data.Enum.UserType;

public interface IStoreData {
    boolean storeUser(UserType userType, String name, String ID, String password, Grade grade, School school, Gender gender);
    boolean storeUser(UserType userType, String name, String ID, String password, School school, Gender gender);
    boolean storeUser(UserType userType, String name, String ID, String password);
}
