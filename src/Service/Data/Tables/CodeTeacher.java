package Service.Data.Tables;

import Service.Data.SQLiteJDBC;

public class CodeTeacher {
    public static final String tableName = Tables.CODE_TEACHER;
    public static final String[] CodeTeacherCol = Tables.CodeTeacherCol;
    private static final SQLiteJDBC codeTeacherTable = new SQLiteJDBC(tableName);

    public static String[] getTeachersID(String courseCode){
        return codeTeacherTable.selectAll("teacherID","code",courseCode);
    }
    public static String[] getCoursesCode(String teacherID){
        return codeTeacherTable.selectAll("code","teacherID",teacherID);
    }
    public static void addInfo(String[] info){
        codeTeacherTable.insert(CodeTeacherCol,info);
    }
    public static void deleteInfo(String courseCode){
        codeTeacherTable.delete("code",courseCode);
    }
}
