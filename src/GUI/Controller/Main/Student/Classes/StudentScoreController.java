package GUI.Controller.Main.Student.Classes;

import GUI.Data.DataPackage.Classes.Classes;
import GUI.Data.DataPackage.Classes.ClassesSet;
import GUI.Data.DataPackage.Classes.StudentCourseScoreTable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;

import static Sevice.Main.Components.ClassServ.ClassesServ.getStudentGPA;
import static Sevice.Main.Components.ClassServ.ClassesServ.getStudentGrade;
import static Sevice.Main.Student.ClassesServ.StudentClassesServ.getStudentAverageGrade;
import static Sevice.Main.Student.ClassesServ.StudentClassesServ.getStudentClassesSet;
import static Sevice.Main.Student.ClassesServ.StudentClassesServ.getStudentCourseCodeSet;
import static Sevice.Main.Student.ClassesServ.StudentClassesServ.getStudentFailedCredits;
import static Sevice.Main.Student.ClassesServ.StudentClassesServ.getStudentGPA;
import static Sevice.Main.Student.ClassesServ.StudentClassesServ.getStudentReceivedCredits;
import static Sevice.Main.Student.ClassesServ.StudentClassesServ.getStudentTotalCredits;
import static Sevice.Main.Student.ClassesServ.StudentClassesServ.getStudentWeightedAverageGrade;

public class StudentScoreController {
    @FXML
    private TableView<StudentCourseScoreTable> CourseTableView = new TableView<>();
    @FXML
    private TableColumn<StudentCourseScoreTable, String> nameColumn = new TableColumn<>("课程");
    @FXML
    private TableColumn<StudentCourseScoreTable, Integer> periodColumn = new TableColumn<>("学时");
    @FXML
    private TableColumn<StudentCourseScoreTable, Double> creditsColumn = new TableColumn<>("学分");
    @FXML
    private TableColumn<StudentCourseScoreTable, Double> GPAColumn = new TableColumn<>("绩点");
    @FXML
    private TableColumn<StudentCourseScoreTable, Integer> scoreColumn = new TableColumn<>("成绩");
    @FXML
    private Label TotalCredits;//总学分
    @FXML
    private Label ReceivedCredits;//已获学分
    @FXML
    private Label FailedCredits;//不及格学分
    @FXML
    private Label GPA;//绩点
    @FXML
    private Label WeightAverageScore;//加权平均分
    @FXML
    private Label ArithmeticAverageScore;//算数平均分
    private ObservableList<StudentCourseScoreTable> data = FXCollections.observableArrayList();//用于表格的展示的ObservableList
    private String ID;

    public void setID(String ID) {
        this.ID = ID;
    }

    public void initialize() {
        loadTable();
    }
    private void setLabel() {
        if(getStudentTotalCredits(ID) != -1) {
            TotalCredits.setText(Double.toString(getStudentTotalCredits(ID)));
        }
        else {
            TotalCredits.setText("NaN");
        }
        if(getStudentReceivedCredits(ID) != -1) {
            ReceivedCredits.setText(Double.toString(getStudentReceivedCredits(ID)));
        }
        else {
            ReceivedCredits.setText("NaN");
        }
        if(getStudentFailedCredits(ID) != -1) {
            FailedCredits.setText(Double.toString(getStudentFailedCredits(ID)));
        }
        else {
            FailedCredits.setText("NaN");
        }
        if(getStudentGPA(ID) != -1) {
            GPA.setText(Double.toString(getStudentGPA(ID)));
        }
        else {
            GPA.setText("NaN");
        }
        if(getStudentWeightedAverageGrade(ID) != -1) {
            WeightAverageScore.setText(Double.toString(getStudentWeightedAverageGrade(ID)));
        }
        else {
            WeightAverageScore.setText("NaN");
        }
        if(getStudentAverageGrade(ID) != -1) {
            ArithmeticAverageScore.setText(Double.toString(getStudentAverageGrade(ID)));
        }
        else {
            ArithmeticAverageScore.setText("NaN");
        }
    }

    public void flush() {
        data = getStudentClassesSet(ID).toGradeObservableList(ID);
        setLabel();
    }

    private void loadTable() {
        nameColumn.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
        periodColumn.setCellValueFactory(cellData -> cellData.getValue().periodProperty().asObject());
        creditsColumn.setCellValueFactory(cellData -> cellData.getValue().creditsProperty().asObject());
        GPAColumn.setCellValueFactory(cellData -> cellData.getValue().GPAProperty().asObject());
        scoreColumn.setCellValueFactory(cellData -> cellData.getValue().scoreProperty().asObject());

        flush();
        CourseTableView.setItems(data);
    }

    public ContextMenu studentScoreContextMenu() {
        ContextMenu contextMenu = new ContextMenu();
        MenuItem flushMenuItem = new MenuItem("刷新");

        flushMenuItem.setAccelerator(new KeyCodeCombination(KeyCode.R, KeyCodeCombination.CONTROL_DOWN));

        flushMenuItem.setOnAction(event -> flush());

        contextMenu.getItems().addAll(flushMenuItem);

        return contextMenu;
    }
}