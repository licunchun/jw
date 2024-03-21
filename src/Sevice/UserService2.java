package Sevice;

public interface UserService2 {
    public void setID(String ID);
    public String getID();
    public String getPassword();
    public void setPassword(String password);
    public int login(String ID_login,String password_login);
}
