package GUI.Controller.Main.Teacher;

import GUI.Data.Enum.User.UserType;
import GUI.Controller.Main.Common.MainMenuController;
import MainPackage.Main;
import javafx.stage.Stage;

public class TeacherMainMenuController {
    private static final Stage stage= Main.getStage();

    private MainMenuController mainMenuController;
    private String ID;


    public void setID(String ID){
        this.ID=ID;
    }
    public void initialize(){
        mainMenuController.setUserType(UserType.Teacher);
    }
}
