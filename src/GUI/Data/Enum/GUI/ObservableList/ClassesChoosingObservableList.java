package GUI.Data.Enum.GUI.ObservableList;

import GUI.Data.Enum.Classes.EnumForClassesSearching.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.Arrays;

public class ClassesChoosingObservableList {
    public static final ObservableList<C_ClassType> C_ClassTypeList =
            FXCollections.observableArrayList(Arrays.asList(C_ClassType.values()));
    public static final ObservableList<C_CourseType> C_CourseTypeList =
            FXCollections.observableArrayList(Arrays.asList(C_CourseType.values()));
    public static final ObservableList<C_Campus> C_CampusList =
            FXCollections.observableArrayList(Arrays.asList(C_Campus.values()));
    public static final ObservableList<C_Education> C_EducationList =
            FXCollections.observableArrayList(Arrays.asList(C_Education.values()));
    public static final ObservableList<C_ExamMode> C_ExamModeList =
            FXCollections.observableArrayList(Arrays.asList(C_ExamMode.values()));
    public static final ObservableList<C_Language> C_LanguageList =
            FXCollections.observableArrayList(Arrays.asList(C_Language.values()));
    public static final ObservableList<C_School> C_SchoolList =
            FXCollections.observableArrayList(Arrays.asList(C_School.values()));
}
