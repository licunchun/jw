package GUI.Controller.Components.UserInformationEditor;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class NameEditorController {
    @FXML
    private TextField ChangedName;
    @FXML
    private Button Confirm;
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
