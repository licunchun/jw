package GUI.Controller.Main.Common;

import Data.Enum.User.UserType;
import javafx.fxml.FXML;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

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
    private Integer intUserType;

    private UserType userType = UserType.None;

    private MainMenuController mainMenuController;
    @FXML
    public void initialize(){
    }

    public void setUserType(UserType userType){
        this.userType=userType;

    }

    public AnchorPane getChooseAnchorPane() {
        return chooseAnchorPane;
    }
}
