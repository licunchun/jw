package Service.Data.Tables;


import Service.Data.SQLiteJDBC;
import Service.Data.Utils.DaysUtil;

public class Students {

    public static final String tableName = Tables.STUDENTS;
    public static final String[] StudentCol=Tables.StudentCol;
    public static final String PRIMARY_KEY = "ID";
    private static final SQLiteJDBC studentsTable = new SQLiteJDBC(tableName);

    public boolean IDExist;
    private String ID;
    public String name;
    public String password;

    public String grade;
    public String gender;
    public String school;
    public String money;

    public String days;
    public String[] codes;

    public Students(String ID) {
        this.ID = ID;
        IDExist = isIDExist();
        if(!IDExist)
            return;
        String[] info = getInfo();
        name = info[1];
        password = info[2];
        grade = info[3];
        gender = info[4];
        school = info[5];
        money = info[6];
        days = info[7];
        codes = CodeStudent.getCoursesCode(ID);
    }
    private boolean isIDExist(){
        return studentsTable.isColValueExist("ID",ID);
    }
    private String[] getInfo(){
        return studentsTable.select(StudentCol,PRIMARY_KEY,ID);
    }
    public boolean isFree(String days){
        return DaysUtil.isFree(days,this.days);
    }


    //所有属性的set方法
    public void setName(String name){
        studentsTable.update("name",name,PRIMARY_KEY,ID);
    }
    public void setPassword(String password){
        studentsTable.update("password",password,PRIMARY_KEY,ID);
    }
    public void setGrade(String grade){
        studentsTable.update("grade",grade,PRIMARY_KEY,ID);
    }
    public void setGender(String gender){
        studentsTable.update("gender",gender,PRIMARY_KEY,ID);
    }
    public void setSchool(String school){
        studentsTable.update("school",school,PRIMARY_KEY,ID);
    }
    public void setMoney(String money){
        studentsTable.update("money",money,PRIMARY_KEY,ID);
    }
    public void setDays(String days){
        studentsTable.update("days",days,PRIMARY_KEY,ID);
    }

    //静态方法区
    public static String[] getAllID(){
        return studentsTable.selectAll("ID");
    }
    //添加学生和删除学生
    public static void addInfo(String[] info){
        studentsTable.insert(StudentCol,info);
    }
    public static void deleteInfo(String ID){
        studentsTable.delete(PRIMARY_KEY,ID);
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
