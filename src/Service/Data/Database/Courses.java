package Service.Data.Database;

import Service.Data.SQLiteJDBC;

public class Courses {
    private static final int STDCOUNT = 5;
    private static final String tableName = "courses";
    public static boolean isCodeExist(String code){
        return coursesTable.isColValueExist(tableName,"code",code);
    }
    public static boolean isCourseFull(String code){
        String full = coursesTable.select(tableName,"full","code",code);
        return full.compareTo("已满") == 0;
    }
    public static void increaseStdCount(String code){
        int stdCount = Integer.parseInt(getCourseInfo(code)[STDCOUNT]);
        coursesTable.update(tableName,"stdCount",String.valueOf(stdCount+1),"code",code);
    }
    public static void decreaseStdCount(String code){
        int stdCount = Integer.parseInt(getCourseInfo(code)[STDCOUNT]);
        coursesTable.update(tableName,"stdCount",String.valueOf(stdCount-1),"code",code);
    }

    public static final int CODE_C = 0;
    public static final int NAME_C = 1;
    public static final int PERIOD_C = 2;
    public static final int CREDITS_C = 3;

    public static final String[] colName={
            "code",
            "name",
            "period",
            "credits",
            "time",
            "stdCount",
            "limitCount",
            "classType",
            "courseType",
            "school",
            "campus",
            "examMode",
            "language",
            "education",
            "teachers",
            "full",
            "place"};

    private static final SQLiteJDBC coursesTable = new SQLiteJDBC();
    public static String[] getCourseInfo(String code){
        return coursesTable.select(tableName,colName,colName[CODE_C],code);
    }
}
