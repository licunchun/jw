package GUI.Controller.Main.Student.Classes;

import GUI.Controller.Main.Common.Classes.ClassesMainPageController;
import GUI.Data.DataPackage.Classes.ClassesForTable;
import GUI.Data.DataPackage.Classes.ClassesSet;
import GUI.Data.Enum.User.UserType;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import static GUI.GUIUtil.StageUtil.changeViews;
import static GUI.GUIUtil.StageUtil.resetLocation;
import static Service.Main.Student.ClassesServ.StudentClassesServ.getStudentClassesSet;

public class DropClassesPageController {
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
    private final TableColumn<ClassesForTable, String> classTypeColumn = new TableColumn<>("课堂类型");
    private final TableColumn<ClassesForTable, String> courseTypeColumn = new TableColumn<>("课程种类");
    private final TableColumn<ClassesForTable, String> schoolColumn = new TableColumn<>("院校");
    private final TableColumn<ClassesForTable, String> campusColumn = new TableColumn<>("校区");
    private final TableColumn<ClassesForTable, String> examModeColumn = new TableColumn<>("考试方式");
    private final TableColumn<ClassesForTable, String> languageColumn = new TableColumn<>("教学语言");
    private final TableColumn<ClassesForTable, String> educationColumn = new TableColumn<>("教育阶段");
    private final TableColumn<ClassesForTable, String> teacherColumn = new TableColumn<>("教师名称");
    @FXML
    private AnchorPane TableViewPane;
    private Pagination pagination;
    /*
     * Classes Main Page
     */
    private boolean isClassesMainPageExist = false;
    private Stage classesMainPageStage;
    private ClassesMainPageController classesMainPageController;
    private ObservableList<ClassesForTable> data = FXCollections.observableArrayList();//用于表格的展示的ObservableList
    /*
     * Basic Information
     */
    private String ID;

    /*
     * Functions
     */
    @FXML
    private void initialize() {
        loadTable();
    }

    public ContextMenu dropClassesPageContextMenu() {
        ContextMenu contextMenu = new ContextMenu();
        MenuItem flushMenuItem = new MenuItem("刷新");

        flushMenuItem.setAccelerator(new KeyCodeCombination(KeyCode.R, KeyCodeCombination.CONTROL_DOWN));

        flushMenuItem.setOnAction(event -> flush());

        contextMenu.getItems().addAll(flushMenuItem);

        return contextMenu;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public void loadTable() {
        tableView.setPrefWidth(1280);
        tableView.setPrefHeight(600);

        {
            nameColumn.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
            periodColumn.setCellValueFactory(cellData -> cellData.getValue().periodProperty());
            creditsColumn.setCellValueFactory(cellData -> cellData.getValue().creditsProperty());
            timeColumn.setCellValueFactory(cellData -> cellData.getValue().timeProperty());
            classTypeColumn.setCellValueFactory(cellData -> cellData.getValue().classTypeProperty());
            courseTypeColumn.setCellValueFactory(cellData -> cellData.getValue().courseTypeProperty());
            schoolColumn.setCellValueFactory(cellData -> cellData.getValue().schoolProperty());
            campusColumn.setCellValueFactory(cellData -> cellData.getValue().campusProperty());
            examModeColumn.setCellValueFactory(cellData -> cellData.getValue().examModeProperty());
            languageColumn.setCellValueFactory(cellData -> cellData.getValue().languageProperty());
            educationColumn.setCellValueFactory(cellData -> cellData.getValue().educationProperty());
            teacherColumn.setCellValueFactory(cellData -> cellData.getValue().teacherProperty());
        }//设置表格列与数据对象的属性关联
        {
            codeColumn.setCellFactory(column -> new TableCell<>() {
                private final Hyperlink hyperlink = new Hyperlink(getTableView().getItems().get(getIndex()).getCode());

                {
                    hyperlink.setOnAction(event -> {
                        isClassesMainPageExist = true;
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
//            nameColumn.setCellValueFactory(new PropertyValueFactory<>("课堂名称"));
//            periodColumn.setCellValueFactory(new PropertyValueFactory<>("学时"));
//            creditsColumn.setCellValueFactory(new PropertyValueFactory<>("学分"));
//            timeColumn.setCellValueFactory(new PropertyValueFactory<>("上课时间"));
//            classTypeColumn.setCellValueFactory(new PropertyValueFactory<>("课堂类型"));
//            courseTypeColumn.setCellValueFactory(new PropertyValueFactory<>("课程种类"));
//            schoolColumn.setCellValueFactory(new PropertyValueFactory<>("院校"));
//            campusColumn.setCellValueFactory(new PropertyValueFactory<>("校区"));
//            examModeColumn.setCellValueFactory(new PropertyValueFactory<>("考试方式"));
//            languageColumn.setCellValueFactory(new PropertyValueFactory<>("教学语言"));
//            educationColumn.setCellValueFactory(new PropertyValueFactory<>("教育阶段"));
//            teacherColumn.setCellValueFactory(new PropertyValueFactory<>("教师名称"));
        }//设置自定义格式工厂

        tableView.setItems(data);
        tableView.getColumns().addAll(
                codeColumn,
                nameColumn,
                periodColumn,
                creditsColumn,
                timeColumn,
                classTypeColumn,
                courseTypeColumn,
                schoolColumn,
                campusColumn,
                examModeColumn,
                languageColumn,
                educationColumn,
                teacherColumn
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
            pagination.setPrefHeight(660);
        }//翻页
        TableViewPane.getChildren().add(pagination);
    }

    public void flush() {
        ClassesSet studentClassesSet = getStudentClassesSet(ID);
        if (studentClassesSet == null) {
            System.err.println("Error:Get student classes set error!\nPlease try again later!");
        }
        data = studentClassesSet.toObservableList();
    }

    private void openClassesMainPage(String classesCode) {
        if (!isClassesMainPageExist) {
            classesMainPageStage = new Stage();

            classesMainPageController = changeViews(classesMainPageStage, "/GUI/Window/Main/Common/Classes/ClassesMainPage.fxml");

            classesMainPageController.setStage(classesMainPageStage);
            classesMainPageController.setID(ID);
            classesMainPageController.setUserType(UserType.Student);
            classesMainPageController.setClassesCode(classesCode);
            classesMainPageController.flush();

            classesMainPageStage.setOnHiding(e -> {
                isClassesMainPageExist = false;
                classesMainPageStage.close();
                flush();
            });

            classesMainPageStage.show();
            classesMainPageStage.setResizable(false);
        } else if (!classesCode.equals(classesMainPageController.getClassesCode())) {
            classesMainPageController.setClassesCode(classesCode);
            classesMainPageController.flush();
        }
    }

    public void close() {
        if (isClassesMainPageExist) {
            classesMainPageStage.close();
        }
    }
}
