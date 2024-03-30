package GUI.Controller.Main.Student;

import Data.Type.Student;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
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
    TableColumn<String, String> Courses = new TableColumn<>();
    TableColumn<String, String> Periods = new TableColumn<>();
    TableColumn<String, String> Credits = new TableColumn<>();
    TableColumn<String, String> GPAs = new TableColumn<>();
    TableColumn<String, String> Scores = new TableColumn<>();
    public void init(Student student){

    }
}
