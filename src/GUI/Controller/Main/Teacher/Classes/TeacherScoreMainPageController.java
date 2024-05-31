package GUI.Controller.Main.Teacher.Classes;

import GUI.Controller.Main.Teacher.TeacherMainMenuController;
import GUI.Data.DataPackage.Classes.CourseTimeSet;
import GUI.Data.DataPackage.Classes.TeacherScoreMainTable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;

import static GUI.Data.Util.Classes.ObservableListUtil.getTeacherScoreMainPageObservableList;
import static GUI.GUIUtil.StageUtil.*;

public class TeacherScoreMainPageController {
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

        });
        // codeColumn 列的 cellFactory
        codeColumn.setCellFactory(column -> new TableCell<>() {
            @Override
            protected void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText(null);
                    setAlignment(Pos.CENTER); // 居中对齐
                } else {
                    setText(item);
                    setAlignment(Pos.CENTER); // 居中对齐
                }
            }
        });

// nameColumn 列的 cellFactory
        nameColumn.setCellFactory(column -> new TableCell<>() {
            @Override
            protected void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText(null);
                    setAlignment(Pos.CENTER); // 居中对齐
                } else {
                    setText(item);
                    setAlignment(Pos.CENTER); // 居中对齐
                }
            }
        });

// timeColumn 列的 cellFactory
        timeColumn.setCellFactory(column -> new TableCell<>() {
            @Override
            protected void updateItem(CourseTimeSet item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText(null);
                    setAlignment(Pos.CENTER); // 居中对齐
                } else {
                    setText(item.toString());
                    setAlignment(Pos.CENTER); // 居中对齐
                }
            }
        });

// totalStudentColumn 列的 cellFactory
        totalStudentColumn.setCellFactory(column -> new TableCell<>() {
            @Override
            protected void updateItem(Integer item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText(null);
                    setAlignment(Pos.CENTER); // 居中对齐
                } else {
                    setText(String.valueOf(item));
                    setAlignment(Pos.CENTER); // 居中对齐
                }
            }
        });

// buttonColumn 列的 cellFactory
        buttonColumn.setCellFactory(column -> new TableCell<>() {
            @Override
            protected void updateItem(Button button, boolean empty) {
                super.updateItem(button, empty);
                if (empty || button == null) {
                    setGraphic(null);
                    setText(null);
                } else {
                    // 为按钮添加点击事件处理程序，并传入按钮的特定ID
                    button.setOnAction(event -> teacherMainMenuController.openScoreSubPage(button.getId(), ID));
                    setGraphic(button);
                    setText(null);
                    setAlignment(Pos.CENTER); // 居中对齐
                }
            }
        });


    }

    private void loadTable() {
        codeColumn.setCellValueFactory(cellData -> cellData.getValue().codeProperty());
        nameColumn.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
        timeColumn.setCellValueFactory(cellData -> cellData.getValue().timeProperty());
        totalStudentColumn.setCellValueFactory(cellData -> cellData.getValue().totalStudentProperty().asObject());
        buttonColumn.setCellValueFactory(cellData -> cellData.getValue().buttonProperty());

        teacherCourseTableView.setItems(data);
    //    data = getTeacherScoreMainPageObservableList(ID);
    }

    public void flush() {
        data.clear();
        data = getTeacherScoreMainPageObservableList(ID);
        teacherCourseTableView.setItems(data);
        System.out.println(data);
        for (TeacherScoreMainTable item : data) {
            System.out.println("Code: " + item.getCode());
            System.out.println("Name: " + item.getName());
            // 打印其它属性
        }

    }

    public ContextMenu teacherCourseMainPageContextMenu() {
        ContextMenu contextMenu = new ContextMenu();
        MenuItem flushMenuItem = new MenuItem("刷新");

        flushMenuItem.setAccelerator(new KeyCodeCombination(KeyCode.R, KeyCodeCombination.CONTROL_DOWN));

        flushMenuItem.setOnAction(event -> flush());

        contextMenu.getItems().addAll(flushMenuItem);

        return contextMenu;
    }
}
