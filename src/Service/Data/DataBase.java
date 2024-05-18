package Service.Data;

import Data.Type.Student;
import GUI.Data.Enum.User.Grade;
import Service.Utils.IDManager;

import java.util.ArrayList;
import java.util.Objects;

public class DataBase {
    private static final SQLiteJDBC db = new SQLiteJDBC();
    public static final int INVALID = 5;
    public static final int STUDENT = 0;
    public static final int TEACHER = 1;
    public static final int MANAGER = 2;
    public static final int COURSE = 3;
    public static final int POINT = 4;
    public static final int ID_ = 0;
    public static final int NAME = 1;

    public static final int GRADE = 3;
    public static final int GENDER = 4;


    public static final String[] TableName = {"students","teachers","managers","courses","points"};
    private static final String[] StudentCol={"ID", "name", "password", "grade", "gender", "school", "classes", "money"};
    private static final String[] TeacherCol={"ID", "name", "password","classes"};
    private static final String[] ManagerCol={"ID", "name", "password"};
    private static final String[] CourseCol={"code","name","period","credits","time","stdCount","limitCount","classType","courseType","school","campus","examMode","language","education","teachers","full","place"};
    private static final String[] PointCol={"code","ID","point"};
    public static final String[][] TableCol = {
            StudentCol,
            TeacherCol,
            ManagerCol,
            CourseCol,
            PointCol,
    };
    public static final String[][] TableColConstraint = {
            {"NOT NULL","NOT NULL","NOT NULL","NOT NULL","NOT NULL","NOT NULL"," "," "},
            {"NOT NULL","NOT NULL","NOT NULL"," "},
            {"NOT NULL","NOT NULL","NOT NULL"},
            {"NOT NULL","NOT NULL","NOT NULL","NOT NULL","NOT NULL","NOT NULL","NOT NULL","NOT NULL","NOT NULL","NOT NULL","NOT NULL","NOT NULL","NOT NULL","NOT NULL","NOT NULL","NOT NULL","NOT NULL"},
            {"NOT NULL","NOT NULL"," "}
    };
    //UserServ
    public static String getUserName(String ID){
        int userType = IDManager.getUserType(ID);
        return selectTable(userType,"name","ID",ID);
    }
    public static void setUserName(String ID, String name){
        int userType = IDManager.getUserType(ID);
        db.update(TableName[userType],"name",name,"ID",ID);
    }
    public static String getUserPassword(String ID){
        int userType = IDManager.getUserType(ID);
        return selectTable(userType,"password","ID",ID);
    }
    public static void setUserPassword(String ID, String password){
        int userType = IDManager.getUserType(ID);
        db.update(TableName[userType],"password",password,"ID",ID);
    }
    public static String[] getUserInfo(String ID){
        int userType = IDManager.getUserType(ID);
        return db.select(TableName[userType],TableCol[userType],"ID",ID);
    }
    public static String[] getSameNameID(String tableName,String name){
        return db.selectAll(tableName,"ID","name",name);
    }
    public static String[] getSameNameID(String tableName,String ID,String name){
        ArrayList<String> valueName = new ArrayList<>();
        ArrayList<String> value = new ArrayList<>();
        if(ID!=null&&!ID.isEmpty()){
            valueName.add("ID");
            value.add(ID);
        }
        if(name!=null&&!name.isEmpty()){
            valueName.add("name");
            value.add(name);
        }
        return db.selectAll(tableName,"ID",valueName.toArray(new String[0]),value.toArray(new String[0]));
    };
    //StudentClassesServ
    public static String[] getClassInfo(String code){
        return db.select(TableName[COURSE],TableCol[COURSE],"code",code);
    }
    public static boolean isCodeIDinPonits(String code,String ID){
        if(!db.isColValueExist("points","code",code))
            return false;
        if(!db.isColValueExist("points","ID",ID)){
            return false;
        }
        return true;
    }

    //LoginServ


    public static String getTeacherID(String name){
        return selectTable(TEACHER,"ID","name",name);
    }
    public static String[] getStudentID(String classesCode){
        return db.selectAll(TableName[POINT],"ID","code",classesCode);
    }
    public static String getStudentScore(String classesCode, String ID){
        return db.selectPonits(classesCode,ID);
    }
    public static boolean setStudentScore(String classesCode, String ID,String score){
        db.updatePoints(classesCode,ID,score);
        return true;
    }
    public static String[] getClassesCode(String[] condition){
        ArrayList<String> valueName = new ArrayList<>();
        ArrayList<String> value = new ArrayList<>();
        for (int i = 0; i < condition.length; i++) {
            if(Objects.equals(condition[i], ""))
                continue;
            else {
                valueName.add(CourseCol[i]);
                value.add(condition[i]);
            }
        }
        return db.selectAll("courses","code",valueName.toArray(new String[0]),value.toArray(new String[0]));
    }
    public static boolean isIDExist(String ID){
        int userType = IDManager.getUserType(ID);
        if(userType == INVALID)
            return false;
        else {
            return db.isColValueExist(TableName[userType],"ID",ID);
        }
    }


    public static void createTable(int tableIndex,boolean primaryKey){
        db.create(TableName[tableIndex],TableCol[tableIndex],TableColConstraint[tableIndex],primaryKey);
    }
    public static void insertTable(int tableIndex,String[] data){
        db.insert(TableName[tableIndex],TableCol[tableIndex],data);
    }
    public static void deleteTable(int tableIndex,String valueName,String value){
        db.delete(TableName[tableIndex],valueName,value);
    }
    public static String[] selectTable(int tableIndex,String valueName,String value){
        return db.select(TableName[tableIndex],TableCol[tableIndex],valueName,value);
    }
    public static String selectTable(int tableIndex,String colName,String valueName,String value){
        return db.select(TableName[tableIndex],colName,valueName,value);
    }
    public static void updateTable(int tableIndex,String[] newData,String valueName,String value)
    {
        db.update(TableName[tableIndex],TableCol[tableIndex],newData,valueName,value);
    }
}
