package MainPackage;

import GUI.Controller.Main.test.testMain;
import GUI.GUIUtil.StageUtil;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class Main extends Application{

    private static Stage stage;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        Main.stage =stage;
        stage.setResizable(false);
        stage.setTitle("学生选课系统");
        //GUI.GUIUtil.StageUtil.changeViews("/GUI/Window/Login/Login.fxml");
        GUI.GUIUtil.StageUtil.changeViews("/GUI/Window/Main/Student/StudentMainMenu.fxml");
        stage.show();
        StageUtil.setCenter(stage);
    }

    public Stage getStage(){
        return stage;
    }

    public static void setScene(Parent root){
        stage.setScene(new Scene(root));
        StageUtil.setCenter(stage);
    }
}