package GUI.Controller.Main.Common;

import GUI.Controller.Main.Student.StudentInformationController;
import GUI.Controller.Main.Teacher.TeacherInformationController;
import GUI.Data.Enum.User.UserType;
import MainPackage.Main;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.MenuBar;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;


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
    private String ID;
    /*
     * Information Page
     */
    private boolean isInformationPageShow =false;
    private UserInformationPageController userInformationPageController;
    private Stage informationPageStage;
    private StudentInformationController studentInformationController;
    private TeacherInformationController teacherInformationController;
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
    public void initialize(){
    }

    @FXML
    private void showInformationPage() {
        if(!isInformationPageShow){
            isInformationPageShow=true;
            informationPageStage = new Stage();

            informationPageStage.setOnCloseRequest(e->{
                isInformationPageShow=false;
                userInformationPageController.closeAllChildren();
                if(userType==UserType.Student){
                    studentInformationController.closeAllChildren();
                }
                if(userType==UserType.Teacher){
                    teacherInformationController.closeAllChildren();
                }
                informationPageStage.close();
            });

            userInformationPageController=changeViews(informationPageStage,"/GUI/Window/Main/Common/UserInformationPage.fxml");
            userInformationPageController.setStage(informationPageStage);
            userInformationPageController.setID(ID);
            if(userType==UserType.Student){
                FXMLLoader loader=loadScene("/GUI/Window/Main/Student/StudentInformation.fxml");
                userInformationPageController.getChooseAnchorPane().getChildren().add(newRoot(loader));
                studentInformationController=getController(loader);
                studentInformationController.setID(ID);
            }
            if(userType==UserType.Teacher){
                FXMLLoader loader=loadScene("/GUI/Window/Main/Teacher/TeacherInformation.fxml");
                userInformationPageController.getChooseAnchorPane().getChildren().add(newRoot(loader));
                teacherInformationController=getController(loader);
                teacherInformationController.setID(ID);
            }

            informationPageStage.setResizable(false);
            informationPageStage.show();
            resetLocation(informationPageStage);
        }
        else{
            resetLocation(informationPageStage);
        }
    }


}
