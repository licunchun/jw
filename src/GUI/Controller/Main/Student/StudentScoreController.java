package GUI.Controller.Main.Student;

import Data.Type.Student;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class StudentScoreController {
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
    private TableView<Coursetable> CourseTable;
    @FXML
    TableColumn<Coursetable, String> courseCol;
    @FXML
    TableColumn<Coursetable, Integer> periodCol;
    @FXML
    TableColumn<Coursetable, Integer> creditCol;
    @FXML
    TableColumn<Coursetable, Double> gpaCol;
    @FXML
    TableColumn<Coursetable, Integer> scoreCol;

    public void initialize() {
        Coursetable[] courses = {
                new Coursetable("Course 1", 120, 3, 3.5, 85),
                new Coursetable("Course 2", 80, 4, 4.0, 92),
                new Coursetable("Course 3", 60, 2, 3.2, 78),
                new Coursetable("Course 4", 80, 3, 3.8, 88),
                new Coursetable("Course 5", 40, 3, 3.6, 86)
        };

        courseCol.setCellValueFactory(new PropertyValueFactory<>("course"));
        periodCol.setCellValueFactory(new PropertyValueFactory<>("period"));
        creditCol.setCellValueFactory(new PropertyValueFactory<>("credit"));
        gpaCol.setCellValueFactory(new PropertyValueFactory<>("gpa"));
        scoreCol.setCellValueFactory(new PropertyValueFactory<>("score"));

        CourseTable.getColumns().addAll(courseCol, periodCol, creditCol, gpaCol, scoreCol);

        CourseTable.setItems(FXCollections.observableArrayList(courses));
    }
}
class Coursetable {
    private final String Course;
    private final int Period;
    private final int Credit;
    private final double GPA;
    private final int Score;

    public Coursetable(String Course, int Period, int Credit, double GPA, int Score) {
        this.Course = Course;
        this.Period = Period;
        this.Credit = Credit;
        this.GPA = GPA;
        this.Score = Score;
    }

    public String getCourse() {
        return Course;
    }

    public int getPeriod() {
        return Period;
    }

    public int getCredit() {
        return Credit;
    }

    public double getGPA() {
        return GPA;
    }

    public int getScore() {
        return Score;
    }
}