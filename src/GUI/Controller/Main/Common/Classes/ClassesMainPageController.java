package GUI.Controller.Main.Common.Classes;

import GUI.Data.Enum.User.UserType;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class ClassesMainPageController {//TODO
    @FXML
    private Label CodeLabel;
    @FXML
    private Label NameLabel;
    @FXML
    private Label PeriodLabel;
    @FXML
    private Label CreditsLabel;
    @FXML
    private Label TimeLabel;
    @FXML
    private Label NumberLabel;
    @FXML
    private Label ClassesTypeLabel;
    @FXML
    private Label CourseTypeLabel;
    @FXML
    private Label SchoolLabel;
    @FXML
    private Label CampusLabel;
    @FXML
    private Label ExamModeLabel;
    @FXML
    private Label LanguageLabel;
    @FXML
    private Label EducationLabel;
    @FXML
    private Label TeacherLabel;
    @FXML
    private Button ClickButton;
    @FXML
    private Button BackButton;
    @FXML
    //Page Information
    private Stage stage;
    private String ID;
    private UserType userType;
    private String classesCode;
    /*
     * Functions
     */
    @FXML
    private void initialize(){

    }
    @FXML
    private void doClick(){

    }
    @FXML
    private void doBack(){
        stage.close();
    }

    private void pickClasses(){

    }
    private void dropClasses(){

    }
    private void deleteClasses(){

    }

    public void flush(){

    }//TODO
    /*
     * Getter&Setter
     */

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }

    public void setClassesCode(String classesCode) {
        this.classesCode = classesCode;
    }

    public String getClassesCode() {
        return classesCode;
    }
}
