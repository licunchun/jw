package Service.Data.Tables;

import Service.Data.SQLiteJDBC;
import Service.Data.Utils.PointUtil;

public class Points {
    public static final String tableName = Tables.POINTS;
    public static final String[] PointCol = Tables.PointCol;
    private static final SQLiteJDBC pointsTable = new SQLiteJDBC(tableName);

    //用于判断选课
    public static boolean isIDExist(String ID){
        return pointsTable.isColValueExist("ID",ID);
    }
    public static boolean isCodeIDExist(String code,String ID){
        return pointsTable.isStudentSelectCourse(code,ID);
    }
    public static void addPoints(String code,String ID,String point){
        pointsTable.insertPoints(code,ID,point);
    }
    public static void deletePoints(String code,String ID){
        pointsTable.deletePoints(code,ID);
    }

    public static String[] getAllCode(String ID){
        return pointsTable.selectAll("code","ID",ID);
    }
    public static String[] getAllID(String code){
        return pointsTable.selectAll("ID","code",code);
    }
    public static String getScore(String code,String ID){
        return pointsTable.selectPoints(code,ID);
    }
    public static void setScore(String code,String ID,String point){
        pointsTable.updatePoints(code,ID,point);
    }
    public static double getGPA(String code,String ID){
        return PointUtil.pointToGPA(getScore(code,ID));
    }
}
