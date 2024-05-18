package Service.Data.Database;

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
}
