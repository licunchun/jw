package Sevice.Main.Component;

import Data.DataBase;
import Data.Enum.Error.UserServ.ChangePasswordError;
import Data.Enum.Error.UserServ.EditError;
import Data.Enum.School;
import Data.Enum.User.Grade;

public class UserServ {
    public static EditError EditName(String ID,String name){
        return EditError.Success;
    }
    public static EditError EditPassword(String ID,String password){
        return EditError.Success;
    }
    public static EditError EditMoney(String ID,double money){
        return EditError.Success;
    }
    public static EditError EditAddMoney(String ID,String addMoney){
        return EditError.Success;
    }
    public static EditError EditGrade(String ID, Grade grade){
        return EditError.Success;
    }
    public static EditError EditSchool(String ID, School school){
        return EditError.Success;
    }
    public static ChangePasswordError ChangePassword(String ID, String originPassword, String newPassword, String newConfirmPassword){
        return ChangePasswordError.Success;
    }
}
