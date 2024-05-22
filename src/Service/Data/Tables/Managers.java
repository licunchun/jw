package Service.Data.Tables;

import Service.Data.SQLiteJDBC;

public class Managers {
    public static final int ID_C = 0;
    public static final int name_C = 1;
    public static final int password_C = 2;
    private static final String tableName = "managers";
    private static final SQLiteJDBC managersTable = new SQLiteJDBC(tableName);
    private static final String[] ManagerCol = {"ID", "name", "password"};

<<<<<<< HEAD
    public static boolean isIDExist(String ID){
        return managersTable.isColValueExist("ID",ID);
    }
    public static boolean isNameExist(String name){
        return managersTable.isColValueExist("name",name);
    }
    public static String[] getAllID(){
        return managersTable.selectAll("ID");
    }
    public static String[] getInfo(String ID){ return managersTable.select(ManagerCol,"ID",ID); }
    public static void addInfo(String[] info){
        managersTable.insert(ManagerCol,info);
=======
    public static boolean isIDExist(String ID) {
        return managersTable.isColValueExist("ID", ID);
>>>>>>> a55caa14a831e51f048c088b9e1ebd00f97bc239
    }

    public static boolean isNameExist(String name) {
        return managersTable.isColValueExist("name", name);
    }

    public static String[] getInfo(String ID) {
        return managersTable.select(ManagerCol, "ID", ID);
    }

    public static void addInfo(String[] info) {
        managersTable.insert(ManagerCol, info);
    }

    public static void setName(String ID, String name) {
        managersTable.update("name", name, "ID", ID);
    }

    public static void setPassword(String ID, String password) {
        managersTable.update("password", password, "ID", ID);
    }

    public static String[] getSameNameID(String name) {
        return managersTable.selectAll("ID", "name", name);
    }
}
