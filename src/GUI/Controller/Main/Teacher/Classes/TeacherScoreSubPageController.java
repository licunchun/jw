package GUI.Controller.Main.Teacher.Classes;

import GUI.Data.DataPackage.Classes.Classes;
import GUI.Data.DataPackage.Classes.IDSet;
import GUI.Data.DataPackage.Classes.TeacherScoreSubTable;
import GUI.Data.Enum.School;
import GUI.Data.Enum.User.Grade;
import javafx.animation.PauseTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.Objects;

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
        ButtonColumn.setCellFactory(column -> new TableCell<>() {
            @Override
            protected void updateItem(Button button, boolean empty) {
                super.updateItem(button, empty);
                if(button == null | empty) {
                    setGraphic(null);
                }
                else {
                    TeacherScoreSubTable rowData = getTableRow().getItem();

                    if (rowData != null) {
                        String studentID = rowData.getStudentID();
                        int newScore = Integer.parseInt(Objects.requireNonNull(findTextField(studentID)).getText());
                        setStudentScore(classesCode, studentID, newScore);
                        Objects.requireNonNull(findTextField(studentID)).setText("");

                        // 创建一个延迟对象，持续 1 秒钟
                        PauseTransition delay = new PauseTransition(Duration.seconds(0.1));
                        delay.setOnFinished(event -> {
                            // 延迟结束后将按钮状态恢复为未点击状态
                            button.setDisable(false);
                        });
                        // 启动延迟
                        button.setDisable(true);
                        delay.play();
                    }
                }
            }
        });
    }
    private TextField findTextField(String ID) {
        for (TeacherScoreSubTable item : data) {
            if (ID.equals(item.getStudentID())) {
                return item.getStudentModifiedScore(); // 假设每个 TeacherScoreSubTable 对象中有一个名为 studentModifiedScore 的 TextField
            }
        }
        return null;
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
