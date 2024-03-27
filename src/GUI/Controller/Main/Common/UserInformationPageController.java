package GUI.Controller.Main.Common;

import GUI.Controller.Components.UserInformationEditor.NameEditorController;
import GUI.Controller.Components.UserInformationEditor.PasswordChangerController;
import GUI.Data.Enum.User.UserType;
import javafx.fxml.FXML;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import static GUI.GUIUtil.StageUtil.*;

public class UserInformationPageController {
    @FXML
    private Label ShowName;
    @FXML
    private Label ShowID;
    @FXML
    private AnchorPane chooseAnchorPane;
    @FXML
    private VBox UserInformationBox;
    @FXML
    private Hyperlink EditPassword;
    @FXML
    private Hyperlink EditName;
    private Stage stage;
    private String ID;
    /*
     * Children Page
     */

    //Edit name page
    private boolean isEditNamePageShow=false;
    private Stage editNamePageStage=null;
    private NameEditorController nameEditorController;

    //Edit password Page
    private boolean isChangePasswordPageShow=false;
    private Stage changePasswordPageStage=null;
    private PasswordChangerController passwordChangerController;
    /*
     * Function
     */

    @FXML
    public void initialize(){

    }
    @FXML
    private void doEditPassword(){
        if(!isChangePasswordPageShow){
            isChangePasswordPageShow=true;
            changePasswordPageStage=new Stage();

            passwordChangerController=changeViews(changePasswordPageStage, "/GUI/Window/Components/UserInformationEditor/PasswordChanger.fxml");
            passwordChangerController.setID(ID);
            changePasswordPageStage.setOnCloseRequest(e->{
                isChangePasswordPageShow=false;
                changePasswordPageStage.close();
            });
            changePasswordPageStage.show();
            changePasswordPageStage.setResizable(false);
            resetLocation(changePasswordPageStage);
        }
        else{
            resetLocation(changePasswordPageStage);
        }
    }
    @FXML
    private void doEditName(){
        if(!isEditNamePageShow){
            isEditNamePageShow=true;
            editNamePageStage=new Stage();

            nameEditorController=changeViews(editNamePageStage,"/GUI/Window/Components/UserInformationEditor/NameEditor.fxml");
            nameEditorController.setID(ID);
            editNamePageStage.setOnCloseRequest(e->{
                isEditNamePageShow=false;
                editNamePageStage.close();
            });
            editNamePageStage.show();
            editNamePageStage.setResizable(false);
            resetLocation(editNamePageStage);
        }
        else{
            resetLocation(editNamePageStage);
        }
    }

    public AnchorPane getChooseAnchorPane() {
        return chooseAnchorPane;
    }

    public void closeAllChildren(){
        if(isEditNamePageShow){
            editNamePageStage.close();
        }
        if(isChangePasswordPageShow){
            changePasswordPageStage.close();
        }
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public void setID(String ID) {
        this.ID = ID;
    }
}
