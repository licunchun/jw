package Service.Data.Tables;


import Service.Data.SQLiteJDBC;

public class Teachers {
    public static final String tableName = Tables.TEACHERS;
    public static final String[] TeacherCol = Tables.TeacherCol;
    public static final String PRIMARY_KEY = "ID";
    private static final SQLiteJDBC teachersTable = new SQLiteJDBC(tableName);



    public static boolean isIDExist(String ID){
        return teachersTable.isColValueExist("ID",ID);
    }
    public static boolean isNameExist(String name){
        return teachersTable.isColValueExist("name",name);
    }
    public static String[] getAllID(){
        return teachersTable.selectAll("ID");
    }
    public static void addInfo(String[] info){
        teachersTable.insert(TeacherCol,info);
    }
    public static void deleteInfo(String ID){
        teachersTable.delete(PRIMARY_KEY,ID);
    }


    public static String[] getInfo(String ID){ return teachersTable.select(TeacherCol,"ID",ID); }
    //所有属性的get方法
    public String getName(String ID){
        return teachersTable.select("name",PRIMARY_KEY,ID);
    }
    public String getPassword(String ID){
        return teachersTable.select("password",PRIMARY_KEY,ID);
    }
    public String getSchool(String ID){
        return teachersTable.select("school",PRIMARY_KEY,ID);
    }
    public String getClasses(String ID){
        return teachersTable.select("classes",PRIMARY_KEY,ID);
    }
    //所有属性的set方法
    public void setName(String ID,String name){
        teachersTable.update("name",name,PRIMARY_KEY,ID);
    }
    public void setPassword(String ID,String password){
        teachersTable.update("password",password,PRIMARY_KEY,ID);
    }
    public void setSchool(String ID,String name){
        teachersTable.update("school",name,PRIMARY_KEY,ID);
    }
    public void setClasses(String ID,String classes){
        teachersTable.update("classes",classes,PRIMARY_KEY,ID);
    }





    public static String getID(String name){
        return teachersTable.select("ID","name",name);
    }
    public static String[] getSameNameID(String name){
        return teachersTable.selectAll("ID","name",name);
    }
    public static String[] getIDWithSubID(String subID){
        return teachersTable.selectLike("ID","ID",subID);
    }
    public static String[] getIDWithSubName(String subName){
        return teachersTable.selectLike("ID","name",subName);
    }
    public static String[] getIDWithString(String subID,String subName){
        return teachersTable.selectLike("ID","ID",subID,"name",subName);
    }
    public static String[] getIDWithCode(String code){
        return teachersTable.selectLike("ID","classes",code);
    }

}
