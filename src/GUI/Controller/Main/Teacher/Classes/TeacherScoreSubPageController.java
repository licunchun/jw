package GUI.Controller.Main.Teacher.Classes;

import GUI.Data.DataPackage.Classes.Classes;
import GUI.Data.DataPackage.Classes.TeacherScoreSubTable;
import GUI.Data.Enum.School;
import GUI.Data.Enum.User.Grade;
import javafx.animation.PauseTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.util.Duration;

import static GUI.Data.Util.Classes.ObservableListUtil.getTeacherScoreSubPageObservableList;
import static Service.Main.Components.ClassServ.ClassesServ.getClasses;
import static Service.Main.Components.ClassServ.ClassesServ.setStudentScore;

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
    @FXML
    private Button changeAllButton;
    private String classesCode;
    private Classes classes;
    private String ID;
    private ObservableList<TeacherScoreSubTable> data = FXCollections.observableArrayList();

    public void setTeacherScoreSubPageController(String buttonId) {
        this.classesCode = buttonId;
        this.classes = getClasses(classesCode);
        this.classesName.setText(classes.getName());
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public void setClassesCode(String classesCode) {
        this.classesCode = classesCode;
    }

    public void flush() {
        data = getTeacherScoreSubPageObservableList(ID, classes);
        teacherScoreSubTableView.setItems(data);
    }
    public void initialize() {
        loadTable();
        // studentIDColumn 列的 cellFactory
        studentIDColumn.setCellFactory(column -> new TableCell<>() {
            @Override
            protected void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText(null);
                    setAlignment(Pos.CENTER); // 居中对齐
                } else {
                    setText(item);
                    setAlignment(Pos.CENTER); // 居中对齐
                }
            }
        });

// studentNameColumn 列的 cellFactory
        studentNameColumn.setCellFactory(column -> new TableCell<>() {
            @Override
            protected void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText(null);
                    setAlignment(Pos.CENTER); // 居中对齐
                } else {
                    setText(item);
                    setAlignment(Pos.CENTER); // 居中对齐
                }
            }
        });

// studentSchoolColumn 列的 cellFactory
        studentSchoolColumn.setCellFactory(column -> new TableCell<>() {
            @Override
            protected void updateItem(School item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText(null);
                    setAlignment(Pos.CENTER); // 居中对齐
                } else {
                    setText(item.toString());
                    setAlignment(Pos.CENTER); // 居中对齐
                }
            }
        });

// studentGradeColumn 列的 cellFactory
        studentGradeColumn.setCellFactory(column -> new TableCell<>() {
            @Override
            protected void updateItem(Grade item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText(null);
                    setAlignment(Pos.CENTER); // 居中对齐
                } else {
                    setText(item.toString());
                    setAlignment(Pos.CENTER); // 居中对齐
                }
            }
        });

// studentCurrentScoreColumn 列的 cellFactory
        studentCurrentScoreColumn.setCellFactory(column -> new TableCell<>() {
            @Override
            protected void updateItem(Integer item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText(null);
                    setAlignment(Pos.CENTER); // 居中对齐
                } else {
                    setText(String.valueOf(item));
                    setAlignment(Pos.CENTER); // 居中对齐
                }
            }
        });

// studentCurrentGPAColumn 列的 cellFactory
        studentCurrentGPAColumn.setCellFactory(column -> new TableCell<>() {
            @Override
            protected void updateItem(Double item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText(null);
                    setAlignment(Pos.CENTER); // 居中对齐
                } else {
                    setText(String.valueOf(item));
                    setAlignment(Pos.CENTER); // 居中对齐
                }
            }
        });

// studentModifiedScoreColumn 列的 cellFactory
        studentModifiedScoreColumn.setCellFactory(column -> new TableCell<>() {
            @Override
            protected void updateItem(TextField textField, boolean empty) {
                super.updateItem(textField, empty);
                if (empty || textField == null) {
                    setGraphic(null);
                    setText(null);
                } else {
                    textField.textProperty().addListener((observable, oldValue, newValue) -> {
                        if (!newValue.matches("\\d*")) { // 确保输入的是数字
                            textField.setText(newValue.replaceAll("[^\\d]", ""));
                        } else if (!newValue.isEmpty()) {
                            int value = Integer.parseInt(newValue);
                            if (value < 0 || value > 100) {
                                textField.setText(oldValue); // 如果值不在0-100范围内，恢复旧值
                            }
                        }
                    });
                    setGraphic(textField);
                    setText(null);
                    setAlignment(Pos.CENTER); // 居中对齐
                }
            }
        });

        ButtonColumn.setCellFactory(column -> new TableCell<>() {
            @Override
            protected void updateItem(Button button, boolean empty) {
                super.updateItem(button, empty);
                if (empty || button == null) {
                    setGraphic(null);
                    setText(null);
                } else {
                    setGraphic(button);
                    setText(null);
                    setAlignment(Pos.CENTER); // 居中对齐

                    button.setOnAction(event -> {
                        TeacherScoreSubTable rowData = getTableRow().getItem();
                        if (rowData != null) {
                            String studentID = rowData.getStudentID();
                            TextField textField = findTextField(studentID);
                            if (textField != null) {
                                String text = textField.getText();
                                if (!text.isEmpty()) {
                                    int newScore = Integer.parseInt(text);
                                    setStudentScore(classesCode, studentID, newScore);
                                    textField.setText("");

                                    // 创建一个延迟对象，持续 1 秒钟
                                    PauseTransition delay = new PauseTransition(Duration.seconds(1));
                                    delay.setOnFinished(e -> {
                                        // 延迟结束后将按钮状态恢复为未点击状态
                                        button.setDisable(false);
                                    });
                                    // 启动延迟
                                    button.setDisable(true);
                                    delay.play();
                                } else {
                                    System.err.println("Unable to find text field for student ID: " + studentID);
                                    // 处理空文本字段的情况
                                }
                            } else {
                                System.err.println("Unable to find text field for student ID: " + studentID);
                                // 处理找不到文本字段的情况
                            }
                        }
                    });
                }
            }
        });

        changeAllButton.setOnAction(event -> {
            // 遍历所有行
            for (TeacherScoreSubTable rowData : teacherScoreSubTableView.getItems()) {
                // 获取学生ID
                String studentID = rowData.getStudentID();
                // 获取对应的文本框
                TextField textField = findTextField(studentID);
                if (textField != null) {
                    // 将文本框中的值解析为新的分数
                    int newScore = Integer.parseInt(textField.getText());
                    // 根据学生ID和新的分数设置分数
                    setStudentScore(classesCode, studentID, newScore);
                    // 清空文本框
                    textField.setText("");
                }
            }
            // 创建一个延迟对象，持续 1 秒钟
            PauseTransition delay = new PauseTransition(Duration.seconds(1));
            delay.setOnFinished(e -> {
                // 延迟结束后将按钮状态恢复为未点击状态
                changeAllButton.setDisable(false);
            });
            // 启动延迟
            delay.play();
        });

    }

    private TextField findTextField(String ID) {
        for (TeacherScoreSubTable item : data) {
            if (ID.equals(item.getStudentID())) {
                return item.getStudentModifiedScore(); // 假设每个 TeacherScoreSubTable 对象中有一个名为 studentModifiedScore 的 TextField
            }
        }
        return null;
    }

    private void loadTable() {
        studentIDColumn.setCellValueFactory(cellData -> cellData.getValue().studentIDProperty());
        studentNameColumn.setCellValueFactory(cellData -> cellData.getValue().studentNameProperty());
        studentSchoolColumn.setCellValueFactory(cellData -> cellData.getValue().studentSchoolProperty());
        studentGradeColumn.setCellValueFactory(cellData -> cellData.getValue().studentGradeProperty());
        studentCurrentScoreColumn.setCellValueFactory(cellData -> cellData.getValue().studentCurrentScoreProperty().asObject());
        studentCurrentGPAColumn.setCellValueFactory(cellData -> cellData.getValue().studentCurrentGPAProperty().asObject());
        ButtonColumn.setCellValueFactory(cellData -> cellData.getValue().buttonProperty());
        studentModifiedScoreColumn.setCellValueFactory(cellData -> cellData.getValue().studentModifiedScoreProperty());

        teacherScoreSubTableView.setItems(data);
    }

    public ContextMenu teacherCourseSubPageContextMenu() {
        ContextMenu contextMenu = new ContextMenu();
        MenuItem flushMenuItem = new MenuItem("刷新");

        flushMenuItem.setAccelerator(new KeyCodeCombination(KeyCode.R, KeyCodeCombination.CONTROL_DOWN));

        flushMenuItem.setOnAction(event -> flush());

        contextMenu.getItems().addAll(flushMenuItem);

        return contextMenu;
    }
}
