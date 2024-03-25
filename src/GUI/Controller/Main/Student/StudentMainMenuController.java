package GUI.Controller.Main.Student;

import Data.Enum.User.UserType;
import GUI.Controller.Main.Common.MainMenuController;
import MainPackage.Main;
import javafx.fxml.FXML;
import javafx.stage.Stage;

public class StudentMainMenuController {
    private static final Stage stage= Main.getStage();

    private MainMenuController mainMenuController;



    public void initialize(){
        mainMenuController.setUserType(UserType.Student);
    }
}
