package GUI.Controller.Components.UserInformationEditor;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

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
    @FXML
    public void initialize(){

    }
    @FXML
    private void doConfirm(){}
    public void setID(String ID) {
        this.ID = ID;
    }
}
