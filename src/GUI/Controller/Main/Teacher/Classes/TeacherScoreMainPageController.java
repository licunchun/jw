package GUI.Controller.Main.Teacher.Classes;

import GUI.Controller.Main.Teacher.TeacherMainMenuController;
import GUI.Data.DataPackage.Classes.CourseTimeSet;
import GUI.Data.DataPackage.Classes.TeacherScoreMainTable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;

import static GUI.Data.Util.Classes.ObservableListUtil.getTeacherScoreMainPageObservableList;
import static GUI.GUIUtil.StageUtil.*;

public class TeacherScoreMainPageController {

    public Tab teacherScoreSubPageTab;
    public TabPane tabPane;
    TeacherMainMenuController teacherMainMenuController;
    @FXML
    private TableView<TeacherScoreMainTable> teacherCourseTableView;
    @FXML
    private TableColumn<TeacherScoreMainTable, String> codeColumn;
    @FXML
    private TableColumn<TeacherScoreMainTable, String> nameColumn;
    @FXML
    private TableColumn<TeacherScoreMainTable, CourseTimeSet> timeColumn;
    @FXML
    private TableColumn<TeacherScoreMainTable, Integer> totalStudentColumn;
    @FXML
    private TableColumn<TeacherScoreMainTable, Button> buttonColumn;
    private ObservableList<TeacherScoreMainTable> data = FXCollections.observableArrayList();//用于表格的展示的ObservableList
    private String ID;
    private TeacherScoreSubPageController teacherScoreSubPageController;

    public void setID(String ID) {
        this.ID = ID;
    }

    public void setTabPane(TabPane tabPane) {
        this.tabPane = tabPane;
    }

    public void setTeacherScoreSubPageController(TeacherMainMenuController teacherMainMenuController) {
        this.teacherMainMenuController = teacherMainMenuController;
    }

    public void initialize() {
        loadTable();
        buttonColumn.setCellFactory(column -> new TableCell<>() {
            @Override
            protected void updateItem(Button button, boolean empty) {
                super.updateItem(button, empty);
                if (button == null || empty) {
                    setGraphic(null);
                } else {
                    // 为按钮添加点击事件处理程序，并传入按钮的特定ID
                    button.setOnAction(event -> openSubPage(button.getId()));
                    setGraphic(button);
                }
            }
        });
        teacherScoreSubPageTab = new Tab("具体给分页面");
        {
            FXMLLoader teacherScoreSubPageLoader = loadScene("/GUI/Window/Main/Teacher/Classes/TeacherScoreSubPage.fxml");
            Parent root = newRoot(teacherScoreSubPageLoader);
            teacherScoreSubPageController = getController(teacherScoreSubPageLoader);

            ContextMenu contextMenu = teacherScoreSubPageController.teacherCourseSubPageContextMenu();
            root.setOnContextMenuRequested(e -> contextMenu.show(root, e.getScreenX(), e.getScreenY()));

            teacherScoreSubPageController.setID(ID);

            teacherScoreSubPageTab.setContent(root);
        }
    }

    private void loadTable() {
        codeColumn.setCellValueFactory(cellData -> cellData.getValue().codeProperty());
        nameColumn.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
        timeColumn.setCellValueFactory(cellData -> cellData.getValue().timeProperty());
        totalStudentColumn.setCellValueFactory(cellData -> cellData.getValue().totalStudentProperty().asObject());
        buttonColumn.setCellValueFactory(cellData -> cellData.getValue().buttonProperty());

        teacherCourseTableView.setItems(data);
    }

    public void flush() {
        data = getTeacherScoreMainPageObservableList(ID);
    }

    public ContextMenu teacherCourseMainPageContextMenu() {
        ContextMenu contextMenu = new ContextMenu();
        MenuItem flushMenuItem = new MenuItem("刷新");

        flushMenuItem.setAccelerator(new KeyCodeCombination(KeyCode.R, KeyCodeCombination.CONTROL_DOWN));

        flushMenuItem.setOnAction(event -> flush());

        contextMenu.getItems().addAll(flushMenuItem);

        return contextMenu;
    }

    //    private void openSubPage(String buttonId) {//TODO
//        try {
//            // 加载子页面的FXML文件
//            FXMLLoader loader = new FXMLLoader(getClass().getResource("GUI/Window/Main/Teacher/Classes/TeacherScoreSubPage.fxml"));
//
//            // 设置控制器工厂，负责创建Controller对象并传递参数
//            loader.setControllerFactory(controllerClass -> {
//                try {
//                    TeacherScoreSubPageController teacherScoreSubPageController = getController(loader);
//
//                    teacherScoreSubPageController.setTeacherScoreSubPageController(buttonId);
//                    teacherScoreSubPageController.setID(ID);
//                    teacherScoreSubPageController.setPrimaryStage(primaryStage);
//
//                    return teacherScoreSubPageController;
//                } catch (Exception e) {
//                    throw new RuntimeException(e);
//                }
//            });
//
//            Parent root = loader.load();
//
//            // 创建子页面的场景
//            Scene scene = new Scene(root);
//
//            // 将子页面的场景设置为主页面的场景，使其覆盖主页面
//            primaryStage.setScene(scene);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
    private void openSubPage(String buttonId) {
        teacherScoreSubPageController.setTeacherScoreSubPageController(buttonId);
        teacherMainMenuController.setIsTeacherScoreSubPageExist();
    }

}
