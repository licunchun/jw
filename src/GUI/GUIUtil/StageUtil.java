package GUI.GUIUtil;

import MainPackage.Main;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
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

    public static void setScene(Stage stage,Parent root){
        stage.setScene(new Scene(root));
        StageUtil.setCenter(stage);
    }
    public static <T> T changeViews(Stage stage,String fxml){
        try {
            FXMLLoader loader=new FXMLLoader(Objects.requireNonNull(StageUtil.class.getResource(fxml)));
            Parent root = loader.load();
            setScene(stage,root);
            return loader.getController();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
