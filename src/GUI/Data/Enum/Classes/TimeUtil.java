package GUI.Data.Enum.Classes;

public class TimeUtil {
    public static Week getWeek(int i) {
        return switch (i) {
            case 1 -> Week.Monday;
            case 2 -> Week.Tuesday;
            case 3 -> Week.Wednesday;
            case 4 -> Week.Thursday;
            case 5 -> Week.Friday;
            case 6 -> Week.Saturday;
            case 7 -> Week.Sunday;
            default -> throw new RuntimeException("getWeek(Util.java) get a out of Range int");
        };
    }

    public static CourseTime getTime(int week, int section) {
        if (week < 1 || week > 7) {
            throw new RuntimeException("getWeek(Util.java) get a out of Range week(int)");
        }
        if (section < 1 || section > 13) {
            throw new RuntimeException("getWeek(Util.java) get a out of Range section(int)");
        }
        for (CourseTime item : CourseTime.values()) {
            if (item.getWeek() == getWeek(week) && item.getSection() == section) {
                return item;
            }
        }
        return CourseTime.Section1;
    }
}
