package Sevice.Main.Components.UserServ;

import GUI.Data.Enum.Error.Main.Components.UserServ.ChangePasswordError;
import GUI.Data.Enum.Error.Main.Components.UserServ.EditError;
import GUI.Data.Enum.School;
import GUI.Data.Enum.User.Grade;

public class UserServ {
    public static EditError editName(String ID,String name){
        return EditError.Success;
    }
    public static EditError editPassword(String ID,String password){
        return EditError.Success;
    }
    public static EditError editMoney(String ID,double money){
        return EditError.Success;
    }
    public static EditError editAddMoney(String ID,String addMoney){
        return EditError.Success;
    }
    public static EditError editGrade(String ID, Grade grade){
        return EditError.Success;
    }
    public static EditError editSchool(String ID, School school){
        return EditError.Success;
    }
    public static ChangePasswordError changePassword(String ID, String originPassword, String newPassword, String newConfirmPassword){
        return ChangePasswordError.Success;
    }
}
