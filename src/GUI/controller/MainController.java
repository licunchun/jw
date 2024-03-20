package GUI.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class MainController {

    @FXML
    private Button btnSelectCourse;

    @FXML
    private Button btnMySchedule;

    @FXML
    private Button btnCurriculumPlan;

    @FXML
    public void initialize() {
        // 初始化操作，如果有需要的话
    }

    @FXML
    public void handleSelectCourse() {
        // 处理学生选课逻辑
        System.out.println("进入学生选课界面");
    }

    @FXML
    public void handleMySchedule() {
        // 处理查看课表逻辑
        System.out.println("查看我的课表");
    }

    @FXML
    public void handleCurriculumPlan() {
        // 处理查看培养计划逻辑
        System.out.println("查看培养计划");
    }
}
