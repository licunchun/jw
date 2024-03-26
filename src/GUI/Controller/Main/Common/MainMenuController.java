package GUI.Controller.Main.Common;

import Data.Enum.User.UserType;
import MainPackage.Main;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.MenuBar;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;


import static GUI.Controller.Main.Util.MainPage.openMainPage;
import static GUI.GUIUtil.StageUtil.*;

public class MainMenuController {
    private final Stage stage= Main.getStage();

    @FXML
    private MenuBar MainMenuBar;
    @FXML
    private Button personalInformation;
    @FXML
    private AnchorPane MainModule;
    @FXML
    private javafx.scene.control.TabPane TabPane;
    private UserType userType=UserType.None;
    private String ID="";
    private boolean isInformationPageShow =false;
    private UserInformationPageController userInformationPageController;
    private Stage informationPageStage;

    /*
     * Page attributes and their getter&setter
     */
    public void setUserType(UserType userType){
        this.userType=userType;
    }
    public void setID(String ID){
        this.ID=ID;
    }

    @FXML
    public void initialize(){}

    @FXML
    private void showInformationPage() {
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
            informationPageStage.setResizable(false);
            informationPageStage.show();
            resetLocation(informationPageStage);
        }
        else{
            resetLocation(informationPageStage);
        }
    }


}
