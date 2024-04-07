package GUI.Controller.Main.Teacher;

import GUI.Data.DataPackage.Classes.Classes;
import GUI.Data.DataPackage.Classes.ClassesSet;

import static Sevice.Main.Teacher.ClassesServ.TeacherClassesServ.getTeacherClassesSet;

public class TeacherScoreController {
    private String ID;
    public void setID(String ID) {
        this.ID=ID;
    }

    public void initialize() {
        loadTeacherClassesTable();
    }
    private void loadTeacherClassesTable() {

        ClassesSet classesSet = getTeacherClassesSet(ID);
        Iterable<Classes> classesSetIterable = classesSet.getClassesIterable();
        for(Classes teacherClass : classesSetIterable) {

        }
    }
}
