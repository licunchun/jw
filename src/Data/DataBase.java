package Data;

import Data.Type.ClassInfo;
import Data.Type.ClassInfoSet;
import Data.Type.Student;
import Data.Type.Teacher;

import java.sql.*;
import java.util.Arrays;

public class DataBase {
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
        }
        catch (ClassNotFoundException e) {
            System.out.println("Fail to Opened DataBase");
        }
        try {
            connection = DriverManager.getConnection("jdbc:sqlite:database.db");
            statement = connection.createStatement();
            System.out.println("Success to Connect DataBase");
        }
        catch (SQLException e) {
            System.out.println("Fail to Connect DataBase");
        }
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
            Student student = new Student();
            student.name = resultSet.getString("name");
            student.account = resultSet.getString("account");
            student.gender = resultSet.getString("gender");
            student.major = resultSet.getString("major");
            student.classes = resultSet.getString("classes");
            student.money = resultSet.getString("money");
            return student;
        } catch (SQLException e) {
            return null;
        }
    }
    public Teacher infoOfTeacher(String account) {
        String sql = "select * from teachers where account = '" + account + "'";
        try {
            resultSet = statement.executeQuery(sql);
            resultSet.next();
            Teacher teacher = new Teacher();
            teacher.name = resultSet.getString("name");
            teacher.classes = resultSet.getString("classes");
            return teacher;
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
        String sql = "insert into teachers (name, account, key, major, classes) " +
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
    public boolean setMoney(String account, int money) {
        String sql = "update students set money = " + money + " where account = '" + account + "'";
        try {
            statement.executeUpdate(sql);
            return true; // 返回操作是否成功
        } catch (SQLException e) {
            return false;
        }
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

    public ClassInfoSet check() { // 查看总课程
        ClassInfoSet classInfoSet = new ClassInfoSet();
        String sql = "select * from courses";
        try{
            resultSet = statement.executeQuery(sql);
            while(resultSet.next()){
                ClassInfo classInfo = new ClassInfo();
                classInfo.code = resultSet.getString("code");
                classInfo.name = resultSet.getString("name");
                classInfo.period = resultSet.getInt("period");
                classInfo.credits = resultSet.getDouble("credits");
                classInfo.time = resultSet.getString("time");
                classInfo.stdCount = resultSet.getInt("stdCount");
                classInfo.limitCount = resultSet.getInt("limitCount");
                classInfo.courseType = resultSet.getString("courseType");
                classInfo.department = resultSet.getString("department");
                classInfo.campus = resultSet.getString("campus");
                classInfo.examMode = resultSet.getString("examMode");
                classInfo.Language = resultSet.getString("Language");
                classInfo.education = resultSet.getString("education");
                classInfo.classType = resultSet.getString("classType");
                classInfo.teachers = resultSet.getString("teachers");
                classInfo.admin = resultSet.getString("admin");
                classInfoSet.classInfos.add(classInfo);
            }
            return classInfoSet;
        }
        catch (SQLException e) {
            return null;
        }
    }
    public boolean addClassOfStudent(String account, String code) {
        String sql = "select classes from students where account = '" + account + "'";
        try {
            resultSet = statement.executeQuery(sql);
            resultSet.next();
            // 从数据库中获取当前 account 对应的 classes 集合
            String string = resultSet.getString("classes");
            String[] classArray = null;
            if (!string.isEmpty()) {
                classArray = resultSet.getString("classes").split(",");//按逗号分隔
            }
            String[] newClassArray = Arrays.copyOf(classArray, classArray.length + 1);
            newClassArray[newClassArray.length - 1] = code;
            String classesString = String.join(",", newClassArray);
            sql = "update " + "students" + " set classes = '" + classesString + "' where account = '" + account + "'";
            // 执行更新操作
            int rowsAffected = statement.executeUpdate(sql);
            return rowsAffected > 0; // 如果有行受影响，更新成功返回 true，否则返回 false
        } catch (SQLException e) {
            return false;
        }
    }
//    public boolean cancelClass(String account, String code) {
//        String sql = "select classes from students where account = '" + account + "'";
//        try {
//            resultSet = statement.executeQuery(sql);
//            resultSet.next();
//            try {
//                String[] classArray = resultSet.getString("classes").split(",");//按逗号分隔
//                String[] newClassArray = new String[classArray.length];
//            }
//
//            int count = 0;
//            for(int i = 0; i< classArray.length; i++) {
//                if(code.equals(classArray[i])) {
//                    continue;
//                }
//                newClassArray[count] = classArray[i];
//                count++;
//            }
//            String classesString = String.join(",", newClassArray);
//            sql = "update students set classes = '" + classesString + "' where account = '" + account + "'";
//            // 执行更新操作
//            int rowsAffected = statement.executeUpdate(sql);
//            return rowsAffected > 0; // 如果有行受影响，更新成功返回 true，否则返回 false
//
//        } catch (SQLException e) {
//            return false;
//        }
//    }
}
