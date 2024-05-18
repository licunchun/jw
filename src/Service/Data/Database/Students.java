package Service.Data.Database;

import Service.Data.SQLiteJDBC;

public class Students {
    private static final String tableName = "students";
    private static final SQLiteJDBC studentsTable = new SQLiteJDBC();

    public static boolean isIDExist(String ID){
        return studentsTable.isColValueExist(tableName,"ID",ID);
    }
}
