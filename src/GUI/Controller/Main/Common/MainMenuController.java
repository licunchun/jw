package GUI.Controller.Main.Common;

import Data.Enum.User.UserType;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.MenuBar;
import javafx.scene.control.TabPane;
import javafx.scene.layout.AnchorPane;

public class MainMenuController {

    @FXML
    public MenuBar MainMenuBar;
    @FXML
    public Button personalInformation;
    @FXML
    public AnchorPane MainModule;
    @FXML
    public javafx.scene.control.TabPane TabPane;

    /*
     * Page attributes and their getter&setter
     */
    private static UserType userType;
    public static void setUserType(UserType userType) {
        MainMenuController.userType = userType;
    }

    @FXML
    public void initialize(){

    }
}
