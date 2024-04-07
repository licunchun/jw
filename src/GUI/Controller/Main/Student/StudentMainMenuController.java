package GUI.Controller.Main.Student;

import GUI.Controller.Main.Common.Classes.ClassesChoosingPageController;
import GUI.Controller.Main.Common.Classes.ClassesSchedulePageController;
import GUI.Controller.Main.Common.MainMenuController;
import GUI.Controller.Main.Student.Classes.CheckGradePageController;
import GUI.Controller.Main.Student.Classes.DropClassesPageController;
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

public class StudentMainMenuController {
    private static final Stage stage = Main.getStage();
    private MainMenuController mainMenuController;
    private TabPane mainMenuTabPane;
    @FXML
    private AnchorPane subPane;
    private String ID;
    /*
     * Main TabPane
     */
    //Classes Choosing Page
    private boolean isClassesChoosingPageExist = false;
    private ClassesChoosingPageController classesChoosingPageController;
    private Tab classesChoosingTab;
    //Classes Schedule Page
    private boolean isClassesSchedulePageExist = false;
    private ClassesSchedulePageController classesSchedulePageController;
    private Tab classesScheduleTab;
    //Drop Classes Page
    private boolean isDropClassesPageExist = false;
    private DropClassesPageController dropClassesPageController;
    private Tab dropClassesTab;
    //Check Grade Page
    private boolean isCheckGradePageExist = false;
    private CheckGradePageController checkGradePageController;
    private Tab checkGradeTab;

    /*
     * Function
     */
    @FXML
    public void initialize() {
        stage.setTitle("学生选课系统");
        //Tab加载并绑定页面
        classesChoosingTab = new Tab("学生选课");
        {
            FXMLLoader classesChoosingPageLoader = loadScene("/GUI/Window/Main/Common/Classes/ClassesChoosingPage.fxml");
            Parent root = newRoot(classesChoosingPageLoader);
            classesChoosingPageController = getController(classesChoosingPageLoader);


            ContextMenu contextMenu = classesChoosingPageController.classesChoosingPageContextMenu();
            root.setOnContextMenuRequested(e -> contextMenu.show(root, e.getScreenX(), e.getScreenY()));

            classesChoosingPageController.setID(ID);
            classesChoosingPageController.setUserType(UserType.Student);

            classesChoosingTab.setContent(root);
            classesChoosingTab.setOnCloseRequest(e -> {
                classesChoosingPageController.close();
                mainMenuTabPane.getSelectionModel().select(0);
                isClassesChoosingPageExist = false;
                mainMenuTabPane.getTabs().remove(classesChoosingTab);
            });
        }
        classesScheduleTab = new Tab("查看课表");
        {
            FXMLLoader classesSchedulePageLoader = loadScene("/GUI/Window/Main/Common/Classes/ClassesSchedulePage.fxml");
            Parent root = newRoot(classesSchedulePageLoader);
            classesSchedulePageController = getController(classesSchedulePageLoader);

            ContextMenu contextMenu = classesSchedulePageController.classesSchedulePageContextMenu();
            root.setOnContextMenuRequested(e -> contextMenu.show(root, e.getScreenX(), e.getScreenY()));

            classesSchedulePageController.setID(ID);
            classesSchedulePageController.setUserType(UserType.Student);

            classesScheduleTab.setContent(root);
            classesScheduleTab.setOnCloseRequest(e -> {
                mainMenuTabPane.getSelectionModel().select(0);
                isClassesSchedulePageExist = false;
                mainMenuTabPane.getTabs().remove(classesScheduleTab);
            });
        }
        dropClassesTab = new Tab("已选课程");
        {
            FXMLLoader dropClassesPageLoader = loadScene("/GUI/Window/Main/Student/Classes/DropClassesPage.fxml");
            Parent root = newRoot(dropClassesPageLoader);
            dropClassesPageController = getController(dropClassesPageLoader);


            ContextMenu contextMenu = dropClassesPageController.dropClassesPageContextMenu();
            root.setOnContextMenuRequested(e -> contextMenu.show(root, e.getScreenX(), e.getScreenY()));

            dropClassesPageController.setID(ID);

            dropClassesTab.setContent(root);
            dropClassesTab.setOnCloseRequest(e -> {
                dropClassesPageController.close();
                mainMenuTabPane.getSelectionModel().select(0);
                isDropClassesPageExist = false;
                mainMenuTabPane.getTabs().remove(dropClassesTab);
            });
        }
        checkGradeTab = new Tab("查看成绩");
        {
            FXMLLoader checkGradePageLoader = loadScene("/GUI/Window/Main/Student/Classes/CheckGradePage.fxml");
            Parent root = newRoot(checkGradePageLoader);
            checkGradePageController = getController(checkGradePageLoader);

            ContextMenu contextMenu = checkGradePageController.checkGradePageContextMenu();
            root.setOnContextMenuRequested(e -> contextMenu.show(root, e.getScreenX(), e.getScreenY()));

            checkGradeTab.setContent(root);
            checkGradeTab.setOnCloseRequest(e -> {
                mainMenuTabPane.getSelectionModel().select(0);
                isCheckGradePageExist = false;
                mainMenuTabPane.getTabs().remove(checkGradeTab);
            });
        }
        //子区域加载，mainMenuController赋值
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI/Window/Main/Common/MainMenu.fxml"));
            AnchorPane subContent = loader.load();
            mainMenuController = loader.getController();
            mainMenuTabPane = mainMenuController.getTabPane();

            subPane.getChildren().add(subContent);
            {
                MenuBar menuBar = mainMenuController.getMenuBar();

                Menu ClasesChoosingMenu = new Menu("选课");
                {
                    MenuItem OpenClassesChoosingPage = new MenuItem("学生选课");

                    OpenClassesChoosingPage.setAccelerator(new KeyCodeCombination(KeyCode.C, KeyCombination.CONTROL_DOWN));

                    OpenClassesChoosingPage.setOnAction(e -> {
                        if (!isClassesChoosingPageExist) {
                            isClassesChoosingPageExist = true;
                            mainMenuTabPane.getTabs().add(classesChoosingTab);
                        } else {
                            mainMenuTabPane.getSelectionModel().select(classesChoosingTab);
                        }
                    });

                    ClasesChoosingMenu.getItems().addAll(OpenClassesChoosingPage);
                }//选课里面的子菜单

                Menu ChoiceClassesMenu = new Menu("已选课程");
                {
                    MenuItem OpenClassesSchedulePage = new MenuItem("查看课表");
                    MenuItem OpenDropClassesPage = new MenuItem("已选课程");
                    MenuItem OpenCheckGradePage = new MenuItem("查看成绩");

                    OpenClassesSchedulePage.setAccelerator(new KeyCodeCombination(KeyCode.S, KeyCombination.CONTROL_DOWN));
                    OpenDropClassesPage.setAccelerator(new KeyCodeCombination(KeyCode.D, KeyCombination.CONTROL_DOWN));
                    OpenCheckGradePage.setAccelerator(new KeyCodeCombination(KeyCode.G, KeyCombination.CONTROL_DOWN));

                    OpenClassesSchedulePage.setOnAction(e -> {
                        if (!isClassesSchedulePageExist) {
                            isClassesSchedulePageExist = true;
                            mainMenuTabPane.getTabs().add(classesScheduleTab);
                        } else {
                            mainMenuTabPane.getSelectionModel().select(classesScheduleTab);
                        }
                    });
                    OpenDropClassesPage.setOnAction(e -> {
                        if (!isDropClassesPageExist) {
                            isDropClassesPageExist = true;
                            mainMenuTabPane.getTabs().add(dropClassesTab);
                        } else {
                            mainMenuTabPane.getSelectionModel().select(dropClassesTab);
                        }
                    });
                    OpenCheckGradePage.setOnAction(e -> {
                        if (!isCheckGradePageExist) {
                            isCheckGradePageExist = true;
                            mainMenuTabPane.getTabs().add(checkGradeTab);
                        } else {
                            mainMenuTabPane.getSelectionModel().select(checkGradeTab);
                        }
                    });

                    ChoiceClassesMenu.getItems().addAll(OpenClassesSchedulePage, OpenDropClassesPage, OpenCheckGradePage);
                }//已选课程里面的子菜单

                Menu PageMenu = new Menu("页面");
                {
                    MenuItem Reload = new MenuItem("重新加载页面");

                    Reload.setAccelerator(new KeyCodeCombination(KeyCode.L, KeyCombination.CONTROL_DOWN));

                    Reload.setOnAction(e -> reloadPage());

                    PageMenu.getItems().addAll(Reload);
                }//页面里的子菜单

                menuBar.getMenus().addAll(ClasesChoosingMenu, ChoiceClassesMenu, PageMenu);
            }//MenuBar配置
        } catch (IOException e) {
            Logger logger = Logger.getLogger("MyLogger");
            logger.log(Level.SEVERE, "An error occurred", e);
        }
        //mainMenuController赋值
        mainMenuController.setUserType(UserType.Student);
        mainMenuController.setID(ID);
    }

    public void setID(String ID) {
        this.ID = ID;
        mainMenuController.setID(ID);
    }

    private void reloadPage() {
        mainMenuController.closeInformationPage();
        openMainPage(stage, UserType.Student, ID);
    }
}
