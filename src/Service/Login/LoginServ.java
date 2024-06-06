package Service.Login;

import GUI.Data.Enum.Error.Login.Login;
import Service.Data.Utils.PasswordUtil;
import Service.Data.Utils.UserUtil;
import Service.Data.Utils.IDUtil;

public class LoginServ {
    public static Login checkIDAndPassword(String ID, String password) {
        IDUtil idUtil = new IDUtil(ID);
        //判断ID为空和null
        if (idUtil.IDEmpty)
            return Login.IDEmpty;
        //判断ID是否存在
        if(!idUtil.IDExist)
            return Login.NotPass;

        PasswordUtil passwordUtil = new PasswordUtil(password);
        //判断password为空和null
        if (passwordUtil.passwordEmpty)
            return Login.PasswordEmpty;

        //判断ID密码是否匹配
        UserUtil user = new UserUtil(ID);
        if(password.compareTo(user.password) != 0)
            return Login.NotPass;

        //匹配成功
        //根据用户类型返回
        return switch (user.userType) {
                case UserUtil.STUDENT -> Login.Student;
                case UserUtil.TEACHER -> Login.Teacher;
                case UserUtil.MANAGER -> Login.Admin;
                default -> throw new RuntimeException("UserService.java(line ) StoreUser have no userType");
            };
    }
}
