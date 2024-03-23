package GUI.controller;

import Data.Enum.Error.Login;
import Data.Enum.User.UserType;
import GUI.util.StringUtil;
import MainPackage.Main;
import Sevice.UserService;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.security.KeyStore;


public class LoginController {
    /*
     * Major function
     */
    @FXML
    private Button ButtonRegist;
    @FXML
    private Button ButtonLogin;
    @FXML
    private Label Tips;
    @FXML
    private TextField UserID;
    @FXML
    private PasswordField UserPassword;

    @FXML
    public void initialize(){}

    @FXML
    public void doLogin(){
        switch(new UserService().checkIDAndPassword(UserID.getText(),UserPassword.getText())){
            case Login.IDEmpty:
                Tips.setText("ID不能为空");
                Tips.setVisible(true);
                return;
            case Login.PasswordEmpty:
                Tips.setText("密码不能为空");
                Tips.setVisible(true);
                return;
            case Login.NotPass:
                Tips.setText("ID密码不匹配");
                Tips.setVisible(true);
                return;
        }
    }

    @FXML
    public void doRegist(){
        Main.changeViews("/GUI/window/regist.fxml");
    }
}
