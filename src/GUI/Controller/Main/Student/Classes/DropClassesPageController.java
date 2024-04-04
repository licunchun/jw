package GUI.Controller.Main.Student.Classes;

import javafx.application.Application;
import javafx.scene.control.ContextMenu;
import javafx.stage.Stage;

public class DropClassesPageController{
    private String ID;
    public ContextMenu dropClassesPageContextMenu(){
        return new ContextMenu();
    }
    public void setID(String ID) {
        this.ID = ID;
    }
    public void close(){

    }
}
