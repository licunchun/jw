package GUI.Data.DataPackage.Classes;

import GUI.Data.Enum.Classes.CourseTime;
import GUI.Data.Enum.Classes.Week;
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
        Matcher m = p.matcher(times);
        while (m.find()) {
            String time = m.group();
            Pattern p_t = Pattern.compile("\\d+");
            Matcher m_t = p_t.matcher(time);
            boolean init = true;
            int week = 0,section;
            while(m_t.find()){
                String s = m_t.group();
                if(init) {
                    week = Integer.parseInt(s);
                    init = false;
                }else{
                    section = Integer.parseInt(s);
                    cts.add(CourseTime.fromInt(week,section));
                }
            }
        }
        return cts;
    }
    @Override
    public String toString(){

        return "";
    }
}
