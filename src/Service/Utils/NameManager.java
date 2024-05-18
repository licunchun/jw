package Service.Utils;

public class NameManager {
    final public static int MAX_NAME_LENGTH = 10;
    public static boolean check(String name){
        if(name==null||name.isEmpty())
            return false;
        if(!checkLength(name))
            return false;
        if(!checkChar(name))
            return false;
        return true;
    }
    public static boolean checkLength(String name) {
        return name.length() <= MAX_NAME_LENGTH;
    }
    public static boolean checkChar(String name) {
        return name.matches("^[一-龥·]+$");
    }
}
