package GUI.Controller.Components.UserInformationEditor;

import GUI.Data.Enum.Error.Main.Components.UserServ.EditError;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import static Service.Main.Components.UserServ.UserServ.editName;

public class NameEditorController {
    @FXML
    private Label tips;
    @FXML
    private TextField ChangedName;
    @FXML
    private Button Confirm;
    private String ID;
    private Stage stage;

    @FXML
    private void doConfirm() {
        System.out.println(ID);
        System.out.println(ChangedName.getText());
        switch (editName(ID, ChangedName.getText())) {
            case EditError.IDNotFound:
                tips.setText("未找到ID，请重新登录！！");
                tips.setVisible(true);
                break;
            case EditError.Invalid:
                tips.setText("用户名不合法，请重新输入");
                tips.setVisible(true);
                break;
            case EditError.Success:
                stage.close();
        }
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
        stage.setTitle("修改姓名");
    }
}
