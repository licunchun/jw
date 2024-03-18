package Data;

import java.sql.*;

public class DataBase {
    Connection connection = null;
    Statement statement = null;
    ResultSet resultSet = null;
    public DataBase() throws Exception {
        Class.forName("org.sqlite.JDBC");
        connection = DriverManager.getConnection("jdbc:sqlite:database.db");
        statement = connection.createStatement();
        System.out.println("Opened DataBase Successfully");
    }

    public String keyOfStudent(String account) throws SQLException {
        resultSet = statement.executeQuery("select key from students where account = '" + account + "'");
        if (resultSet.next()) {
            String key = resultSet.getString(1);
            resultSet.close();
            return key;
        }
        else {
            return "";
        }
    }

    public String keyOfTeacher(String account) throws SQLException {
        resultSet = statement.executeQuery("select key from teachers where account = '" + account + "'");
        if (resultSet.next()) {
            String key = resultSet.getString(1);
            resultSet.close();
            return key;
        }
        else {
            return "";
        }
    }

    public String keyOfManager(String account) throws SQLException {
        resultSet = statement.executeQuery("select key from managers where account = '" + account + "'");
        if (resultSet.next()) {
            String key = resultSet.getString(1);
            resultSet.close();
            return key;
        }
        else {
            return "";
        }
    }

    public void close() throws SQLException {
        statement.close();
        connection.close();
    }
}
