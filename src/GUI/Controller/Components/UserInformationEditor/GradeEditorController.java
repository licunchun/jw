package GUI.Controller.Components.UserInformationEditor;

import GUI.Data.Enum.Error.Main.Components.UserServ.EditError;
import GUI.Data.Enum.User.Grade;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.util.StringConverter;

import static GUI.Data.Enum.ObservableList.UserObservableList.GradeList;
import static Sevice.Main.Components.UserServ.UserServ.editGrade;

public class GradeEditorController {
    @FXML
    private Label tips;
    @FXML
    private ChoiceBox<Grade> GradeChooser;
    @FXML
    private Button Confirm;
    private String ID;
    private Stage stage;
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
    private void doConfirm(){
        switch(editGrade(ID,GradeChooser.getValue())){
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
    }

}
