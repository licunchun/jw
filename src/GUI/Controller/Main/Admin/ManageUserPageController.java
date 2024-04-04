package GUI.Controller.Main.Admin;

import GUI.Controller.Components.UserInformationEditor.*;
import GUI.Data.DataPackage.UserInformation.UserInformationForTable;
import GUI.Data.Enum.GUI.Scene.EditUserPage;
import GUI.Data.Enum.User.UserType;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class ManageUserPageController {
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
    private static final int ROWS_PER_PAGE=20;//每页最多有多少行
    /*
     * Edit Pages
     */
    private boolean isEditPagesExist=false;
    private EditUserPage existPage=null;
    private Stage editPageStage;
    private NameEditorController nameEditorController;
    private PasswordEditorController passwordEditorController;
    private GradeEditorController gradeEditorController;
    private StudentSchoolEditorController studentSchoolEditorController;
    private TeacherSchoolEditorController teacherSchoolEditorController;
    private MoneyEditorController moneyEditorController;
    private ObservableList<UserInformationForTable> data= FXCollections.observableArrayList();//用于表格的展示的ObservableList
    /*
     * Functions
     */
    @FXML
    private void initialize(){
        loadTable();
    }
    @FXML
    private void doSearch(){

    }//TODO

    public void setUserType(UserType userType) {
        this.userType = userType;
    }
    private void loadTable(){

    }//TODO
    public void flush(){

    }//TODO
    public ContextMenu manageUserPageContextMenu(){
        return new ContextMenu();
    }//TODO
    public void close(){
        if(isEditPagesExist){
            editPageStage.close();
        }
    }
}
