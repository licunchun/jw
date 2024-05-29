package Service.Data.Tables;

import Service.Data.SQLiteJDBC;
import Service.Data.Utils.TimeUtil;

import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Tables {
    public static final String STUDENTS = "students";
    public static final String TEACHERS = "teachers";
    public static final String MANAGERS = "managers";
    public static final String COURSES = "courses";
    public static final String POINTS = "points";
    public static final String[] TableName = {
        STUDENTS,TEACHERS,MANAGERS,COURSES,POINTS
    } ;
    public static final String[] StudentCol={
            "ID",
            "name",
            "password",
            "grade",
            "gender",
            "school",
            "classes",
            "money",
            "times",
    };
    public static final String[] TeacherCol={
            "ID",
            "name",
            "password",
            "school",
            "classes",
    };
    public static final String[] ManagerCol={
            "ID",
            "name",
            "password",
    };
    public static final String[] CourseCol={
            "code",
            "name",
            "period",
            "credits",
            "times",
            "stdCount",
            "limitCount",
            "classType",
            "courseType",
            "school",
            "campus",
            "examMode",
            "language",
            "education",
            "teachers",
            "full",
            "place"
    };
    public static final String[] PointCol = {
            "code",
            "ID",
            "point",
    };
    public static final String[][] ColName = {
            StudentCol,TeacherCol,ManagerCol,CourseCol,PointCol
    };
    private static final String originalDatabasePath = "src/原版数据库.db";
    private static final String currentDatabasePath = "src/Service/Data/test.db";
    private SQLiteJDBC originalDB = new SQLiteJDBC();
    private SQLiteJDBC currentDB = new SQLiteJDBC();

    public void createTables(){
        currentDB.setDatabasePath(currentDatabasePath);
        currentDB.setTableName(STUDENTS);
        currentDB.create(StudentCol,true);
        currentDB.setTableName(TEACHERS);
        currentDB.create(TeacherCol,true);
        currentDB.setTableName(MANAGERS);
        currentDB.create(ManagerCol,true);
        currentDB.setTableName(COURSES);
        currentDB.create(CourseCol,true);
        currentDB.setTableName(POINTS);
        currentDB.create(PointCol,false);
    }
    public static final String[] OriginalCourseCol={
            "code",
            "name",
            "period",
            "credits",
            "time",
            "stdCount",
            "limitCount",
            "courseType",

            "department",
            "campus",
            "examMode",
            "Language",
            "education",
            "classType",
            "teachers",
    };
    public void courseDataProcess(){
        originalDB.setDatabasePath(originalDatabasePath);
        originalDB.setTableName(COURSES);
        currentDB.setDatabasePath(currentDatabasePath);
        currentDB.setTableName(COURSES);
        String[] codes = originalDB.selectAll("code");
        for (String code : codes) {
            String[] info = originalDB.select(OriginalCourseCol, "code", code);
            String[] newInfo = new String[17];
            for (int i = 0; i < 4; i++) {
                newInfo[i] = info[i];
            }
            //时间
            if(info[4]==null){
                System.out.println(Arrays.toString(info));
                continue;
            }
            String[] day = TimeUtil.getDay(info[4]);
            //去除没有固定时间的课程
            if(day.length==0)
            {
                System.out.println(Arrays.toString(info));
                continue;
            }
            String[] days = TimeUtil.getTimes();
            for (int i = 0; i < day.length; i++) {
                TimeUtil.addDayInDays(day[i], days);
            }
            newInfo[4] = Arrays.toString(days);

            for (int i = 5; i < 8; i++) {
                newInfo[i] = info[i];
            }
            newInfo[8] = info[13];
            for (int i = 9; i < 14; i++) {
                newInfo[i] = info[i-1];
            }
            newInfo[14] = info[14];
            newInfo[15] = "未满";
            Pattern p = Pattern.compile("(?<=周\\s).*?(?=\\s:)");
            Matcher m = p.matcher(info[4]);
            m.find();
            newInfo[16] = m.group();
            currentDB.insert(CourseCol,newInfo);
        }
    }

    public static final String[] OriginalTeacherCol={
            "name",
            "account",
            "key",
            "classes",
    };
    public void teacherDataProcess(){
        originalDB.setDatabasePath(originalDatabasePath);
        originalDB.setTableName(TEACHERS);
        currentDB.setDatabasePath(currentDatabasePath);
        currentDB.setTableName(TEACHERS);

        String[] IDs = originalDB.selectAll("account");
        for (String ID:IDs){
            String[] info = originalDB.select(OriginalTeacherCol, "account", ID);
            String[] newInfo = new String[5];
            newInfo[0] = info[1];
            newInfo[1] = info[0];
            newInfo[2] = info[2];

            currentDB.setTableName(COURSES);
            String[] temp = currentDB.selectLike("school","teachers",newInfo[1]);
            //开课被删除的老师学院为教务处
            if(temp.length==0) {
                newInfo[3] = "教务处";
                newInfo[4] = "[]";
            } else {
                //选第一门课的开课单位作为该老师的院系
                newInfo[3] = temp[0];
                String[] codes = currentDB.selectLike("code","teachers",newInfo[1]);
                newInfo[4] = Arrays.toString(codes);
            }
            currentDB.setTableName(TEACHERS);
            currentDB.insert(TeacherCol,newInfo);
        }
    }
    //生成随机学生
    //TODO
    //挑选随机学生随机选课
    //TODO
    public static void main(String[] args) {
        Tables tables = new Tables();
        tables.createTables();
//        tables.courseDataProcess();
        tables.teacherDataProcess();
    }
}
