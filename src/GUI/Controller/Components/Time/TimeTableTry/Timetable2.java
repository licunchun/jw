package GUI.Controller.Components.Time.TimeTableTry;

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
    public void start(Stage stage) throws Exception {
        GUI.Controller.Components.Time.TimeTableTry.Timetable2.stage = stage;
        stage.setOnHiding(e -> {
            exit(0);
        });
        stage.setResizable(false);
        TimeTableController timeTableController=GUI.GUIUtil.StageUtil.changeViews(stage, "/GUI/Controller/Main/test/TimeTable/UsingTableView/TimeTable.fxml");
        timeTableController.setStage(stage);
        stage.setOnHiding(e->{
            courseTimeSet=timeTableController.getTimeSet();
            stage.close();
            courseTimeSet.show();
        });
        stage.show();
        resetLocation(stage);
    }

    public static Stage getStage() {
        return stage;
    }
}