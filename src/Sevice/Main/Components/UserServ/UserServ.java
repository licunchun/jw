package Sevice.Main.Components.UserServ;

import Data.DataBase;
import Data.Type.Student;
import GUI.Data.DataPackage.Classes.IDSet;
import GUI.Data.Enum.Error.Main.Components.UserServ.ChangePasswordError;
import GUI.Data.Enum.Error.Main.Components.UserServ.EditError;
import GUI.Data.Enum.School;
import GUI.Data.Enum.User.Gender;
import GUI.Data.Enum.User.Grade;
import GUI.Data.Enum.User.UserType;
import Sevice.Utils.IDUtil;
import Sevice.Utils.NameUtil;
import Sevice.Utils.PasswordUtil;
import Sevice.Utils.UserTypeUtil;

public class UserServ {
    /*
     * Editor
     */
    private static EditError checkID(String ID){
        IDUtil idUtil = new IDUtil(ID);
        if(!idUtil.checkValid())
            return EditError.IDNotFound;

        DataBase db = new DataBase();
        String key = db.key(ID, UserTypeUtil.IDToAccount(ID));
        db.close();

        if(key.isEmpty())
            return EditError.IDNotFound;

        return EditError.Success;
    }
    public static EditError editName(String ID,String name){
        NameUtil nameUtil = new NameUtil(name);
        if(!nameUtil.checkValid())
            return EditError.Invalid;

        if(checkID(ID)!=EditError.Success)
            return checkID(ID);

        DataBase db = new DataBase();
        //db.changeName(idUtil.getID(),idUtil.getType(),name);
        db.close();

        return EditError.Success;
    }
    public static EditError editPassword(String ID,String password){
        PasswordUtil passwordUtil = new PasswordUtil(password);
        if(!passwordUtil.checkValid())
            return EditError.Invalid;

        if(checkID(ID)!=EditError.Success)
            return checkID(ID);

        DataBase db = new DataBase();
        db.changeKeyOfStudent(ID,password);
        db.setKey(ID,password,UserTypeUtil.IDToAccount(ID));
        db.close();

        return EditError.Success;
    }
    public static EditError editMoney(String ID,double money){
        if(checkID(ID)!=EditError.Success)
            return checkID(ID);
        DataBase db = new DataBase();
        //db.setMoney(ID,money);
        db.close();
        return EditError.Success;
    }
    public static EditError editAddMoney(String ID,double addMoney){
        if(checkID(ID)!=EditError.Success)
            return checkID(ID);
        DataBase db = new DataBase();
        //money = db.getMoney(ID);
        //db.setMoney(ID,money);
        db.close();
        return EditError.Success;
    }
    public static EditError editGrade(String ID, Grade grade){
        if(checkID(ID)!=EditError.Success)
            return checkID(ID);
        DataBase db = new DataBase();
        //db.changeGrade();
        db.close();
        return EditError.Success;
    }
    public static EditError editSchool(String ID, School school){
        if(checkID(ID)!=EditError.Success)
            return checkID(ID);
        DataBase db = new DataBase();
        //db.changeSchool();
        db.close();
        return EditError.Success;
    }
    /*
     * Getter
     */
    public static UserType getUserType(String ID){
        return UserTypeUtil.IDToEnum(ID);
    }
    public static String getName(String ID){
        DataBase db = new DataBase();
        Student s = db.infoOfStudent(ID);
        return s.name;
    }
    public static Gender getGender(String ID){
        DataBase db = new DataBase();
        Student s = db.infoOfStudent(ID);
//        switch (s.gender){
//            case
//        }
        return Gender.Male;
    }
    public static School getSchool(String ID){

        return School.GiftedYoung;
    }
    public static Grade getGrade(String ID){return Grade.Grade1;}//TODO
    public static Double getMoney(String ID){
        return (double) 0;
    }//TODO
    /*
     * Else
     */
    public static ChangePasswordError changePassword(String ID, String originPassword, String newPassword, String newConfirmPassword){
        return ChangePasswordError.Success;
    }//TODO
    public static IDSet findStudent(String name){

        return new IDSet();
    }//TODO
    public static IDSet findTeacher(String teacher){
        return new IDSet();
    }//TODO

}
