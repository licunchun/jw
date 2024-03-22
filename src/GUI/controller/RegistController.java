package GUI.controller;

import Data.Enum.UserType;
import GUI.util.StringUtil;
import Sevice.UserService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.util.StringConverter;

public class RegistController {
    /*
     * Major subassembly
     */
    @FXML
    private Button Confirm;
    @FXML
    private TextField UserName;
    @FXML
    private PasswordField Password;
    @FXML
    private PasswordField ConfirmPassword;
    @FXML
    private ChoiceBox<UserType> AccountType;
    @FXML
    private Label Tips;
    /*
     * ChoiceBlock properties
     */
    private final ObservableList<UserType> UserTypeList=
            FXCollections.observableArrayList();
    /*
     * Major function
     */
    @FXML
    public void initialize(){
        /*
         * ChoiceBlock init
         */
        UserTypeList.add(UserType.Student);
        UserTypeList.add(UserType.Teacher);
        UserTypeList.add(UserType.Admin);

        AccountType.setValue(UserType.Student);

        AccountType.setConverter(new StringConverter<UserType>() {
            @Override
            public String toString(UserType userType) {
                return userType.toString();
            }

            @Override
            public UserType fromString(String s) {
                return null;
            }
        });

        AccountType.setItems(UserTypeList);
    }

    @FXML
    public void doConfirm(){
        String name=UserName.getText();
        String password=Password.getText();
        String confirmPassword=ConfirmPassword.getText();
        /*
         * Empty Tips
         */
        if(StringUtil.isEmpty(name)){
            Tips.setText("用户名不能为空!");
            Tips.setVisible(true);
        }
        if(StringUtil.isEmpty(password)){
            Tips.setText("密码不能为空!");
            Tips.setVisible(true);
        }
        if(StringUtil.isEmpty(confirmPassword)){
            Tips.setText("确认密码不能为空!");
            Tips.setVisible(true);
        }
        if(!password.equals(confirmPassword)){
            Tips.setText("两次输入的密码不一致!");
        }

    }

    /*
     * Function for ChoiceBlock
     */

}
