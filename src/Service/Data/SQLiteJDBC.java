package Service.Data;

import java.sql.*;
import java.util.ArrayList;

public class SQLiteJDBC {
    private static final String databasePath = "src/Service/Data/database.db";
    private Connection c;
    private Statement stmt;
    private ResultSet rs;
    public SQLiteJDBC(){}
    private void connect(){
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:"+databasePath);
            stmt = c.createStatement();
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }
    }
    public void close() {
        try {
            c.close();
            stmt.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    private void execute(String sql) {
        connect();
        try {
            stmt.executeUpdate(sql);
            close();
        } catch (SQLException e) {
            close();
            System.out.println("Database execution failure");
            throw new RuntimeException(e);
        }

    }
    //
    public void create(String tableName,String[] colName,String[] dataConstraint,boolean primaryKey){
        StringBuilder sb = new StringBuilder();
        sb.append("CREATE TABLE IF NOT EXISTS ").append(tableName).append(" ");
        sb.append("(");
        for (int i = 0; i < colName.length; i++) {
            if(i==0)
                sb.append(colName[i]).append(" ").append("TEXT").append(" ").append(dataConstraint[i]);
            else
                sb.append(",").append(colName[i]).append(" ").append("TEXT").append(" ").append(dataConstraint[i]);
        }
        if(primaryKey)
            sb.append(",PRIMARY KEY (").append(colName[0]).append(")");
        sb.append(");");
        String sql = sb.toString();
        execute(sql);
    }
    public void insert(String tableName,String[] colName,String[] data){
        StringBuilder columns = new StringBuilder(colName[0]);
        StringBuilder values = new StringBuilder("'"+data[0]+"'");
        for (int i = 1; i < colName.length; i++) {
            columns.append(",").append(colName[i]);
            values.append(",").append("'").append(data[i]).append("'");
        }
        String sql = "INSERT INTO " + tableName +
                "(" + columns + ")" +
                " VALUES " +
                "(" + values + ");";
        execute(sql);
    }
    public boolean isColValueExist(String tableName,String colName,String value){
        String sql = "SELECT " + colName + " FROM " + tableName + " WHERE "+colName+" = '" + value + "';";
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

    //根据主键返回一组信息
    public String[] select(String tableName,String[] colName,String valueName,String value){
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
    //根据主键返回所需要的单个信息
    public String select(String tableName,String colName,String valueName,String value){
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
    //根据条件返回所有满足该条件的值
    public String[] selectAll(String tableName,String colName,String valueName,String value){
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
    //根据所有条件返回所有满足该条件的值
    public String[] selectAll(String tableName,String colName,String[] valueName,String[] value){
        StringBuilder conditions = new StringBuilder();
        for (int i = 0; i < valueName.length; i++) {
            if(i==0)
                conditions.append(valueName[i]).append(" = '").append(value[i]).append("'");
            else
                conditions.append(" AND ").append(valueName[i]).append(" = '").append(value[i]).append("'");
        }
        conditions.append(";");
        String sql = "SELECT " + colName + " FROM " + tableName + " WHERE " + conditions;
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
    //根据主键更新
    public void update(String tableName,String[] colName,String[] newData,String valueName,String value){
        for (int i = 1; i < colName.length; i++) {
            String sql = "UPDATE " + tableName + " SET " + colName[i] + " = '" + newData[i] +  "' WHERE " + valueName + " = '" + value + "';";
            execute(sql);
        }
    }
    //根据主键更新
    public void update(String tableName,String colName,String newData,String valueName,String value){
        String sql = "UPDATE " + tableName + " SET " + colName + " = '" + newData +  "' WHERE " + valueName + " = '" + value + "';";
        execute(sql);
    }
    //根据主键删除
    public void delete(String tableName,String valueName,String value){
        String sql = "DELETE " + " FROM " + tableName + " WHERE " + valueName + " = '" + value + "';";
        execute(sql);
    }


    //points操作
    public void insertPoints(String classesCode, String ID){
        String sql = "INSERT INTO points (code,ID,point) VALUES ('" + classesCode+"','"+ID+"','0');";
        execute(sql);
    }
    public void deletePoints(String classesCode, String ID){
        String sql = "DELETE " + " FROM " + "points" + " WHERE code = '" + classesCode + "' AND ID = '" + ID + "';";
        execute(sql);
    }
    public  String selectPonits(String classesCode, String ID){
        String sql = "SELECT " + "point" + " FROM " + "points" + " WHERE code = '" + classesCode + "' AND ID = '"+ ID +"';";
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
    public void updatePoints(String classesCode, String ID,String score){
        String sql = "UPDATE " + "points" + " SET " + "point" + " = '" + score +  "' WHERE code = '" + classesCode + "' AND ID = '" + ID + "';";
        execute(sql);
    }
}
