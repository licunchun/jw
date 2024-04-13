package GUI.Controller.Main.Common.Classes;

import GUI.Data.DataPackage.Classes.StudentCourseScoreTable;
import GUI.Data.DataPackage.Classes.TimeTable;
import GUI.Data.Enum.User.UserType;
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;

import static Sevice.Main.Components.UserServ.UserServ.getName;

public class ClassesSchedulePageController {//TODO
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
    private ObservableList<TimeTable> data = FXCollections.observableArrayList();//用于表格的展示的ObservableList
    private String ID;
    private UserType userType;

    public ContextMenu classesSchedulePageContextMenu() {
        ContextMenu contextMenu = new ContextMenu();
        MenuItem flushMenuItem = new MenuItem("刷新");

        flushMenuItem.setAccelerator(new KeyCodeCombination(KeyCode.R, KeyCodeCombination.CONTROL_DOWN));

        flushMenuItem.setOnAction(event -> flush());

        contextMenu.getItems().addAll(flushMenuItem);

        return contextMenu;
    }
    public void initialize(){
        loadTable();
    }
    public void flush() {
        initializeData();
    }//TODO
    private void initializeData() {
        for(int i = 1; i <= 13 ; i++) {
            TimeTable newTimetable = new TimeTable(i);
            data.add(newTimetable);
        }
        if(ID != null){
            name.setText(getName(ID));
            userID.setText(ID);
        }
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
    private double findLayoutX(int columnIndex) {//列
        // 获取 TableView 在父容器中的布局位置
        double tableViewLayoutX = timeTable.getLayoutX();
        return tableViewLayoutX + 90 + 130 * (columnIndex - 1);
    }
    private double findLayoutY(int rowIndex, int columnIndex) {//行
        // 获取 TableView 在父容器中的布局位置
        double tableViewLayoutY = timeTable.getLayoutY();
        return tableViewLayoutY + 40 * (rowIndex - 1);
    }
}
