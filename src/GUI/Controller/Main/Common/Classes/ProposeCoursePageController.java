package GUI.Controller.Main.Common.Classes;

import GUI.Data.Enum.Classes.CourseType;
import GUI.Data.Enum.User.UserType;
import javafx.stage.Stage;

public class ProposeCoursePageController {//TODO
    private Stage stage;
    private UserType userType;
    private String ID;

    public void flush() {

    }//TODO 教师内容同步

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }

    public void setID(String ID) {
        this.ID = ID;
    }
}
