package MainPackage;

import GUI.GUIUtil.StageUtil;
import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

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
        //GUI.GUIUtil.StageUtil.changeViews(stage,"/GUI/Window/Login/Login.fxml");
        GUI.GUIUtil.StageUtil.changeViews(stage,"/GUI/Window/Main/Common/MainMenu.fxml");
        stage.show();
        StageUtil.setCenter(stage);
    }

    public static Stage getStage(){
        return stage;
    }
}