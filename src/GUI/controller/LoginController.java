package GUI.controller;

import Data.Enum.User.UserType;
import GUI.util.StringUtil;
import MainPackage.Main;
import Sevice.UserService;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;


public class LoginController {
    /*
     * Major function
     */
    @FXML
    private Button ButtonRegist;
    @FXML
    private Button ButtonLogin;
    @FXML
    private Label IDEmptyTip;
    @FXML
    private Label PasswordEmptyTip;
    @FXML
    private Label LoginFail;
    @FXML
    private TextField UserID;
    @FXML
    private PasswordField UserPassword;

    @FXML
    public void initialize(){}

    @FXML
    public void doLogin(){
        String ID=UserID.getText();
        String password=UserPassword.getText();

        /*
         * Empty Tips
         */
        if (StringUtil.isEmpty(ID)){
            IDEmptyTip.setVisible(true);
            return;
        }
        else{
            IDEmptyTip.setVisible(false);
        }

        if (StringUtil.isEmpty(password)){
            PasswordEmptyTip.setVisible(true);
            return;
        }
        else{
            PasswordEmptyTip.setVisible(false);
        }

        /*
         * Login
         */

        if (new UserService(UserType.Admin,"",ID,password).checkIDAndPassword()){
            //Main.changeViews("/GUI/window/regist.fxml");
            System.out.println("Login successfully!");
            System.out.println("id:"+ID);
            System.out.println("password:"+password);
        }
        else{
            LoginFail.setVisible(true);
            return;
        }
    }

    @FXML
    public void doRegist(){
        Main.changeViews("/GUI/window/regist.fxml");
    }
}
