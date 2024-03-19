import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class GUI extends Application {

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("教务系统");

        // 创建按钮
        Button studentButton = new Button("学生信息");
        Button courseButton = new Button("课程信息");
        Button gradeButton = new Button("成绩管理");
        Button checkButton = new Button("查看课表");

        // 创建垂直布局
        VBox vbox = new VBox(10);
        vbox.getChildren().addAll(studentButton, courseButton, gradeButton, checkButton);

        // 应用 CSS 样式
        vbox.setStyle("-fx-padding: 10;" +
                "-fx-border-style: solid inside;" +
                "-fx-border-width: 2;" +
                "-fx-border-insets: 5;" +
                "-fx-border-radius: 5;" +
                "-fx-border-color: #336699;");

        // 创建场景
        Scene scene = new Scene(vbox, 300, 200);

        // 将场景添加到舞台
        primaryStage.setScene(scene);

        // 为查看课表按钮设置回调函数
        checkButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                checkButtonCallback();
            }
        });

        // 显示舞台
        primaryStage.show();
    }

    // 查看课表按钮的回调函数
    private void checkButtonCallback() {
        // 在此处添加处理查看课表按钮点击事件的逻辑
        System.out.println("查看课表按钮被点击了");
    }

    public static void main(String[] args) {
        launch(args);
    }
}
