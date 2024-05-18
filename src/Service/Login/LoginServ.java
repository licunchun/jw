package Service.Login;

import Service.Data.DataBase;
import GUI.Data.Enum.Error.Login.Login;
import Service.Utils.IDManager;

public class LoginServ {
    public static Login checkIDAndPassword(String ID, String password) {
        //判断ID为空和null
        if (ID==null||ID.isEmpty())
            return Login.IDEmpty;
        //判断ID是否存在
        if(!IDManager.isIDExist(ID))
            return Login.NotPass;

        //判断password为空和null
        if (password==null||password.isEmpty())
            return Login.PasswordEmpty;

        //判断ID密码是否匹配
        String password_get = DataBase.getUserPassword(ID);
        if(password.compareTo(password_get) != 0)
            return Login.NotPass;

        //匹配成功

        //判断用户类型
        int type = IDManager.getUserType(ID);

        return switch (type) {
                case DataBase.STUDENT -> Login.Student;
                case DataBase.TEACHER -> Login.Teacher;
                case DataBase.MANAGER -> Login.Admin;
                default -> throw new RuntimeException("UserService.java(line ) StoreUser have no userType");
            };
    }
}
