package Utils.User;

import Data.Enum.Error.Regist;
import Data.Enum.Tools.StringState;

public class NameUtil extends StrUtil {

    public static boolean charCheck(String str) {

        return true;
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
