package GUI.Controller.Components.UserInformationEditor;

import Data.Enum.User.Grade;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.util.StringConverter;

import static Data.Enum.User.UserObservableList.GradeList;

public class GradeEditorController {
    @FXML
    private ChoiceBox<Grade> GradeChooser;
    @FXML
    private Button Confirm;
    @FXML
    public void initialize(){
        GradeChooser.setValue(Grade.Grade1);
        GradeChooser.setConverter(new StringConverter<>() {
            @Override
            public String toString(Grade grade) {
                return grade.toString();
            }

            @Override
            public Grade fromString(String s) {
                return null;
            }
        });

        GradeChooser.setItems(GradeList);
    }
    @FXML
    private void doConfirm(){}
}
