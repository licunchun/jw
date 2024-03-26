package GUI.Controller.Main.Common;

import Data.Enum.User.UserType;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.MenuBar;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

import static GUI.GUIUtil.StageUtil.*;

public class MainMenuController {

    @FXML
    public MenuBar MainMenuBar;
    @FXML
    public Button personalInformation;
    @FXML
    public AnchorPane MainModule;
    @FXML
    public javafx.scene.control.TabPane TabPane;
    private UserType userType=UserType.None;
    private boolean isInformationPageShow =false;
    private UserInformationPageController userInformationPageController;
    private Stage informationPageStage;

    /*
     * Page attributes and their getter&setter
     */
    public void setUserType(UserType userType){
        this.userType=userType;
    }

    @FXML
    public void initialize(){}

    @FXML
    public void showInformationPage() throws IOException {
        if(!isInformationPageShow){
            isInformationPageShow=true;
            informationPageStage = new Stage();

            userInformationPageController=changeViews(informationPageStage,"/GUI/Window/Main/Common/UserInformationPage.fxml");

            userInformationPageController.setUserType(userType);
            if(userType==UserType.Student){
                userInformationPageController.getChooseAnchorPane().getChildren().add(loadScene("/GUI/Window/Main/Student/StudentInformation.fxml"));
            }
            if(userType==UserType.Teacher){
                userInformationPageController.getChooseAnchorPane().getChildren().add(loadScene("/GUI/Window/Main/Teacher/TeacherInformation.fxml"));
            }

            informationPageStage.setOnCloseRequest(e->{
                isInformationPageShow=false;
                informationPageStage.close();
            });
            informationPageStage.show();
            resetLocation(informationPageStage);
        }
        else{
            resetLocation(informationPageStage);
        }
    }


}
