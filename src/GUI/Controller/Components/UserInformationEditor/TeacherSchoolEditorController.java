package GUI.Controller.Components.UserInformationEditor;

import GUI.Data.Enum.School;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.util.StringConverter;

import static GUI.Data.Enum.ObservableList.UserObservableList.SchoolList;

public class TeacherSchoolEditorController{
    @FXML
    private ChoiceBox<School> TeacherSchoolChooser;
    @FXML
    private Button Confirm;
    private String ID;
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
    private void doConfirm(){}
    public void setID(String ID) {
        this.ID = ID;
    }
}
