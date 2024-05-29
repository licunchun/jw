package Service.Data;//package Service.Data;
//
//import java.util.*;
//import java.util.regex.Matcher;
//import java.util.regex.Pattern;
//
//public class Main {
//    private static SQLiteJDBC originalDB = new SQLiteJDBC();
//    private static SQLiteJDBC currentDB = new SQLiteJDBC();
//    private static final String originalDatabasePath = "src/原版数据库.db";
//    private static final String currentDatabasePath = "src/Service/Data/test.db";
//    private static final DatabaseProcess dbp = new DatabaseProcess();
//    private static void CourseDataProcess(){
//        originalDB.setTableName("courses");
//        currentDB.setTableName("courses");
//        String[] codes = originalDB.selectAll("code");
//        for (String code : codes) {
//            String[] info = originalDB.select(DatabaseProcess.OriginalCourseCol, "code", code);
//            String[] newInfo = new String[21];
//            boolean WEEK = true;
//            for (int i = 0; i < 4; i++) {
//                newInfo[i] = info[i];
//            }
//            newInfo[4] = info[7];
//            newInfo[5] = info[13];
//            for (int i = 6; i < 11; i++) {
//                newInfo[i] = info[i+2];
//            }
//            newInfo[11] = info[14];
//            //去除没有固定时间的课程
//            if(info[4]==null)
//                continue;
//            Pattern p = Pattern.compile("周(.*?):");
//            Matcher m = p.matcher(info[4]);
//            while (m.find()) {
//                String place = m.group();
//                newInfo[12] = place.substring(2, place.length() - 2);
//                break;
//            }
//            newInfo[13] = "";
//            newInfo[14] = "";
//            newInfo[15] = "";
//            newInfo[16] = "";
//            newInfo[17] = "";
//            p = Pattern.compile("\\d\\(\\d+(,\\d+)*\\)");
//            m = p.matcher(info[4]);
//            while (m.find()) {
//                Pattern p_t = Pattern.compile("\\d+");
//                Matcher m_t = p_t.matcher(m.group());
//                String week = "";
//                HashSet<String> set = new HashSet<>();
//                while (m_t.find()) {
//                    if (week.isEmpty()) {
//                        week = m_t.group();
//                    } else {
//                        set.add(m_t.group());
//                    }
//                }
//                ArrayList<String> array = new ArrayList<>(set);
//                int week_t = Integer.parseInt(week);
//                if(week_t>5){
//                    WEEK = false;
//                }
//                else if (newInfo[13 + week_t - 1].isEmpty())
//                    newInfo[13 + week_t - 1] = Arrays.toString(array.toArray());
//
//            }
//            newInfo[18] = info[5];
//            newInfo[19] = info[6];
//            newInfo[20] = "未满";
//            if(WEEK)
//                currentDB.insert(DatabaseProcess.CourseCol,newInfo);
//        }
//    }
//    public static void TeacherDataProcess(){
//        originalDB.setTableName("teachers");
//
//        String[] IDs = originalDB.selectAll("account");
//        for (String ID:IDs){
//            String[] info = originalDB.select(DatabaseProcess.OriginalTeacherCol, "account", ID);
//            String[] newInfo = new String[10];
//            newInfo[0] = info[1];
//            newInfo[1] = info[0];
//            newInfo[2] = info[2];
//            currentDB.setTableName("courses");
//
//            String[] temp = currentDB.selectLike("school","teachers",newInfo[1]);
//            //开课被删除的老师学院为教务处
//            if(temp.length==0) {
//                newInfo[3] = "教务处";
//                newInfo[4] = "[]";
//            } else {
//                //选第一门课的开课单位作为该老师的院系
//                newInfo[3] = temp[0];
//                String[] codes = currentDB.selectLike("code","teachers",newInfo[1]);
//                newInfo[4] = Arrays.toString(codes);
//            }
//            currentDB.setTableName("teachers");
//            currentDB.insert(DatabaseProcess.TeacherCol,newInfo);
//        }
//    }
//
//    public static void main(String[] args) {
//        originalDB.setDatabasePath(originalDatabasePath);
//        currentDB.setDatabasePath(currentDatabasePath);
//        //建表
//        dbp.initSQLiteJDBC(currentDatabasePath);
//        dbp.createTables();
//        //读取数据//处理数据//插入数据
//
//        //先处理Course
////        CourseDataProcess();
////        TeacherDataProcess();
//    }
//}
import Service.Data.Utils.TimeUtil;
import Service.Main.Teacher.ClassesServ.TeacherClassesServ;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        String[] days = new String[7];
        for (int i = 0; i < 7; i++) {
            days[i] = i+1 +"()";
        }
        System.out.println(Arrays.toString(days));
        String day = "4(3,4,5)";
        TimeUtil.addDayInDays(day,days);
        System.out.println(Arrays.toString(days));
    }
}
