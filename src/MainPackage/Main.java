package MainPackage;

import javafx.application.Application;
import javafx.stage.Stage;

import static GUI.GUIUtil.StageUtil.changeViews;
import static GUI.GUIUtil.StageUtil.resetLocation;
import static java.lang.System.exit;

public class Main extends Application {

    private static Stage stage;

    public static void main(String[] args) {
        launch(args);
    }

    public static Stage getStage() {
        return stage;
    }

    @Override
    public void start(Stage stage) {
        Main.stage = stage;
        stage.setOnHiding(e -> exit(0));
        stage.setResizable(false);
        changeViews(stage, "/GUI/Window/Login/Login.fxml");
        //changeViews(stage,"/GUI/Window/Main/Student/StudentMainMenu.fxml");
        //changeViews(stage,"/GUI/Window/Main/Teacher/TeacherMainMenu.fxml");
        //changeViews(stage,"/GUI/Window/Main/Admin/AdminMainMenu.fxml");
        //changeViews(stage,"/GUI/Window/Main/Common/Classes/ClassesChoosingPage.fxml");
        stage.show();
        resetLocation(stage);
    }
}