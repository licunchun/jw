package GUI.controller;

import Data.Enum.*;
import Data.Student;
import GUI.util.StringUtil;
import MainPackage.Main;
import Sevice.UserService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.util.StringConverter;

import java.util.Arrays;

public class RegistController {
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

    private static final UserService us=new UserService();
    private static boolean isService=true;
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
        if(isService){
            registInitialize();
            return;
        }//regist
        if(us.getUserType()==UserType.Student){
            studentInitialize();
        }//student
        else{
            teacherInitialize();
        }//teacher



    }
    @FXML
    public void doRegistConfirm(){
        String name=UserName.getText();
        String password=Password.getText();
        String confirmPassword=ConfirmPassword.getText();
        UserType usertype=AccountType.getValue();
        /*
         * Empty Tips
         */
        if(StringUtil.isEmpty(name)){
            Tips.setText("用户名不能为空!");
            Tips.setVisible(true);
            return;
        }
        if(StringUtil.isEmpty(password)){
            Tips.setText("密码不能为空!");
            Tips.setVisible(true);
            return;
        }
        if(StringUtil.isEmpty(confirmPassword)){
            Tips.setText("确认密码不能为空!");
            Tips.setVisible(true);
            return;
        }
        if(!password.equals(confirmPassword)){
            Tips.setText("两次输入的密码不一致!");
            return;
        }
        /*
         * ValidTips
         */
        us.setName(name);
        us.setPassword(password);
        us.setUserType(usertype);
        if(!us.isNameValid()){
            Tips.setText("用户名不合法，请重新输入!");
            Tips.setVisible(true);
            return;
        }
        if(!us.isPasswordValid()){
            Tips.setText("密码不合法，请重新输入!");
            Tips.setVisible(true);
            return;
        }

        switch(usertype){
            case UserType.Student:
                isService=false;
                Main.changeViews("/GUI/window/registStudent.fxml");
                return;
            case UserType.Teacher:
                isService=false;
                Main.changeViews("/GUI/window/registTeacher.fxml");
                return;
            case UserType.Admin:
                us.Regist();
                showIDPage();
                break;
            default:
                throw new RuntimeException("RegistController.java:(line 136)switch receive a None UserType");
        }
    }
    @FXML
    public void doStudentConfirm(){
        us.setGrade(StudentGradeChooser.getValue());
        us.setSchool(StudentSchoolChooser.getValue().toSchool());
        us.setGender(StudentGenderChooser.getValue());

        us.Regist();
        showIDPage();
    }
    @FXML
    public void doTeacherConfirm(){
        us.setSchool(TeacherSchoolChooser.getValue());
        us.setGender(TeacherGenderChooser.getValue());

        us.Regist();
        showIDPage();
    }

    public void showIDPage(){
        Main.changeViews("/GUI/window/IDPage.fxml");
    }

    public static String getID(){
        return us.getID();
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
