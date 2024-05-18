package Service.Data.Database;

import Service.Data.DataBase;
import Service.Data.SQLiteJDBC;

public class Students {
    private static final String tableName = "students";
    private static final SQLiteJDBC studentsTable = new SQLiteJDBC();
    public static final int ID_C = 0;
    public static final int name_C = 1;
    public static final int password_C = 2;
    public static final int grade_C = 3;
    public static final int gender_C = 4;
    public static final int school_C = 5;
    public static final int classes_C = 6;
    public static final int money_C = 7;


    public static boolean isIDExist(String ID){
        return studentsTable.isColValueExist(tableName,"ID",ID);
    }
    public static String[] getInfo(String ID){ return DataBase.selectTable(DataBase.STUDENT,"ID",ID); }
    public static void setMoney(String ID, double money){
        studentsTable.update(tableName,"money",String.valueOf(money),"ID",ID);
    }
    public static void setGrade(String ID,String grade){
        studentsTable.update(tableName,"grade",grade,"ID",ID);
    }
    public static void setSchool(String ID,String school){
        studentsTable.update(tableName,"school",school,"ID",ID);
    }

}
