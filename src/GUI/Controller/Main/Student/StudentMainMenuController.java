package GUI.Controller.Main.Student;

import Data.Enum.User.UserType;
import GUI.Controller.Main.Common.MainMenuController;
import MainPackage.Main;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class StudentMainMenuController {
    private static final Stage stage= Main.getStage();

    private MainMenuController mainMenuController;
    @FXML
    private AnchorPane subPane;



    public void initialize(){
        //子区域加载，mainMenuController赋值
        try {
            FXMLLoader loader=new FXMLLoader(getClass().getResource("/GUI/Window/Main/Common/MainMenu.fxml"));
            AnchorPane subContent=loader.load();
            mainMenuController=loader.getController();
            subPane.getChildren().add(subContent);
        } catch (IOException e){
            e.printStackTrace();
        }
        //mainMenuController赋值
        mainMenuController.setUserType(UserType.Student);
    }
}
