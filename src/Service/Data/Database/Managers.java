package Service.Data.Database;

import Service.Data.SQLiteJDBC;

public class Managers {
    private static final String tableName = "managers";
    private static final SQLiteJDBC managersTable = new SQLiteJDBC();
    public static final int ID_C = 0;
    public static final int name_C = 1;
    public static final int password_C = 2;

    public static boolean isIDExist(String ID){
        return managersTable.isColValueExist(tableName,"ID",ID);
    }
}
