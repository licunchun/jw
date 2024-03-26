package GUI.Controller.Login;

import Data.Enum.Error.Login;
import Data.Enum.User.UserType;
import MainPackage.Main;
import Sevice.UserService;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import static GUI.Controller.Main.Util.MainPage.openMainPage;
import static GUI.GUIUtil.StageUtil.changeViews;


public class LoginController {
    private static final Stage stage=Main.getStage();
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
            case Login.Student:
                openMainPage(stage,UserType.Student,UserID.getText());
                return;
            case Login.Teacher:
                openMainPage(stage,UserType.Teacher,UserID.getText());
                return;
            case Login.Admin:
                openMainPage(stage,UserType.Admin,UserID.getText());
                return;
        }
    }

    @FXML
    public void doRegist(){
        changeViews(stage,"/GUI/Window/Login/regist.fxml");
    }

    }
