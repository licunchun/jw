package Utils;

import Data.Enum.StringState;

public abstract class StrUtil {
    public static boolean lengthCheck(String str){
        int MAX_LENGTH = 10;
        int length = str.length();
        if(length<=MAX_LENGTH&&length>0)
            return true;
        else
            return false;
    }
    public static boolean lengthCheck(String str,int max_length){
        int length = str.length();
        if(length<=max_length && length>0)
            return true;
        else
            return false;
    }


}
