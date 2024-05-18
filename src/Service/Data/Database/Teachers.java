package Service.Data.Database;

import Service.Data.DataBase;
import Service.Data.SQLiteJDBC;

public class Teachers {
    private static final String tableName = "teachers";
    private static final SQLiteJDBC teachersTable = new SQLiteJDBC();
    public static final int ID_C = 0;
    public static final int name_C = 1;
    public static final int password_C = 2;
    public static final int classes_C = 3;

    public static boolean isIDExist(String ID){
        return teachersTable.isColValueExist(tableName,"ID",ID);
    }
    public static boolean isNameExist(String name){
        return teachersTable.isColValueExist(tableName,"name",name);
    }
    public static String[] getInfo(String ID){ return DataBase.selectTable(DataBase.TEACHER,"ID",ID); }
    public static String getID(String name){
        return teachersTable.select(tableName,"ID","name",name);
    }
    public static void addClasses(String code,String ID){
        String classes = getInfo(ID)[classes_C];
        if(classes.isEmpty())
            teachersTable.update("teachers","classes",code,"ID",ID);
        else
            teachersTable.update("teachers","classes",classes+","+code,"ID",ID);
    }
}
