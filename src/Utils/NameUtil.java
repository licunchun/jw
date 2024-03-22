package Utils;

import Data.Enum.Tools.StringState;

public class NameUtil extends StrUtil {

    public static boolean charCheck(String str) {
        return true;
    }
    public static StringState checkValid(String str){
        if (str.isEmpty())
            return StringState.EMPTY;
        else if (!lengthCheck(str))
            return StringState.OVERLENGTH;
        else if(!charCheck(str))
            return StringState.INVALIDCHAR;
        else
            return StringState.RIGHT;
    }
}
