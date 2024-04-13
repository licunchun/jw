package GUI.Controller.Main.Common.Classes;

import GUI.Data.DataPackage.Classes.Classes;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

public class ClassesController {
    @FXML
    private VBox vBox;
    @FXML
    private Label classesCode;
    @FXML
    private Label classesName;
    @FXML
    private Label teacherName;
    @FXML
    private Label classesPlace;
    private Classes classes;
    private int length = 2;
    public void setClassesController(Classes classes, int length) {
        this.classes = classes;
        this.length = length;
    }
    public void initialize() {
        setLabel();
        setSize();
    }
    private void setLabel() {
        classesCode.setText(classes.getCode());
        classesName.setText(classes.getName());
        teacherName.setText(classes.getTeacher().getNames());
        classesPlace.setText(classes.getPlace());
    }
    private void setSize() {//直接打开fxml发现没变化是因为没有调用classesController
        vBox.setPrefHeight(length * 40);
        vBox.setPrefWidth(130.0);
    }
}
