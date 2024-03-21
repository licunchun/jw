package Sevice;

import Data.Enum.UserType;

public class User {
    private UserType userType;
    private String name;
    private String ID;
    private String password;

    public User() {
    }

    public User(UserType userType, String name, String ID, String password) {
        this.userType = userType;
        this.name = name;
        this.ID = ID;
        this.password = password;
    }

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
}
