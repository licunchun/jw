package GUI.Controller.Main.Admin;

import GUI.Controller.Components.UserInformationEditor.*;
import GUI.Data.DataPackage.UserInformation.UserInformationForTable;
import GUI.Data.Enum.GUI.Scene.EditUserPage;
import GUI.Data.Enum.User.UserType;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import static GUI.GUIUtil.StageUtil.changeViews;
import static GUI.GUIUtil.StageUtil.resetLocation;
import static Sevice.Main.Components.UserServ.UserServ.findUser;

public class ManageUserPageController {
    private static final int ROWS_PER_PAGE = 20;//每页最多有多少行
    @FXML
    private TextField IDField;
    @FXML
    private TextField NameField;
    @FXML
    private Button SearchButton;
    @FXML
    private AnchorPane TablePane;
    private UserType userType;//管理什么用户的界面
    /*
     * Table Component
     */
    private TableView<UserInformationForTable> tableView = new TableView<>();
    private TableColumn<UserInformationForTable, String> IDColumn = new TableColumn<>("ID");
    private TableColumn<UserInformationForTable, Void> NameColumn = new TableColumn<>("姓名");
    private TableColumn<UserInformationForTable, Void> PasswordColumn = new TableColumn<>("密码");
    private TableColumn<UserInformationForTable, Void> GradeColumn = new TableColumn<>("年级");
    private TableColumn<UserInformationForTable, Void> SchoolColumn = new TableColumn<>("院校");
    private TableColumn<UserInformationForTable, String> GenderColumn = new TableColumn<>("性别");
    private TableColumn<UserInformationForTable, Void> MoneyColumn = new TableColumn<>("账户金额");
    private Pagination pagination;
    /*
     * Edit Pages
     */
    private boolean isEditPagesExist = false;
    private EditUserPage existPage = null;
    private Stage editPageStage;
    private NameEditorController nameEditorController;
    private PasswordEditorController passwordEditorController;
    private GradeEditorController gradeEditorController;
    private StudentSchoolEditorController studentSchoolEditorController;
    private TeacherSchoolEditorController teacherSchoolEditorController;
    private MoneyEditorController moneyEditorController;
    private ObservableList<UserInformationForTable> data = FXCollections.observableArrayList();//用于表格的展示的ObservableList
    /*
     * Else
     */
    private String name = null;
    private String ID = null;

    /*
     * Functions
     */
    @FXML
    private void initialize() {
        loadTable();
    }

    @FXML
    private void doSearch() {
        name = NameField.getText();
        ID = IDField.getText();
        flush();
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
        loadColumn();
    }

    private void loadTable() {
        tableView.setPrefWidth(1280);
        tableView.setPrefHeight(560);

        {
            IDColumn.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
            GenderColumn.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
        }//设置表格列与数据对象的属性关联
        {
            IDColumn.setCellValueFactory(new PropertyValueFactory<>("ID"));
            NameColumn.setCellFactory(column -> new TableCell<>() {
                private final Hyperlink hyperlink = new Hyperlink(getTableView().getItems().get(getIndex()).getName());

                {
                    hyperlink.setOnAction(event -> {
                        isEditPagesExist = true;
                        openEditPage(getTableView().getItems().get(getIndex()).getID(), EditUserPage.Name);
                        resetLocation(editPageStage);
                    });
                }//超链接点击事件

                @Override
                protected void updateItem(Void item, boolean empty) {
                    super.updateItem(item, empty);
                    if (empty) {
                        setGraphic(null);
                    } else {
                        setGraphic(hyperlink);
                    }
                }
            });
            PasswordColumn.setCellFactory(column -> new TableCell<>() {
                private final Hyperlink hyperlink = new Hyperlink(getTableView().getItems().get(getIndex()).getPassword());

                {
                    hyperlink.setOnAction(event -> {
                        isEditPagesExist = true;
                        openEditPage(getTableView().getItems().get(getIndex()).getID(), EditUserPage.Password);
                        resetLocation(editPageStage);
                    });
                }//超链接点击事件

                @Override
                protected void updateItem(Void item, boolean empty) {
                    super.updateItem(item, empty);
                    if (empty) {
                        setGraphic(null);
                    } else {
                        setGraphic(hyperlink);
                    }
                }
            });
            GradeColumn.setCellFactory(column -> new TableCell<>() {
                private final Hyperlink hyperlink = new Hyperlink(getTableView().getItems().get(getIndex()).getGrade());

                {
                    hyperlink.setOnAction(event -> {
                        isEditPagesExist = true;
                        openEditPage(getTableView().getItems().get(getIndex()).getID(), EditUserPage.Grade);
                        resetLocation(editPageStage);
                    });
                }//超链接点击事件

                @Override
                protected void updateItem(Void item, boolean empty) {
                    super.updateItem(item, empty);
                    if (empty) {
                        setGraphic(null);
                    } else {
                        setGraphic(hyperlink);
                    }
                }
            });
            SchoolColumn.setCellFactory(column -> new TableCell<>() {
                private final Hyperlink hyperlink = new Hyperlink(getTableView().getItems().get(getIndex()).getSchool());

                {
                    hyperlink.setOnAction(event -> {
                        isEditPagesExist = true;
                        if (userType == UserType.Student) {
                            openEditPage(getTableView().getItems().get(getIndex()).getID(), EditUserPage.StudentSchool);
                        } else {
                            openEditPage(getTableView().getItems().get(getIndex()).getID(), EditUserPage.TeacherSchool);
                        }
                        resetLocation(editPageStage);
                    });
                }//超链接点击事件

                @Override
                protected void updateItem(Void item, boolean empty) {
                    super.updateItem(item, empty);
                    if (empty) {
                        setGraphic(null);
                    } else {
                        setGraphic(hyperlink);
                    }
                }
            });
            GenderColumn.setCellValueFactory(new PropertyValueFactory<>("性别"));
            MoneyColumn.setCellFactory(column -> new TableCell<>() {
                private final Hyperlink hyperlink = new Hyperlink(getTableView().getItems().get(getIndex()).getMoney());

                {
                    hyperlink.setOnAction(event -> {
                        isEditPagesExist = true;
                        openEditPage(getTableView().getItems().get(getIndex()).getID(), EditUserPage.Money);
                        resetLocation(editPageStage);
                    });
                }//超链接点击事件

                @Override
                protected void updateItem(Void item, boolean empty) {
                    super.updateItem(item, empty);
                    if (empty) {
                        setGraphic(null);
                    } else {
                        setGraphic(hyperlink);
                    }
                }
            });
        }//设置自定义格式工厂

        flush();//刷新数据
        tableView.setItems(data);

        {
            pagination = new Pagination((tableView.getItems().size() - 1) / ROWS_PER_PAGE + 1);
            pagination.setPageFactory(pageIndex -> {
                int fromIndex = pageIndex * ROWS_PER_PAGE;
                int toIndex = Math.min(fromIndex + ROWS_PER_PAGE, data.size());
                tableView.setItems(FXCollections.observableArrayList(data.subList(fromIndex, toIndex)));
                return new VBox(tableView);
            });
            pagination.setPrefWidth(1280);
            pagination.setPrefHeight(600);
        }//翻页
        TablePane.getChildren().add(pagination);
    }

    private void loadColumn() {
        switch (userType) {
            case UserType.Student -> tableView.getColumns().addAll(
                    IDColumn,
                    NameColumn,
                    PasswordColumn,
                    GradeColumn,
                    SchoolColumn,
                    GenderColumn,
                    MoneyColumn
            );
            case UserType.Teacher -> tableView.getColumns().addAll(
                    IDColumn,
                    NameColumn,
                    PasswordColumn,
                    SchoolColumn
            );
            case UserType.Admin -> tableView.getColumns().addAll(
                    IDColumn,
                    NameColumn,
                    PasswordColumn
            );
        }
    }

    private void openEditPage(String ID, EditUserPage editUserPage) {
        if (!isEditPagesExist) {
            isEditPagesExist = true;
            editPageStage = new Stage();
            doOpen(ID, editUserPage);

            editPageStage.setOnHiding(e -> {
                isEditPagesExist = false;
                this.existPage = null;
                editPageStage.close();
            });

            editPageStage.show();
            editPageStage.setResizable(false);
        } else {
            doOpen(ID, editUserPage);
        }
    }

    private void doOpen(String ID, EditUserPage editUserPage) {
        switch (editUserPage) {
            case EditUserPage.Name -> {
                nameEditorController = changeViews(editPageStage, "/GUI/Window/Components/UserInformationEditor/NameEditor.fxml");
                nameEditorController.setStage(editPageStage);
                nameEditorController.setID(ID);
            }
            case EditUserPage.Password -> {
                passwordEditorController = changeViews(editPageStage, "/GUI/Window/Components/UserInformationEditor/PasswordEditor.fxml");
                passwordEditorController.setStage(editPageStage);
                passwordEditorController.setID(ID);
            }
            case EditUserPage.Grade -> {
                gradeEditorController = changeViews(editPageStage, "/GUI/Window/Components/UserInformationEditor/GradeEditor.fxml");
                gradeEditorController.setStage(editPageStage);
                gradeEditorController.setID(ID);
            }
            case EditUserPage.StudentSchool -> {
                studentSchoolEditorController = changeViews(editPageStage, "/GUI/Window/Components/UserInformationEditor/StudentSchoolEditor.fxml");
                studentSchoolEditorController.setStage(editPageStage);
                studentSchoolEditorController.setID(ID);
            }
            case EditUserPage.TeacherSchool -> {
                teacherSchoolEditorController = changeViews(editPageStage, "/GUI/Window/Components/UserInformationEditor/TeacherSchoolEditor.fxml");
                teacherSchoolEditorController.setStage(editPageStage);
                teacherSchoolEditorController.setID(ID);
            }
            case EditUserPage.Money -> {
                moneyEditorController = changeViews(editPageStage, "/GUI/Window/Components/UserInformationEditor/MoneyEditor.fxml");
                moneyEditorController.setStage(editPageStage);
                moneyEditorController.setID(ID);
            }
        }
    }

    public void flush() {
        data = findUser(userType, ID, name).toObservableList();
    }

    public ContextMenu manageUserPageContextMenu() {
        ContextMenu contextMenu = new ContextMenu();
        MenuItem flushMenuItem = new MenuItem("刷新");

        flushMenuItem.setAccelerator(new KeyCodeCombination(KeyCode.R, KeyCodeCombination.CONTROL_DOWN));

        flushMenuItem.setOnAction(event -> flush());

        contextMenu.getItems().addAll(flushMenuItem);

        return contextMenu;
    }

    public void close() {
        if (isEditPagesExist) {
            editPageStage.close();
        }
    }
}
