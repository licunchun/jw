package Sevice.Utils;

import Data.Enum.Error.Login.Regist;

public class NameUtil extends StrUtil {
    public static boolean charCheck(String str) {
        return str.matches("^[\u4e00-\u9fa5]+$");
    }
    public static Regist checkValid(String str){
        if (str.isEmpty())
            return Regist.NameEmpty;
        else if (!lengthCheck(str))
            return Regist.NameOverLength;
        else if(!charCheck(str))
            return Regist.NameInvalidChar;
        else
            return Regist.Pass;
    }
}
