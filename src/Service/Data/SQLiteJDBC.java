package Service.Data;

import java.sql.*;
import java.util.ArrayList;
import java.util.Objects;

public class SQLiteJDBC {
    private static final String DefaultPath = "src/Service/Data/test.db";//"src/Service/Data/database.db"
    private String tableName;
    private String databasePath;
    private Connection c;
    private Statement stmt;
    private ResultSet rs;
    private String sql;
    public SQLiteJDBC(){

    }
    public SQLiteJDBC(String tableName){
        this.databasePath = DefaultPath;
        this.tableName = tableName;
    }
    public void setTableName(String tableName){
        this.tableName = tableName;
    }
    public void setDatabasePath(String databasePath){
        this.databasePath = databasePath;
    }
    private void connect(){
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:"+ databasePath);
            stmt = c.createStatement();
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }
    }
    public void close() {
        try {
            sql = null;
            c.close();
            stmt.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    private void execute() {
        connect();
        try {
            stmt.executeUpdate(sql);
            close();
        } catch (SQLException e) {
            close();
            System.out.println(sql + "execution failure");
            throw new RuntimeException(e);
        }
    }
    //
    public void create(String[] colName,boolean primaryKey){
        StringBuilder col = new StringBuilder();
        for (int i = 0; i < colName.length; i++) {
            if(i==0)
                col.append(colName[i]).append(" TEXT NOT NULL ");
            else
                col.append(" , ").append(colName[i]).append(" TEXT NOT NULL ");
        }
        if(primaryKey)
            col.append(" ,PRIMARY KEY ( ").append(colName[0]).append(" ) ");
        sql = "CREATE TABLE IF NOT EXISTS " + tableName +
                " ( " + col + " );";
        execute();
    }
    //增
    public void insert(String[] colName,String[] data){
        StringBuilder columns = new StringBuilder();
        StringBuilder values = new StringBuilder();
        for (int i = 0; i < colName.length; i++) {
            if(i==0){
                columns.append(colName[i]);
                values.append("'").append(data[i]).append("'");
            } else{
                columns.append(",").append(colName[i]);
                values.append(",").append("'").append(data[i]).append("'");
            }
        }
        sql = "INSERT INTO " + tableName +
                " ( " + columns + " ) " +
                " VALUES " +
                " ( " + values + " );";
        execute();
    }
    //删
    //根据单值相等删除数据
    public void delete(String valueName,String value){
        sql = "DELETE " + " FROM " + tableName + " WHERE " + valueName + " = '" + value + "';";
        execute();
    }
    //根据双值相等删除数据
    public void delete(String valueName1,String value1,String valueName2,String value2){
        sql = "DELETE " + " FROM " + tableName + " WHERE " + valueName1 + " = '" + value1 + "' AND " + valueName2 + " = '" + value2 + "';";
        execute();
    }
    //改
    //根据主键更新一个值
    public void update(String colName,String newData,String valueName,String value){
        sql = "UPDATE " + tableName + " SET " + colName + " = '" + newData +  "' WHERE " + valueName + " = '" + value + "';";
        execute();
    }
    public void update(String colName,String newData,String valueName1,String value1,String valueName2,String value2){
        sql = "UPDATE " + tableName + " SET " + colName + " = '" + newData +  "' WHERE " + valueName1 + " = '" + value1 + "' AND " + valueName2 + " = '" + value2 + "';";
        execute();
    }
    //根据主键更新一些值
    public void update(String[] colName,String[] newData,String valueName,String value){
        for (int i = 1; i < colName.length; i++) {
            sql = "UPDATE " + tableName + " SET " + colName[i] + " = '" + newData[i] +  "' WHERE " + valueName + " = '" + value + "';";
            execute();
        }
    }

    //查
    public boolean isColValueExist(String colName,String value){
        String sql = "SELECT " + colName + " FROM " + tableName + " WHERE " + colName + " = '" + value + "';";
        try {
            connect();
            rs = stmt.executeQuery(sql);
            rs.next();
            rs.getString(colName);
            rs.close();
            close();
            return true;
        } catch (SQLException e) {
            close();
            return false;
        }
    }

    //根据主键查询数据库单个值
    public String select(String colName,String valueName,String value){
        String sql = "SELECT " + colName + " FROM " + tableName + " WHERE " + valueName + " = '" + value + "';";
        try {
            connect();
            rs = stmt.executeQuery(sql);
            rs.next();
            String res = rs.getString(colName);
            rs.close();
            close();
            return res;
        } catch (SQLException e) {
            close();
            System.out.println("Database lookup failure");
            throw new RuntimeException(e);
        }
    }
    ////根据双值相等查询数据库单个值
    public  String select(String colName,String valueName1,String value1,String valueName2,String value2){
        String sql = "SELECT " + colName + " FROM " + tableName + " WHERE " + valueName1 + " = '" + value1 + "' AND " + valueName2 + " = '" + value2 + "';";
        try {
            connect();
            rs = stmt.executeQuery(sql);
            rs.next();
            String res = rs.getString("point");
            rs.close();
            close();
            return res;
        } catch (SQLException e) {
            close();
            System.out.println("Database lookup failure");
            throw new RuntimeException(e);
        }
    }

    //根据主键查询数据库一些值

    public String[] select(String[] colName,String valueName,String value){
        StringBuilder colNames = new StringBuilder();
        for (int i = 0; i < colName.length; i++) {
            if(i==0)
                colNames.append(colName[0]);
            else
                colNames.append(",").append(colName[i]);
        }
        String sql = "SELECT " + colNames + " FROM " + tableName + " WHERE " + valueName + " = '" + value + "';";
        try {
            connect();
            rs = stmt.executeQuery(sql);
            rs.next();
            String[] res = new String[colName.length];
            for (int i = 0; i < colName.length; i++) {
                res[i] = rs.getString(colName[i]);
            }
            rs.close();
            close();
            return res;
        } catch (SQLException e) {
            close();
            System.out.println("Database lookup failure");
            throw new RuntimeException(e);
        }
    }

    public String[] selectAll(String colName){
        String sql = "SELECT " + colName + " FROM " + tableName + ";";
        try {
            connect();
            rs = stmt.executeQuery(sql);
            ArrayList<String> strings = new ArrayList<>();
            while (rs.next()){
                strings.add(rs.getString(colName));
            }
            rs.close();
            close();
            return strings.toArray(new String[0]);
        } catch (SQLException e) {
            close();
            System.out.println("Database lookup failure");
            throw new RuntimeException(e);
        }
    }

    //根据一个条件返回所有满足该条件的值
    public String[] selectAll(String colName,String valueName,String value){
        String sql = "SELECT " + colName + " FROM " + tableName + " WHERE " + valueName + " = '" + value + "';";
        try {
            connect();
            rs = stmt.executeQuery(sql);
            ArrayList<String> strings = new ArrayList<>();
            while (rs.next()){
                strings.add(rs.getString(colName));
            }
            rs.close();
            close();
            return strings.toArray(new String[0]);
        } catch (SQLException e) {
            close();
            System.out.println("Database lookup failure");
            throw new RuntimeException(e);
        }
    }
    //根据一些条件返回所有满足该条件的值
    public String[] selectAll(String colName,String[] valueName,String[] value){
        StringBuilder conditions = new StringBuilder();
        for (int i = 0; i < valueName.length; i++) {
            if(i==0)
            {
                conditions.append(" WHERE  ");
                conditions.append(valueName[i]).append(" = '").append(value[i]).append("'");
            }
            else if(Objects.equals(valueName[i], "teachers"))
                conditions.append(" AND ").append(valueName[i]).append(" LIKE '%").append(value[i]).append("%'");
            else
                conditions.append(" AND ").append(valueName[i]).append(" = '").append(value[i]).append("'");
        }
        conditions.append(";");


        String sql = "SELECT " + colName + " FROM " + tableName + conditions;
        try {
            connect();
            rs = stmt.executeQuery(sql);
            ArrayList<String> arrayList = new ArrayList<>();
            while (rs.next()){
                arrayList.add(rs.getString(colName));
            }
            rs.close();
            close();
            return arrayList.toArray(new String[0]);
        } catch (SQLException e) {
            close();
            System.out.println("Database lookup failure");
            throw new RuntimeException(e);
        }
    }
    public String[] selectLike(String colName,String valueName,String value){
        String sql = "SELECT " + colName + " FROM " + tableName + " WHERE " + valueName + " LIKE '%" + value + "%';";
        try {
            connect();
            rs = stmt.executeQuery(sql);
            ArrayList<String> strings = new ArrayList<>();
            while (rs.next()){
                strings.add(rs.getString(colName));
            }
            rs.close();
            close();
            return strings.toArray(new String[0]);
        } catch (SQLException e) {
            close();
            System.out.println("Database lookup failure");
            throw new RuntimeException(e);
        }
    }
    public String[] selectLike(String colName,String valueName1,String value1,String valueName2,String value2){
        String sql =
                " SELECT " + colName +
                " FROM " + tableName +
                " WHERE " + valueName1 + " LIKE '%" + value1 + "%'" +
                " AND " + valueName2 + " LIKE '%" + value2 + "%'" + ";";
        try {
            connect();
            rs = stmt.executeQuery(sql);
            ArrayList<String> strings = new ArrayList<>();
            while (rs.next()){
                strings.add(rs.getString(colName));
            }
            rs.close();
            close();
            return strings.toArray(new String[0]);
        } catch (SQLException e) {
            close();
            System.out.println("Database lookup failure");
            throw new RuntimeException(e);
        }
    }
    public String[] selectLike(String colName,String[] valueName,String[] value){
        StringBuilder conditions = new StringBuilder();
        for (int i = 0; i < valueName.length; i++) {
            if(i==0)
            {
                conditions.append(" WHERE  ");
                conditions.append(valueName[i]).append(" LIKE '%").append(value[i]).append("%'");
            }
            else
                conditions.append(" AND ").append(valueName[i]).append(" LIKE '%").append(value[i]).append("%'");
        }
        conditions.append(";");


        String sql = "SELECT " + colName + " FROM " + tableName + conditions;
        try {
            connect();
            rs = stmt.executeQuery(sql);
            ArrayList<String> arrayList = new ArrayList<>();
            while (rs.next()){
                arrayList.add(rs.getString(colName));
            }
            rs.close();
            close();
            return arrayList.toArray(new String[0]);
        } catch (SQLException e) {
            close();
            System.out.println("Database lookup failure");
            throw new RuntimeException(e);
        }
    }

    public boolean isTimeFree(String ID,String days){
        sql = "SELECT ID FROM " + tableName + " WHERE ID = '" +ID + "' AND days LIKE '" + days + "';";
        try {
            connect();
            rs = stmt.executeQuery(sql);
            if(rs.next()) {
                rs.close();
                close();
                return false;
            } else {
                rs.close();
                close();
                return true;
            }
        } catch (SQLException e) {
            close();
            throw new RuntimeException(e);
        }
    }


//    //points操作
//    public boolean isStudentSelectCourse(String code,String ID){
//        sql = "SELECT point FROM points WHERE code = '" +code + "' AND ID = '" + ID + "';";
//        try {
//            connect();
//            rs = stmt.executeQuery(sql);
//            rs.next();
//            String res = rs.getString("point");
//            rs.close();
//            close();
//            return true;
//        } catch (SQLException e) {
//            close();
//            return false;
//        }
//    }
//    public void insertPoints(String classesCode, String ID,String point){
//        sql = "INSERT INTO points (code,ID,point) VALUES ('" + classesCode+"','"+ID+"','');";
//        execute();
//    }
//    public void deletePoints(String classesCode, String ID){
//        sql = "DELETE " + " FROM " + "points" + " WHERE code = '" + classesCode + "' AND ID = '" + ID + "';";
//        execute();
//    }
//    public void updatePoints(String classesCode, String ID,String point){
//        sql = "UPDATE " + "points" + " SET " + "point" + " = '" + point +  "' WHERE code = '" + classesCode + "' AND ID = '" + ID + "';";
//        execute();
//    }
//    public  String selectPoints(String classesCode, String ID){
//        String sql = "SELECT " + "point" + " FROM " + "points" + " WHERE code = '" + classesCode + "' AND ID = '"+ ID +"';";
//        try {
//            connect();
//            rs = stmt.executeQuery(sql);
//            rs.next();
//            String res = rs.getString("point");
//            rs.close();
//            close();
//            return res;
//        } catch (SQLException e) {
//            close();
//            System.out.println("Database lookup failure");
//            throw new RuntimeException(e);
//        }
//    }



}
