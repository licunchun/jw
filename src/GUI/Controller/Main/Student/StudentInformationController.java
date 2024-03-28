package GUI.Controller.Main.Student;

import GUI.Controller.Components.UserInformationEditor.GradeEditorController;
import GUI.Controller.Components.UserInformationEditor.RechargerController;
import GUI.Controller.Components.UserInformationEditor.StudentSchoolEditorController;
import javafx.fxml.FXML;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import static GUI.GUIUtil.StageUtil.changeViews;
import static GUI.GUIUtil.StageUtil.resetLocation;
import static Sevice.Main.Components.UserServ.UserServ.*;

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
    private String ID;
    /*
     * Children Page
     */
    //Grade editor
    private boolean isEditGradePageShow=false;
    private Stage editGradePageStage=null;
    private GradeEditorController gradeEditorController;
    //School editor
    private boolean isEditSchoolPageShow=false;
    private Stage editSchoolPageStage=null;
    private StudentSchoolEditorController studentSchoolEditorController;
    //Recharger
    private boolean isRechargePageShow=false;
    private Stage rechargePageStage=null;
    private RechargerController rechargerController;
    /*
     * Function
     */
    @FXML
    private void initialize(){}
    @FXML
    private void doEditGrade(){
        if(!isEditGradePageShow){
            isEditGradePageShow=true;
            editGradePageStage=new Stage();

            gradeEditorController=changeViews(editGradePageStage,"/GUI/Window/Components/UserInformationEditor/GradeEditor.fxml");
            gradeEditorController.setID(ID);

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

            studentSchoolEditorController=changeViews(editSchoolPageStage,"/GUI/Window/Components/UserInformationEditor/StudentSchoolEditor.fxml");
            studentSchoolEditorController.setID(ID);

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

            rechargerController=changeViews(rechargePageStage,"/GUI/Window/Components/UserInformationEditor/Recharger.fxml");
            rechargerController.setID(ID);

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
    public void closeAllChildren(){
        if(isEditGradePageShow){
            editGradePageStage.close();
        }
        if(isEditSchoolPageShow){
            editSchoolPageStage.close();
        }
        if(isRechargePageShow){
            rechargePageStage.close();
        }
    }
    public void setID(String ID) {
        this.ID = ID;
    }
    public void flush(){
        Money.setText(getMoney(ID).toString());
        Grade.setText(getGrade(ID).toString());
        Gender.setText(getGender(ID).toString());
        School.setText(getSchool(ID).toString());
    }
}
