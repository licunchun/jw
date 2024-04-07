package GUI.Controller.Main.Common.Classes;

import GUI.Controller.Components.Time.TimeTableController;
import GUI.Data.DataPackage.Classes.CourseTimeSet;
import GUI.Data.Enum.Classes.*;
import GUI.Data.Enum.Error.Main.Components.ClassesServ.NewClassesError;
import GUI.Data.Enum.School;
import GUI.Data.Enum.User.UserType;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.util.StringConverter;

import static GUI.Data.Enum.GUI.ObservableList.ClassesInformationObservableList.*;
import static GUI.GUIUtil.StageUtil.changeViews;
import static GUI.GUIUtil.StageUtil.resetLocation;
import static Sevice.Main.Components.ClassServ.ClassesServ.newClasses;
import static Sevice.Main.Components.UserServ.UserServ.getName;

public class ProposeCoursePageController {
    @FXML
    public TextField CodeTextField;
    @FXML
    public TextField NameTextField;
    @FXML
    public TextField PeriodTextField;
    @FXML
    public TextField CreditsTextField;
    @FXML
    public Button TimeTableButton;
    @FXML
    public TextField MaxCountTextField;
    @FXML
    public ChoiceBox<ClassType> ClassesTypeChoiceBox;
    @FXML
    public ChoiceBox<CourseType> CourseTypeChoiceBox;
    @FXML
    public ChoiceBox<School> SchoolChoiceBox;
    @FXML
    public ChoiceBox<Campus> CampusChoiceBox;
    @FXML
    public ChoiceBox<ExamMode> ExamModeChoiceBox;
    @FXML
    public ChoiceBox<Language> LanguageChoiceBox;
    @FXML
    public ChoiceBox<Education> EducationChoiceBox;
    @FXML
    public TextField TeacherTextField;
    @FXML
    public Button ClickButton;
    @FXML
    public Button BackButton;
    @FXML
    public Label Tips;

    private Stage stage;
    private UserType userType;
    private String ID;
    /*
     * Time Table
     */
    private boolean isTimePageExist = false;
    private Stage TimePageStage;
    private TimeTableController timePageController;
    private CourseTimeSet courseTimeSet = new CourseTimeSet();

    /*
     * Functions
     */
    @FXML
    private void initialize() {
        choiceBoxInitialize();
    }

    @FXML
    private void doClick() {
        switch (newClasses(
                CodeTextField.getText(),
                NameTextField.getText(),
                PeriodTextField.getText(),
                CreditsTextField.getText(),
                courseTimeSet,
                MaxCountTextField.getText(),
                ClassesTypeChoiceBox.getValue(),
                CourseTypeChoiceBox.getValue(),
                SchoolChoiceBox.getValue(),
                CampusChoiceBox.getValue(),
                ExamModeChoiceBox.getValue(),
                LanguageChoiceBox.getValue(),
                EducationChoiceBox.getValue(),
                TeacherTextField.getText()
        )) {
            case NewClassesError.CodeIsEmpty -> {
                Tips.setText("课程编号不能为空，请重新输入");
                Tips.setVisible(true);
            }
            case NewClassesError.CodeInvalid -> {
                Tips.setText("课程编号不合法，请重新输入");
                Tips.setVisible(true);
            }
            case NewClassesError.NameIsEmpty -> {
                Tips.setText("课程名称不能为空，请重新输入");
                Tips.setVisible(true);
            }
            case NewClassesError.NameInvalid -> {
                Tips.setText("课程名称不合法，请重新输入");
                Tips.setVisible(true);
            }
            case NewClassesError.PeriodIsEmpty -> {
                Tips.setText("学时不能为空，请重新输入");
                Tips.setVisible(true);
            }
            case NewClassesError.PeriodInvalid -> {
                Tips.setText("学时不合法，请重新输入");
                Tips.setVisible(true);
            }
            case NewClassesError.CreditsIsEmpty -> {
                Tips.setText("学分不能为空，请重新输入");
                Tips.setVisible(true);
            }
            case NewClassesError.CreditsInvalid -> {
                Tips.setText("学分不合法，请重新输入");
                Tips.setVisible(true);
            }
            case NewClassesError.MaxCountIsEmpty -> {
                Tips.setText("课程容量不能为空，请重新输入");
                Tips.setVisible(true);
            }
            case NewClassesError.MaxCountInvalid -> {
                Tips.setText("课程容量不合法，请重新输入");
                Tips.setVisible(true);
            }
            case NewClassesError.TeacherIsEmpty -> {
                Tips.setText("教师名称不能为空，请重新输入");
                Tips.setVisible(true);
            }
            case NewClassesError.TeacherInvalid -> {
                Tips.setText("教师名称不合法，请重新输入");
                Tips.setVisible(true);
            }
            case NewClassesError.Success -> stage.close();
        }
    }

    @FXML
    private void doBack() {
        stage.close();
    }

    @FXML
    private void openTimePage() {
        if (!isTimePageExist) {
            isTimePageExist = true;
            TimePageStage = new Stage();

            timePageController = changeViews(TimePageStage, "/GUI/Window/Components/Time/TimeTable.fxml");

            timePageController.setStage(TimePageStage);

            TimePageStage.setOnHiding(e -> {
                courseTimeSet = timePageController.getTimeSet();
                TimePageStage.close();
                isTimePageExist = false;
            });

            TimePageStage.show();
            TimePageStage.setResizable(false);
            resetLocation(TimePageStage);
        } else {
            resetLocation(TimePageStage);
        }
    }

    public void flush() {
        if (userType == UserType.Teacher) {
            String name = getName(ID);
            if (name == null) {
                System.err.println("Error:ID not found!\nPlease refresh the page and try again!");
            } else {
                TeacherTextField.setText(name);
                TeacherTextField.setEditable(false);
            }
        }
    }

    private void choiceBoxInitialize() {
        {
            ClassesTypeChoiceBox.setValue(ClassType.Experiment);
            ClassesTypeChoiceBox.setConverter(new StringConverter<>() {
                @Override
                public String toString(ClassType classType) {
                    return classType.toString();
                }

                @Override
                public ClassType fromString(String s) {
                    return null;
                }
            });

            ClassesTypeChoiceBox.setItems(ClassTypeList);
        }//ClassesTypeChoiceBox
        {
            CourseTypeChoiceBox.setValue(CourseType.Basic);
            CourseTypeChoiceBox.setConverter(new StringConverter<>() {
                @Override
                public String toString(CourseType courseType) {
                    return courseType.toString();
                }

                @Override
                public CourseType fromString(String s) {
                    return null;
                }
            });

            CourseTypeChoiceBox.setItems(CourseTypeList);
        }//CourseTypeChoiceBox
        {
            SchoolChoiceBox.setValue(School.GiftedYoung);
            SchoolChoiceBox.setConverter(new StringConverter<>() {
                @Override
                public String toString(School school) {
                    return school.toString();
                }

                @Override
                public School fromString(String s) {
                    return null;
                }
            });

            SchoolChoiceBox.setItems(SchoolList);
        }//SchoolChoiceBox
        {
            CampusChoiceBox.setValue(Campus.Middle);
            CampusChoiceBox.setConverter(new StringConverter<>() {
                @Override
                public String toString(Campus campus) {
                    return campus.toString();
                }

                @Override
                public Campus fromString(String s) {
                    return null;
                }
            });

            CampusChoiceBox.setItems(CampusList);
        }//CampusChoiceBox
        {
            ExamModeChoiceBox.setValue(ExamMode.Experiment);
            ExamModeChoiceBox.setConverter(new StringConverter<>() {
                @Override
                public String toString(ExamMode examMode) {
                    return examMode.toString();
                }

                @Override
                public ExamMode fromString(String s) {
                    return null;
                }
            });

            ExamModeChoiceBox.setItems(ExamModeList);
        }//ExamModeChoiceBox
        {
            LanguageChoiceBox.setValue(Language.Chinese);
            LanguageChoiceBox.setConverter(new StringConverter<>() {
                @Override
                public String toString(Language language) {
                    return language.toString();
                }

                @Override
                public Language fromString(String s) {
                    return null;
                }
            });

            LanguageChoiceBox.setItems(LanguageList);
        }//LanguageChoiceBox
        {
            EducationChoiceBox.setValue(Education.Undergraduate);
            EducationChoiceBox.setConverter(new StringConverter<>() {
                @Override
                public String toString(Education education) {
                    return education.toString();
                }

                @Override
                public Education fromString(String s) {
                    return null;
                }
            });

            EducationChoiceBox.setItems(EducationList);
        }//EducationChoiceBox
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public void close() {
        if (isTimePageExist) {
            TimePageStage.close();
        }
    }
}
