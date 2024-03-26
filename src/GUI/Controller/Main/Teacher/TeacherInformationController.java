package GUI.Controller.Main.Teacher;

import javafx.fxml.FXML;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import static GUI.GUIUtil.StageUtil.changeViews;
import static GUI.GUIUtil.StageUtil.resetLocation;

public class TeacherInformationController {
    @FXML
    private Label School;
    @FXML
    private Label Gender;
    @FXML
    private Hyperlink EditSchool;
    @FXML
    private void initialize(){}
    @FXML
    private void doEditSchool(){
        if(!isEditSchoolPageShow){
            isEditSchoolPageShow=true;
            editSchoolPageStage=new Stage();

            changeViews(editSchoolPageStage,"/GUI/Window/Components/UserInformationEditor/TeacherSchoolEditor.fxml");

            editSchoolPageStage.setOnCloseRequest(e->{
                isEditSchoolPageShow=false;
                editSchoolPageStage.close();
            });
            editSchoolPageStage.setResizable(false);
            editSchoolPageStage.show();
            resetLocation(editSchoolPageStage);
        }
        else{
            resetLocation(editSchoolPageStage);
        }
    }

    private boolean isEditSchoolPageShow=false;
    private Stage editSchoolPageStage=null;
}
