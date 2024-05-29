package Service.Data.Tables;


import Service.Data.SQLiteJDBC;

public class Students {

    public static final String tableName = Tables.STUDENTS;
    public static final String[] StudentCol=Tables.StudentCol;
    public static final String PRIMARY_KEY = "ID";
    private static final SQLiteJDBC studentsTable = new SQLiteJDBC(tableName);


    public static boolean isIDExist(String ID){
        return studentsTable.isColValueExist("ID",ID);
    }
    public static boolean isNameExist(String name){
        return studentsTable.isColValueExist("name",name);
    }


    public static String[] getAllID(){
        return studentsTable.selectAll("ID");
    }



    public static void addInfo(String[] info){
        studentsTable.insert(StudentCol,info);
    }
    public static void deleteInfo(String ID){
        studentsTable.delete(PRIMARY_KEY,ID);
    }



    public String[] getInfo(String ID){
        return studentsTable.select(StudentCol,PRIMARY_KEY,ID);
    }
    //所有属性的get方法
    public String getName(String ID){
        return studentsTable.select("name",PRIMARY_KEY,ID);
    }
    public String getPassword(String ID) {
        return studentsTable.select("password", PRIMARY_KEY, ID);
    }
    public String getGrade(String ID) {
        return studentsTable.select("grade", PRIMARY_KEY, ID);
    }
    public String getGender(String ID) {
        return studentsTable.select("gender", PRIMARY_KEY, ID);
    }
    public String getSchool(String ID) {
        return studentsTable.select("school", PRIMARY_KEY, ID);
    }
    public String getClasses(String ID) {
        return studentsTable.select("classes", PRIMARY_KEY, ID);
    }
    public String getMoney(String ID) {
        return studentsTable.select("money", PRIMARY_KEY, ID);
    }
    public String getTimes(String ID) {
        return studentsTable.select("times", PRIMARY_KEY, ID);
    }


    //所有属性的set方法
    public void setName(String ID,String name){
        studentsTable.update("name",name,PRIMARY_KEY,ID);
    }
    public void setPassword(String ID,String password){
        studentsTable.update("password",password,PRIMARY_KEY,ID);
    }
    public void setGrade(String ID,String grade){
        studentsTable.update("grade",grade,PRIMARY_KEY,ID);
    }
    public void setGender(String ID,String gender){
        studentsTable.update("gender",gender,PRIMARY_KEY,ID);
    }
    public void setSchool(String ID,String school){
        studentsTable.update("school",school,PRIMARY_KEY,ID);
    }
    public void setClasses(String ID,String classes){
        studentsTable.update("classes",classes,PRIMARY_KEY,ID);
    }
    public void setMoney(String ID,String money){
        studentsTable.update("money",money,PRIMARY_KEY,ID);
    }
    public void setTimes(String ID,String times){
        studentsTable.update("times",times,PRIMARY_KEY,ID);
    }


    //一些特殊需求
    public static String[] getSameNameID(String name){
        return studentsTable.selectAll("ID","name",name);
    }
    public static String[] getIDWithSubID(String subID){
        return studentsTable.selectLike("ID","ID",subID);
    }
    public static String[] getIDWithSubName(String subName){
        return studentsTable.selectLike("ID","name",subName);
    }
    public static String[] getIDWithString(String subID,String subName){
        return studentsTable.selectLike("ID","ID",subID,"name",subName);
    }
}
