package GUI.GUIUtil;

import GUI.Controller.Main.test.testMain;
import MainPackage.Main;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.stage.Stage;

import java.awt.*;
import java.io.IOException;
import java.util.Objects;

public class StageUtil {
    public StageUtil(){};
    //将窗口移至屏幕中间
    public static void setCenter(Stage stage){
        double x=(Toolkit.getDefaultToolkit().getScreenSize().getWidth()-stage.getWidth())/2;
        double y=(Toolkit.getDefaultToolkit().getScreenSize().getHeight()-stage.getHeight())/2;
        stage.setX(x);
        stage.setY(y);
    }
    public static void changeViews(String fxml){
        try {
            Parent root = FXMLLoader.load(Objects.requireNonNull(StageUtil.class.getResource(fxml)));
            Main.setScene(root);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
