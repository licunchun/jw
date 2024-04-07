package GUI.Controller.Components.UserInformationEditor;

import GUI.Data.Enum.Error.Main.Components.UserServ.EditError;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import static Sevice.Main.Components.UserServ.UserServ.editPassword;

public class PasswordEditorController {
    @FXML
    private TextField ChangedPassword;
    @FXML
    private Label Tips;
    @FXML
    private Button Confirm;
    private Stage stage;
    private String ID;

    @FXML
    private void doConfirm() {
        switch (editPassword(ID, ChangedPassword.getText())) {
            case EditError.IDNotFound:
                Tips.setText("未找到ID，请重新登录！！");
                Tips.setVisible(true);
                break;
            case EditError.Invalid:
                Tips.setText("密码不合法，请重新输入");
                Tips.setVisible(true);
                break;
            case Success:
                stage.close();
        }
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public void setID(String ID) {
        this.ID = ID;
    }
}
