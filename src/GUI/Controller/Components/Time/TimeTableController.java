package GUI.Controller.Components.Time;

import GUI.Data.DataPackage.Classes.CourseTimeSet;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import static GUI.Data.Enum.Classes.Util.getTime;

public class TimeTableController {
    static final double BUTTON_WIDE = 45.0;
    static final double BUTTON_HEIGHT = 30.0;
    static final double Distance = 20.0;
    static boolean[][] isChosen = new boolean[8][14];
    @FXML
    private AnchorPane TablePlace;
    @FXML
    private Button ConfirmButton;
    private Stage stage;
    private Button[][] Table = new Button[8][14];

    @FXML
    private void initialize() {

        for (int i = 0; i <= 7; ++i) {
            for (int j = 0; j <= 13; ++j) {
                Button button = newButton();
                AnchorPane.setTopAnchor(button, Distance + j * BUTTON_HEIGHT);
                AnchorPane.setLeftAnchor(button, Distance + i * BUTTON_WIDE);
                TablePlace.getChildren().add(button);
                Table[i][j] = button;
            }
        }

        Table[0][0].setOnAction(e -> {
            if (isChosen[0][0]) {
                for (int i = 1; i <= 7; i++) {
                    for (int j = 1; j <= 13; j++) {
                        isChosen[i][j] = false;
                    }
                }
            } else {
                for (int i = 1; i <= 7; i++) {
                    for (int j = 1; j <= 13; j++) {
                        isChosen[i][j] = true;
                    }
                }
            }
            flush();
        });

        for (int i = 1; i <= 7; ++i) {
            Table[i][0].setText(Integer.toString(i));
            int finalI = i;
            Table[i][0].setOnAction(e -> {
                if (isChosen[finalI][0]) {
                    for (int j = 1; j <= 13; j++) {
                        isChosen[finalI][j] = false;
                    }
                } else {
                    for (int j = 1; j <= 13; j++) {
                        isChosen[finalI][j] = true;
                    }
                }
                flush();
            });
        }

        for (int j = 1; j <= 13; ++j) {
            Table[0][j].setText(Integer.toString(j));
            int finalJ = j;
            Table[0][j].setOnAction(e -> {
                if (isChosen[0][finalJ]) {
                    for (int i = 1; i <= 7; i++) {
                        isChosen[i][finalJ] = false;
                    }
                } else {
                    for (int i = 1; i <= 7; i++) {
                        isChosen[i][finalJ] = true;
                    }
                }
                flush();
            });
        }

        for (int i = 1; i <= 7; i++) {
            for (int j = 1; j <= 13; j++) {
                int finalI = i;
                int finalJ = j;
                Table[i][j].setOnAction(e -> {
                    isChosen[finalI][finalJ] = !isChosen[finalI][finalJ];
                    flush();
                });
            }
        }
    }

    private Button newButton() {
        Button button = new Button();
        button.setPrefHeight(30);
        button.setPrefWidth(45);
        button.setStyle("-fx-background-color: lightgray;");
        return button;
    }

    private void flush() {
        for (int i = 1; i <= 7; ++i) {
            boolean Flag = true;
            for (int j = 1; j <= 13; j++) {
                if (!isChosen[i][j]) {
                    Flag = false;
                    break;
                }
            }
            isChosen[i][0] = Flag;
        }

        for (int j = 0; j <= 13; ++j) {
            boolean Flag = true;
            for (int i = 1; i <= 7; i++) {
                if (!isChosen[i][j]) {
                    Flag = false;
                    break;
                }
            }
            isChosen[0][j] = Flag;
        }

        for (int i = 0; i <= 7; i++) {
            for (int j = 0; j <= 13; j++) {
                if (isChosen[i][j]) {
                    Table[i][j].setStyle("-fx-background-color: skyblue;");
                } else {
                    Table[i][j].setStyle("-fx-background-color: lightgray;");
                }
            }
        }
    }

    @FXML
    private void doConfirm() {
        stage.close();
    }

    public void setStage(Stage stage) {
        this.stage = stage;
        stage.setTitle("时间选择页面");
    }

    public CourseTimeSet getTimeSet() {
        CourseTimeSet courseTimeSet = new CourseTimeSet();
        for (int i = 1; i <= 7; ++i) {
            for (int j = 1; j <= 13; ++j) {
                if (isChosen[i][j]) {
                    courseTimeSet.add(getTime(i, j));
                }
            }
        }
        return courseTimeSet;
    }

}
