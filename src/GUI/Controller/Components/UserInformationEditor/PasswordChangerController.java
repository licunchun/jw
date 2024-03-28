package GUI.Controller.Components.UserInformationEditor;

import GUI.Data.Enum.Error.Main.Components.UserServ.ChangePasswordError;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import static Sevice.Main.Components.UserServ.UserServ.changePassword;

public class PasswordChangerController {
    @FXML
    private Button Confirm;
    @FXML
    private Label tips;
    @FXML
    private TextField ConfirmNewPassword;
    @FXML
    private TextField NewPassword;
    @FXML
    private TextField OriginPassword;
    private String ID;
    private Stage stage;
    @FXML
    public void initialize(){

    }
    @FXML
    private void doConfirm(){
        switch (changePassword(ID,OriginPassword.getText(),NewPassword.getText(),ConfirmNewPassword.getText())){
            case ChangePasswordError.Success:
                tips.setText("");
                tips.setVisible(true);
                stage.close();
                break;
            case ChangePasswordError.IDNotFound:
                tips.setText("未找到ID，请重新登录！！");
                tips.setVisible(true);
                break;
            case ChangePasswordError.EmptyInput:
                tips.setText("输入不能为空，请重新输入");
                tips.setVisible(true);
                break;
            case ChangePasswordError.InvalidChar:
                tips.setText("新密码含非法字符，请重新输入");
                tips.setVisible(true);
                break;
            case ChangePasswordError.WrongOriginPassword:
                tips.setText("原始密码输入错误，请重新输入");
                tips.setVisible(true);
                break;
            case ChangePasswordError.NotMatch:
                tips.setText("两次输入的新密码不一致，请重新输入");
                tips.setVisible(true);
                break;
            case ChangePasswordError.OverLength:
                tips.setText("新密码过长，请重新输入");
                tips.setVisible(true);
                break;
        }
    }
    public void setID(String ID) {
        this.ID = ID;
    }
    public void setStage(Stage stage) {
        this.stage = stage;
    }
}
