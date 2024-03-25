package GUI.Controller.Login;

import MainPackage.Main;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import static GUI.GUIUtil.StageUtil.changeViews;

public class IDPageController {
    @FXML
    public Label IDShowLabel;
    @FXML
    public Button ConfirmButton;

    public static final Stage stage=Main.getStage();
    @FXML
    private  void initialize(){
        IDShowLabel.setText("您的ID是："+RegistController.getID());
    }
    @FXML
    public void doClick(){
        changeViews(stage,"/GUI/Window/Login/login.fxml");
    }
}
