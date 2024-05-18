package Service.Data.Database;

import Service.Data.SQLiteJDBC;

public class Managers {
    private static final String tableName = "managers";
    private static final SQLiteJDBC managersTable = new SQLiteJDBC();
    public static boolean isIDExist(String ID){
        return managersTable.isColValueExist(tableName,"ID",ID);
    }
}
