package GUI.Data.Enum.GUI.ObservableList;

import GUI.Data.Enum.Classes.EnumForClassesSearching.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.Arrays;

public class ClassesChoosingObservableList {
    public static final ObservableList<C_ClassType> ClassTypeList=
            FXCollections.observableArrayList(Arrays.asList(C_ClassType.values()));
    public static final ObservableList<C_CourseType> CourseTypeList=
            FXCollections.observableArrayList(Arrays.asList(C_CourseType.values()));
    public static final ObservableList<C_Campus> CampusList=
            FXCollections.observableArrayList(Arrays.asList(C_Campus.values()));
    public static final ObservableList<C_Education> EducationList=
            FXCollections.observableArrayList(Arrays.asList(C_Education.values()));
    public static final ObservableList<C_ExamMode> ExamModeList=
            FXCollections.observableArrayList(Arrays.asList(C_ExamMode.values()));
    public static final ObservableList<C_Language> LanguageList=
            FXCollections.observableArrayList(Arrays.asList(C_Language.values()));
    public static final ObservableList<C_School> SchoolList=
            FXCollections.observableArrayList(Arrays.asList(C_School.values()));
}
