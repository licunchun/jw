package GUI.Controller.Main.Common.Classes;

import GUI.Controller.Components.Time.TimeTableController;
import GUI.Controller.Components.Time.TimeTableTry.Timetable;
import GUI.Data.DataPackage.Classes.Classes;
import GUI.Data.Enum.User.UserType;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import static GUI.GUIUtil.StageUtil.changeViews;
import static GUI.GUIUtil.StageUtil.resetLocation;

public class ClassesChoosingPageController {
    @FXML
    private TextField ClassesCodeField;
    @FXML
    private TextField ClassesNameField;
    @FXML
    private TextField ClassesPeriodField;
    @FXML
    private TextField ClassesCreditField;
    @FXML
    private TextField ClassesCreditField1;
    @FXML
    private ChoiceBox ClassTypeChoiceBox;
    @FXML
    private ChoiceBox CourseTypeChoiceBox;
    @FXML
    private ChoiceBox SchoolChoiceBox;
    @FXML
    private ChoiceBox CampusChoiceBox;
    @FXML
    private ChoiceBox ExamModeChoiceBox;
    @FXML
    private ChoiceBox LanguageChoiceBox;
    @FXML
    private ChoiceBox EducationChoiceBox;
    @FXML
    private CheckBox FullCheckBox;
    @FXML
    private Button TimeButton;
    @FXML
    private Button SearchButton;
    /*
     * Time Table
     */
    private boolean isTimePageExist=false;
    private Stage TimePageStage;
    private TimeTableController timePageController;
    /*
     * Basic Information
     */
    private String ID;
    private UserType userType;
    /*
     * Table Information
     */
    private TableView<ClassesForTable> tableView = new TableView<>();

    private TableColumn<ClassesForTable, String> codeColumn = new TableColumn<>("课堂编号");
    private TableColumn<ClassesForTable, String> nameColumn = new TableColumn<>("课堂名称");
    private TableColumn<ClassesForTable, String> periodColumn = new TableColumn<>("学时");
    private TableColumn<ClassesForTable, String> creditsColumn = new TableColumn<>("学分");
    private TableColumn<ClassesForTable, String> timeColumn = new TableColumn<>("上课时间");
    private TableColumn<ClassesForTable, String> studentColumn = new TableColumn<>("课堂人数");
    private TableColumn<ClassesForTable, String> classTypeColumn = new TableColumn<>("课堂类型");
    private TableColumn<ClassesForTable, String> courseTypeColumn = new TableColumn<>("课程种类");
    private TableColumn<ClassesForTable, String> schoolColumn = new TableColumn<>("院校");
    private TableColumn<ClassesForTable, String> campusColumn = new TableColumn<>("校区");
    private TableColumn<ClassesForTable, String> examModeColumn = new TableColumn<>("考试方式");
    private TableColumn<ClassesForTable, String> languageColumn = new TableColumn<>("教学语言");
    private TableColumn<ClassesForTable, String> educationColumn = new TableColumn<>("教育阶段");
    private TableColumn<ClassesForTable, String> teacherColumn = new TableColumn<>("教师名称");
    private TableColumn<ClassesForTable, String> fullColumn = new TableColumn<>("是否满人");
    //TODO
    /*
     * Else
     */
    private Classes searchClasses=new Classes();
    /*
     * Function
     */
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

            timePageController.setStage(TimePageStage);

            TimePageStage.setOnHiding(e->{
                searchClasses.setTime(timePageController.getTimeSet());
                TimePageStage.close();
                isTimePageExist=false;
            });

            TimePageStage.show();
            TimePageStage.setResizable(false);
            resetLocation(TimePageStage);
        }
        else{
            resetLocation(TimePageStage);
        }
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }

    public void loadTable(){

    }

    public static class ClassesForTable{
        private final SimpleStringProperty code;
        private final SimpleStringProperty name;
        private final SimpleStringProperty period;
        private final SimpleStringProperty credits;
        private final SimpleStringProperty time;
        private final SimpleStringProperty student;
        private final SimpleStringProperty classType;
        private final SimpleStringProperty courseType;
        private final SimpleStringProperty school;
        private final SimpleStringProperty campus;
        private final SimpleStringProperty examMode;
        private final SimpleStringProperty language;
        private final SimpleStringProperty education;
        private final SimpleStringProperty teacher;
        private final SimpleStringProperty full;

        public ClassesForTable(Classes classes) {
            this.code = new SimpleStringProperty(classes.getCode());
            this.name = new SimpleStringProperty(classes.getName());
            this.period = new SimpleStringProperty(classes.getPeriod().toString());
            this.credits = new SimpleStringProperty(classes.getCredits().toString());
            this.time = new SimpleStringProperty(classes.getStringTime());
            this.student=new SimpleStringProperty(Integer.toString(classes.getStdCount())+'/'+
                    classes.getLimitCount());
            this.classType = new SimpleStringProperty(classes.getClassType().toString());
            this.courseType = new SimpleStringProperty(classes.getCourseType().toString());
            this.school = new SimpleStringProperty(classes.getSchool().toString());
            this.campus = new SimpleStringProperty(classes.getCampus().toString());
            this.examMode = new SimpleStringProperty(classes.getExamMode().toString());
            this.language = new SimpleStringProperty(classes.getLanguage().toString());
            this.education = new SimpleStringProperty(classes.getEducation().toString());
            this.teacher = new SimpleStringProperty(classes.getTeacher().getNames());
            this.full = new SimpleStringProperty(classes.getFull().toString());
        }

        public String getCode() {
            return code.get();
        }

        public SimpleStringProperty codeProperty() {
            return code;
        }

        public String getName() {
            return name.get();
        }

        public SimpleStringProperty nameProperty() {
            return name;
        }

        public String getPeriod() {
            return period.get();
        }

        public SimpleStringProperty periodProperty() {
            return period;
        }

        public String getCredits() {
            return credits.get();
        }

        public SimpleStringProperty creditsProperty() {
            return credits;
        }

        public String getTime() {
            return time.get();
        }

        public SimpleStringProperty timeProperty() {
            return time;
        }

        public String getStudent() {
            return student.get();
        }

        public SimpleStringProperty studentProperty() {
            return student;
        }

        public String getClassType() {
            return classType.get();
        }

        public SimpleStringProperty classTypeProperty() {
            return classType;
        }

        public String getCourseType() {
            return courseType.get();
        }

        public SimpleStringProperty courseTypeProperty() {
            return courseType;
        }

        public String getSchool() {
            return school.get();
        }

        public SimpleStringProperty schoolProperty() {
            return school;
        }

        public String getCampus() {
            return campus.get();
        }

        public SimpleStringProperty campusProperty() {
            return campus;
        }

        public String getExamMode() {
            return examMode.get();
        }

        public SimpleStringProperty examModeProperty() {
            return examMode;
        }

        public String getLanguage() {
            return language.get();
        }

        public SimpleStringProperty languageProperty() {
            return language;
        }

        public String getEducation() {
            return education.get();
        }

        public SimpleStringProperty educationProperty() {
            return education;
        }

        public String getTeacher() {
            return teacher.get();
        }

        public SimpleStringProperty teacherProperty() {
            return teacher;
        }

        public String getFull() {
            return full.get();
        }

        public SimpleStringProperty fullProperty() {
            return full;
        }
    }
}
