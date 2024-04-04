package GUI.Controller.Main.Common.Classes;

import GUI.Controller.Components.Time.TimeTableController;
import GUI.Data.DataPackage.Classes.Classes;
import GUI.Data.DataPackage.Classes.ClassesForTable;
import GUI.Data.Enum.Classes.*;
import GUI.Data.Enum.Classes.EnumForClassesSearching.*;
import GUI.Data.Enum.User.UserType;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.StringConverter;

import static GUI.Data.Enum.ObservableList.ClassesChoosingObservableList.*;
import static GUI.GUIUtil.StageUtil.changeViews;
import static GUI.GUIUtil.StageUtil.resetLocation;
import static Sevice.Main.Components.ClassServ.ClassesServ.searchClasses;
import static Sevice.Main.Components.UserServ.UserServ.findTeacher;

public class ClassesChoosingPageController {
    @FXML
    private AnchorPane TableViewPane;
    @FXML
    private TextField ClassesCodeField;
    @FXML
    private TextField ClassesNameField;
    @FXML
    private TextField ClassesPeriodField;
    @FXML
    private TextField ClassesCreditField;
    @FXML
    private TextField TeacherNameField;
    @FXML
    private ChoiceBox<C_ClassType> ClassTypeChoiceBox;
    @FXML
    private ChoiceBox<C_CourseType> CourseTypeChoiceBox;
    @FXML
    private ChoiceBox<C_School> SchoolChoiceBox;
    @FXML
    private ChoiceBox<C_Campus> CampusChoiceBox;
    @FXML
    private ChoiceBox<C_ExamMode> ExamModeChoiceBox;
    @FXML
    private ChoiceBox<C_Language> LanguageChoiceBox;
    @FXML
    private ChoiceBox<C_Education> EducationChoiceBox;
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

    private TableColumn<ClassesForTable, Void> codeColumn = new TableColumn<>("课堂编号");
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
    private Pagination pagination;
    private static final int ROWS_PER_PAGE=20;//每页最多有多少行
    /*
     * Classes Main Page
     */
    private boolean isClassesMainPageExist=false;
    private Stage classesMainPageStage;
    private ClassesMainPageController classesMainPageController;
    /*
     * Else
     */
    private Classes searchingClasses=new Classes();//用于搜索的Classes
    private ObservableList<ClassesForTable> data= FXCollections.observableArrayList();//用于表格的展示的ObservableList
    /*
     * Function
     */
    @FXML
    private void initialize(){
        choiceBoxInitialize();
        loadTable();
    }
    @FXML
    private void doSearch(){
        {
            searchingClasses.setCode(ClassesCodeField.getText());
            searchingClasses.setName(ClassesNameField.getText());
            try{
                Integer m_period=Integer.valueOf(ClassesPeriodField.getText());
                searchingClasses.setPeriod(m_period);
            }catch (NumberFormatException e){
                searchingClasses.setPeriod(null);
            }
            try{
                Double m_credits=Double.valueOf(ClassesCreditField.getText());
                searchingClasses.setCredits(m_credits);
            }catch (NumberFormatException e){
                searchingClasses.setCredits(null);
            }
            searchingClasses.setTeacher(findTeacher(ClassesCodeField.getText()));
            searchingClasses.setClassType(ClassTypeChoiceBox.getValue().toClassType());
            searchingClasses.setCourseType(CourseTypeChoiceBox.getValue().toCourseType());
            searchingClasses.setSchool(SchoolChoiceBox.getValue().toSchool());
            searchingClasses.setCampus(CampusChoiceBox.getValue().toCampus());
            searchingClasses.setExamMode(ExamModeChoiceBox.getValue().toExamMode());
            searchingClasses.setLanguage(LanguageChoiceBox.getValue().toLanguage());
            searchingClasses.setEducation(EducationChoiceBox.getValue().toEducation());
            if(FullCheckBox.isSelected()){
                searchingClasses.setFull(Full.NotFull);
            }
            else{
                searchingClasses.setFull(null);
            }
        }//读取参数
        flush();
    }
    @FXML
    private void openTimePage(){
        if(!isTimePageExist){
            isTimePageExist=true;
            TimePageStage=new Stage();

            timePageController=changeViews(TimePageStage,"/GUI/Window/Components/Time/TimeTable.fxml");

            timePageController.setStage(TimePageStage);

            TimePageStage.setOnHiding(e->{
                searchingClasses.setTime(timePageController.getTimeSet());
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
        tableView.setPrefWidth(1280);
        tableView.setPrefHeight(560);

        {
            nameColumn.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
            periodColumn.setCellValueFactory(cellData -> cellData.getValue().periodProperty());
            creditsColumn.setCellValueFactory(cellData -> cellData.getValue().creditsProperty());
            timeColumn.setCellValueFactory(cellData -> cellData.getValue().timeProperty());
            studentColumn.setCellValueFactory(cellData -> cellData.getValue().studentProperty());
            classTypeColumn.setCellValueFactory(cellData -> cellData.getValue().classTypeProperty());
            courseTypeColumn.setCellValueFactory(cellData -> cellData.getValue().courseTypeProperty());
            schoolColumn.setCellValueFactory(cellData -> cellData.getValue().schoolProperty());
            campusColumn.setCellValueFactory(cellData -> cellData.getValue().campusProperty());
            examModeColumn.setCellValueFactory(cellData -> cellData.getValue().examModeProperty());
            languageColumn.setCellValueFactory(cellData -> cellData.getValue().languageProperty());
            educationColumn.setCellValueFactory(cellData -> cellData.getValue().educationProperty());
            teacherColumn.setCellValueFactory(cellData -> cellData.getValue().teacherProperty());
            fullColumn.setCellValueFactory(cellData -> cellData.getValue().fullProperty());
        }//设置表格列与数据对象的属性关联
        {
            codeColumn.setCellFactory(column-> new TableCell<>() {
                private final Hyperlink hyperlink = new Hyperlink(getTableView().getItems().get(getIndex()).getCode());

                {
                    hyperlink.setOnAction(event -> {
                        isClassesMainPageExist=true;
                        openClassesMainPage(getTableView().getItems().get(getIndex()).getCode());
                        resetLocation(classesMainPageStage);
                    });
                }//超链接点击事件

                @Override
                protected void updateItem(Void item, boolean empty) {
                    super.updateItem(item, empty);
                    if (empty) {
                        setGraphic(null);
                    } else {
                        setGraphic(hyperlink);
                    }
                }
            });
            nameColumn.setCellValueFactory(new PropertyValueFactory<>("课堂名称"));
            periodColumn.setCellValueFactory(new PropertyValueFactory<>("学时"));
            creditsColumn.setCellValueFactory(new PropertyValueFactory<>("学分"));
            timeColumn.setCellValueFactory(new PropertyValueFactory<>("上课时间"));
            studentColumn.setCellValueFactory(new PropertyValueFactory<>("课堂人数"));
            classTypeColumn.setCellValueFactory(new PropertyValueFactory<>("课堂类型"));
            courseTypeColumn.setCellValueFactory(new PropertyValueFactory<>("课程种类"));
            schoolColumn.setCellValueFactory(new PropertyValueFactory<>("院校"));
            campusColumn.setCellValueFactory(new PropertyValueFactory<>("校区"));
            examModeColumn.setCellValueFactory(new PropertyValueFactory<>("考试方式"));
            languageColumn.setCellValueFactory(new PropertyValueFactory<>("教学语言"));
            educationColumn.setCellValueFactory(new PropertyValueFactory<>("教育阶段"));
            teacherColumn.setCellValueFactory(new PropertyValueFactory<>("教师名称"));
            fullColumn.setCellValueFactory(new PropertyValueFactory<>("是否满人"));
        }//设置自定义格式工厂

        flush();//刷新数据
        tableView.setItems(data);
        tableView.getColumns().addAll(
                codeColumn,
                nameColumn,
                periodColumn,
                creditsColumn,
                timeColumn,
                studentColumn,
                classTypeColumn,
                courseTypeColumn,
                schoolColumn,
                campusColumn,
                examModeColumn,
                languageColumn,
                educationColumn,
                teacherColumn,
                fullColumn
                );

        {
            pagination = new Pagination((tableView.getItems().size() - 1) / ROWS_PER_PAGE + 1);
            pagination.setPageFactory(pageIndex -> {
                int fromIndex = pageIndex * ROWS_PER_PAGE;
                int toIndex = Math.min(fromIndex + ROWS_PER_PAGE, data.size());
                tableView.setItems(FXCollections.observableArrayList(data.subList(fromIndex, toIndex)));
                return new VBox(tableView);
            });
            pagination.setPrefWidth(1280);
            pagination.setPrefHeight(600);
        }//翻页
        TableViewPane.getChildren().add(pagination);
    }

    public void flush(){
        data=searchClasses(searchingClasses).toOBservableList();
    }

    private void choiceBoxInitialize(){
        {
            ClassTypeChoiceBox.setValue(C_ClassType.None);
            ClassTypeChoiceBox.setConverter(new StringConverter<>() {
                @Override
                public String toString(C_ClassType c_classType) {
                    return c_classType.toString();
                }

                @Override
                public C_ClassType fromString(String s) {
                    return null;
                }
            });

            ClassTypeChoiceBox.setItems(ClassTypeList);
        }//ClassTypeChoiceBox
        {
            CourseTypeChoiceBox.setValue(C_CourseType.None);
            CourseTypeChoiceBox.setConverter(new StringConverter<>() {
                @Override
                public String toString(C_CourseType c_courseType) {
                    return c_courseType.toString();
                }

                @Override
                public C_CourseType fromString(String s) {
                    return null;
                }
            });

            CourseTypeChoiceBox.setItems(CourseTypeList);
        }//CourseTypeChoiceBox
        {
            SchoolChoiceBox.setValue(C_School.None);
            SchoolChoiceBox.setConverter(new StringConverter<>() {
                @Override
                public String toString(C_School c_school) {
                    return c_school.toString();
                }

                @Override
                public C_School fromString(String s) {
                    return null;
                }
            });

            SchoolChoiceBox.setItems(SchoolList);
        }//SchoolChoiceBox
        {
            CampusChoiceBox.setValue(C_Campus.None);
            CampusChoiceBox.setConverter(new StringConverter<>() {
                @Override
                public String toString(C_Campus c_campus) {
                    return c_campus.toString();
                }

                @Override
                public C_Campus fromString(String s) {
                    return null;
                }
            });

            CampusChoiceBox.setItems(CampusList);
        }//CampusChoiceBox
        {
            ExamModeChoiceBox.setValue(C_ExamMode.None);
            ExamModeChoiceBox.setConverter(new StringConverter<>() {
                @Override
                public String toString(C_ExamMode c_examMode) {
                    return c_examMode.toString();
                }

                @Override
                public C_ExamMode fromString(String s) {
                    return null;
                }
            });

            ExamModeChoiceBox.setItems(ExamModeList);
        }//ExamModeChoiceBox
        {
            LanguageChoiceBox.setValue(C_Language.None);
            LanguageChoiceBox.setConverter(new StringConverter<>() {
                @Override
                public String toString(C_Language c_language) {
                    return c_language.toString();
                }

                @Override
                public C_Language fromString(String s) {
                    return null;
                }
            });

            LanguageChoiceBox.setItems(LanguageList);
        }//LanguageChoiceBox
        {
            EducationChoiceBox.setValue(C_Education.None);
            EducationChoiceBox.setConverter(new StringConverter<>() {
                @Override
                public String toString(C_Education c_education) {
                    return c_education.toString();
                }

                @Override
                public C_Education fromString(String s) {
                    return null;
                }
            });

            EducationChoiceBox.setItems(EducationList);
        }//EducationChoiceBox
    }

    public ContextMenu classesChoosingPageContextMenu(){
        ContextMenu contextMenu=new ContextMenu();
        MenuItem flushMenuItem = new MenuItem("刷新");

        flushMenuItem.setAccelerator(new KeyCodeCombination(KeyCode.R, KeyCodeCombination.CONTROL_DOWN));

        flushMenuItem.setOnAction(event-> flush());

        contextMenu.getItems().addAll(flushMenuItem);

        return contextMenu;
    }

    private void openClassesMainPage(String classesCode){
        if(!isTimePageExist){
            classesMainPageStage=new Stage();

            classesMainPageController=changeViews(classesMainPageStage, "/GUI/Window/Main/Common/Classes/ClassesMainPage.fxml");

            classesMainPageController.setStage(classesMainPageStage);
            classesMainPageController.setID(ID);
            classesMainPageController.setUserType(userType);
            classesMainPageController.setClassesCode(classesCode);
            classesMainPageController.flush();

            classesMainPageStage.setOnHiding(e->{
                isClassesMainPageExist=false;
                classesMainPageStage.close();
            });

            classesMainPageStage.show();
            classesMainPageStage.setResizable(false);
        }
        else if(!classesCode.equals(classesMainPageController.getClassesCode())){
            classesMainPageController.setClassesCode(classesCode);
            classesMainPageController.flush();
        }
    }

    public void close(){
        if(isTimePageExist){
            TimePageStage.close();
        }
        if(isClassesMainPageExist){
            classesMainPageStage.close();
        }
    }
}
