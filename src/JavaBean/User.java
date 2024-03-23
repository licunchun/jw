package JavaBean;

import Data.Enum.Error.Regist;
import Data.Enum.User.UserType;
import Utils.User.NameUtil;
import Utils.User.PasswordUtil;

public class User {
    private UserType userType;
    private String name;
    private String ID;
    private String password;
    private int index;

    public UserType getUserType() {
        return userType;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }


}
