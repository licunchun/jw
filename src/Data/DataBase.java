package Data;

import Data.Type.ClassInfo;
import Data.Type.ClassInfoSet;
import Data.Type.Student;
import Data.Type.Teacher;

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;

public class DataBase {
    public static final int INVALID = 0;
    public static final int STUDENT = 1;
    public static final int TEACHER = 2;
    public static final int MANAGER = 3;
    Connection connection = null;
    Statement statement = null;
    ResultSet resultSet = null;

    public DataBase() {
        try {
            Class.forName("org.sqlite.JDBC");
            System.out.println("Success to Opened DataBase");
        } catch (ClassNotFoundException e) {
            System.out.println("Fail to Opened DataBase");
        }
        try {
            connection = DriverManager.getConnection("jdbc:sqlite:database.db");
            statement = connection.createStatement();
            System.out.println("Success to Connect DataBase");
        } catch (SQLException e) {
            System.out.println("Fail to Connect DataBase");
        }
    }

    public String key(String account, int type) { // 返回密码
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
        } catch (SQLException e) {
            return "";
        }
    }

    public boolean setKey(String account, String key, int type) { // 更改密码
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

    public Student infoOfStudent(String account) { // 已返回对应账号的学生信息
        String sql = "select * from students where account = '" + account + "'";
        try {
            resultSet = statement.executeQuery(sql);
            resultSet.next();
            String[] info = new String[8];
            for (int i = 0; i < 8; i++) {
                info[i] = resultSet.getString(i + 1);
            }
            return new Student(info);
        } catch (SQLException e) {
            return null;
        }
    }

    public Teacher infoOfTeacher(String account) { // 返回对应账号的老师信息
        String sql = "select * from teachers where account = '" + account + "'";
        try {
            resultSet = statement.executeQuery(sql);
            resultSet.next();
            String[] info = new String[4];
            for (int i = 0; i < 4; i++) {
                info[i] = resultSet.getString(i + 1);
            }
            return new Teacher(info);
        } catch (SQLException e) {
            return null;
        }
    }

    public boolean addStudent(String name, String account, String key, String grade, String gender, String major) { // 注册学生
        String sql = "insert into students (name, account, key, grade, gender, major, classes, money) " +
                "values ( '" + name + "', '" + account + "', '" + key + "', '" + grade + "', '" + gender +
                "', '" + major + "', '', '0')";
        try {
            statement.execute(sql);
            return true;
        } catch (SQLException e) {
            return false;
        }
    }

    public boolean addTeacher(String name, String account, String key) { // 注册老师
        String sql = "insert into teachers (name, account, key, classes) " +
                "values ( '" + name + "', '" + account + "', '" + key + "', '')";
        try {
            statement.execute(sql);
            return true;
        } catch (SQLException e) {
            return false;
        }
    }

    public boolean addManager(String name, String account, String key) { // 注册管理员
        String sql = "insert into managers (name, account, key) " +
                "values ( '" + name + "', '" + account + "', '" + key + "')";
        try {
            statement.execute(sql);
            return true;
        } catch (SQLException e) {
            return false;
        }
    }

    public boolean addCourse(String[] info) {
        if (info.length != 15) return false;
        try {
            StringBuilder sql = new StringBuilder("select * from courses where code = '").append(info[0]).append("'");
            resultSet = statement.executeQuery(sql.toString());
            resultSet.next();
            if (resultSet.getRow() == 1) return false;
            sql = new StringBuilder("insert into courses values ('");
            for (int i = 0; i < 14; i++) {
                sql.append(info[i]).append("', '");
            }
            sql.append(info[14]).append("')");
            System.out.println(sql);
            statement.execute(sql.toString());
            return true;
        } catch (SQLException e) {
            return false;
        }
    }

    public boolean deleteAccount(String account, int type) { // 删除学生或老师
        String table = switch (type) {
            case STUDENT -> "students";
            case TEACHER -> "teachers";
            default -> "";
        };
        String sql = "delete from " + table + " where account = '" + account + "'";
        try {
            statement.execute(sql);
            return true;
        } catch (SQLException e) {
            return false;
        }
    }

    public boolean setMoney(String account, int money) { // 设置学生余额
        String sql = "update students set money = " + money + " where account = '" + account + "'";
        try {
            statement.executeUpdate(sql);
            return true; // 返回操作是否成功
        } catch (SQLException e) {
            return false;
        }
    }
    public ClassInfoSet check() { // 查看总课程
        ClassInfoSet classInfoSet = new ClassInfoSet();
        String sql = "select * from courses";
        try {
            resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                String[] info = new String[16];
                for (int i = 0; i < 15; i++) {
                    info[i] = resultSet.getString(i + 1);
                }
                ClassInfo classInfo = new ClassInfo(info);
                classInfoSet.classInfos.add(classInfo);
            }
            return classInfoSet;
        } catch (SQLException e) {
            return null;
        }
    }

    public boolean addClassOfStudent(String account, String code) { // 学生添加课程
        String sql = "select classes from students where account = '" + account + "'";
        try {
            resultSet = statement.executeQuery(sql);
            resultSet.next();
            String classes = resultSet.getString(1);
            String[] classList = classes.split(", ");
            for (String i : classList) {
                if (i.equals(code)) return false;
            }
            if (classes.isEmpty()) {
                classes = code;
            } else {
                classes += ", " + code;
            }

            sql = "update students set classes = '" + classes + "' where account = '" + account + "'";
            if (statement.executeUpdate(sql) != 1) return false;
            sql = "insert into points values ('" + code + "', '" + account + "', 'null')";
            statement.execute(sql);
            return true;
        } catch (SQLException e) {
            return false;
        }
    }

    public boolean cancelClassOfStudent(String account, String code) { // 学生取消课程
        StringBuilder sql = new StringBuilder("select classes from students where account = '" + account + "'");
        try {
            resultSet = statement.executeQuery(sql.toString());
            resultSet.next();
            StringBuilder classes = new StringBuilder(resultSet.getString(1));
            ArrayList<String> classList = new ArrayList<>(Arrays.asList(classes.toString().split(", ")));
            if (!classList.remove(code)) return false;
            classes = new StringBuilder();
            for (String i : classList) {
                if (classes.isEmpty()) {
                    classes = new StringBuilder(i);
                } else {
                    classes.append(", ").append(i);
                }
            }
            sql = new StringBuilder().append("update students set classes = '").append(classes).append("' where account = '").append(account).append("'");
            return statement.executeUpdate(sql.toString()) == 1;
        } catch (SQLException e) {
            return false;
        }
    }

    public String availableAccount(String grade) { // 获取可行学生账号
        String account = switch (grade) {
            case "大一" -> "PB23";
            case "大二" -> "PB22";
            case "大三" -> "PB21";
            case "大四" -> "PB20";
            default -> "";
        };
        String sql = "select * from students where account = '";
        try {
            for (int i = 0; i < 1000000; i++) {
                resultSet = statement.executeQuery(sql + account + String.format("%06d", i) + "'");
                resultSet.next();
                if (resultSet.getRow() == 0) {
                    return account + String.format("%06d", i);
                }
            }
        } catch (SQLException e) {
            return "";
        }
        return "";
    }

    public String keyOfStudent(String account) {
        return key(account, STUDENT);
    }

    public String keyOfTeacher(String account) {
        return key(account, TEACHER);
    }

    public String keyOfManager(String account) {
        return key(account, MANAGER);
    }

    public boolean changeKeyOfStudent(String account, String key) {
        return setKey(account, key, STUDENT);
    }

    public boolean changeKeyOfTeacher(String account, String key) {
        return setKey(account, key, TEACHER);
    }

    public boolean changeKeyOfManager(String account, String key) {
        return setKey(account, key, MANAGER);
    }

    public void close() {
        try {
            statement.close();
            connection.close();
        } catch (SQLException e) {
            System.out.println("Fail to Close DataBase");
        }
    }
}
