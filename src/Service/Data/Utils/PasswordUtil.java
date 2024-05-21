package Service.Data.Utils;

public class PasswordUtil {
    final public static int MAX_NAME_LENGTH = 10;
    public static boolean check(String password){
        if(password==null||password.isEmpty())
            return false;
        if(!checkLength(password))
            return false;
        if(!checkChar(password))
            return false;
        return true;
    }
    public static boolean checkLength(String password) {
        return password.length() <= MAX_NAME_LENGTH;
    }

    public static boolean checkChar(String password) {
        return password.matches("^[a-zA-z0-9]+$");
    }

}
