package GUI.Controller.Main.Common.Classes;

import GUI.Data.DataPackage.Classes.Classes;
import GUI.Data.DataPackage.Classes.ClassesSet;
import GUI.Data.DataPackage.Classes.CourseTimeSet;
import GUI.Data.DataPackage.Classes.TimeTable;
import GUI.Data.Enum.Classes.CourseTime;
import GUI.Data.Enum.Classes.Week;
import GUI.Data.Enum.User.UserType;
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

import static Service.Main.Components.UserServ.UserServ.getName;
import static Service.Main.Student.ClassesServ.StudentClassesServ.getStudentClassesSet;
import static Service.Main.Teacher.ClassesServ.TeacherClassesServ.getTeacherClassesSet;

public class ClassesSchedulePageController {
    private final ObservableList<TimeTable> data = FXCollections.observableArrayList();//用于表格的展示的ObservableList
    private final String[] classesScheduleString = new String[100];
    @FXML
    private AnchorPane anchorPane;
    @FXML
    private Label name;
    @FXML
    private Label userID;
    @FXML
    private TableView<TimeTable> timeTable;
    @FXML
    private TableColumn<TimeTable, Integer> numberColumn;
    @FXML
    private TableColumn<TimeTable, String> MondayColumn;
    @FXML
    private TableColumn<TimeTable, String> TuesdayColumn;
    @FXML
    private TableColumn<TimeTable, String> WednesdayColumn;
    @FXML
    private TableColumn<TimeTable, String> ThursdayColumn;
    @FXML
    private TableColumn<TimeTable, String> FridayColumn;
    @FXML
    private TableColumn<TimeTable, String> SaturdayColumn;
    @FXML
    private TableColumn<TimeTable, String> SundayColumn;
    private String ID;
    private UserType userType;
    private ClassesSet classesSet;

    public ContextMenu classesSchedulePageContextMenu() {
        ContextMenu contextMenu = new ContextMenu();
        MenuItem flushMenuItem = new MenuItem("刷新");

        flushMenuItem.setAccelerator(new KeyCodeCombination(KeyCode.R, KeyCodeCombination.CONTROL_DOWN));

        flushMenuItem.setOnAction(event -> {
            try {
                flush();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

        contextMenu.getItems().addAll(flushMenuItem);

        return contextMenu;
    }

    public void initialize() throws IOException {
        loadTable();
        loadClasses();
    }

    public void flush() throws IOException {
        initializeData();
        loadTable();
        loadClasses();
    }

    private void initializeData() {
        data.clear();
        if (ID != null) {
            name.setText(getName(ID));
            userID.setText(ID);
        }
        for (int i = 1; i <= 91; i++) classesScheduleString[i] = null;
        MondayColumn.setSortable(false);
        TuesdayColumn.setSortable(false);
        WednesdayColumn.setSortable(false);
        ThursdayColumn.setSortable(false);
        FridayColumn.setSortable(false);
        SaturdayColumn.setSortable(false);
        SundayColumn.setSortable(false);
        numberColumn.setSortable(false);
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }

    private void loadTable() {
        numberColumn.setCellValueFactory(cellData -> cellData.getValue().numberProperty().asObject());
        MondayColumn.setCellValueFactory(cellData -> cellData.getValue().mondayProperty());
        TuesdayColumn.setCellValueFactory(cellData -> cellData.getValue().tuesdayProperty());
        WednesdayColumn.setCellValueFactory(cellData -> cellData.getValue().wednesdayProperty());
        ThursdayColumn.setCellValueFactory(cellData -> cellData.getValue().thursdayProperty());
        FridayColumn.setCellValueFactory(cellData -> cellData.getValue().fridayProperty());
        SaturdayColumn.setCellValueFactory(cellData -> cellData.getValue().saturdayProperty());
        SundayColumn.setCellValueFactory(cellData -> cellData.getValue().sundayProperty());

        timeTable.setItems(data);

        timeTable.setFixedCellSize(40); // 设置每行的高度
        timeTable.prefHeightProperty().bind(Bindings.size(data).multiply(timeTable.getFixedCellSize()).add(40)); // 设置表格的高度
    }

    private void loadClasses() {
        if (ID == null) {
            return;
        }
        if (userType.equals(UserType.Teacher)) classesSet = getTeacherClassesSet(ID);
        if (userType.equals(UserType.Student)) classesSet = getStudentClassesSet(ID);
        Iterable<Classes> classesSetIterable = classesSet.getClassesIterable();
        for (Classes classes : classesSetIterable) {
            CourseTimeSet courseTimeSet = classes.getTime();
            Iterable<CourseTime> courseTimeSetIterable = courseTimeSet.getCourseTimeIterable();
            for (CourseTime courseTime : courseTimeSetIterable) {
                Week formalWeek = courseTime.getWeek();
                int section = courseTime.getSection();
                int week = translateWeek(formalWeek);
                classesScheduleString[(week - 1) * 13 + section] = classes.getName();
            }
        }
        for (int i = 1; i <= 13; i++) {
            TimeTable timeTable = new TimeTable(i, classesScheduleString[i], classesScheduleString[i + 13], classesScheduleString[i + 26],
                    classesScheduleString[i + 39], classesScheduleString[i + 52], classesScheduleString[i + 65], classesScheduleString[i + 78]);
            data.add(timeTable);
        }
    }

    private int translateWeek(Week week) {
        if (week == Week.Monday) return 1;
        if (week == Week.Tuesday) return 2;
        if (week == Week.Wednesday) return 3;
        if (week == Week.Thursday) return 4;
        if (week == Week.Friday) return 5;
        if (week == Week.Saturday) return 6;
        if (week == Week.Sunday) return 7;
        return 0;
    }
}
