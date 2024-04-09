package GUI.Data.DataPackage.Classes;

import javafx.beans.property.*;
import javafx.scene.control.Button;

public class TeacherScoreMainTable {
    private final SimpleStringProperty code;
    private final SimpleStringProperty name;
    private final ObjectProperty<CourseTimeSet> time;
    private final SimpleIntegerProperty totalStudent;
    private final ObjectProperty<Button> button;

    public TeacherScoreMainTable(Classes classes) {
        this.code = new SimpleStringProperty(classes.getCode());
        this.name = new SimpleStringProperty(classes.getName());
        this.time = new SimpleObjectProperty<>(classes.getTime());
        this.totalStudent = new SimpleIntegerProperty(classes.getStdCount());
        this.button = new SimpleObjectProperty<>(new Button("更改"));
        this.button.get().setId(getCode());
    }
    public String getCode() {
        return code.get();
    }

    public SimpleStringProperty codeProperty() {
        return code;
    }

    public String getName() {
        return name.get();
    }

    public SimpleStringProperty nameProperty() {
        return name;
    }

    public CourseTimeSet getTime() {
        return time.get();
    }
    public ObjectProperty<CourseTimeSet> timeProperty() {
        return time;
    }

    public int getTotalStudent() {
        return totalStudent.get();
    }

    public SimpleIntegerProperty totalStudentProperty() {
        return totalStudent;
    }

    public ObjectProperty<Button> buttonProperty() {
        return button;
    }
    public Button getButton() {
        return button.get();
    }

}