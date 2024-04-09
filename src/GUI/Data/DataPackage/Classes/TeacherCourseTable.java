package GUI.Data.DataPackage.Classes;

import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.Button;

public class TeacherCourseTable {
    private final SimpleStringProperty code;
    private final SimpleStringProperty name;
    private final CourseTimeSet time;
    private final SimpleIntegerProperty totalStudent;
    Button button = new Button("更改");

    public TeacherCourseTable(Classes classes) {
        this.code = new SimpleStringProperty(classes.getCode());
        this.name = new SimpleStringProperty(classes.getName());
        this.time = classes.getTime();
        this.totalStudent = new SimpleIntegerProperty(classes.getStdCount());
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
        return time;
    }

    public ReadOnlyObjectProperty<CourseTimeSet> timeProperty() {
        return time.timeProperty();
    }

    public int getTotalStudent() {
        return totalStudent.get();
    }

    public SimpleIntegerProperty totalStudentProperty() {
        return totalStudent;
    }

    public ReadOnlyObjectProperty<Button> buttonProperty() {
        return new ReadOnlyObjectWrapper<>(button);
    }

}