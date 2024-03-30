package GUI.Controller.Main.Student;

import Data.Type.Student;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;

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
    private TableView CourseTable;
    @FXML
    ObservableList<String> Courses = FXCollections.observableArrayList();
    @FXML
    ObservableList<String> Periods = FXCollections.observableArrayList();
    @FXML
    ObservableList<String> Cridits = FXCollections.observableArrayList();
    @FXML
    ObservableList<String> GPAs = FXCollections.observableArrayList();
    @FXML
    ObservableList<String> Scores = FXCollections.observableArrayList();
    public void init(Student student){

    }
}
