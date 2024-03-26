package GUI.Controller.Main.Common;

import Data.Enum.User.UserType;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.MenuBar;
import javafx.scene.control.TabPane;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import javax.swing.plaf.nimbus.State;
import java.io.IOException;

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
            FXMLLoader loader=new FXMLLoader(getClass().getResource("/GUI/Window/Main/Common/UserInformationPage.fxml"));
            Parent root = loader.load();

            userInformationPageController=loader.getController();
            userInformationPageController.setUserType(userType);
            if(userType==UserType.Student){
                FXMLLoader studentLoader=new FXMLLoader(getClass().getResource("/GUI/Window/Main/Student/StudentInformation.fxml"));
                Parent studentRoot=studentLoader.load();
                userInformationPageController.getChooseAnchorPane().getChildren().add(studentRoot);
            }
            if(userType==UserType.Teacher){
                FXMLLoader teacherLoader=new FXMLLoader(getClass().getResource("/GUI/Window/Main/Teacher/TeacherInformation.fxml"));
                Parent teacherRoot=teacherLoader.load();
                userInformationPageController.getChooseAnchorPane().getChildren().add(teacherRoot);
            }

            informationPageStage.setScene(new Scene(root));
            informationPageStage.setOnCloseRequest(e->{
                isInformationPageShow=false;
                informationPageStage.close();
            });
            informationPageStage.show();
        }
    }


}
