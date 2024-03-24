package GUI.GUIUtil;

import javafx.stage.Stage;

import java.awt.*;

public class StageUtil {
    public StageUtil(){};
    //将窗口移至屏幕中间
    public static void setCenter(Stage stage){
        double x=(Toolkit.getDefaultToolkit().getScreenSize().getWidth()-stage.getWidth())/2;
        double y=(Toolkit.getDefaultToolkit().getScreenSize().getHeight()-stage.getHeight())/2;
        stage.setX(x);
        stage.setY(y);
    }
}
