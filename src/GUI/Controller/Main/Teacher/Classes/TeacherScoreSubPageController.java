package GUI.Controller.Main.Teacher.Classes;

import GUI.Data.DataPackage.Classes.Classes;
import GUI.Data.DataPackage.Classes.IDSet;
import GUI.Data.DataPackage.Classes.TeacherScoreSubTable;
import GUI.Data.Enum.School;
import GUI.Data.Enum.User.Grade;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

import static GUI.Data.Util.Classes.ObservableListUtil.getTeacherScoreSubPageObservableList;
import static Sevice.Main.Components.ClassServ.ClassesServ.*;

public class TeacherScoreSubPageController {
    @FXML
    private Label classesName;
    @FXML
    private TableView<TeacherScoreSubTable> teacherScoreSubTableView;
    @FXML
    private TableColumn<TeacherScoreSubTable, String> studentIDColumn;
    @FXML
    private TableColumn<TeacherScoreSubTable, String> studentNameColumn;
    @FXML
    private TableColumn<TeacherScoreSubTable, School> studentSchoolColumn;
    @FXML
    private TableColumn<TeacherScoreSubTable, Grade> studentGradeColumn;
    @FXML
    private TableColumn<TeacherScoreSubTable, Integer> studentCurrentScoreColumn;
    @FXML
    private TableColumn<TeacherScoreSubTable, Double> studentCurrentGPAColumn;
    @FXML
    private TableColumn<TeacherScoreSubTable, Button> ButtonColumn;
    @FXML
    private TableColumn<TeacherScoreSubTable, TextField> studentModifiedScoreColumn;
    private Stage primaryStage;
    private String classesCode;
    private Classes classes;
    private IDSet studentSet;
    private String ID;
    private ObservableList<TeacherScoreSubTable> data =  FXCollections.observableArrayList();

    public void setTeacherScoreSubPageController(String buttonId) {
        this.classesCode = buttonId;
        this.classes = getClasses(classesCode);
        this.classesName.setText(classes.getName());
        this.studentSet = getStudentSet(classes.getCode());
    }

    public void setPrimaryStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public void flush() {
        data = getTeacherScoreSubPageObservableList(ID, classes);
    }

    public void initialize() {
        loadTable();
    }

    private void loadTable() {
        studentIDColumn.setCellValueFactory(cellData -> cellData.getValue().studentIDProperty());
        studentNameColumn.setCellValueFactory(cellData -> cellData.getValue().studentNameProperty());
        studentSchoolColumn.setCellValueFactory(cellData -> cellData.getValue().studentSchoolProperty());
        studentGradeColumn.setCellValueFactory(cellData -> cellData.getValue().studentGradeProperty());
        studentCurrentScoreColumn.setCellValueFactory(cellData -> cellData.getValue().studentCurrentScoreProperty().asObject());
        studentCurrentGPAColumn.setCellValueFactory(cellData -> cellData.getValue().studentCurrentGPAProperty().asObject());
        ButtonColumn.setCellValueFactory(cellData -> cellData.getValue().buttonProperty());
        studentModifiedScoreColumn.setCellValueFactory(cellData -> cellData.getValue().studentModifiedScoreProperty());

        teacherScoreSubTableView.setItems(data);
    }

}
