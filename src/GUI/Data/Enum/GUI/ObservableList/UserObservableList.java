package GUI.Data.Enum.GUI.ObservableList;

import GUI.Data.Enum.School;
import GUI.Data.Enum.User.Gender;
import GUI.Data.Enum.User.Grade;
import GUI.Data.Enum.User.StudentSchool;
import GUI.Data.Enum.User.UserType;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.Arrays;

public class UserObservableList {
    public static final ObservableList<UserType> UserTypeList =
            FXCollections.observableArrayList(UserType.Student, UserType.Teacher, UserType.Admin);
    public static final ObservableList<Grade> GradeList =
            FXCollections.observableArrayList(Arrays.asList(Grade.values()));
    public static final ObservableList<StudentSchool> StudentSchoolList =
            FXCollections.observableArrayList(Arrays.asList(StudentSchool.values()));
    public static final ObservableList<School> SchoolList =
            FXCollections.observableArrayList(Arrays.asList(School.values()));
    public static final ObservableList<Gender> GenderList =
            FXCollections.observableArrayList(Arrays.asList(Gender.values()));
}
