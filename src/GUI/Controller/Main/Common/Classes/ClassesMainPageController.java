package GUI.Controller.Main.Common.Classes;

import GUI.Data.DataPackage.Classes.Classes;
import GUI.Data.DataPackage.Classes.ClassesForTable;
import GUI.Data.Enum.Error.Main.Components.ClassesServ.DeleteClassesError;
import GUI.Data.Enum.Error.Main.Student.ClassesServ.DropClassesError;
import GUI.Data.Enum.Error.Main.Student.ClassesServ.PickClassesError;
import GUI.Data.Enum.User.UserType;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import static Service.Main.Components.ClassServ.ClassesServ.deleteClasses;
import static Service.Main.Components.ClassServ.ClassesServ.getClasses;
import static Service.Main.Student.ClassesServ.StudentClassesServ.*;

public class ClassesMainPageController {
    @FXML
    private Label CodeLabel;
    @FXML
    private Label NameLabel;
    @FXML
    private Label PeriodLabel;
    @FXML
    private Label CreditsLabel;
    @FXML
    private Label TimeLabel;
    @FXML
    private Label NumberLabel;
    @FXML
    private Label ClassesTypeLabel;
    @FXML
    private Label CourseTypeLabel;
    @FXML
    private Label SchoolLabel;
    @FXML
    private Label CampusLabel;
    @FXML
    private Label ExamModeLabel;
    @FXML
    private Label LanguageLabel;
    @FXML
    private Label EducationLabel;
    @FXML
    private Label TeacherLabel;
    @FXML
    private Button ClickButton;
    @FXML
    private Button BackButton;
    @FXML
    private Label Tips;
    //Page Information
    private Stage stage;
    private String ID;
    private UserType userType;
    private String classesCode;

    @FXML
    private void doClick() {
        if (userType == UserType.Student) {
            Boolean isChosen = isPicked(ID, classesCode);
            if (isChosen == null) {
                stage.close();
                System.err.println("Error:ClassesCode not found or ID not found!\nPlease refresh the page and try again!");
                return;
            }
            if (Boolean.TRUE.equals(isChosen)) {
                doDropClasses();
            } else {
                doPickClasses();
            }
        }
        if (userType == UserType.Admin) {
            doDeleteClasses();
        }

        stage.close();
    }

    @FXML
    private void doBack() {
        stage.close();
    }

    private void doPickClasses() {
        switch (pickClasses(ID, classesCode)) {
            case PickClassesError.ClassesCodeNotFind -> {
                stage.close();
                System.err.println("Error:ClassesCode not found!\nPlease refresh the page and try again!");
            }
            case PickClassesError.IDNotFind -> {
                stage.close();
                System.err.println("Error:ID not found!\nPlease refresh the page and try again!");
            }
            case PickClassesError.ClassesISChosen -> Tips.setText("同类课程已选择，不可重复选课！");
            case PickClassesError.ClassesIsFull -> Tips.setText("课程人数已满！");
            case PickClassesError.TimeCrash -> Tips.setText("时间冲突");
            case PickClassesError.Success -> flush();
        }
    }

    private void doDropClasses() {
        switch (dropClasses(ID, classesCode)) {
            case DropClassesError.ClassesCodeNotFind -> {
                stage.close();
                System.err.println("Error:ClassesCode not found!\nPlease refresh the page and try again!");
            }
            case DropClassesError.IDNotFind -> {
                stage.close();
                System.err.println("Error:ID not found!\nPlease refresh the page and try again!");
            }
            case DropClassesError.Success -> flush();
        }
    }

    private void doDeleteClasses() {
        switch (deleteClasses(classesCode)) {
            case DeleteClassesError.ClassesCodeNotFind -> {
                stage.close();
                System.err.println("Error:ClassesCode not found!\nPlease refresh the page and try again!");
            }
            case DeleteClassesError.Success -> stage.close();
        }
    }

    public void flush() {
        {
            Classes classes = getClasses(classesCode);
            ClassesForTable classesForTable = new ClassesForTable(classes);

            CodeLabel.setText(classesForTable.getCode());
            NameLabel.setText(classesForTable.getName());
            PeriodLabel.setText(classesForTable.getPeriod());
            CreditsLabel.setText(classesForTable.getCredits());
            TimeLabel.setText(classesForTable.getTime());
            NumberLabel.setText(classesForTable.getStudent());
            ClassesTypeLabel.setText(classesForTable.getClassType());
            CourseTypeLabel.setText(classesForTable.getCourseType());
            SchoolLabel.setText(classesForTable.getSchool());
            CampusLabel.setText(classesForTable.getCampus());
            ExamModeLabel.setText(classesForTable.getExamMode());
            LanguageLabel.setText(classesForTable.getLanguage());
            EducationLabel.setText(classesForTable.getEducation());
            TeacherLabel.setText(classesForTable.getTeacher());
            Tips.setText("");
        }//Classes Basic Information
        {
            if (userType == UserType.Student) {
                Boolean isChosen = isPicked(ID, classesCode);
                if (isChosen == null) {
                    stage.close();
                    System.err.println("ClassesCode not found or ID not found!\nPlease refresh the page and try again!");
                    return;
                }
                if (Boolean.TRUE.equals(isChosen)) {
                    ClickButton.setText("退课");
                } else {
                    ClickButton.setText("选课");
                }
                return;
            }
            if (userType == UserType.Admin) {
                ClickButton.setText("删除");
            }
        }//Button Setting
    }
    /*
     * Getter&Setter
     */

    public void setStage(Stage stage) {
        this.stage = stage;
        stage.setTitle("课程主页");
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }

    public String getClassesCode() {
        return classesCode;
    }

    public void setClassesCode(String classesCode) {
        this.classesCode = classesCode;
    }
}
