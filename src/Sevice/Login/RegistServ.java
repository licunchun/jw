package Sevice.Login;

import Data.DataBase;
import GUI.Data.Enum.Error.Login.Regist;
import GUI.Data.Enum.School;
import GUI.Data.Enum.User.Gender;
import GUI.Data.Enum.User.Grade;
import GUI.Data.Enum.User.UserType;
import Sevice.Utils.NameUtil;
import Sevice.Utils.PasswordUtil;
import Sevice.Utils.UserTypeUtil;

public class RegistServ{
    public static Regist regist(UserType userType, String name, String password, String password_confirm)
    {
        NameUtil nameUtil = new NameUtil(name);
        //名字合法性检查
        switch (nameUtil.checkLength()){
            case NameUtil.EMPTY -> {
                return Regist.NameEmpty;
            }
            case NameUtil.INVALID_LENGTH -> {
                return Regist.NameOverLength;
            }
            case NameUtil.PASS -> {
                if(nameUtil.checkChar())
                    break;
                else
                    return Regist.NameInvalidChar;
            }
        }
        //密码合法性检查
        PasswordUtil passwordUtil = new PasswordUtil(password);
        switch (passwordUtil.checkLength()){
            case PasswordUtil.EMPTY -> {
                return Regist.PasswordEmpty;
            }
            case PasswordUtil.INVALID_LENGTH -> {
                return Regist.PasswordOverLength;
            }
            case PasswordUtil.PASS -> {
                if(passwordUtil.checkChar())
                    break;
                else
                    return Regist.PasswordInvalidChar;
            }
        }
        //重复输入密码判断
        if(password.compareTo(password_confirm)!=0)
            return Regist.PasswordNotMatch;
        return Regist.Pass;
    }
    public static String store(UserType userType, String name, String password, String password_confirm,Gender gender, School school, Grade grade){
        DataBase db = new DataBase();
        //转成数据库用户类型
        int type = UserTypeUtil.EnumToDataBase(userType);
        String ID = db.availableAccount(grade.toString());
        db.close();
        if(ID.isEmpty())
            throw new RuntimeException("RegistServ.java(line )");
        switch (userType) {
            case UserType.Student-> db.addStudent(name,ID,password,school.toString(),gender.toString(),grade.toString());
            case UserType.Teacher-> db.addTeacher(name,ID,password);
            case UserType.Admin-> db.addManager(name,ID,password);
            default -> throw new RuntimeException("RegistServ.java(line )");
        }
        db.close();
        return ID;
    }
}
