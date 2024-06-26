package GUI.Controller.Main.Common;

import GUI.Controller.Components.UserInformationEditor.NameEditorController;
import GUI.Controller.Components.UserInformationEditor.PasswordChangerController;
import javafx.fxml.FXML;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import static GUI.GUIUtil.StageUtil.changeViews;
import static GUI.GUIUtil.StageUtil.resetLocation;
import static Service.Main.Components.UserServ.UserServ.getName;

public class UserInformationPageController {
    @FXML
    private Label ShowName;
    @FXML
    private Label ShowID;
    @FXML
    private AnchorPane chooseAnchorPane;
    @FXML
    private VBox UserInformationBox;
    @FXML
    private Hyperlink ChangePassword;
    @FXML
    private Hyperlink EditName;
    private Stage stage;
    private String ID;
    /*
     * Children Page
     */

    //Edit name page
    private boolean isEditNamePageShow = false;
    private Stage editNamePageStage = null;
    private NameEditorController nameEditorController;

    //Edit password Page
    private boolean isChangePasswordPageShow = false;
    private Stage changePasswordPageStage = null;
    private PasswordChangerController passwordChangerController;
    /*
     * Function
     */

    @FXML
    private void doChangePassword() {
        if (!isChangePasswordPageShow) {
            isChangePasswordPageShow = true;
            changePasswordPageStage = new Stage();

            passwordChangerController = changeViews(changePasswordPageStage, "/GUI/Window/Components/UserInformationEditor/PasswordChanger.fxml");
            passwordChangerController.setID(ID);
            passwordChangerController.setStage(changePasswordPageStage);

            changePasswordPageStage.setOnHiding(e -> {
                isChangePasswordPageShow = false;
                changePasswordPageStage.close();
                flush();
            });
            changePasswordPageStage.show();
            changePasswordPageStage.setResizable(false);
            resetLocation(changePasswordPageStage);
        } else {
            resetLocation(changePasswordPageStage);
        }
    }

    @FXML
    private void doEditName() {
        if (!isEditNamePageShow) {
            isEditNamePageShow = true;
            editNamePageStage = new Stage();

            nameEditorController = changeViews(editNamePageStage, "/GUI/Window/Components/UserInformationEditor/NameEditor.fxml");
            nameEditorController.setID(ID);
            nameEditorController.setStage(editNamePageStage);

            editNamePageStage.setOnHiding(e -> {
                isEditNamePageShow = false;
                editNamePageStage.close();
                flush();
            });
            editNamePageStage.show();
            editNamePageStage.setResizable(false);
            resetLocation(editNamePageStage);
        } else {
            resetLocation(editNamePageStage);
        }
    }

    public AnchorPane getChooseAnchorPane() {
        return chooseAnchorPane;
    }

    public void closeAllChildren() {
        if (isEditNamePageShow) {
            editNamePageStage.close();
        }
        if (isChangePasswordPageShow) {
            changePasswordPageStage.close();
        }
    }

    public void setStage(Stage stage) {
        this.stage = stage;
        this.stage.setTitle("个人信息");
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public void flush() {
        ShowID.setText(ID);
        if (getName(ID) == null) {
            throw new RuntimeException("UserInformationPageController.java:运行过程中账户ID改变，故停止程序运行");
        }
        ShowName.setText(getName(ID));
    }
}
