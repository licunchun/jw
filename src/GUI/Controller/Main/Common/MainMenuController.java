package GUI.Controller.Main.Common;

import GUI.Controller.Main.Student.StudentInformationController;
import GUI.Controller.Main.Teacher.TeacherInformationController;
import GUI.Data.Enum.User.UserType;
import MainPackage.Main;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyCharacterCombination;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;


import static GUI.GUIUtil.StageUtil.*;

public class MainMenuController {
    private final Stage stage= Main.getStage();

    @FXML
    private MenuBar MainMenuBar;
    @FXML
    private Button personalInformation;
    @FXML
    private AnchorPane MainModule;
    @FXML
    private javafx.scene.control.TabPane TabPane;
    private UserType userType=UserType.None;
    private String ID;
    /*
     * Information Page
     */
    private boolean isInformationPageShow =false;
    private UserInformationPageController userInformationPageController;
    private Stage informationPageStage;
    private StudentInformationController studentInformationController;
    private TeacherInformationController teacherInformationController;
    /*
     * Page attributes and their getter&setter
     */
    public void setUserType(UserType userType){
        this.userType=userType;
    }
    public void setID(String ID){
        this.ID=ID;
    }

    @FXML
    public void initialize(){
    }

    @FXML
    private void showInformationPage() {
        if(!isInformationPageShow){
            isInformationPageShow = true;
            {
                informationPageStage = new Stage();

                informationPageStage.setOnHiding(e -> {
                    isInformationPageShow = false;
                    userInformationPageController.closeAllChildren();
                    if (userType == UserType.Student) {
                        studentInformationController.closeAllChildren();
                    }
                    if (userType == UserType.Teacher) {
                        teacherInformationController.closeAllChildren();
                    }
                    informationPageStage.close();
                });
            }//Stage的创建和基本信息的设置
            {
                FXMLLoader loader1=loadScene("/GUI/Window/Main/Common/UserInformationPage.fxml");
                Parent root =newRoot(loader1);
                Scene scene=new Scene(root);
                {
                    ContextMenu contextMenu=initInformationPageContextMenu();
                    scene.setOnContextMenuRequested(e->{
                        contextMenu.show(scene.getWindow(),e.getScreenX(),e.getScreenY());
                    });
                }//右键菜单栏代码块
                informationPageStage.setScene(scene);
                resetLocation(informationPageStage);
                userInformationPageController=loader1.getController();

                userInformationPageController.setStage(informationPageStage);
                userInformationPageController.setID(ID);
                if (userType == UserType.Student) {
                    FXMLLoader loader = loadScene("/GUI/Window/Main/Student/StudentInformation.fxml");
                    userInformationPageController.getChooseAnchorPane().getChildren().add(newRoot(loader));
                    studentInformationController = getController(loader);
                    studentInformationController.setID(ID);
                }
                if (userType == UserType.Teacher) {
                    FXMLLoader loader = loadScene("/GUI/Window/Main/Teacher/TeacherInformation.fxml");
                    userInformationPageController.getChooseAnchorPane().getChildren().add(newRoot(loader));
                    teacherInformationController = getController(loader);
                    teacherInformationController.setID(ID);
                }
            }//Scene的加载和基本信息的设置
            {
                informationPageStage.setResizable(false);
                informationPageStage.show();
                resetLocation(informationPageStage);
            }//Stage的显示
        }
        else{
            resetLocation(informationPageStage);
        }
    }

    private ContextMenu initInformationPageContextMenu(){
        ContextMenu contextMenu=new ContextMenu();
        MenuItem flushMenuItem = new MenuItem("刷新");

        flushMenuItem.setAccelerator(new KeyCodeCombination(KeyCode.R, KeyCodeCombination.CONTROL_DOWN));

        flushMenuItem.setOnAction(event->{
            flushUserInformationPage();
        });

        contextMenu.getItems().addAll(flushMenuItem);

        return contextMenu;
    }

    private void flushUserInformationPage(){
        userInformationPageController.flush();
        if(userType==UserType.Student){
            studentInformationController.flush();
        }
        if(userType==UserType.Teacher){
            teacherInformationController.flush();
        }
    }

    public MenuBar getMenuBar(){
        return MainMenuBar;
    }
    public void closeInformationPage(){
        if(isInformationPageShow){
            informationPageStage.close();
        }
    }
}
