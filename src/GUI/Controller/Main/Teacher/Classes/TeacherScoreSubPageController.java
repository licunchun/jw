package GUI.Controller.Main.Teacher.Classes;

import GUI.Data.DataPackage.Classes.Classes;
import GUI.Data.DataPackage.Classes.IDSet;
import GUI.Data.DataPackage.Classes.TeacherScoreSubTable;
import GUI.Data.Enum.School;
import GUI.Data.Enum.User.Grade;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import static Sevice.Main.Components.ClassServ.ClassesServ.*;

public class TeacherScoreSubPageController {
    @FXML
    private Label classesName;
    @FXML
    private TableView<TeacherScoreSubTable> teacherScoreSubTableView;
    @FXML
    private TableColumn<TeacherScoreSubTable, String> studentIDColumn;
    @FXML
    private TableColumn<TeacherScoreSubTable, String> studentNameColumn;
    @FXML
    private TableColumn<TeacherScoreSubTable, School> studentSchoolColumn;
    @FXML
    private TableColumn<TeacherScoreSubTable, Grade> studentGradeColumn;
    @FXML
    private TableColumn<TeacherScoreSubTable, Integer> studentCurrentScoreColumn;
    @FXML
    private TableColumn<TeacherScoreSubTable, Double> studentCurrentGPAColumn;
    @FXML
    private TableColumn<TeacherScoreSubTable, Button> ButtonColumn;
    @FXML
    private TableColumn<TeacherScoreSubTable, TextField> studentModifiedScoreColumn;
    private String classesCode;
    private Classes classes;
    private IDSet studentSet;
    private String ID;

    public void setTeacherScoreSubPageController(String buttonId) {
        this.classesCode = buttonId;
        this.classes = getClasses(classesCode);
        this.classesName.setText(classes.getName());
        this.studentSet = getStudentSet(classes.getCode());
    }
    public void setID(String ID) {
        this.ID = ID;
    }
}
