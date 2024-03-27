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
        Regist r;
        //名字合法性检查
        r = NameUtil.checkValid(name);
        if(r != Regist.Pass)
            return r;
        //密码合法性检查
        r = PasswordUtil.checkValid(password);
        if(r != Regist.Pass)
            return r;
        //重复输入密码判断
        if(password.compareTo(password_confirm)!=0)
            return Regist.PasswordNotMatch;
        return Regist.Pass;
    }
    public static String store(UserType userType, String name, String password, String password_confirm,Gender gender, School school, Grade grade){
        DataBase db = new DataBase();
        //转成数据库用户类型
        int type = UserTypeUtil.EnumToDataBase(userType);
        //String ID = db.getAvailableAccount(type);
        String ID = new String();
        if(ID.isEmpty())
            throw new RuntimeException("RegistServ.java(line )");
        switch (userType) {
            case UserType.Student->{
                //db.addStudent(name,ID,password,school.toString(),gender.toString(),grade.toString());
            }
            case UserType.Teacher->{
                db.addTeacher(name,ID,password,school.toString());
            }
            case UserType.Admin->{
                db.addManager(name,ID,password);
            }
            default -> {
                throw new RuntimeException("RegistServ.java(line )");
            }
        }
        //db.close();
        return ID;
    }
}
