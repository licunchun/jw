package GUI.controller;

import MainPackage.Main;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class IDPageController {
    @FXML
    public Label IDShowLabel;
    @FXML
    public Button ConfirmButton;
    @FXML
    private  void initialize(){
        IDShowLabel.setText("您的ID是："+RegistController.getID());
    }
    @FXML
    public void doClick(){
        Main.changeViews("/GUI/window/login.fxml");
    }
}
