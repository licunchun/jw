package Service.Login;


import GUI.Data.Enum.Error.Login.Regist;
import GUI.Data.Enum.School;
import GUI.Data.Enum.User.Gender;
import GUI.Data.Enum.User.Grade;
import GUI.Data.Enum.User.UserType;
import Service.Data.Tables.Managers;
import Service.Data.Tables.Students;
import Service.Data.Tables.Teachers;
import Service.Data.Tables.User;
import Service.Data.Utils.*;

public class RegistServ {
    public static Regist regist(UserType userType, String name, String password, String password_confirm) {

        //名字为null或空
        if(name==null||name.isEmpty())
            return Regist.NameEmpty;
        //名字超长
        if(!NameUtil.checkLength(name))
            return Regist.NameOverLength;
        //名字字符不合法
        if (!NameUtil.checkChar(name))
            return Regist.NameInvalidChar;
        if(userType==UserType.Teacher&& Teachers.isNameExist(name))
            throw new IllegalArgumentException("同名老师");
        //密码为null或空
        if(password==null||password.isEmpty())
            return Regist.PasswordEmpty;
        //密码超长
        if (!PasswordUtil.checkLength(password))
            return Regist.PasswordOverLength;
        //密码字符不合法
        if (!PasswordUtil.checkChar(password))
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
        if(type== User.STUDENT) {
            if(gender==null||school==null||grade==null)
                throw new RuntimeException("RegistServ:gender/school/grade为null");
            ID = IDUtil.getavailableID(grade.toString());
        } else
            ID = IDUtil.getavailableID(type);
        String[] dataS = {ID,name,password, grade.toString(), gender.toString(),school.toString(),"","0"};
        String[] dataT = {ID,name,password,""};
        String[] dataM = {ID,name,password};

        switch (userType) {
            case UserType.Student -> Students.addInfo(dataS);
            case UserType.Teacher -> Teachers.addInfo(dataT);
            case UserType.Admin -> Managers.addInfo(dataM);
            default -> throw new RuntimeException("RegistServ.java(line )");
        }
        return ID;
    }
}
