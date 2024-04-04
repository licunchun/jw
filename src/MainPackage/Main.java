package MainPackage;

import javafx.application.Application;
import javafx.stage.Stage;

import static GUI.GUIUtil.StageUtil.resetLocation;
import static java.lang.System.exit;

public class Main extends Application{

    private static Stage stage;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        Main.stage =stage;
        stage.setOnHiding(e-> exit(0));
        stage.setResizable(false);
        stage.setTitle("课程管理一体化系统");
        //GUI.GUIUtil.StageUtil.changeViews(stage,"/GUI/Window/Login/Login.fxml");
        GUI.GUIUtil.StageUtil.changeViews(stage,"/GUI/Window/Main/Student/StudentMainMenu.fxml");
        //GUI.GUIUtil.StageUtil.changeViews(stage,"/GUI/Window/Main/Teacher/TeacherMainMenu.fxml");
        //GUI.GUIUtil.StageUtil.changeViews(stage,"/GUI/Window/Main/Admin/AdminMainMenu.fxml");
        //GUI.GUIUtil.StageUtil.changeViews(stage,"/GUI/Window/Main/Common/Classes/ClassesChoosingPage.fxml");
        stage.show();
        resetLocation(stage);
    }

    public static Stage getStage(){
        return stage;
    }
}

//TODO 界面名字等落实