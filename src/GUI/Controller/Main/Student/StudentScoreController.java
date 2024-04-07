package GUI.Controller.Main.Student;

import GUI.Data.DataPackage.Classes.Classes;
import GUI.Data.DataPackage.Classes.ClassesSet;
import GUI.Data.DataPackage.Classes.CourseCodeSet;
import GUI.Data.DataPackage.Classes.StudentCourseScoreTable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.text.DecimalFormat;

import static Sevice.Main.Components.ClassServ.ClassesServ.*;
import static Sevice.Main.Student.ClassesServ.StudentClassesServ.getStudentClassesSet;

public class StudentScoreController {
    private final TableView<StudentCourseScoreTable> CourseTableView = new TableView<>();
    private TableColumn<StudentCourseScoreTable, String> nameColumn = new TableColumn<>("课程");
    private TableColumn<StudentCourseScoreTable, Integer> periodColumn = new TableColumn<>("学时");
    private TableColumn<StudentCourseScoreTable, Double> creditsColumn = new TableColumn<>("学分");
    private TableColumn<StudentCourseScoreTable, Double> GPAColumn = new TableColumn<>("绩点");
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

    private int countClasses = 0;
    private double totalCredits = 0, receivedCredits = 0, failedCredits = 0;
    private double sumGPA = 0, weightAverageScore = 0, arithmeticAverageScore = 0, averageGPA;
    DecimalFormat decimalFormat1 = new DecimalFormat("#.0");
    DecimalFormat decimalFormat2 = new DecimalFormat("#.00");
    String formattedTotalCredits, formattedReceivedCredits, formattedFailedCredits, formattedGPA, formattedWeightAverageScore, formattedArithmeticAverageScore;
    public void setID(String ID) {
        this.ID = ID;
    }
    public void initialize() {
//        CourseTable[] courses = {
//                new CourseTable("Course 1", 120, 3, 3.5, 85),
//                new CourseTable("Course 2", 80, 4, 4.0, 92),
//                new CourseTable("Course 3", 60, 2, 3.2, 78),
//                new CourseTable("Course 4", 80, 3, 3.8, 88),
//                new CourseTable("Course 5", 40, 3, 3.6, 86)
//        };
//
//        courseCol.setCellValueFactory(cellData -> cellData.getValue().courseProperty());
//        periodCol.setCellValueFactory(new PropertyValueFactory<>("period"));
//        creditCol.setCellValueFactory(new PropertyValueFactory<>("credit"));
//        gpaCol.setCellValueFactory(new PropertyValueFactory<>("gpa"));
//        scoreCol.setCellValueFactory(new PropertyValueFactory<>("score"));
//
//        CourseTableView.getColumns().addAll(courseCol, periodCol, creditCol, gpaCol, scoreCol);
//
//        CourseTableView.setItems(FXCollections.observableArrayList(courses));
        loadTable();
    }
    public void flush() {
        data.clear();

        countClasses = 0;
        totalCredits = 0;
        receivedCredits = 0;
        failedCredits = 0;
        sumGPA = 0;
        weightAverageScore = 0;
        arithmeticAverageScore = 0;
        averageGPA = 0;

        TotalCredits.setId("");
        ReceivedCredits.setId("");
        FailedCredits.setId("");
        GPA.setId("");
        WeightAverageScore.setId("");
        ArithmeticAverageScore.setId("");
    }
    private void loadTable() {
        flush();

        CourseTableView.setPrefWidth(1280);
        CourseTableView.setPrefHeight(560);

        nameColumn.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
        periodColumn.setCellValueFactory(cellData -> cellData.getValue().periodProperty().asObject());
        creditsColumn.setCellValueFactory(cellData -> cellData.getValue().creditProperty().asObject());
        GPAColumn.setCellValueFactory(cellData -> cellData.getValue().GPAProperty().asObject());
        scoreColumn.setCellValueFactory(cellData -> cellData.getValue().scoreProperty().asObject());

        ClassesSet classesSet = getStudentClassesSet(ID);
        Iterable<Classes> classesSetIterable = classesSet.getClassesIterable();

        for(Classes studentClass : classesSetIterable) {
            String code = studentClass.getCode();
            int score = getStudentGrade(code, ID);
            double GPA = getStudentGPA(code, ID);
            StudentCourseScoreTable newData = new StudentCourseScoreTable(studentClass, GPA, score);
            data.add(newData);

            double credits = studentClass.getCredits();
            totalCredits = totalCredits + credits;
            if(score >= 60) {
                receivedCredits = receivedCredits + credits;
            }
            else {
                failedCredits = failedCredits + credits;
            }
            sumGPA = sumGPA + GPA * credits;
            weightAverageScore = weightAverageScore + score * credits;
            arithmeticAverageScore = arithmeticAverageScore + score;
            countClasses = countClasses + 1;
        }

        averageGPA = sumGPA / countClasses;
        weightAverageScore /= totalCredits;
        arithmeticAverageScore /=countClasses;
        formattedTotalCredits = decimalFormat1.format(totalCredits);
        formattedReceivedCredits = decimalFormat1.format(receivedCredits);
        formattedFailedCredits = decimalFormat1.format(failedCredits);
        formattedGPA = decimalFormat2.format(averageGPA);
        formattedWeightAverageScore = decimalFormat2.format(weightAverageScore);
        formattedArithmeticAverageScore = decimalFormat2.format(arithmeticAverageScore);

        TotalCredits.setId(formattedTotalCredits);
        ReceivedCredits.setId(formattedReceivedCredits);
        FailedCredits.setId(formattedFailedCredits);
        GPA.setId(formattedGPA);
        WeightAverageScore.setId(formattedWeightAverageScore);
        ArithmeticAverageScore.setId(formattedArithmeticAverageScore);

        CourseTableView.setItems(data);

        CourseTableView.getColumns().addAll(
                nameColumn,
                periodColumn,
                creditsColumn,
                GPAColumn,
                scoreColumn
        );
    }
}