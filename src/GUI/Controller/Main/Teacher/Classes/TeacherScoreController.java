package GUI.Controller.Main.Teacher.Classes;

import GUI.Data.DataPackage.Classes.Classes;
import GUI.Data.DataPackage.Classes.ClassesSet;

import javafx.scene.control.ListView;

import javafx.fxml.FXML;

import static Sevice.Main.Teacher.ClassesServ.TeacherClassesServ.getTeacherClassesSet;

public class TeacherScoreController {
    private String ID;
    @FXML
    private ListView<String> teacherClassesList = new ListView<>();

    public void setID(String ID) {
        this.ID=ID;
    }

    public void initialize() {
        loadTeacherClassesTable();
    }
    private void loadTeacherClassesTable() {


    }//TODO
}
