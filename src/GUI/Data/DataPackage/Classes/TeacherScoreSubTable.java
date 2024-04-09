package GUI.Data.DataPackage.Classes;

import GUI.Data.Enum.School;
import GUI.Data.Enum.User.Grade;
import javafx.beans.property.*;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import static Sevice.Main.Components.ClassServ.ClassesServ.*;
import static Sevice.Main.Components.UserServ.UserServ.*;

public class TeacherScoreSubTable {
    private final SimpleStringProperty studentID;
    private final SimpleStringProperty studentName;
    private final ObjectProperty<School> studentSchool;
    private final ObjectProperty<Grade> studentGrade;
    private final SimpleIntegerProperty studentCurrentScore;
    private final SimpleDoubleProperty studentCurrentGPA;
    private final ObjectProperty<Button> button;
    private final ObjectProperty<TextField> studentModifiedScore;

    public String getStudentID() {
        return studentID.get();
    }

    public SimpleStringProperty studentIDProperty() {
        return studentID;
    }

    public String getStudentName() {
        return studentName.get();
    }

    public SimpleStringProperty studentNameProperty() {
        return studentName;
    }

    public School getStudentSchool() {
        return studentSchool.get();
    }
    public ObjectProperty<School> studentSchoolProperty() {
        return studentSchool;
    }
    public Grade getStudentGrade(){
        return studentGrade.get();
    }
    public ObjectProperty<Grade> studentGradeProperty() {
        return studentGrade;
    }
    public int getStudentCurrentScore() {
        return studentCurrentScore.get();
    }
    public SimpleIntegerProperty studentCurrentScoreProperty() {
        return studentCurrentScore;
    }
    public double getStudentCurrentGPA() {
        return studentCurrentGPA.get();
    }
    public SimpleDoubleProperty studentCurrentGPAProperty() {
        return studentCurrentGPA;
    }
    public Button getButton() {
        return button.get();
    }
    public ObjectProperty<Button> buttonProperty() {
        return button;
    }
    public TextField getStudentModifiedScore() {
        return studentModifiedScore.get();
    }
    public ObjectProperty<TextField> studentModifiedScoreProperty() {
        return studentModifiedScore;
    }
    public TeacherScoreSubTable(String code, String ID) {
        this.studentID = new SimpleStringProperty(ID);
        this.studentName = new SimpleStringProperty(getName(ID));
        this.studentSchool = new SimpleObjectProperty<>(getSchool(ID));
        this.studentGrade = new SimpleObjectProperty<>(getGrade(ID));
        this.studentCurrentScore = new SimpleIntegerProperty(getStudentScore(code, ID));
        this.studentCurrentGPA = new SimpleDoubleProperty(getStudentGPA(code, ID));
        this.button = new SimpleObjectProperty<>(new Button("更改"));
        this.button.get().setId(getStudentID());
        this.studentModifiedScore = new SimpleObjectProperty<>(new TextField());
        this.studentModifiedScore.get().setId(getStudentID());
    }
}
