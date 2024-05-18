package Service.Utils;

public class NameUtil {
    final public static int MAX_NAME_LENGTH = 10;
    final public static int EMPTY = 0;
    final public static int PASS = 1;
    final public static int INVALID_LENGTH = 2;
    String name;

    public NameUtil(String name) {
        this.name = name;
    }

    public int checkLength() {
        if (this.name.isEmpty())
            return EMPTY;
        else if (this.name.length() > MAX_NAME_LENGTH)//
            return INVALID_LENGTH;
        else
            return PASS;
    }

    public boolean checkChar() {
        return this.name.matches("^[一-龥·]+$");
    }

    public boolean checkValid() {
        if (checkLength() != PASS)
            return false;
        return checkChar();
    }

}
