package GUI.Data.DataPackage.Classes;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class CourseTable {
    private final SimpleStringProperty name;
    private final SimpleIntegerProperty period;
    private final SimpleDoubleProperty credits;
    private final SimpleDoubleProperty GPA;
    private final SimpleIntegerProperty score;

    public CourseTable(Classes classes, double GPA, int score) {
        this.name = new SimpleStringProperty(classes.getName());
        this.period = new SimpleIntegerProperty(classes.getPeriod());
        this.credits = new SimpleDoubleProperty(classes.getCredits());
        this.GPA = new SimpleDoubleProperty(GPA);
        this.score = new SimpleIntegerProperty(score);
    }

    public String getName() {
        return name.get();
    }
    public SimpleStringProperty nameProperty() {
        return name;
    }

    public Integer getPeriod() {
        return period.get();
    }
    public SimpleIntegerProperty periodProperty() {
        return period;
    }

    public Double getCredits() {
        return credits.get();
    }
    public SimpleDoubleProperty creditProperty() {
        return credits;
    }

    public Double getGPA() {
        return GPA.get();
    }
    public SimpleDoubleProperty GPAProperty() {
        return GPA;
    }

    public Integer getScore() {
        return score.get();
    }
    public SimpleIntegerProperty scoreProperty() {
        return score;
    }
}
