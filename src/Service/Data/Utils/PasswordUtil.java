package Service.Data.Utils;

import Service.Data.Tables.Managers;

import java.security.SecureRandom;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PasswordUtil {
    private String password;
    public boolean passwordEmpty;
    public boolean passwordCharValid;
    public boolean passwordOverLength;
    public static final int MAX_PASSWORD_LENGTH = 10;
    public static final Pattern charPattern = Pattern.compile("^[A-Za-z0-9]+$");
    public static final Pattern pattern = Pattern.compile("^[A-Za-z0-9]{1,10}$");
    private Matcher matcher;

    public PasswordUtil(String password) {
        this.password = password;
        passwordEmpty = isPasswordEmpty();
        passwordCharValid = isPasswordCharValid();
        passwordOverLength = isPasswordOverLength();
    }

    public String getPassword() {
        return password;
    }
    private boolean isPasswordEmpty(){
        if(password==null||password.isEmpty())
            return true;
        return false;
    }
    private boolean isPasswordCharValid(){
        matcher = charPattern.matcher(password);
        if(matcher.matches())
            return true;
        return false;
    }
    private boolean isPasswordOverLength(){
        matcher = pattern.matcher(password);
        if(matcher.matches())
            return false;
        return true;
    }

//    public static boolean check(String password){
//        if(password==null||password.isEmpty())
//            return false;
//        if(!checkLength(password))
//            return false;
//        return checkChar(password);
//    }
    public static String getRandomPassword(int length){
        String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        SecureRandom random = new SecureRandom();
        StringBuilder sb = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            sb.append(CHARACTERS.charAt(random.nextInt(CHARACTERS.length())));
        }
        return sb.toString();
    }
//    public static boolean checkLength(String password) {
//        return password.length() <= MAX_NAME_LENGTH;
//    }
//
//    public static boolean checkChar(String password) {
//        return password.matches("^[a-zA-z0-9]+$");
//    }

}
