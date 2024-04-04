package GUI.Controller.Main.Student.Classes;

import javafx.scene.control.ContextMenu;

public class CheckGradePageController {
    private String ID;

    public ContextMenu checkGradePageContextMenu(){
        return new ContextMenu();
    }
    public void setID(String ID) {
        this.ID = ID;
    }
}
