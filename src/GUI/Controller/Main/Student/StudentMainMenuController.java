package GUI.Controller.Main.Student;

import GUI.Data.Enum.User.UserType;
import GUI.Controller.Main.Common.MainMenuController;
import MainPackage.Main;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.MenuBar;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class StudentMainMenuController {
    private static final Stage stage= Main.getStage();

    private MainMenuController mainMenuController;
    @FXML
    private AnchorPane subPane;
    private String ID;
    @FXML
    public void initialize(){
        //子区域加载，mainMenuController赋值
        try {
            FXMLLoader loader=new FXMLLoader(getClass().getResource("/GUI/Window/Main/Common/MainMenu.fxml"));
            AnchorPane subContent=loader.load();
            mainMenuController=loader.getController();
            subPane.getChildren().add(subContent);
            {
                MenuBar menuBar=mainMenuController.getMenuBar();

            }//MenuBar配置
        } catch (IOException e){
            e.printStackTrace();
        }
        //mainMenuController赋值
        mainMenuController.setUserType(UserType.Student);
        mainMenuController.setID(ID);
    }
    public void setID(String ID){
        this.ID=ID;
        mainMenuController.setID(ID);
    }
}
