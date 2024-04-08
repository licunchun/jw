package GUI.Controller.Main.Student;

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
    private final TableView<StudentCourseScoreTable> CourseTableView = new TableView<>();
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

    public void flush() {
        data = getStudentCourseCodeSet(ID).toGradeObservableList();
        TotalCredits.setText(Double.toString(getStudentTotalCredits(ID)));
        ReceivedCredits.setText(Double.toString(getStudentReceivedCredits(ID)));
        FailedCredits.setText(Double.toString(getStudentFailedCredits(ID)));
        GPA.setText(Double.toString(getStudentGPA(ID)));
        WeightAverageScore.setText(Double.toString(getStudentWeightedAverageGrade(ID)));
        ArithmeticAverageScore.setText(Double.toString(getStudentAverageGrade(ID)));
    }

    private void loadTable() {
        nameColumn.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
        periodColumn.setCellValueFactory(cellData -> cellData.getValue().periodProperty().asObject());
        creditsColumn.setCellValueFactory(cellData -> cellData.getValue().creditsProperty().asObject());
        GPAColumn.setCellValueFactory(cellData -> cellData.getValue().GPAProperty().asObject());
        scoreColumn.setCellValueFactory(cellData -> cellData.getValue().scoreProperty().asObject());

        ClassesSet classesSet = getStudentClassesSet(ID);
        Iterable<Classes> classesSetIterable = classesSet.getClassesIterable();

        for (Classes studentClass : classesSetIterable) {
            String code = studentClass.getCode();
            int score = getStudentGrade(code, ID);
            double GPA = getStudentGPA(code, ID);
            StudentCourseScoreTable newData = new StudentCourseScoreTable(studentClass, GPA, score);
            data.add(newData);
        }
        CourseTableView.setItems(data);
        flush();
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