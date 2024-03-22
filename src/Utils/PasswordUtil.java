package Utils;

import Data.Enum.StringState;

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
