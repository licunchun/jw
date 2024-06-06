package Service.Data.Tables;


import Service.Data.SQLiteJDBC;

public class Teachers {
    public static final String tableName = Tables.TEACHERS;
    public static final String[] TeacherCol = Tables.TeacherCol;
    public static final String PRIMARY_KEY = "ID";
    private static final SQLiteJDBC teachersTable = new SQLiteJDBC(tableName);
    public boolean IDExist;

    private String ID;
    public String name;
    public String password;

    public String school;

    public String[] codes;
    public Teachers(String ID) {
        this.ID = ID;
        IDExist = isIDExist();
        if(!IDExist)
            return;
        String[] info = getInfo();
        name = info[1];
        password = info[2];
        school = info[3];
        codes = CodeTeacher.getCoursesCode(ID);
    }

    private boolean isIDExist(){
        return teachersTable.isColValueExist("ID",ID);
    }
    private String[] getInfo(){ return teachersTable.select(TeacherCol,"ID",ID); }
    //所有属性的set方法
    public void setName(String name){
        teachersTable.update("name",name,PRIMARY_KEY,ID);
    }
    public void setPassword(String password){
        teachersTable.update("password",password,PRIMARY_KEY,ID);
    }
    public void setSchool(String school){
        teachersTable.update("school",school,PRIMARY_KEY,ID);
    }

    //静态方法区
    public static String[] getAllID(){
        return teachersTable.selectAll("ID");
    }
    //添加老师和删除老师
    public static void addInfo(String[] info){
        teachersTable.insert(TeacherCol,info);
    }
    public static void deleteInfo(String ID){
        teachersTable.delete(PRIMARY_KEY,ID);
    }
    //一些特殊需求
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
}
