package Service.Data.Tables;

import Service.Data.SQLiteJDBC;

public class Managers {
    public static final String tableName = Tables.MANAGERS;
    public static final String[] ManagerCol = Tables.ManagerCol;
    public static final String PRIMARY_KEY = "ID";
    private static final SQLiteJDBC managersTable = new SQLiteJDBC(tableName);
    public boolean IDExist;
    private String ID;
    public String name;
    public String password;
    public Managers(String ID) {
        this.ID = ID;
        IDExist = isIDExist();
        if(!IDExist)
            return;
        String[] info = getInfo();
        name = info[1];
        password = info[2];
    }

    private boolean isIDExist(){
        return managersTable.isColValueExist("ID",ID);
    }
    private String[] getInfo(){ return managersTable.select(ManagerCol,"ID",ID); }
    //所有属性的set方法
    public void setName(String name){
        managersTable.update("name",name,"ID",ID);
    }
    public void setPassword(String password){
        managersTable.update("password",password,"ID",ID);
    }




    //静态方法区
    public static String[] getAllID(){
        return managersTable.selectAll("ID");
    }
    //添加和删除管理员
    public static void addInfo(String[] info){
        managersTable.insert(ManagerCol,info);
    }
    public static void deleteInfo(String ID){
        managersTable.delete(PRIMARY_KEY,ID);
    }
    //一些特殊需求
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
