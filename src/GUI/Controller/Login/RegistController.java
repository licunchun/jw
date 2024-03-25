package GUI.Controller.Login;

import Data.Enum.Error.Regist;
import Data.Enum.School;
import Data.Enum.User.*;
import MainPackage.Main;
import Sevice.UserService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.util.StringConverter;

import java.util.Arrays;

import static GUI.GUIUtil.StageUtil.changeViews;

public class RegistController {
    private static final Stage stage=Main.getStage();
    /*
     * Major subassembly
     */
    @FXML
    private Button Confirm;
    @FXML
    private TextField UserName;
    @FXML
    private PasswordField Password;
    @FXML
    private PasswordField ConfirmPassword;
    @FXML
    private ChoiceBox<UserType> AccountType;
    @FXML
    private Label Tips;
    /*
     * Student subassembly
     */
    @FXML
    private ChoiceBox<Grade> StudentGradeChooser;
    @FXML
    private ChoiceBox<StudentSchool> StudentSchoolChooser;
    @FXML
    private ChoiceBox<Gender> StudentGenderChooser;
    @FXML
    private Button StudentConfirmButton;
    /*
     * Teacher subassembly
     */
    @FXML
    private ChoiceBox<School> TeacherSchoolChooser;
    @FXML
    private ChoiceBox<Gender> TeacherGenderChooser;
    @FXML
    private Button TeacherConfirmButton;
    private static boolean isRegist=true;//是否在第一页面
    private static boolean isStudent=true;//是否在学生页面

    private static UserService us=new UserService();
    private static String ID= "";
    /*
     * ChoiceBlock properties
     */
    private final ObservableList<UserType> UserTypeList=
            FXCollections.observableArrayList();
    private final ObservableList<Grade> GradeList=
            FXCollections.observableArrayList();
    private final ObservableList<StudentSchool> StudentSchoolList=
            FXCollections.observableArrayList();
    private final ObservableList<School> SchoolList=
            FXCollections.observableArrayList();
    private final ObservableList<Gender> GenderList=
            FXCollections.observableArrayList();
    /*
     * Major function
     */
    @FXML
    public void initialize(){
        /*
         * ChoiceBlock init
         */
        listInitialize();
        if(isRegist){
            registInitialize();
            return;
        }//regist
        if(isStudent){
            studentInitialize();
        }//student
        else{
            teacherInitialize();
        }//teacher



    }
    @FXML
    public void doRegistConfirm(){
        switch(us.regist(AccountType.getValue(),UserName.getText(),Password.getText(),ConfirmPassword.getText())){
            case Regist.NameEmpty:
                Tips.setText("用户名不可为空，请重新输入!");
                Tips.setVisible(true);
                return;
            case Regist.NameOverLength:
                Tips.setText("用户名过长，请重新输入!");
                Tips.setVisible(true);
                return;
            case Regist.NameInvalidChar:
                Tips.setText("用户名不合法，请重新输入!");
                Tips.setVisible(true);
                return;
            case Regist.PasswordEmpty:
                Tips.setText("密码不可为空，请重新输入!");
                Tips.setVisible(true);
                return;
            case Regist.PasswordOverLength:
                Tips.setText("密码过长，请重新输入!");
                Tips.setVisible(true);
                return;
            case Regist.PasswordInvalidChar:
                Tips.setText("密码不合法，请重新输入!");
                Tips.setVisible(true);
                return;
            case Regist.PasswordNotMatch:
                Tips.setText("密码不匹配，请重新输入!");
                Tips.setVisible(true);
                return;
            case Regist.Pass:
                break;
        }
        switch(AccountType.getValue()){
            case UserType.Student:
                isRegist=false;
                isStudent=true;
                changeViews(stage,"/GUI/Window/Login/registStudent.fxml");
                return;
            case UserType.Teacher:
                isRegist=false;
                isStudent=false;
                changeViews(stage,"/GUI/Window/Login/registTeacher.fxml");
                return;
            case UserType.Admin:
                ID=us.storeUser(null,null,null);
                showIDPage();
                break;
            default:
                throw new RuntimeException("RegistController.java:(line 136)switch receive a None UserType");
        }
    }
    @FXML
    public void doStudentConfirm(){
        ID=us.storeUser(StudentGenderChooser.getValue(),StudentSchoolChooser.getValue().toSchool(),StudentGradeChooser.getValue());
        showIDPage();
    }
    @FXML
    public void doTeacherConfirm(){
        ID=us.storeUser(StudentGenderChooser.getValue(),StudentSchoolChooser.getValue().toSchool(),StudentGradeChooser.getValue());
        showIDPage();
    }

    public void showIDPage(){
        changeViews(stage,"/GUI/Window/Login/IDPage.fxml");
    }

    public static String getID(){
        return ID;
    }
    /*
     * Init
     */
    private void listInitialize(){
        GradeList.clear();
        GradeList.addAll(Arrays.asList(Grade.values()));

        StudentSchoolList.clear();
        StudentSchoolList.addAll(Arrays.asList(StudentSchool.values()));

        SchoolList.clear();
        SchoolList.addAll(Arrays.asList(School.values()));

        GenderList.clear();
        GenderList.addAll(Arrays.asList(Gender.values()));

        UserTypeList.clear();
        UserTypeList.add(UserType.Student);
        UserTypeList.add(UserType.Teacher);
        UserTypeList.add(UserType.Admin);
    }
    private void registInitialize(){
        {
            AccountType.setValue(UserType.Student);

            AccountType.setConverter(new StringConverter<UserType>() {
                @Override
                public String toString(UserType userType) {
                    return userType.toString();
                }

                @Override
                public UserType fromString(String s) {
                    return null;
                }
            });

            AccountType.setItems(UserTypeList);
        }//AccountType
    }
    private void studentInitialize(){
        {
            StudentGradeChooser.setValue(Grade.Grade1);

            StudentGradeChooser.setConverter(new StringConverter<Grade>() {
                @Override
                public String toString(Grade grade) {
                    return grade.toString();
                }

                @Override
                public Grade fromString(String s) {
                    return null;
                }
            });

            StudentGradeChooser.setItems(GradeList);
        }//StudentGradeChooser
        {
            StudentSchoolChooser.setValue(StudentSchool.GiftedYoung);

            StudentSchoolChooser.setConverter(new StringConverter<StudentSchool>() {
                @Override
                public String toString(StudentSchool studentSchool) {
                    return studentSchool.toString();
                }

                @Override
                public StudentSchool fromString(String s) {
                    return null;
                }
            });

            StudentSchoolChooser.setItems(StudentSchoolList);
        }//StudentSchoolChooser
        {
            StudentGenderChooser.setValue(Gender.Male);
            StudentGenderChooser.setConverter(new StringConverter<Gender>() {
                @Override
                public String toString(Gender gender) {
                    return gender.toString();
                }

                @Override
                public Gender fromString(String s) {
                    return null;
                }
            });
            StudentGenderChooser.setItems(GenderList);
        }//GenderChooser
    }
    private void teacherInitialize(){
        {
            TeacherSchoolChooser.setValue(School.GiftedYoung);

            TeacherSchoolChooser.setConverter(new StringConverter<School>() {
                @Override
                public String toString(School school) {
                    return school.toString();
                }

                @Override
                public School fromString(String s) {
                    return null;
                }
            });

            TeacherSchoolChooser.setItems(SchoolList);
        }//TeacherSchoolChooser
        {
            TeacherGenderChooser.setValue(Gender.Male);
            TeacherGenderChooser.setConverter(new StringConverter<Gender>() {
                @Override
                public String toString(Gender gender) {
                    return gender.toString();
                }

                @Override
                public Gender fromString(String s) {
                    return null;
                }
            });
            TeacherGenderChooser.setItems(GenderList);
        }//GenderChooser
    }
}
