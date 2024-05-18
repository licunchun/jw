package Service.Data;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class DataBaseProcess {
    private static final SQLiteJDBC db = new SQLiteJDBC();
    public static String[] getAllCourseSchool(){
        return db.selectAll("courses","school");
    }
    public static String[] uniqueCourseSchool(){
        String[] courseSchools = getAllCourseSchool();
        Set<String> set = new HashSet<>(Arrays.asList(courseSchools));
        return set.toArray(new String[0]);
    }
    //获得该开课单位所开课程数目
    public static int getCourseNum(String school){
        String[] codes = db.selectAll("courses","code","school",school);
        return codes.length;
    }
}
