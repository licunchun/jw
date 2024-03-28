package Sevice.Login;

import Data.DataBase;
import GUI.Data.Enum.Error.Login.Login;
import Sevice.Utils.UserTypeUtil;

public class LoginServ {
    public static Login checkIDAndPassword(String ID, String password){
        //判断为空
        if(ID.isEmpty())
            return Login.IDEmpty;
        if(password.isEmpty())
            return Login.PasswordEmpty;
        //判断用户类型
        int type = UserTypeUtil.IDToAccount(ID);
        if(type==0)
            return Login.NotPass;
        //从数据库获得用户密码
        DataBase db = new DataBase();
        String key = db.key(ID,type);
        db.close();
        if(key.isEmpty())
            return Login.NotPass;
        //密码判断
        if(password.compareTo(key)==0)
        {
            switch (type){
                case DataBase.STUDENT -> {
                    return Login.Student;
                }
                case DataBase.TEACHER -> {
                    return Login.Teacher;
                }
                case DataBase.MANAGER -> {
                    return Login.Admin;
                }
                default -> {
                    throw new RuntimeException("UserService.java(line ) StoreUser have no userType");
                }
            }
        }
        else
            return Login.NotPass;
    }
}
