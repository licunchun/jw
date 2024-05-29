package GUI.Data.DataPackage.Classes;

import GUI.Data.Enum.Classes.CourseTime;
import GUI.Data.Enum.Classes.Week;
import Service.Data.Utils.TimeUtil;
import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.beans.property.ReadOnlyObjectWrapper;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CourseTimeSet {
    private static final Pattern p = Pattern.compile("\\d\\(\\d(,\\d+)*\\)");
    private final Set<CourseTime> courseTimeSet = new HashSet<>();

    public CourseTimeSet() {

    }

    public void add(CourseTime courseTime) {
        courseTimeSet.add(courseTime);
    }

    public ReadOnlyObjectProperty<CourseTimeSet> timeProperty() {
        return new ReadOnlyObjectWrapper<>(this);
    }

    public Iterable<CourseTime> getCourseTimeIterable() {
        return courseTimeSet;
    }

    public static CourseTimeSet fromString(String times){
        CourseTimeSet cts = new CourseTimeSet();
        String[] days = TimeUtil.getDay(times);
        for (String day:days){
            int[] section = TimeUtil.getSection(day);
            if(section.length==1)
                continue;
            for (int i = 1; i < section.length; i++) {
                cts.add(CourseTime.fromInt(section[0],section[i]));
            }
        }
        return cts;
    }
    @Override
    public String toString(){

        return "";
    }
}
