package GUI.Controller.Main.Common.Classes;

import GUI.Controller.Components.Time.TimeTableController;
import GUI.Data.DataPackage.Classes.Classes;
import GUI.Data.DataPackage.Classes.ClassesForTable;
import GUI.Data.Enum.Classes.EnumForClassesSearching.*;
import GUI.Data.Enum.Classes.Full;
import GUI.Data.Enum.User.UserType;
import Service.Main.Components.ClassServ.ClassesSearchingServ;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.StringConverter;

import static GUI.Data.Enum.GUI.ObservableList.ClassesChoosingObservableList.*;
import static GUI.GUIUtil.StageUtil.changeViews;
import static GUI.GUIUtil.StageUtil.resetLocation;
import static Service.Main.Components.UserServ.UserServ.findTeacher;

public class ClassesChoosingPageController {
    private static final int ROWS_PER_PAGE = 20;//每页最多有多少行
    /*
     * Table Information
     */
    private final TableView<ClassesForTable> tableView = new TableView<>();
    private final TableColumn<ClassesForTable, Void> codeColumn = new TableColumn<>("课堂编号");
    private final TableColumn<ClassesForTable, String> nameColumn = new TableColumn<>("课堂名称");
    private final TableColumn<ClassesForTable, String> periodColumn = new TableColumn<>("学时");
    private final TableColumn<ClassesForTable, String> creditsColumn = new TableColumn<>("学分");
    private final TableColumn<ClassesForTable, String> timeColumn = new TableColumn<>("上课时间");
    private final TableColumn<ClassesForTable, String> studentColumn = new TableColumn<>("课堂人数");
    private final TableColumn<ClassesForTable, String> classTypeColumn = new TableColumn<>("课堂类型");
    private final TableColumn<ClassesForTable, String> courseTypeColumn = new TableColumn<>("课程种类");
    private final TableColumn<ClassesForTable, String> schoolColumn = new TableColumn<>("院校");
    private final TableColumn<ClassesForTable, String> campusColumn = new TableColumn<>("校区");
    private final TableColumn<ClassesForTable, String> examModeColumn = new TableColumn<>("考试方式");
    private final TableColumn<ClassesForTable, String> languageColumn = new TableColumn<>("教学语言");
    private final TableColumn<ClassesForTable, String> educationColumn = new TableColumn<>("教育阶段");
    private final TableColumn<ClassesForTable, String> teacherColumn = new TableColumn<>("教师名称");
    private final TableColumn<ClassesForTable, String> fullColumn = new TableColumn<>("是否满人");
    /*
     * Else
     */
    private final Classes searchingClasses = new Classes();//用于搜索的Classes
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
    private boolean isTimePageExist = false;
    private Stage TimePageStage = new Stage();
    private TimeTableController timePageController = changeViews(TimePageStage, "/GUI/Window/Components/Time/TimeTable.fxml");
    ;
    /*
     * Basic Information
     */
    private String ID;
    private UserType userType;
    private Pagination pagination;
    private String teacher = null;
    /*
     * Classes Main Page
     */
    private boolean isClassesMainPageExist = false;
    private Stage classesMainPageStage;
    private ClassesMainPageController classesMainPageController;
    private ObservableList<ClassesForTable> data = FXCollections.observableArrayList();//用于表格的展示的ObservableList
    /*
     * Classes Searching Serv
     */
    private final ClassesSearchingServ classesSearchingServ = new ClassesSearchingServ();

    /*
     * Function
     */
    @FXML
    private void initialize() {
        choiceBoxInitialize();
        loadTable();
    }

    @FXML
    private void doSearch() {
        {
            searchingClasses.setCode(ClassesCodeField.getText());
            searchingClasses.setName(ClassesNameField.getText());
            try {
                Integer m_period = Integer.valueOf(ClassesPeriodField.getText());
                searchingClasses.setPeriod(m_period);
            } catch (NumberFormatException e) {
                searchingClasses.setPeriod(null);
            }
            try {
                Double m_credits = Double.valueOf(ClassesCreditField.getText());
                searchingClasses.setCredits(m_credits);
            } catch (NumberFormatException e) {
                searchingClasses.setCredits(null);
            }
            teacher = TeacherNameField.getText();
            searchingClasses.setClassType(ClassTypeChoiceBox.getValue().toClassType());
            searchingClasses.setCourseType(CourseTypeChoiceBox.getValue().toCourseType());
            searchingClasses.setSchool(SchoolChoiceBox.getValue().toSchool());
            searchingClasses.setCampus(CampusChoiceBox.getValue().toCampus());
            searchingClasses.setExamMode(ExamModeChoiceBox.getValue().toExamMode());
            searchingClasses.setLanguage(LanguageChoiceBox.getValue().toLanguage());
            searchingClasses.setEducation(EducationChoiceBox.getValue().toEducation());
            if (FullCheckBox.isSelected()) {
                searchingClasses.setFull(Full.NotFull);
            } else {
                searchingClasses.setFull(null);
            }
        }//读取参数
        flush();
    }

    @FXML
    private void openTimePage() {
        if (!isTimePageExist) {
            isTimePageExist = true;

            timePageController.setStage(TimePageStage);
            timePageController.flush();

            TimePageStage.setOnHiding(e -> {
                searchingClasses.setTime(timePageController.getTimeSet());
                TimePageStage.hide();
                isTimePageExist = false;
            });

            TimePageStage.show();
            TimePageStage.setResizable(false);
            resetLocation(TimePageStage);
        } else {
            resetLocation(TimePageStage);
        }
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }

    private void loadTable() {
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
            codeColumn.setCellFactory(column -> new TableCell<>() {
                private final Hyperlink hyperlink = new Hyperlink();

                {
                    hyperlink.setOnAction(event -> {
                        if (getTableView() != null && getIndex() < getTableView().getItems().size()) {
                            String code = getTableView().getItems().get(getIndex()).getCode();
                            openClassesMainPage(code);
                            resetLocation(classesMainPageStage);
                        }
                    });
                }

                @Override
                protected void updateItem(Void item, boolean empty) {
                    super.updateItem(item, empty);
                    if (empty) {
                        setGraphic(null);
                    } else {
                        if (getTableView() != null && getIndex() < getTableView().getItems().size()) {
                            hyperlink.setText(getTableView().getItems().get(getIndex()).getCode());
                            setGraphic(hyperlink);
                        } else {
                            setGraphic(null);
                        }
                    }
                }
            });
        }//设置自定义格式工厂

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
            pagination = new Pagination((classesSearchingServ.getCount() - 1) / ROWS_PER_PAGE + 1);
            pagination.setPageFactory(pageIndex -> {
                int fromIndex = pageIndex * ROWS_PER_PAGE;
                int toIndex = Math.min(fromIndex + ROWS_PER_PAGE, classesSearchingServ.getCount());
                data = FXCollections.observableArrayList(classesSearchingServ.getClassesSet(fromIndex, toIndex).
                        toObservableList());
                tableView.setItems(data);
                return new VBox(tableView);
            });
            pagination.setPrefWidth(1280);
            pagination.setPrefHeight(600);
        }//翻页
        TableViewPane.getChildren().add(pagination);
    }

    public void flush() {
        classesSearchingServ.searchClasses(searchingClasses, teacher);
        pagination.setPageCount((classesSearchingServ.getCount() - 1) / ROWS_PER_PAGE + 1);
        int fromIndex = pagination.getCurrentPageIndex() * ROWS_PER_PAGE;
        int toIndex = Math.min(fromIndex + ROWS_PER_PAGE, classesSearchingServ.getCount());
        data = FXCollections.observableArrayList(classesSearchingServ.getClassesSet(fromIndex, toIndex).
                toObservableList());
        tableView.setItems(data);
    }

    private void choiceBoxInitialize() {
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

            ClassTypeChoiceBox.setItems(C_ClassTypeList);
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

            CourseTypeChoiceBox.setItems(C_CourseTypeList);
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

            SchoolChoiceBox.setItems(C_SchoolList);
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

            CampusChoiceBox.setItems(C_CampusList);
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

            ExamModeChoiceBox.setItems(C_ExamModeList);
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

            LanguageChoiceBox.setItems(C_LanguageList);
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

            EducationChoiceBox.setItems(C_EducationList);
        }//EducationChoiceBox
    }

    public ContextMenu classesChoosingPageContextMenu() {
        ContextMenu contextMenu = new ContextMenu();
        MenuItem flushMenuItem = new MenuItem("刷新");

        flushMenuItem.setAccelerator(new KeyCodeCombination(KeyCode.R, KeyCodeCombination.CONTROL_DOWN));

        flushMenuItem.setOnAction(event -> flush());

        contextMenu.getItems().addAll(flushMenuItem);

        return contextMenu;
    }

    private void openClassesMainPage(String classesCode) {
        if (!isClassesMainPageExist) {
            isClassesMainPageExist = true;
            classesMainPageStage = new Stage();

            classesMainPageController = changeViews(classesMainPageStage, "/GUI/Window/Main/Common/Classes/ClassesMainPage.fxml");

            classesMainPageController.setStage(classesMainPageStage);
            classesMainPageController.setID(ID);
            classesMainPageController.setUserType(userType);
            classesMainPageController.setClassesCode(classesCode);
            classesMainPageController.flush();

            classesMainPageStage.setOnHiding(e -> {
                isClassesMainPageExist = false;
                classesMainPageStage.close();
                doSearch();
            });

            classesMainPageStage.show();
            classesMainPageStage.setResizable(false);
        } else if (!classesCode.equals(classesMainPageController.getClassesCode())) {
            classesMainPageController.setClassesCode(classesCode);
            classesMainPageController.flush();
        }
    }

    public void close() {
        if (isTimePageExist) {
            TimePageStage.close();
        }
        if (isClassesMainPageExist) {
            classesMainPageStage.close();
        }
    }
}
