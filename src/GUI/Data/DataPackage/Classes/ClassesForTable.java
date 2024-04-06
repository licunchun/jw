package GUI.Data.DataPackage.Classes;

import javafx.beans.property.SimpleStringProperty;

import static Sevice.Main.Components.ClassServ.ClassesServ.toStringTime;

public class ClassesForTable {
    private final SimpleStringProperty code;
    private final SimpleStringProperty name;
    private final SimpleStringProperty period;
    private final SimpleStringProperty credits;
    private final SimpleStringProperty time;
    private final SimpleStringProperty student;
    private final SimpleStringProperty classType;
    private final SimpleStringProperty courseType;
    private final SimpleStringProperty school;
    private final SimpleStringProperty campus;
    private final SimpleStringProperty examMode;
    private final SimpleStringProperty language;
    private final SimpleStringProperty education;
    private final SimpleStringProperty teacher;
    private final SimpleStringProperty full;

    public ClassesForTable(Classes classes) {
        this.code = new SimpleStringProperty(classes.getCode());
        this.name = new SimpleStringProperty(classes.getName());
        this.period = new SimpleStringProperty(classes.getPeriod().toString());
        this.credits = new SimpleStringProperty(classes.getCredits().toString());
        this.time = new SimpleStringProperty(toStringTime(classes.getTime()));
        this.student = new SimpleStringProperty(Integer.toString(classes.getStdCount()) + '/' +
                classes.getLimitCount());
        this.classType = new SimpleStringProperty(classes.getClassType().toString());
        this.courseType = new SimpleStringProperty(classes.getCourseType().toString());
        this.school = new SimpleStringProperty(classes.getSchool().toString());
        this.campus = new SimpleStringProperty(classes.getCampus().toString());
        this.examMode = new SimpleStringProperty(classes.getExamMode().toString());
        this.language = new SimpleStringProperty(classes.getLanguage().toString());
        this.education = new SimpleStringProperty(classes.getEducation().toString());
        this.teacher = new SimpleStringProperty(classes.getTeacher().getNames());
        this.full = new SimpleStringProperty(classes.getFull().toString());
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

    public String getPeriod() {
        return period.get();
    }

    public SimpleStringProperty periodProperty() {
        return period;
    }

    public String getCredits() {
        return credits.get();
    }

    public SimpleStringProperty creditsProperty() {
        return credits;
    }

    public String getTime() {
        return time.get();
    }

    public SimpleStringProperty timeProperty() {
        return time;
    }

    public String getStudent() {
        return student.get();
    }

    public SimpleStringProperty studentProperty() {
        return student;
    }

    public String getClassType() {
        return classType.get();
    }

    public SimpleStringProperty classTypeProperty() {
        return classType;
    }

    public String getCourseType() {
        return courseType.get();
    }

    public SimpleStringProperty courseTypeProperty() {
        return courseType;
    }

    public String getSchool() {
        return school.get();
    }

    public SimpleStringProperty schoolProperty() {
        return school;
    }

    public String getCampus() {
        return campus.get();
    }

    public SimpleStringProperty campusProperty() {
        return campus;
    }

    public String getExamMode() {
        return examMode.get();
    }

    public SimpleStringProperty examModeProperty() {
        return examMode;
    }

    public String getLanguage() {
        return language.get();
    }

    public SimpleStringProperty languageProperty() {
        return language;
    }

    public String getEducation() {
        return education.get();
    }

    public SimpleStringProperty educationProperty() {
        return education;
    }

    public String getTeacher() {
        return teacher.get();
    }

    public SimpleStringProperty teacherProperty() {
        return teacher;
    }

    public String getFull() {
        return full.get();
    }

    public SimpleStringProperty fullProperty() {
        return full;
    }
}
