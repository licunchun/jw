package GUI.controller;

import Data.Enum.UserType;
import GUI.util.StringUtil;
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
    public Button ButtonRegist;
    @FXML
    public Button ButtonLogin;
    @FXML
    public Label IDEmptyTip;
    @FXML
    public Label PasswordEmptyTip;
    @FXML
    public Label LoginFail;

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
        UserService us=new UserService();

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
            System.out.println("Login successfully!");
            System.out.println("id:"+ID);
            System.out.println("password:"+password);
        }
        else{
            LoginFail.setVisible(true);
            return;
        }
    }
}
