package GUI.Controller.Components.UserInformationEditor;

import GUI.Data.Enum.Error.Main.Components.UserServ.EditError;
import GUI.Data.Enum.School;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.util.StringConverter;

import static GUI.Data.Enum.GUI.ObservableList.UserObservableList.SchoolList;
import static Sevice.Main.Components.UserServ.UserServ.editSchool;

public class TeacherSchoolEditorController{
    @FXML
    private Label tips;
    @FXML
    private ChoiceBox<School> TeacherSchoolChooser;
    @FXML
    private Button Confirm;
    private String ID;
    private Stage stage;
    @FXML
    public void initialize(){
        TeacherSchoolChooser.setValue(School.GiftedYoung);
        TeacherSchoolChooser.setConverter(new StringConverter<>() {
            @Override
            public String toString(School school) {
                return school.toString();
            }

            @Override
            public School fromString(String s) {
                return null;
            }
        });

        TeacherSchoolChooser.setItems(SchoolList);
    }
    @FXML
    private void doConfirm(){
        switch(editSchool(ID,TeacherSchoolChooser.getValue())){
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
        stage.setTitle("修改教师院系");
    }
}
