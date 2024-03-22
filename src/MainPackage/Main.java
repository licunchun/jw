package MainPackage;

import GUI.util.StageUtil;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class Main extends Application{

    private static  Stage stage;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        Main.stage=stage;
        stage.setResizable(false);
        stage.setTitle("Class choosing system");
        changeViews("/GUI/window/login.fxml");
        stage.show();
        StageUtil.setCenter(stage);
    }

    public static void changeViews(String fxml){
        try {
            Parent root = FXMLLoader.load(Objects.requireNonNull(Main.class.getResource(fxml)));
            stage.setScene(new Scene(root));
            StageUtil.setCenter(stage);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}