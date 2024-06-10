package Service.Login;


import GUI.Data.Enum.Error.Login.Regist;
import GUI.Data.Enum.School;
import GUI.Data.Enum.User.Gender;
import GUI.Data.Enum.User.Grade;
import GUI.Data.Enum.User.UserType;
import Service.Data.Tables.Managers;
import Service.Data.Tables.Students;
import Service.Data.Tables.Teachers;
import Service.Data.Utils.*;

import java.util.Arrays;

public class RegisterServ {
    private static UserUtil userUtil;
    public static Regist regist(UserType userType, String name, String password, String password_confirm) {
        NameUtil nameUtil = new NameUtil(name);
        //名字为null或空
        if(nameUtil.nameEmpty)
            return Regist.NameEmpty;
        //名字字符不合法
        if (!nameUtil.nameCharValid)
            return Regist.NameInvalidChar;
        //名字超长
        if(nameUtil.nameOverLength)
            return Regist.NameOverLength;

        PasswordUtil passwordUtil = new PasswordUtil(password);
        //密码为null或空
        if(passwordUtil.passwordEmpty)
            return Regist.PasswordEmpty;
        //密码字符不合法
        if (!passwordUtil.passwordCharValid)
            return Regist.PasswordInvalidChar;
        //密码超长
        if (passwordUtil.passwordOverLength)
            return Regist.PasswordOverLength;


        //重复输入密码判断
        if (password.compareTo(password_confirm)!=0)
            return Regist.PasswordNotMatch;

        return Regist.Pass;
    }

    public static String store(UserType userType, String name, String password, String password_confirm, Gender gender, School school, Grade grade) {
        String ID;
        IDUtil idUtil = new IDUtil();
        switch (userType) {
            case UserType.Student -> {
                if(gender==null||school==null||grade==null)
                    throw new RuntimeException("RegistServ:gender/school/grade为null");
                ID = switch (grade){
                    case Grade1 -> idUtil.student23AvailableID;
                    case Grade2 -> idUtil.student22AvailableID;
                    case Grade3 -> idUtil.student21AvailableID;
                    case Grade4 -> idUtil.student20AvailableID;
                };
                String[] data = {ID,name,password, grade.toString(), gender.toString(),school.toString(),"0",DaysUtil.getEmptyDays()};
                Students.addInfo(data);
            }
            case UserType.Teacher -> {
                ID = idUtil.teacherAvailableID;
                String[] data = {ID,name,password,school.toString()};
                Teachers.addInfo(data);
            }
            case UserType.Admin -> {
                ID = idUtil.managerAvailableID;
                String[] data = {ID,name,password};
                Managers.addInfo(data);
            }
            default -> throw new RuntimeException("RegistServ.java(line )");
        }
        return ID;
    }
}
