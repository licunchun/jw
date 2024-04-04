package GUI.Controller.Main.Admin;

import GUI.Controller.Main.Common.Classes.ClassesChoosingPageController;
import GUI.Data.Enum.User.UserType;
import GUI.Controller.Main.Common.MainMenuController;
import MainPackage.Main;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

import static GUI.GUIUtil.MainPageUtil.openMainPage;
import static GUI.GUIUtil.StageUtil.*;

public class AdminMainMenuController {
    private static final Stage stage= Main.getStage();

    private MainMenuController mainMenuController;
    private TabPane mainMenuTabPane;
    @FXML
    private AnchorPane subPane;
    private String ID;
    /*
     * Main TabPane
     */
    //Manage Student Page
    private boolean isManageStudentPageExist=false;
    private ManageUserPageController manageStudentPageController;
    private Tab manageStudentTab;
    //Manage Teacher Page
    private boolean isManageTeacherPageExist=false;
    private ManageUserPageController manageTeacherPageController;
    private Tab manageTeacherTab;
    //Manage Admin Page
    private boolean isManageAdminPageExist=false;
    private ManageUserPageController manageAdminPageController;
    private Tab manageAdminTab;
    //Manage Classes Page
    private boolean isClassesManagePageExist=false;
    private ClassesChoosingPageController classesManagePageController;
    private Tab classesManageTab;

    /*
     * Function
     */
    public void setID(String ID){
        this.ID=ID;
    }
    @FXML
    public void initialize(){
        stage.setTitle("课程管理系统");
        //Tab加载并绑定页面
        classesManageTab = new Tab("管理课程");
        {
            FXMLLoader classesManagePageLoader = loadScene("/GUI/Window/Main/Common/Classes/ClassesChoosingPage.fxml");
            Parent root = newRoot(classesManagePageLoader);
            classesManagePageController = getController(classesManagePageLoader);


            ContextMenu contextMenu=classesManagePageController.classesChoosingPageContextMenu();
            root.setOnContextMenuRequested(e-> contextMenu.show(root,e.getScreenX(),e.getScreenY()));

            classesManagePageController.setID(ID);
            classesManagePageController.setUserType(UserType.Admin);

            classesManageTab.setContent(root);
            classesManageTab.setOnCloseRequest(e->{
                classesManagePageController.close();
                mainMenuTabPane.getSelectionModel().select(0);
                isClassesManagePageExist=false;
                mainMenuTabPane.getTabs().remove(classesManageTab);
            });
        }
        manageStudentTab = new Tab("管理学生");
        {
            FXMLLoader manageStudentPageLoader = loadScene("/GUI/Window/Main/Admin/ManageUserPage.fxml");
            Parent root = newRoot(manageStudentPageLoader);

            ContextMenu contextMenu=manageStudentPageController.manageUserPageContextMenu();
            root.setOnContextMenuRequested(e-> contextMenu.show(root,e.getScreenX(),e.getScreenY()));

            manageStudentPageController.setUserType(UserType.Student);

            manageStudentPageController = getController(manageStudentPageLoader);
            manageStudentTab.setContent(root);
            manageStudentTab.setOnCloseRequest(e -> {
                manageStudentPageController.close();
                mainMenuTabPane.getSelectionModel().select(0);
                isManageStudentPageExist = false;
                mainMenuTabPane.getTabs().remove(manageStudentTab);
            });
        }
        manageTeacherTab = new Tab("管理教师");
        {
            FXMLLoader manageTeacherPageLoader = loadScene("/GUI/Window/Main/Admin/ManageUserPage.fxml");
            Parent root = newRoot(manageTeacherPageLoader);

            ContextMenu contextMenu=manageTeacherPageController.manageUserPageContextMenu();
            root.setOnContextMenuRequested(e-> contextMenu.show(root,e.getScreenX(),e.getScreenY()));

            manageTeacherPageController.setUserType(UserType.Teacher);

            manageTeacherPageController = getController(manageTeacherPageLoader);
            manageTeacherTab.setContent(root);
            manageTeacherTab.setOnCloseRequest(e -> {
                manageTeacherPageController.close();
                mainMenuTabPane.getSelectionModel().select(0);
                isManageTeacherPageExist = false;
                mainMenuTabPane.getTabs().remove(manageTeacherTab);
            });
        }
        manageAdminTab = new Tab("管理管理员");
        {
            FXMLLoader manageAdminPageLoader = loadScene("/GUI/Window/Main/Admin/ManageUserPage.fxml");
            Parent root = newRoot(manageAdminPageLoader);

            ContextMenu contextMenu=manageAdminPageController.manageUserPageContextMenu();
            root.setOnContextMenuRequested(e-> contextMenu.show(root,e.getScreenX(),e.getScreenY()));

            manageAdminPageController.setUserType(UserType.Admin);

            manageAdminPageController = getController(manageAdminPageLoader);
            manageAdminTab.setContent(root);
            manageAdminTab.setOnCloseRequest(e -> {
                manageAdminPageController.close();
                mainMenuTabPane.getSelectionModel().select(0);
                isManageAdminPageExist = false;
                mainMenuTabPane.getTabs().remove(manageAdminTab);
            });
        }
        //子区域加载，mainMenuController赋值
        try {
            FXMLLoader loader=new FXMLLoader(getClass().getResource("/GUI/Window/Main/Common/MainMenu.fxml"));
            AnchorPane subContent=loader.load();
            mainMenuController=loader.getController();
            mainMenuTabPane=mainMenuController.getTabPane();

            Menu ClasesMenu = new Menu("课程");
            {
                MenuItem OpenClassesManagePage=new MenuItem("管理课程");

                OpenClassesManagePage.setAccelerator(new KeyCodeCombination(KeyCode.C, KeyCombination.CONTROL_DOWN));

                OpenClassesManagePage.setOnAction(e->{
                    if(!isClassesManagePageExist){
                        isClassesManagePageExist=true;
                        mainMenuTabPane.getTabs().add(classesManageTab);
                    }
                    else{
                        mainMenuTabPane.getSelectionModel().select(classesManageTab);
                    }
                });

                ClasesMenu.getItems().addAll(OpenClassesManagePage);
            }//选课里面的子菜单
            Menu ClassesTeachingMenu = new Menu("用户");
            {
                MenuItem OpenManageStudentPage=new MenuItem("管理学生");
                MenuItem OpenManageTeacherPage=new MenuItem("管理教师");
                MenuItem OpenManageAdminPage=new MenuItem("管理管理员");

                OpenManageStudentPage.setAccelerator(new KeyCodeCombination(KeyCode.S, KeyCombination.CONTROL_DOWN));
                OpenManageTeacherPage.setAccelerator(new KeyCodeCombination(KeyCode.T, KeyCombination.CONTROL_DOWN));
                OpenManageAdminPage.setAccelerator(new KeyCodeCombination(KeyCode.A, KeyCombination.CONTROL_DOWN));

                OpenManageStudentPage.setOnAction(e->{
                    if(!isManageStudentPageExist) {
                        isManageStudentPageExist=true;
                        mainMenuTabPane.getTabs().add(manageStudentTab);
                    }
                    else{
                        mainMenuTabPane.getSelectionModel().select(manageStudentTab);
                    }
                });
                OpenManageTeacherPage.setOnAction(e->{
                    if(!isManageTeacherPageExist) {
                        isManageTeacherPageExist=true;
                        mainMenuTabPane.getTabs().add(manageTeacherTab);
                    }
                    else{
                        mainMenuTabPane.getSelectionModel().select(manageTeacherTab);
                    }
                });
                OpenManageAdminPage.setOnAction(e->{
                    if(!isManageAdminPageExist) {
                        isManageAdminPageExist=true;
                        mainMenuTabPane.getTabs().add(manageAdminTab);
                    }
                    else{
                        mainMenuTabPane.getSelectionModel().select(manageAdminTab);
                    }
                });

                ClassesTeachingMenu.getItems().addAll(OpenManageStudentPage,OpenManageTeacherPage,OpenManageAdminPage);
            }//管理人员里面的子菜单
            Menu PageMenu = new Menu("页面");
            {
                MenuItem Reload = new MenuItem("重新加载页面");

                Reload.setAccelerator(new KeyCodeCombination(KeyCode.L, KeyCombination.CONTROL_DOWN));

                Reload.setOnAction(e-> reloadPage());

                PageMenu.getItems().addAll(Reload);
            }//页面里的子菜单
            subPane.getChildren().add(subContent);
            {
                MenuBar menuBar=mainMenuController.getMenuBar();
                menuBar.getMenus().addAll(ClasesMenu,ClassesTeachingMenu,PageMenu);
            }//MenuBar配置
        } catch (IOException e){
            e.printStackTrace();
        }
        //mainMenuController赋值
        mainMenuController.setUserType(UserType.Admin);
        mainMenuController.setID(ID);
    }
    private void reloadPage(){
        openMainPage(stage,UserType.Admin,ID);
    }
}
