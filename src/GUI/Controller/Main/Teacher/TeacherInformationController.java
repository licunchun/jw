package GUI.Controller.Main.Teacher;

import GUI.Controller.Components.UserInformationEditor.TeacherSchoolEditorController;
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
    private String ID;
    /*
     * Children Page
     */
    private boolean isEditSchoolPageShow=false;
    private Stage editSchoolPageStage=null;
    private TeacherSchoolEditorController teacherSchoolEditorController;
    /*
     * Function
     */
    @FXML
    private void initialize(){}
    @FXML
    private void doEditSchool(){
        if(!isEditSchoolPageShow){
            isEditSchoolPageShow=true;
            editSchoolPageStage=new Stage();

            teacherSchoolEditorController=changeViews(editSchoolPageStage,"/GUI/Window/Components/UserInformationEditor/TeacherSchoolEditor.fxml");
            teacherSchoolEditorController.setID(ID);

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
    public void closeAllChildren(){
        if(isEditSchoolPageShow){
            editSchoolPageStage.close();
        }
    }

    public void setID(String ID) {
        this.ID = ID;
    }
}
