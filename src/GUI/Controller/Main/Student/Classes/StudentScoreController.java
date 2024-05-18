package GUI.Controller.Main.Student.Classes;

import GUI.Data.DataPackage.Classes.StudentCourseScoreTable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;

import static GUI.Data.Util.Classes.ObservableListUtil.getStudentScoreObservableList;
import static Service.Main.Student.ClassesServ.StudentClassesServ.*;

public class StudentScoreController {
    @FXML
    private TableView<StudentCourseScoreTable> CourseTableView;
    @FXML
    private TableColumn<StudentCourseScoreTable, String> nameColumn;
    @FXML
    private TableColumn<StudentCourseScoreTable, Integer> periodColumn;
    @FXML
    private TableColumn<StudentCourseScoreTable, Double> creditsColumn;
    @FXML
    private TableColumn<StudentCourseScoreTable, Double> GPAColumn;
    @FXML
    private TableColumn<StudentCourseScoreTable, Integer> scoreColumn;
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


    public void initialize() {
        loadTable();
    }

    private void setLabel() {
        if (getStudentTotalCredits(ID) != -1) {
            TotalCredits.setText(Double.toString(getStudentTotalCredits(ID)));
        } else {
            TotalCredits.setText("NaN");
        }
        if (getStudentReceivedCredits(ID) != -1) {
            ReceivedCredits.setText(Double.toString(getStudentReceivedCredits(ID)));
        } else {
            ReceivedCredits.setText("NaN");
        }
        if (getStudentFailedCredits(ID) != -1) {
            FailedCredits.setText(Double.toString(getStudentFailedCredits(ID)));
        } else {
            FailedCredits.setText("NaN");
        }
        if (getStudentGPA(ID) != -1) {
            GPA.setText(Double.toString(getStudentGPA(ID)));
        } else {
            GPA.setText("NaN");
        }
        if (getStudentWeightedAverageGrade(ID) != -1) {
            WeightAverageScore.setText(Double.toString(getStudentWeightedAverageGrade(ID)));
        } else {
            WeightAverageScore.setText("NaN");
        }
        if (getStudentAverageGrade(ID) != -1) {
            ArithmeticAverageScore.setText(Double.toString(getStudentAverageGrade(ID)));
        } else {
            ArithmeticAverageScore.setText("NaN");
        }
    }

    public void flush() {
        data = getStudentScoreObservableList(ID);
        setLabel();
    }

    private void loadTable() {
        nameColumn.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
        periodColumn.setCellValueFactory(cellData -> cellData.getValue().periodProperty().asObject());
        creditsColumn.setCellValueFactory(cellData -> cellData.getValue().creditsProperty().asObject());
        GPAColumn.setCellValueFactory(cellData -> cellData.getValue().GPAProperty().asObject());
        scoreColumn.setCellValueFactory(cellData -> cellData.getValue().scoreProperty().asObject());

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

    public void setID(String ID) {
        this.ID = ID;
    }
}