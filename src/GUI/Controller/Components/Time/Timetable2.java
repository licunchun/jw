package GUI.Controller.Components.Time;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class Timetable2 extends Application {
    private static final int NUM_ROWS = 14;
    private static final int NUM_COLUMNS = 8;
    private boolean isClicked = false;

    @Override
    public void start(Stage primaryStage) {
        GridPane root = new GridPane();

        for (int row = 0; row < NUM_ROWS; row++) {
            for (int col = 0; col < NUM_COLUMNS; col++) {
                Button button = null;
                if(row == 0) {
                    if(col == 0) button = new Button("     ");
                    if(col == 1) button = new Button("星期一");
                    if(col == 2) button = new Button("星期二");
                    if(col == 3) button = new Button("星期三");
                    if(col == 4) button = new Button("星期四");
                    if(col == 5) button = new Button("星期五");
                    if(col == 6) button = new Button("星期六");
                    if(col == 7) button = new Button("星期日");
                    button.setId(String.valueOf(col + 1));
                }
                else if(col == 0) {
                    button = new Button(String.valueOf(row));
                }
                else{
                    button = new Button("     ");
                }
                Button finalButton = button;
                button.setOnAction(event -> {
                    if (isClicked) {
                        finalButton.setStyle(""); // 恢复原始样式
                    } else {
                        finalButton.setStyle("-fx-background-color: #FF0000;"); // 设置点击后的颜色（红色）
                    }
                    isClicked = !isClicked; // 切换状态
                });
                button.setMinWidth(80);
                root.add(button, col, row);
            }
        }

        Scene scene = new Scene(root, 650, 400);
        primaryStage.setScene(scene);
        primaryStage.setTitle("课程时间选择");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
