package GUI.Controller.Main.Student;

import GUI.Data.DataPackage.Classes.CourseTable;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class StudentScoreController {
    @FXML
    TableColumn<CourseTable, String> nameColumn;
    @FXML
    TableColumn<CourseTable, Integer> periodColumn;
    @FXML
    TableColumn<CourseTable, Double> creditsColumn;
    @FXML
    TableColumn<CourseTable, Double> GPAColumn;
    @FXML
    TableColumn<CourseTable, Integer> scoreColumn;
    @FXML
    private Label TotalCredits;
    @FXML
    private Label ReceivedCredits;
    @FXML
    private Label FailedCredits;
    @FXML
    private Label GPA;
    @FXML
    private Label WeightAverageScore;
    @FXML
    private Label ArithmeticAverageScore;
    @FXML
    private TableView<CourseTable> CourseTableView;

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
    }
    private void loadTable() {
        CourseTableView.setPrefWidth(1280);
        CourseTableView.setPrefHeight(560);

        nameColumn.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
        periodColumn.setCellValueFactory(cellData -> cellData.getValue().periodProperty().asObject());
    }
}