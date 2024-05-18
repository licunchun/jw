package Service.Login;

import Service.Data.DataBase;
import GUI.Data.Enum.Error.Login.Regist;
import GUI.Data.Enum.School;
import GUI.Data.Enum.User.Gender;
import GUI.Data.Enum.User.Grade;
import GUI.Data.Enum.User.UserType;
import Service.Utils.*;

public class RegistServ {
    public static Regist regist(UserType userType, String name, String password, String password_confirm) {
        //名字为null或空
        if(name==null||name.isEmpty())
            return Regist.NameEmpty;
        //名字超长
        if(!NameManager.checkLength(name))
            return Regist.NameOverLength;
        //名字字符不合法
        if (!NameManager.checkChar(name))
            return Regist.NameInvalidChar;

        //密码为null或空
        if(password==null||password.isEmpty())
            return Regist.PasswordEmpty;
        //密码超长
        if (!PasswordManager.checkLength(password))
            return Regist.PasswordOverLength;
        //密码字符不合法
        if (!PasswordManager.checkChar(password))
            return Regist.PasswordInvalidChar;

        //重复输入密码判断
        if (password.compareTo(password_confirm)!=0)
            return Regist.PasswordNotMatch;

        return Regist.Pass;
    }

    public static String store(UserType userType, String name, String password, String password_confirm, Gender gender, School school, Grade grade) {
        if(userType==null)
            throw new RuntimeException("RegistServ:userType为null");
        //转成数据库用户类型
        int type = UserTypeTransformer.fromUserType(userType);
        String ID;
        if(type==DataBase.STUDENT) {
            if(gender==null||school==null||grade==null)
                throw new RuntimeException("RegistServ:gender/school/grade为null");
            ID = IDManager.getavailableID(grade.toString());
        } else
            ID = IDManager.getavailableID(type);
        String[] dataS = {ID,name,password, grade.toString(), gender.toString(),school.toString(),"","0"};
        String[] dataT = {ID,name,password,""};
        String[] dataM = {ID,name,password};

        switch (userType) {
            case UserType.Student -> DataBase.insertTable(DataBase.STUDENT,dataS);
            case UserType.Teacher -> DataBase.insertTable(DataBase.TEACHER,dataT);
            case UserType.Admin -> DataBase.insertTable(DataBase.MANAGER,dataM);
            default -> throw new RuntimeException("RegistServ.java(line )");
        }
        return ID;
    }
}
