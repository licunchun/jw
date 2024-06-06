package GUI.Data.DataPackage.Classes;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.Button;

import static Service.Main.Components.ClassServ.ClassesServ.toStringTime;

public class TeacherScoreMainTable {
    private final SimpleStringProperty code;
    private final SimpleStringProperty name;
    private final SimpleStringProperty time;
    private final SimpleIntegerProperty totalStudent;
    private final ObjectProperty<Button> button;

    public TeacherScoreMainTable(Classes classes) {
        this.code = new SimpleStringProperty(classes.getCode());
        this.name = new SimpleStringProperty(classes.getName());
        this.time = new SimpleStringProperty(toStringTime(classes.getTime()));
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

    public String getTime() {
        return time.get();
    }

    public SimpleStringProperty timeProperty() {
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