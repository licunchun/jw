package Interface;

public interface IUserSevice {
    void Regist();
    boolean isNameValid();
    boolean isPasswordValid();
    boolean checkIDAndPassword();
    boolean exit();
}
