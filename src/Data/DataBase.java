package Data;

import java.sql.*;

public class DataBase {
    public static final int STUDENT = 1;
    public static final int TEACHER = 2;
    public static final int MANAGER = 3;
    Connection connection = null;
    Statement statement = null;
    ResultSet resultSet = null;
    public DataBase() throws Exception {
        Class.forName("org.sqlite.JDBC");
        connection = DriverManager.getConnection("jdbc:sqlite:database.db");
        statement = connection.createStatement();
        System.out.println("Opened DataBase Successfully");
    }
    public String key(String account, int type) {
        String table = switch (type) {
            case STUDENT -> "students";
            case TEACHER -> "teachers";
            case MANAGER -> "managers";
            default -> "";
        };
        try {
            resultSet = statement.executeQuery("select key from " + table + " where account = '" + account + "'");
            resultSet.next();
            String key = resultSet.getString(1);
            resultSet.close();
            return key;
        }
        catch (SQLException e) {
            return "";
        }
    }
    public String keyOfStudent(String account){
        return key(account,1);
    }
    public String keyOfTeacher(String account) {
        return key(account,2);
    }
    public String keyOfManager(String account) {
        return key(account,3);
    }
    public boolean changeKey(String account, String key, int type) {
        String table = switch (type) {
            case STUDENT -> "students";
            case TEACHER -> "teachers";
            case MANAGER -> "managers";
            default -> "";
        };

        String updateQuery = "UPDATE " + table + " SET key = '" + key + "' WHERE account = '" + account + "'";
        try {
            int rowsAffected = statement.executeUpdate(updateQuery);
            return rowsAffected > 0; // 返回操作是否成功
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    public void changeKeyOfStudent(String account,String key){//记得interface.md改一下
        changeKey(account, key, 1);
    }
    public void changeKeyOfTeacher(String account,String key){
        changeKey(account, key, 2);
    }
    public void changeKeyOfManager(String account,String key){
        changeKey(account, key, 3);
    }
    public void close() throws SQLException {
        statement.close();
        connection.close();
    }
}
