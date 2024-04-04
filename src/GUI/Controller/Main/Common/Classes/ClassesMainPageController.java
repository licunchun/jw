package GUI.Controller.Main.Common.Classes;

import GUI.Data.Enum.User.UserType;
import javafx.fxml.FXML;
import javafx.stage.Stage;

public class ClassesMainPageController {
    //Page Information
    private Stage stage;
    private String ID;
    private UserType userType;
    private String classesCode;
    /*
     * Functions
     */
    @FXML
    private void initialize(){

    }

    public void flush(){

    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }

    public void setClassesCode(String classesCode) {
        this.classesCode = classesCode;
    }

    public String getClassesCode() {
        return classesCode;
    }
}
