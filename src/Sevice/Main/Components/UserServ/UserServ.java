package Sevice.Main.Components.UserServ;

import GUI.Data.Enum.Error.Main.Components.UserServ.ChangePasswordError;
import GUI.Data.Enum.Error.Main.Components.UserServ.EditError;
import GUI.Data.Enum.School;
import GUI.Data.Enum.User.Gender;
import GUI.Data.Enum.User.Grade;
import GUI.Data.Enum.User.UserType;

public class UserServ {
    /*
     * Editor
     */
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
    /*
     * Getter
     */
    public static UserType getUserType(String ID){
        return UserType.None;
    }
    public static String getName(String ID){
        return "";
    }
    public static Gender getGender(String ID){
        return Gender.Male;
    }
    public static School getSchool(String ID){return School.GiftedYoung;}
    public static Grade getGrade(String ID){return Grade.Grade1;}
    public static Double getMoney(String ID){
        return (double) 0;
    }
    /*
     * Else
     */
    public static ChangePasswordError changePassword(String ID, String originPassword, String newPassword, String newConfirmPassword){
        return ChangePasswordError.Success;
    }

}
