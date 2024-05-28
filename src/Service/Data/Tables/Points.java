package Service.Data.Tables;

import Service.Data.SQLiteJDBC;

public class Points {
    private static final String tableName = "points";
    private static final SQLiteJDBC pointsTable = new SQLiteJDBC(tableName);
    public static final int code_C = 0;
    public static final int ID_C = 1;
    public static final int point_C = 2;

    public static boolean isIDExist(String ID){
        return pointsTable.isColValueExist("ID",ID);
    }
    public static boolean isCodeExist(String code){
        return pointsTable.isColValueExist("code",code);
    }
    public static boolean isCodeIDExist(String code,String ID){
        return isIDExist(ID)&&isCodeExist(code);
    }
    public static void addPoints(String code,String ID){
        pointsTable.insertPoints(code,ID);
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
        return pointsTable.selectPonits(code,ID);
    }
    public static void setScore(String code,String ID,String score){
        pointsTable.updatePoints(score,code,ID);
    }
    public static double getGPA(String code,String ID){
        return pointToGPA(getScore(code,ID));
    }
    //获得学生某门课的学分和分数
    public static double pointToGPA(String point){
        int grade = Integer.parseInt(point);
        if(grade>=95)
            return 4.3;
        else if (grade>=90)
            return 4.0;
        else if (grade>=85)
            return 3.7;
        else if (grade>=82)
            return 3.3;
        else if (grade>=78)
            return 3.0;
        else if (grade>=75)
            return 2.7;
        else if (grade>=72)
            return 2.3;
        else if (grade>=68)
            return 2.0;
        else if (grade>=65)
            return 1.7;
        else if (grade == 64)
            return 1.5;
        else if (grade>=61)
            return 1.3;
        else if (grade == 60)
            return 1.0;
        else
            return 0.0;
    }
}