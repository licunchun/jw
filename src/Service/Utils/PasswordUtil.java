package Service.Utils;

public class PasswordUtil {
    final public static int MAX_NAME_LENGTH = 10;
    final public static int EMPTY = 0;
    final public static int PASS = 1;
    final public static int INVALID_LENGTH = 2;
    private final String password;

    public PasswordUtil(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public int checkLength() {
        if (this.password.isEmpty())
            return EMPTY;
        else if (this.password.length() > MAX_NAME_LENGTH)//
            return INVALID_LENGTH;
        else
            return PASS;
    }

    public boolean checkChar() {
        return this.password.matches("^[a-zA-z0-9]+$");
    }

    public boolean checkValid() {
        if (checkLength() != PASS)
            return false;
        return checkChar();
    }
}
