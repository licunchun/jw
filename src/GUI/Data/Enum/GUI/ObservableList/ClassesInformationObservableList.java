package GUI.Data.Enum.GUI.ObservableList;

import GUI.Data.Enum.Classes.*;
import GUI.Data.Enum.School;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.Arrays;

public class ClassesInformationObservableList {

    public static final ObservableList<ClassType> ClassTypeList =
            FXCollections.observableArrayList(Arrays.asList(ClassType.values()));

    public static final ObservableList<CourseType> CourseTypeList =
            FXCollections.observableArrayList(Arrays.asList(CourseType.values()));

    public static final ObservableList<School> SchoolList =
            FXCollections.observableArrayList(Arrays.asList(School.values()));

    public static final ObservableList<Campus> CampusList =
            FXCollections.observableArrayList(Arrays.asList(Campus.values()));

    public static final ObservableList<ExamMode> ExamModeList =
            FXCollections.observableArrayList(Arrays.asList(ExamMode.values()));

    public static final ObservableList<Language> LanguageList =
            FXCollections.observableArrayList(Arrays.asList(Language.values()));

    public static final ObservableList<Education> EducationList =
            FXCollections.observableArrayList(Arrays.asList(Education.values()));
}
