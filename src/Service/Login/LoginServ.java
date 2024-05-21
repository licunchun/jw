package Service.Login;

import GUI.Data.Enum.Error.Login.Login;
import Service.Data.Tables.User;
import Service.Data.Utils.IDUtil;

public class LoginServ {
    public static Login checkIDAndPassword(String ID, String password) {
        //判断ID为空和null
        if (ID == null || ID.isEmpty())
            return Login.IDEmpty;
        //判断ID是否存在
        if (!IDUtil.isIDExist(ID))
            return Login.NotPass;

        //判断password为空和null
        if (password == null || password.isEmpty())
            return Login.PasswordEmpty;

        //判断ID密码是否匹配
        String password_get = User.getPassword(ID);
        if (password.compareTo(password_get) != 0)
            return Login.NotPass;

        //匹配成功

        //判断用户类型
        int type = IDUtil.getUserType(ID);

        return switch (type) {
            case User.STUDENT -> Login.Student;
            case User.TEACHER -> Login.Teacher;
            case User.MANAGER -> Login.Admin;
            default -> throw new RuntimeException("UserService.java(line ) StoreUser have no userType");
        };
    }
}
