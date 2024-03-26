package GUI.Controller.Components.UserInformationEditor;

import Data.Enum.User.StudentSchool;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.util.StringConverter;

import static Data.Enum.User.UserObservableList.StudentSchoolList;

public class StudentSchoolEditorController {
    @FXML
    private ChoiceBox<StudentSchool> StudentSchoolChooser;
    @FXML
    private Button Confirm;
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
    private void doConfirm(){}
}
