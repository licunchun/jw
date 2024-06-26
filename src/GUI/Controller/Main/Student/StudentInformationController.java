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
import static Service.Main.Components.UserServ.UserServ.*;

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
    private boolean isEditGradePageShow = false;
    private Stage editGradePageStage = null;
    private GradeEditorController gradeEditorController;
    //School editor
    private boolean isEditSchoolPageShow = false;
    private Stage editSchoolPageStage = null;
    private StudentSchoolEditorController studentSchoolEditorController;
    //Recharger
    private boolean isRechargePageShow = false;
    private Stage rechargePageStage = null;
    private RechargerController rechargerController;

    /*
     * Function
     */
    @FXML
    private void doEditGrade() {
        if (!isEditGradePageShow) {
            isEditGradePageShow = true;
            editGradePageStage = new Stage();

            gradeEditorController = changeViews(editGradePageStage, "/GUI/Window/Components/UserInformationEditor/GradeEditor.fxml");
            gradeEditorController.setID(ID);
            gradeEditorController.setStage(editGradePageStage);

            editGradePageStage.setOnHiding(e -> {
                isEditGradePageShow = false;
                editGradePageStage.close();
                flush();
            });
            editGradePageStage.setResizable(false);
            editGradePageStage.show();
            resetLocation(editGradePageStage);
        } else {
            resetLocation(editGradePageStage);
        }
    }

    @FXML
    private void doEditSchool() {
        if (!isEditSchoolPageShow) {
            isEditSchoolPageShow = true;
            editSchoolPageStage = new Stage();

            studentSchoolEditorController = changeViews(editSchoolPageStage, "/GUI/Window/Components/UserInformationEditor/StudentSchoolEditor.fxml");
            studentSchoolEditorController.setID(ID);
            studentSchoolEditorController.setStage(editSchoolPageStage);

            editSchoolPageStage.setOnHiding(e -> {
                isEditSchoolPageShow = false;
                editSchoolPageStage.close();
                flush();
            });
            editSchoolPageStage.setResizable(false);
            editSchoolPageStage.show();
            resetLocation(editSchoolPageStage);
        } else {
            resetLocation(editSchoolPageStage);
        }
    }

    @FXML
    private void doRecharge() {
        if (!isRechargePageShow) {
            isRechargePageShow = true;
            rechargePageStage = new Stage();

            rechargerController = changeViews(rechargePageStage, "/GUI/Window/Components/UserInformationEditor/Recharger.fxml");
            rechargerController.setID(ID);
            rechargerController.setStage(rechargePageStage);

            rechargePageStage.setOnHiding(e -> {
                isRechargePageShow = false;
                rechargePageStage.close();
                flush();
            });
            rechargePageStage.setResizable(false);
            rechargePageStage.show();
            resetLocation(rechargePageStage);
        } else {
            resetLocation(rechargePageStage);
        }
    }

    public void closeAllChildren() {
        if (isEditGradePageShow) {
            editGradePageStage.close();
        }
        if (isEditSchoolPageShow) {
            editSchoolPageStage.close();
        }
        if (isRechargePageShow) {
            rechargePageStage.close();
        }
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public void flush() {
        getMoney(ID);
        Money.setText(getMoney(ID).toString());
        if (getGrade(ID) == null) {
            throw new RuntimeException("StudentInformationController.java:运行过程中账户ID改变，故停止程序运行");
        }
        Grade.setText(getGrade(ID).toString());
        if (getGender(ID) == null) {
            throw new RuntimeException("StudentInformationController.java:运行过程中账户ID改变，故停止程序运行");
        }
        Gender.setText(getGender(ID).toString());
        if (getSchool(ID) == null) {
            throw new RuntimeException("StudentInformationController.java:运行过程中账户ID改变，故停止程序运行");
        }
        School.setText(getSchool(ID).toString());
    }
}
