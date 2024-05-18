package Service.Data.Database;

import Service.Data.SQLiteJDBC;

public class Teachers {
    private static final String tableName = "teachers";
    private static final SQLiteJDBC teachersTable = new SQLiteJDBC();
    public static boolean isIDExist(String ID){
        return teachersTable.isColValueExist(tableName,"ID",ID);
    }
}
