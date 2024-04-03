package GUI.Controller.Components.Time.TimeTableBy_LCC;

import GUI.Controller.Components.Time.TimeTableController;
import GUI.Data.DataPackage.Classes.CourseTimeSet;
import javafx.application.Application;
import javafx.stage.Stage;

import static GUI.GUIUtil.StageUtil.resetLocation;
import static java.lang.System.exit;

public class Timetable2 extends Application {

    private static Stage stage;
    private static CourseTimeSet courseTimeSet;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        GUI.Controller.Components.Time.TimeTableBy_LCC.Timetable2.stage = stage;
        stage.setOnHiding(e -> exit(0));
        stage.setResizable(false);
        TimeTableController timeTableController=GUI.GUIUtil.StageUtil.changeViews(stage, "/GUI/Window/Components/Time/TimeTable.fxml");
        timeTableController.setStage(stage);
        stage.setOnHiding(e->{
            courseTimeSet=timeTableController.getTimeSet();
            stage.close();
        });
        stage.show();
        resetLocation(stage);
    }

    public static Stage getStage() {
        return stage;
    }
}