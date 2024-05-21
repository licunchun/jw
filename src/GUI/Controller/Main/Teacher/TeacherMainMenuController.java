package GUI.Controller.Main.Teacher;

import GUI.Controller.Main.Common.Classes.ClassesSchedulePageController;
import GUI.Controller.Main.Common.Classes.ProposeCoursePageController;
import GUI.Controller.Main.Common.MainMenuController;
import GUI.Controller.Main.Teacher.Classes.TeacherScoreMainPageController;
import GUI.Controller.Main.Teacher.Classes.TeacherScoreSubPageController;
import GUI.Data.Enum.User.UserType;
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
import java.util.logging.Level;
import java.util.logging.Logger;

import static GUI.GUIUtil.MainPageUtil.openMainPage;
import static GUI.GUIUtil.StageUtil.*;

public class TeacherMainMenuController {
    private static final Stage stage = Main.getStage();

    private MainMenuController mainMenuController;
    private TabPane mainMenuTabPane;
    @FXML
    private AnchorPane subPane;
    private String ID;

    //Propose Course Page
    private boolean isProposeCoursePageExist = false;
    private ProposeCoursePageController proposeCoursePageController;
    private Stage proposeCoursePageStage;
    /*
     * Main TabPane
     */
    //Classes Schedule Page
    private boolean isClassesSchedulePageExist = false;
    private ClassesSchedulePageController classesSchedulePageController;
    private Tab classesScheduleTab;
    //Assign Grade Main Page
    private boolean isTeacherScoreMainPageExist = false;
    private TeacherScoreMainPageController teacherScoreMainPageController;
    private Tab teacherScoreMainPageTab;
    //Assign Grade Sub Page
    private boolean isTeacherScoreSubPageExist = false;
    private TeacherScoreSubPageController teacherScoreSubPageController;
    private Tab teacherScoreSubPageTab;

    /*
     * Function
     */
    public void setID(String ID) {
        this.ID = ID;
        mainMenuController.setID(ID);
        classesSchedulePageController.setID(ID);
    }

    @FXML
    public void initialize() {
        stage.setTitle("教师授课系统");
        //Tab加载并绑定页面
        classesScheduleTab = new Tab("查看课表");
        {
            FXMLLoader classesSchedulePageLoader = loadScene("/GUI/Window/Main/Common/Classes/ClassesSchedulePage.fxml");
            Parent root = newRoot(classesSchedulePageLoader);
            classesSchedulePageController = getController(classesSchedulePageLoader);

            ContextMenu contextMenu = classesSchedulePageController.classesSchedulePageContextMenu();
            root.setOnContextMenuRequested(e -> contextMenu.show(root, e.getScreenX(), e.getScreenY()));

            classesSchedulePageController.setID(ID);
            classesSchedulePageController.setUserType(UserType.Teacher);

            classesScheduleTab.setContent(root);
            classesScheduleTab.setOnCloseRequest(e -> {
                mainMenuTabPane.getSelectionModel().select(0);
                isClassesSchedulePageExist = false;
                mainMenuTabPane.getTabs().remove(classesScheduleTab);
            });
        }
        teacherScoreMainPageTab = new Tab("评分页面");
        {
            FXMLLoader teacherScoreMainPageLoader = loadScene("/GUI/Window/Main/Teacher/Classes/TeacherScoreMainPage.fxml");
            Parent root = newRoot(teacherScoreMainPageLoader);
            teacherScoreMainPageController = getController(teacherScoreMainPageLoader);

            ContextMenu contextMenu = teacherScoreMainPageController.teacherCourseMainPageContextMenu();
            root.setOnContextMenuRequested(e -> contextMenu.show(root, e.getScreenX(), e.getScreenY()));

            teacherScoreMainPageController.setID(ID);
            teacherScoreMainPageController.setTabPane(mainMenuTabPane);
            teacherScoreMainPageController.setTeacherScoreSubPageController(this);
            //    teacherScoreMainPageController.setPrimaryStage(stage);

            teacherScoreMainPageTab.setContent(root);
            teacherScoreMainPageTab.setOnCloseRequest(e -> {
                mainMenuTabPane.getSelectionModel().select(0);
                isTeacherScoreMainPageExist = false;
                mainMenuTabPane.getTabs().remove(teacherScoreMainPageTab);
            });
        }
//        teacherScoreSubPageTab = new Tab("具体给分页面");
//        {
//            FXMLLoader teacherScoreSubPageLoader = loadScene("/GUI/Window/Main/Teacher/Classes/TeacherScoreSubPage.fxml");
//            Parent root = newRoot(teacherScoreSubPageLoader);
//            teacherScoreSubPageController = getController(teacherScoreSubPageLoader);
//
//            ContextMenu contextMenu = teacherScoreSubPageController.teacherCourseSubPageContextMenu();
//            root.setOnContextMenuRequested(e -> contextMenu.show(root, e.getScreenX(), e.getScreenY()));
//
//            teacherScoreSubPageController.setID(ID);
//        //    teacherScoreSubPageController.setTeacherScoreSubPageController(buttonId);
//            //TODO
//        }

        //子区域加载，mainMenuController赋值
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI/Window/Main/Common/MainMenu.fxml"));
            AnchorPane subContent = loader.load();
            mainMenuController = loader.getController();
            mainMenuTabPane = mainMenuController.getTabPane();

            subPane.getChildren().add(subContent);
            {
                MenuBar menuBar = mainMenuController.getMenuBar();

                Menu ClassesTeachingMenu = new Menu("教授课程");
                {
                    MenuItem OpenClassesSchedulePage = new MenuItem("查看课表");
                    MenuItem OpenProposeCoursePage = new MenuItem("申请开课");
                    MenuItem OpenTeacherScoreMainPage = new MenuItem("评分主页面");
                    MenuItem OpenTeacherScoreSubPage = new MenuItem("评分具体界面");

                    OpenClassesSchedulePage.setAccelerator(new KeyCodeCombination(KeyCode.S, KeyCombination.CONTROL_DOWN));
                    OpenProposeCoursePage.setAccelerator(new KeyCodeCombination(KeyCode.P, KeyCombination.CONTROL_DOWN));
                    OpenTeacherScoreMainPage.setAccelerator(new KeyCodeCombination(KeyCode.A, KeyCombination.CONTROL_DOWN));
                    OpenTeacherScoreSubPage.setAccelerator(new KeyCodeCombination(KeyCode.R, KeyCombination.CONTROL_DOWN));

                    OpenClassesSchedulePage.setOnAction(e -> {
                        if (!isClassesSchedulePageExist) {
                            isClassesSchedulePageExist = true;
                            mainMenuTabPane.getTabs().add(classesScheduleTab);
                            classesSchedulePageController.flush();
                        } else {
                            mainMenuTabPane.getSelectionModel().select(classesScheduleTab);
                        }
                    });
                    OpenProposeCoursePage.setOnAction(e -> {
                        if (!isProposeCoursePageExist) {
                            isProposeCoursePageExist = true;
                            proposeCoursePageStage = new Stage();
                            proposeCoursePageStage.setTitle("新建课程");

                            proposeCoursePageController = changeViews(proposeCoursePageStage, "/GUI/Window/Main/Common/Classes/ProposeCoursePage.fxml");
                            proposeCoursePageController.setUserType(UserType.Teacher);
                            proposeCoursePageController.setStage(proposeCoursePageStage);
                            proposeCoursePageController.setID(ID);
                            proposeCoursePageController.flush();

                            proposeCoursePageStage.setOnHiding(event -> {
                                proposeCoursePageController.close();
                                proposeCoursePageStage.close();
                            });

                            proposeCoursePageStage.show();
                            resetLocation(proposeCoursePageStage);
                        } else {
                            resetLocation(proposeCoursePageStage);
                        }
                    });
                    OpenTeacherScoreMainPage.setOnAction(e -> {
                        if (!isTeacherScoreMainPageExist) {
                            isTeacherScoreMainPageExist = true;
                            mainMenuTabPane.getTabs().add(teacherScoreMainPageTab);
                            teacherScoreMainPageController.flush();
                        } else {
                            mainMenuTabPane.getSelectionModel().select(teacherScoreMainPageTab);
                        }
                    });
                    OpenTeacherScoreSubPage.setOnAction(e -> {
                        if (isTeacherScoreSubPageExist) {
                            mainMenuTabPane.getTabs().add(teacherScoreSubPageTab);
                            teacherScoreSubPageController.flush();
                            mainMenuTabPane.getSelectionModel().select(teacherScoreSubPageTab);
                        }
                    });
                    ClassesTeachingMenu.getItems().addAll(OpenClassesSchedulePage, OpenProposeCoursePage, OpenTeacherScoreMainPage, OpenTeacherScoreSubPage);
                }//教授课程里面的子菜单

                Menu PageMenu = new Menu("页面");
                {
                    MenuItem Reload = new MenuItem("重新加载页面");

                    Reload.setAccelerator(new KeyCodeCombination(KeyCode.L, KeyCombination.CONTROL_DOWN));

                    Reload.setOnAction(e -> reloadPage());

                    PageMenu.getItems().addAll(Reload);
                }//页面里的子菜单
                menuBar.getMenus().addAll(ClassesTeachingMenu, PageMenu);
            }//MenuBar配置
        } catch (IOException e) {
            Logger logger = Logger.getLogger("MyLogger");
            logger.log(Level.SEVERE, "An error occurred", e);
        }
        //mainMenuController赋值
        mainMenuController.setUserType(UserType.Teacher);
        mainMenuController.setID(ID);
    }

    public void setIsTeacherScoreSubPageExist() {
        isTeacherScoreSubPageExist = true;
    }

    private void reloadPage() {
        openMainPage(stage, UserType.Teacher, ID);
    }

    public TabPane getMainMenuTabPane() {
        return mainMenuTabPane;
    }
}
