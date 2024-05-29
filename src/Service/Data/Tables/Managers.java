package Service.Data.Tables;

import Service.Data.SQLiteJDBC;

public class Managers {
    public static final String tableName = Tables.MANAGERS;
    public static final String[] ManagerCol = Tables.ManagerCol;
    public static final String PRIMARY_KEY = "ID";
    private static final SQLiteJDBC managersTable = new SQLiteJDBC(tableName);



    public static boolean isIDExist(String ID){
        return managersTable.isColValueExist("ID",ID);
    }
    public static boolean isNameExist(String name){
        return managersTable.isColValueExist("name",name);
    }
    public static String[] getAllID(){
        return managersTable.selectAll("ID");
    }
    public static void addInfo(String[] info){
        managersTable.insert(ManagerCol,info);
    }
    public static void deleteInfo(String ID){
        managersTable.delete(PRIMARY_KEY,ID);
    }



    public static String[] getInfo(String ID){ return managersTable.select(ManagerCol,"ID",ID); }
    //所有属性的get方法
    public String getName(String ID){
        return managersTable.select("name",PRIMARY_KEY,ID);
    }
    public String getPassword(String ID){
        return managersTable.select("password",PRIMARY_KEY,ID);
    }
    //所有属性的set方法
    public void setName(String ID,String name){
        managersTable.update("name",name,"ID",ID);
    }
    public void setPassword(String ID,String password){
        managersTable.update("password",password,"ID",ID);
    }









    public static String[] getSameNameID(String name){
        return managersTable.selectAll("ID","name",name);
    }
    public static String[] getIDWithSubID(String subID){
        return managersTable.selectLike("ID","ID",subID);
    }
    public static String[] getIDWithSubName(String subName){
        return managersTable.selectLike("ID","name",subName);
    }
    public static String[] getIDWithString(String subID,String subName){
        return managersTable.selectLike("ID","ID",subID,"name",subName);
    }
}
