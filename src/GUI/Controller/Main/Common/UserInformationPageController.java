package GUI.Controller.Main.Common;

import GUI.Data.Enum.User.UserType;
import javafx.fxml.FXML;
import javafx.scene.control.Hyperlink;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import static GUI.GUIUtil.StageUtil.*;

public class UserInformationPageController {
    @FXML
    private AnchorPane chooseAnchorPane;
    @FXML
    private VBox UserInformationBox;
    @FXML
    private Hyperlink EditPassword;
    @FXML
    private Hyperlink EditName;
    @FXML
    public void initialize(){
    }
    @FXML
    private void doEditPassword(){
        if(!isEditPasswordPageShow){
            isEditPasswordPageShow=true;
            editPasswordPageStage=new Stage();

            changeViews(editPasswordPageStage, "/GUI/Window/Components/UserInformationEditor/PasswordChanger.fxml");

            editPasswordPageStage.setOnCloseRequest(e->{
                isEditPasswordPageShow=false;
                editPasswordPageStage.close();
            });
            editPasswordPageStage.show();
            editPasswordPageStage.setResizable(false);
            resetLocation(editPasswordPageStage);
        }
        else{
            resetLocation(editPasswordPageStage);
        }
    }
    @FXML
    private void doEditName(){
        if(!isEditNamePageShow){
            isEditNamePageShow=true;
            editNamePageStage=new Stage();

            changeViews(editNamePageStage,"/GUI/Window/Components/UserInformationEditor/NameEditor.fxml");

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

    private boolean isEditNamePageShow=false;
    private Stage editNamePageStage=null;
    private boolean isEditPasswordPageShow=false;
    private Stage editPasswordPageStage=null;

    private UserType userType = UserType.None;

    private MainMenuController mainMenuController;

    public void setUserType(UserType userType){
        this.userType=userType;

    }

    public AnchorPane getChooseAnchorPane() {
        return chooseAnchorPane;
    }
}
