package GUI.Controller.Main.Admin;

import GUI.Data.Enum.User.UserType;
import javafx.scene.control.ContextMenu;

public class ManageUserPageController {//TODO
    private UserType userType;
    public static ContextMenu manageUserPageContextMenu(){
        return new ContextMenu();
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }
    public void close(){

    }
}
