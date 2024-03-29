package Sevice.Utils;

import GUI.Data.Enum.Error.Login.Regist;

public class PasswordUtil extends StrUtil {

    public static boolean charCheck(String str) {
        int num = str.length();
        for (int i = 0; i < num; i++) {
            char ch = str.charAt(i);
            if(ch>='a'&&ch<='z'||ch>='A'&&ch<='Z'||ch>='0'&&ch<='9')
                continue;
            else
                return false;
        }
        return true;
    }
    public static Regist checkValid(String str) {
        if (str.isEmpty())
            return Regist.PasswordEmpty;
        else if (!lengthCheck(str))
            return Regist.PasswordOverLength;
        else if (!charCheck(str))
            return Regist.PasswordInvalidChar;
        else
            return Regist.Pass;
    }
}
