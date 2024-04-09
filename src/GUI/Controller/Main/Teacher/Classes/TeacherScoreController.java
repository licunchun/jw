package GUI.Controller.Main.Teacher.Classes;

import Data.Type.Teacher;
import GUI.Data.DataPackage.Classes.*;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.*;

import javafx.fxml.FXML;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;

import static GUI.Data.Util.Classes.ObservableListUtil.getTeacherCourseObservableList;
import static Sevice.Main.Teacher.ClassesServ.TeacherClassesServ.getTeacherClassesSet;

public class TeacherScoreController {

    @FXML
    private TableView<TeacherCourseTable> teacherCourseTableView;
    @FXML
    private TableColumn<TeacherCourseTable, String> codeColumn;
    @FXML
    private TableColumn<TeacherCourseTable, String> nameColumn;
    @FXML
    private TableColumn<TeacherCourseTable, CourseTimeSet> timeColumn;
    @FXML
    private TableColumn<TeacherCourseTable, Integer> totalStudentColumn;
    @FXML
    private TableColumn<TeacherCourseTable, Button> buttonColumn;
    private ObservableList<TeacherCourseTable> data = FXCollections.observableArrayList();//用于表格的展示的ObservableList
    private String ID;
    public void setID(String ID) {
        this.ID=ID;
    }
    public void initialize() {
        loadTable();
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
        data = getTeacherCourseObservableList(ID);
    }
    public ContextMenu teacherCourseContextMenu() {
        ContextMenu contextMenu = new ContextMenu();
        MenuItem flushMenuItem = new MenuItem("刷新");

        flushMenuItem.setAccelerator(new KeyCodeCombination(KeyCode.R, KeyCodeCombination.CONTROL_DOWN));

        flushMenuItem.setOnAction(event -> flush());

        contextMenu.getItems().addAll(flushMenuItem);

        return contextMenu;
    }
}
