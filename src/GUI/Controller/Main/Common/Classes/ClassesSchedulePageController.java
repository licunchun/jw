package GUI.Controller.Main.Common.Classes;

import GUI.Data.Enum.User.UserType;
import javafx.fxml.FXML;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;

public class ClassesSchedulePageController {//TODO
    @FXML
    private Label name;
    @FXML
    private Label userID;


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

    public void flush() {

    }//TODO

    public void setID(String ID) {
        this.ID = ID;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }
}
