package GUI.Controller.Main.Common.Classes;

import GUI.Data.Enum.User.UserType;
import javafx.scene.control.ContextMenu;

public class ClassesSchedulePageController {//TODO
    private String ID;
    private UserType userType;
    public ContextMenu classesSchedulePageContextMenu(){
        return new ContextMenu();
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }
}
