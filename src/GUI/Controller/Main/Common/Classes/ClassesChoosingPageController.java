package GUI.Controller.Main.Common.Classes;

import GUI.Controller.Components.Time.TimeTableTry.Timetable;
import GUI.Data.DataPackage.Classes.Classes;
import GUI.Data.Enum.User.UserType;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import static GUI.GUIUtil.StageUtil.changeViews;

public class ClassesChoosingPageController {
    @FXML
    public TextField ClassesCodeField;
    @FXML
    public TextField ClassesNameField;
    @FXML
    public TextField ClassesPeriodField;
    @FXML
    public TextField ClassesCreditField;
    @FXML
    public TextField ClassesCreditField1;
    @FXML
    public ChoiceBox ClassTypeChoiceBox;
    @FXML
    public ChoiceBox CourseTypeChoiceBox;
    @FXML
    public ChoiceBox SchoolChoiceBox;
    @FXML
    public ChoiceBox CampusChoiceBox;
    @FXML
    public ChoiceBox ExamModeChoiceBox;
    @FXML
    public ChoiceBox LanguageChoiceBox;
    @FXML
    public ChoiceBox EducationChoiceBox;
    @FXML
    public CheckBox FullCheckBox;
    @FXML
    public Button TimeButton;
    @FXML
    public Button SearchButton;
    private boolean isTimePageExist=false;
    private Stage TimePageStage;
    private Timetable timePageController;
    private String ID;
    private UserType userType;
    private Classes searchClasses=new Classes();
    @FXML
    private void initialize(){

    }
    @FXML
    private void doSearch(){

    }
    @FXML
    private void openTimePage(){
        if(!isTimePageExist){
            isTimePageExist=true;
            TimePageStage=new Stage();

            timePageController=changeViews(TimePageStage,"/GUI/Window/Components/Time/TimeTable.fxml");

        }
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }
}
