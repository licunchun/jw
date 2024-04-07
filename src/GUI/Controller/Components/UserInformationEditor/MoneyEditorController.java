package GUI.Controller.Components.UserInformationEditor;

import GUI.Data.Enum.Error.Main.Components.UserServ.EditError;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import static Sevice.Main.Components.UserServ.UserServ.editMoney;

public class MoneyEditorController {
    @FXML
    public TextField ChangedMoney;
    @FXML
    public Label Tips;
    @FXML
    public Button Confirm;
    private Stage stage;
    private String ID;

    @FXML
    private void doConfirm() {
        double money;
        try {
            money = Double.parseDouble(ChangedMoney.getText());
        } catch (NumberFormatException e) {
            Tips.setText("金额不合法，请重新输入");
            Tips.setVisible(true);
            return;
        }
        switch (editMoney(ID, money)) {
            case EditError.IDNotFound:
                Tips.setText("未找到ID，请重新登录！！");
                Tips.setVisible(true);
                break;
            case EditError.Invalid:
                Tips.setText("金额不合法，请重新输入");
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
