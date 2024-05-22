package Service.Data.Tables;

import Service.Data.SQLiteJDBC;
import Service.Data.Utils.NameUtil;

import java.util.ArrayList;

public class Courses {
    public static final String tableName = "courses";
    private static final SQLiteJDBC coursesTable = new SQLiteJDBC(tableName);
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
    public static final String[] CourseCol={
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
            "place"
    };

    public static boolean isCodeExist(String code){
        return coursesTable.isColValueExist("code",code);
    }
    public static boolean isCourseFull(String code){
        String full = coursesTable.select("full","code",code);
        return full.compareTo("已满") == 0;
    }
    public static String[] geInfo(String code){
        return coursesTable.select(CourseCol,"code",code);
    }
    public static void addInfo(String[] info){
        coursesTable.insert(CourseCol,info);
    }
    public static void deleteInfo(String code){
        coursesTable.delete("code",code);
    }

    public static void increaseStdCount(String code){
        int stdCount = Integer.parseInt(geInfo(code)[stdCount_C]);
        coursesTable.update("stdCount",String.valueOf(stdCount+1),"code",code);
    }
    public static void decreaseStdCount(String code){
        int stdCount = Integer.parseInt(geInfo(code)[stdCount_C]);
        coursesTable.update("stdCount",String.valueOf(stdCount-1),"code",code);
    }

    public static String[] findCode(String[] conditions){
        ArrayList<String> valueName = new ArrayList<>();
        ArrayList<String> value = new ArrayList<>();
        for (int i = 0; i < conditions.length; i++) {
            if(conditions[i].isEmpty())
                continue;
            else if(i==stdCount_C||i==limitCount_C)
                continue;
            else
                {
                    valueName.add(CourseCol[i]);
                    value.add(conditions[i]);
                }
        }
        return coursesTable.selectAll("code",valueName.toArray(new String[0]),value.toArray(new String[0]));
    }
}
