package GUI.Controller.Components.UserInformationEditor;

import GUI.Data.Enum.Error.Main.Components.UserServ.EditError;
import GUI.Data.Enum.User.StudentSchool;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.util.StringConverter;

import static GUI.Data.Enum.GUI.ObservableList.UserObservableList.StudentSchoolList;
import static Sevice.Main.Components.UserServ.UserServ.editSchool;

public class StudentSchoolEditorController {
    @FXML
    private Label tips;
    @FXML
    private ChoiceBox<StudentSchool> StudentSchoolChooser;
    @FXML
    private Button Confirm;
    private String ID;
    private Stage stage;
    @FXML
    public void initialize(){
        StudentSchoolChooser.setValue(StudentSchool.GiftedYoung);
        StudentSchoolChooser.setConverter(new StringConverter<>() {
            @Override
            public String toString(StudentSchool studentSchool) {
                return studentSchool.toString();
            }

            @Override
            public StudentSchool fromString(String s) {
                return null;
            }
        });

        StudentSchoolChooser.setItems(StudentSchoolList);
    }
    @FXML
    private void doConfirm(){
        switch(editSchool(ID,StudentSchoolChooser.getValue().toSchool())){
            case EditError.IDNotFound:
                tips.setText("未找到ID，请重新登录！！");
                tips.setVisible(true);
                break;
            case Success:
                stage.close();
        }
    }
    public void setID(String ID) {
        this.ID = ID;
    }
    public void setStage(Stage stage) {
        this.stage = stage;
        stage.setTitle("修改学生院系");
    }
}
