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
        try {
            resultSet = statement.executeQuery("select key from students where account = '" + account + "'");
            resultSet.next();
            String key = resultSet.getString(1);
            resultSet.close();
            return key;
        }
        catch (SQLException e) {
            return "";
        }
    }
    public String keyOfTeacher(String account) {
        try {
            resultSet = statement.executeQuery("select key from teachers where account = '" + account + "'");
            resultSet.next();
                String key = resultSet.getString(1);
                resultSet.close();
                return key;
        }
        catch (SQLException e) {
            return "";
        }
    }
    public String keyOfManager(String account) {
        try {
            resultSet = statement.executeQuery("select key from managers where account = '" + account + "'");
            resultSet.next();
            String key = resultSet.getString(1);
            resultSet.close();
            return key;
            }
        catch (SQLException e) {
            return "";
        }
    }
    boolean changeKey(String account, String key, int type) {

//        statement.execute("")
        return true;
    }
    public void close() throws SQLException {
        statement.close();
        connection.close();
    }
}
