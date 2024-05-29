package Service.Data.Utils;

import java.security.SecureRandom;

public class PasswordUtil {
    final public static int MAX_NAME_LENGTH = 10;
    public static boolean check(String password){
        if(password==null||password.isEmpty())
            return false;
        if(!checkLength(password))
            return false;
        return checkChar(password);
    }
    public static String getRandomPassword(int length){
        String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        SecureRandom random = new SecureRandom();
        StringBuilder sb = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            sb.append(CHARACTERS.charAt(random.nextInt(CHARACTERS.length())));
        }
        return sb.toString();
    }
    public static boolean checkLength(String password) {
        return password.length() <= MAX_NAME_LENGTH;
    }

    public static boolean checkChar(String password) {
        return password.matches("^[a-zA-z0-9]+$");
    }

}
