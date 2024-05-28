package GUI.GUIUtil;

import GUI.Controller.Main.Admin.AdminMainMenuController;
import GUI.Controller.Main.Student.StudentMainMenuController;
import GUI.Controller.Main.Teacher.TeacherMainMenuController;
import GUI.Data.Enum.User.UserType;
import javafx.stage.Stage;

import static GUI.GUIUtil.StageUtil.changeViews;

public class MainPageUtil {
    public static void openMainPage(Stage stage, UserType userType, String ID) {
        switch (userType) {
            case UserType.Student:
                StudentMainMenuController studentMainMenuController = changeViews(stage, "/GUI/Window/Main/Student/StudentMainMenu.fxml");
                studentMainMenuController.setID(ID);
                break;
            case UserType.Teacher:
                TeacherMainMenuController teacherMainMenuController = changeViews(stage, "/GUI/Window/Main/Teacher/TeacherMainMenu.fxml");
                teacherMainMenuController.setID(ID);
                break;
            case UserType.Admin:
                AdminMainMenuController adminMainMenuController = changeViews(stage, "/GUI/Window/Main/Admin/AdminMainMenu.fxml");
                adminMainMenuController.setID(ID);
                break;
        }
    }
}
