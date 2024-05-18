package Service.Data.Database;

import Service.Data.DataBase;
import Service.Data.SQLiteJDBC;

public class Courses {
    private static final String tableName = "courses";
    private static final SQLiteJDBC coursesTable = new SQLiteJDBC();
    public static final int code_C = 0;
    public static final int name_C = 1;
    public static final int period_C = 2;
    public static final int credits_C = 3;
    public static final int time_C = 4;
    public static final int stdCount_C = 5;
    public static final int limitCount_C = 6;
    public static final int classType_C = 7;
    public static final int courseType_C = 8;
    public static final int school_C = 9;
    public static final int campus_C = 10;
    public static final int examMode_C = 11;
    public static final int language_C = 12;
    public static final int education_C = 13;
    public static final int teachers_C = 14;
    public static final int full_C = 15;
    public static final int place_C = 16;

    public static boolean isCodeExist(String code){
        return coursesTable.isColValueExist(tableName,"code",code);
    }
    public static boolean isCourseFull(String code){
        String full = coursesTable.select(tableName,"full","code",code);
        return full.compareTo("已满") == 0;
    }
    public static void increaseStdCount(String code){
        int stdCount = Integer.parseInt(getCourseInfo(code)[stdCount_C]);
        coursesTable.update(tableName,"stdCount",String.valueOf(stdCount+1),"code",code);
    }
    public static void decreaseStdCount(String code){
        int stdCount = Integer.parseInt(getCourseInfo(code)[stdCount_C]);
        coursesTable.update(tableName,"stdCount",String.valueOf(stdCount-1),"code",code);
    }


    public static String[] getCourseInfo(String code){
        return DataBase.selectTable(DataBase.COURSE,"code",code);
    }
    public static void addCourseInfo(){

    }
}
