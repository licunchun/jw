package Service.Data.Tables;

import Service.Data.SQLiteJDBC;

public class CodeStudent {
    public static final String tableName = Tables.CODE_STUDENT;
    public static final String[] CodeStudentCol = Tables.CodeStudentCol;
    private static final SQLiteJDBC codeStudentTable = new SQLiteJDBC(tableName);
    public static boolean isStudentPickedCode(String ID,String code){
        String[] valueName = {"studentID","code"};
        String[] value = {ID,code};
        String[] point = codeStudentTable.selectAll("point",valueName,value);
        return point.length==1;
    }


    public static String[] getStudentsID(String code){
        return codeStudentTable.selectAll("studentID","code",code);
    }
    public static String[] getCoursesCode(String studentID){
        return codeStudentTable.selectAll("code","studentID",studentID);
    }
    public static void setPoint(String code,String studentID,String point){
        codeStudentTable.update("point",point,"code",code,"studentID",studentID);
    }
    public static String getPoint(String code,String studentID){
        return codeStudentTable.select("point","code",code,"studentID",studentID);
    }

    public static void addInfo(String[] info){
        codeStudentTable.insert(CodeStudentCol,info);
    }
    public static void deleteInfo(String code,String studentID){
        codeStudentTable.delete("code",code,"studentID",studentID);
    }
    public static void deleteInfo(String courseCode){
        codeStudentTable.delete("code",courseCode);
    }
}
