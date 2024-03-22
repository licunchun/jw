package Data;

import java.sql.*;
import java.util.concurrent.TimeUnit;

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
            String sql = "select key from " + table + " where account = '" + account + "'";
            resultSet = statement.executeQuery(sql);
            resultSet.next();
            String key = resultSet.getString(1);
            resultSet.close();
            return key;
        }
        catch (SQLException e) {
            return "";
        }
    }
    public boolean changeKey(String account, String key, int type) {
        String table = switch (type) {
            case STUDENT -> "students";
            case TEACHER -> "teachers";
            case MANAGER -> "managers";
            default -> "";
        };

        String sql = "update " + table + " set key = '" + key + "' where account = '" + account + "'";
        try {
            int rowsAffected = statement.executeUpdate(sql);
            return rowsAffected > 0; // 返回操作是否成功
        } catch (SQLException e) {
            return false;
        }
    }
    public Student infoOfStudent(String account) {
        String sql = "select * from students where account = '" + account + "'";
        try {
            resultSet = statement.executeQuery(sql);
            resultSet.next();
            Student student = new Student("");
            student.name = resultSet.getString("name");
            student.account = resultSet.getString("account");
            student.key = resultSet.getString("key");
            student.gender = resultSet.getString("gender");
            student.major = resultSet.getString("major");
            student.classes = resultSet.getString("classes");
            student.money = resultSet.getString("money");
            return student;
        } catch (SQLException e) {
            return null;
        }
    }
    public boolean addStudent(String name, String account, String key, String gender, String major) {
        String sql = "insert into students (name, account, key, gender, major, classes) " +
                "values ( '" + name + "', '" + account + "', '" + key + "', '" + gender + "', '" + major + "', 0)";
        System.out.println(sql);
        try {
            statement.execute(sql);
            return true;
        } catch (SQLException e) {
            return false;
        }
    }
    public boolean addTeacher(String name, String account, String key, String major) {
        String sql = "insert into teachers (name, account, key, major) " +
                "values ( '" + name + "', '" + account + "', '" + key + "', '" + major + "')";
        System.out.println(sql);
        try {
            statement.execute(sql);
            return true;
        } catch (SQLException e) {
            return false;
        }
    }
    public boolean addManager(String name, String account, String key) {
        String sql = "insert into managers (name, account, key) " +
                "values ( '" + name + "', '" + account + "', '" + key + "')";
        System.out.println(sql);
        try {
            statement.execute(sql);
            return true;
        } catch (SQLException e) {
            return false;
        }
    }
    public String keyOfStudent(String account) { return key(account, STUDENT); }
    public String keyOfTeacher(String account) {
        return key(account, TEACHER);
    }
    public String keyOfManager(String account) {
        return key(account, MANAGER);
    }
    public boolean changeKeyOfStudent(String account,String key){
        return changeKey(account, key, STUDENT);
    }
    public boolean changeKeyOfTeacher(String account,String key){
        return changeKey(account, key, TEACHER);
    }
    public boolean changeKeyOfManager(String account,String key){
        return changeKey(account, key, MANAGER);
    }
    public void close() throws SQLException {
        statement.close();
        connection.close();
    }
}
