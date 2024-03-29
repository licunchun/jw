package GUI.Data.Enum.Classes;

import java.time.MonthDay;

public class Util {
    public static Week getWeek(int i){
        switch (i){
            case 1:
                return Week.Monday;
            case 2:
                return Week.Tuesday;
            case 3:
                return Week.Wednesday;
            case 4:
                return Week.Thursday;
            case 5:
                return Week.Friday;
            case 6:
                return Week.Saturday;
            case 7:
                return Week.Sunday;
            default:
                throw new RuntimeException("getWeek(Util.java) get a out of Range int");
        }
    }

    public static CourseTime getTime(int week,int section){
        if(week<1||week>7){
            throw new RuntimeException("getWeek(Util.java) get a out of Range week(int)");
        }
        if(section<1||section>13){
            throw new RuntimeException("getWeek(Util.java) get a out of Range section(int)");
        }
        for(CourseTime item:CourseTime.values()){
            if(item.getWeek()==getWeek(week)&&item.getSection()==section){
                return item;
            }
        }
        return CourseTime.Section1;
    }
}
