package GUI.Controller.Components.Time;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class Timetable2 extends Application {
    private static final int NUM_ROWS = 14;
    private static final int NUM_COLUMNS = 8;

    @Override
    public void start(Stage primaryStage) {
        GridPane root = new GridPane();

        for (int row = 0; row < NUM_ROWS; row++) {
            for (int col = 0; col < NUM_COLUMNS; col++) {
                CheckBox checkbox = new CheckBox();
                if (row == 0) {
                    if (col == 0) checkbox.setText("    ");
                    if (col == 1) checkbox.setText("星期一");
                    if (col == 2) checkbox.setText("星期二");
                    if (col == 3) checkbox.setText("星期三");
                    if (col == 4) checkbox.setText("星期四");
                    if (col == 5) checkbox.setText("星期五");
                    if (col == 6) checkbox.setText("星期六");
                    if (col == 7) checkbox.setText("星期日");

                } else if (col == 0) {
                    checkbox.setText(String.valueOf(row));
                } else {
                    checkbox.setText("     ");
                }
                checkbox.setId(String.valueOf(row*NUM_COLUMNS + col));
                checkbox.setOnAction(event -> {
                    boolean isSelected = checkbox.isSelected();
                    if (isSelected) {
                        checkbox.setStyle("-fx-background-color: #FF0000;"); // 设置点击后的颜色（红色）
                    } else {
                        checkbox.setStyle(""); // 恢复原始样式
                    }
                });
                checkbox.setMinWidth(80);
                root.add(checkbox, col, row);
            }
        }
        Button button = new Button("确认");
        button.setMinWidth(80);
        root.add(button, NUM_COLUMNS - 1, NUM_ROWS);
        Scene scene = new Scene(root, 650, 400);
        primaryStage.setScene(scene);
        primaryStage.setTitle("课程时间选择");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

