package GUI.Controller.Main.Student;

import javafx.fxml.FXML;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import static GUI.GUIUtil.StageUtil.changeViews;
import static GUI.GUIUtil.StageUtil.resetLocation;

public class StudentInformationController {
    @FXML
    private Label Money;
    @FXML
    private Label Grade;
    @FXML
    private Label Gender;
    @FXML
    private Label School;
    @FXML
    private Hyperlink EditGrade;
    @FXML
    private Hyperlink EditSchool;
    @FXML
    private Hyperlink Recharge;
    @FXML
    private void initialize(){}
    @FXML
    private void doEditGrade(){
        if(!isEditGradePageShow){
            isEditGradePageShow=true;
            editGradePageStage=new Stage();

            changeViews(editGradePageStage,"/GUI/Window/Components/UserInformationEditor/GradeEditor.fxml");

            editGradePageStage.setOnCloseRequest(e->{
                isEditGradePageShow=false;
                editGradePageStage.close();
            });
            editGradePageStage.setResizable(false);
            editGradePageStage.show();
            resetLocation(editGradePageStage);
        }
        else{
            resetLocation(editGradePageStage);
        }
    }
    @FXML
    private void doEditSchool(){
        if(!isEditSchoolPageShow){
            isEditSchoolPageShow=true;
            editSchoolPageStage=new Stage();

            changeViews(editSchoolPageStage,"/GUI/Window/Components/UserInformationEditor/StudentSchoolEditor.fxml");

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
    @FXML
    private void doRecharge(){
        if(!isRechargePageShow){
            isRechargePageShow=true;
            rechargePageStage=new Stage();

            changeViews(rechargePageStage,"/GUI/Window/Components/UserInformationEditor/Recharger.fxml");

            rechargePageStage.setOnCloseRequest(e->{
                isRechargePageShow=false;
                rechargePageStage.close();
            });
            rechargePageStage.setResizable(false);
            rechargePageStage.show();
            resetLocation(rechargePageStage);
        }
        else{
            resetLocation(rechargePageStage);
        }
    }

    private boolean isEditGradePageShow=false;
    private Stage editGradePageStage=null;
    private boolean isEditSchoolPageShow=false;
    private Stage editSchoolPageStage=null;
    private boolean isRechargePageShow=false;
    private Stage rechargePageStage=null;
}
