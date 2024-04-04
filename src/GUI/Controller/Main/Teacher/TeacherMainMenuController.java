package GUI.Controller.Main.Teacher;

import GUI.Controller.Main.Common.Classes.ClassesSchedulePageController;
import GUI.Controller.Main.Teacher.Classes.AssignGradePageController;
import GUI.Controller.Main.Teacher.Classes.ProposeCoursePageController;
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

public class TeacherMainMenuController {
    private static final Stage stage= Main.getStage();

    private MainMenuController mainMenuController;
    private TabPane mainMenuTabPane;
    @FXML
    private AnchorPane subPane;
    private String ID;
    /*
     * Main TabPane
     */
    //Classes Schedule Page
    private boolean isClassesSchedulePageExist=false;
    private ClassesSchedulePageController classesSchedulePageController;
    private Tab classesScheduleTab;
    //Propose Course Page
    private boolean isProposeCoursePageExist=false;
    private ProposeCoursePageController proposeCoursePageController;
    private Tab proposeCourseTab;
    //Assign Grade Page
    private boolean isAssignGradePageExist=false;
    private AssignGradePageController assignGradePageController;
    private Tab assignGradeTab;

    /*
     * Function
     */
    public void setID(String ID){
        this.ID=ID;
    }
    @FXML
    public void initialize(){
        stage.setTitle("教师授课系统");
        //Tab加载并绑定页面
        classesScheduleTab = new Tab("查看课表");
        {
            FXMLLoader classesSchedulePageLoader = loadScene("/GUI/Window/Main/Common/Classes/ClassesSchedulePage.fxml");
            Parent root = newRoot(classesSchedulePageLoader);
            classesSchedulePageController = getController(classesSchedulePageLoader);

            ContextMenu contextMenu=classesSchedulePageController.classesSchedulePageContextMenu();
            root.setOnContextMenuRequested(e-> contextMenu.show(root,e.getScreenX(),e.getScreenY()));

            classesSchedulePageController.setID(ID);
            classesSchedulePageController.setUserType(UserType.Teacher);

            classesScheduleTab.setContent(root);
            classesScheduleTab.setOnCloseRequest(e -> {
                mainMenuTabPane.getSelectionModel().select(0);
                isClassesSchedulePageExist = false;
                mainMenuTabPane.getTabs().remove(classesScheduleTab);
            });
        }
        proposeCourseTab = new Tab("申请开课");
        {
            FXMLLoader proposeCoursePageLoader = loadScene("/GUI/Window/Main/Teacher/Classes/ProposeCoursePage.fxml");
            Parent root = newRoot(proposeCoursePageLoader);

            //这里不需要右键菜单

            proposeCoursePageController = getController(proposeCoursePageLoader);
            proposeCourseTab.setContent(root);
            proposeCourseTab.setOnCloseRequest(e -> {
                mainMenuTabPane.getSelectionModel().select(0);
                isProposeCoursePageExist = false;
                mainMenuTabPane.getTabs().remove(proposeCourseTab);
            });
        }
        assignGradeTab = new Tab("给分");
        {
            FXMLLoader assignGradePageLoader = loadScene("/GUI/Window/Main/Teacher/Classes/AssignGradePage.fxml");
            Parent root = newRoot(assignGradePageLoader);

            //等lcc的界面

            assignGradePageController = getController(assignGradePageLoader);
            assignGradeTab.setContent(root);
            assignGradeTab.setOnCloseRequest(e -> {
                mainMenuTabPane.getSelectionModel().select(0);
                isAssignGradePageExist = false;
                mainMenuTabPane.getTabs().remove(assignGradeTab);
            });
        }

        //子区域加载，mainMenuController赋值
        try {
            FXMLLoader loader=new FXMLLoader(getClass().getResource("/GUI/Window/Main/Common/MainMenu.fxml"));
            AnchorPane subContent=loader.load();
            mainMenuController=loader.getController();
            mainMenuTabPane=mainMenuController.getTabPane();

            subPane.getChildren().add(subContent);
            {
                MenuBar menuBar=mainMenuController.getMenuBar();

                Menu ClassesTeachingMenu = new Menu("教授课程");
                {
                    MenuItem OpenClassesSchedulePage=new MenuItem("查看课表");
                    MenuItem OpenProposeCoursePage=new MenuItem("申请开课");
                    MenuItem OpenAssignGradePage=new MenuItem("给分");

                    OpenClassesSchedulePage.setAccelerator(new KeyCodeCombination(KeyCode.S, KeyCombination.CONTROL_DOWN));
                    OpenProposeCoursePage.setAccelerator(new KeyCodeCombination(KeyCode.P, KeyCombination.CONTROL_DOWN));
                    OpenAssignGradePage.setAccelerator(new KeyCodeCombination(KeyCode.A, KeyCombination.CONTROL_DOWN));

                    OpenClassesSchedulePage.setOnAction(e->{
                        if(!isClassesSchedulePageExist) {
                            isClassesSchedulePageExist=true;
                            mainMenuTabPane.getTabs().add(classesScheduleTab);
                        }
                        else{
                            mainMenuTabPane.getSelectionModel().select(classesScheduleTab);
                        }
                    });
                    OpenProposeCoursePage.setOnAction(e->{
                        if(!isProposeCoursePageExist) {
                            isProposeCoursePageExist=true;
                            mainMenuTabPane.getTabs().add(proposeCourseTab);
                        }
                        else{
                            mainMenuTabPane.getSelectionModel().select(proposeCourseTab);
                        }
                    });
                    OpenAssignGradePage.setOnAction(e->{
                        if(!isAssignGradePageExist) {
                            isAssignGradePageExist=true;
                            mainMenuTabPane.getTabs().add(assignGradeTab);
                        }
                        else{
                            mainMenuTabPane.getSelectionModel().select(assignGradeTab);
                        }
                    });

                    ClassesTeachingMenu.getItems().addAll(OpenClassesSchedulePage,OpenProposeCoursePage,OpenAssignGradePage);
                }//教授课程里面的子菜单

                Menu PageMenu = new Menu("页面");
                {
                    MenuItem Reload = new MenuItem("重新加载页面");

                    Reload.setAccelerator(new KeyCodeCombination(KeyCode.L, KeyCombination.CONTROL_DOWN));

                    Reload.setOnAction(e-> reloadPage());

                    PageMenu.getItems().addAll(Reload);
                }//页面里的子菜单
                menuBar.getMenus().addAll(ClassesTeachingMenu,PageMenu);
            }//MenuBar配置
        } catch (IOException e){
            e.printStackTrace();
        }
        //mainMenuController赋值
        mainMenuController.setUserType(UserType.Teacher);
        mainMenuController.setID(ID);
    }
    private void reloadPage(){
        openMainPage(stage,UserType.Teacher,ID);
    }
}
