package GUI.Controller.Main.Admin;

import Data.Enum.User.UserType;
import GUI.Controller.Main.Common.MainMenuController;
import MainPackage.Main;
import javafx.stage.Stage;

public class AdminMainMenuController {
    private static final Stage stage= Main.getStage();

    private MainMenuController mainMenuController;



    public void initialize(){
        mainMenuController.setUserType(UserType.Admin);
    }
}
