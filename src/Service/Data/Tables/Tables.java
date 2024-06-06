package Service.Data.Tables;

import GUI.Data.Enum.School;
import GUI.Data.Enum.User.Gender;
import GUI.Data.Enum.User.Grade;
import GUI.Data.Enum.User.StudentSchool;
import GUI.Data.Enum.User.UserType;
import Service.Data.SQLiteJDBC;
import Service.Data.Utils.DaysUtil;
import Service.Data.Utils.NameUtil;
import Service.Data.Utils.PasswordUtil;
import Service.Data.Utils.TimeUtil;
import Service.Login.RegisterServ;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Tables {
    public static final String STUDENTS = "students";
    public static final String TEACHERS = "teachers";
    public static final String MANAGERS = "managers";
    public static final String COURSES = "courses";
    public static final String CODE_TEACHER = "codeTeacher";
    public static final String CODE_STUDENT = "codeStudent";
    public static final String[] TableName = {
        STUDENTS,TEACHERS,MANAGERS,COURSES,CODE_STUDENT,CODE_TEACHER
    } ;
    public static final String[] StudentCol={
            "ID",
            "name",
            "password",

            "grade",
            "gender",
            "school",
            "money",

            "days",
    };
    public static final String[] TeacherCol={
            "ID",
            "name",
            "password",

            "school",
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
    public static final String[] CodeStudentCol = {
            "code",
            "studentID",
            "point",
    };
    public static final String[] CodeTeacherCol = {
            "code",
            "teacherID",
    };
    public static final String[] PointCol = {
            "code",
            "ID",
            "point",
    };
    public static final String[][] ColName = {
            StudentCol,TeacherCol,ManagerCol,CourseCol,CodeStudentCol,CodeTeacherCol
    };
    private static final String originalDatabasePath = "src/原版数据库.db";
    private static final String currentDatabasePath = "src/Service/Data/test.db";
    private final SQLiteJDBC originalDB = new SQLiteJDBC();
    private final SQLiteJDBC currentDB = new SQLiteJDBC();

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
        currentDB.setTableName(CODE_STUDENT);
        currentDB.create(CodeStudentCol,false);
        currentDB.setTableName(CODE_TEACHER);
        currentDB.create(CodeTeacherCol,false);
    }
    public static final String[] OriginalTeacherCol={
            "name",
            "account",
            "key",
            "classes",
    };
    public void initTeachersTable(){
        originalDB.setDatabasePath(originalDatabasePath);
        originalDB.setTableName(TEACHERS);
        currentDB.setDatabasePath(currentDatabasePath);
        currentDB.setTableName(TEACHERS);

        String[] IDs = originalDB.selectAll("account");
        for (String ID:IDs){
            originalDB.setTableName(TEACHERS);
            String[] info = originalDB.select(OriginalTeacherCol, "account", ID);
            String[] newInfo = new String[4];
            newInfo[0] = info[1];
            newInfo[1] = info[0];
            newInfo[2] = info[2];

            originalDB.setTableName(COURSES);
            String[] temp = originalDB.selectLike("department","teachers",newInfo[1]);
            //开课被删除的老师学院为教务处
            if(temp.length==0) {
                newInfo[3] = "教务处";
                System.out.println(Arrays.toString(newInfo));
            } else {
                //选第一门课的开课单位作为该老师的院系
                newInfo[3] = temp[0];
            }
            currentDB.setTableName(TEACHERS);
            currentDB.insert(TeacherCol,newInfo);
        }
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

    public void initCoursesTable(){
        originalDB.setDatabasePath(originalDatabasePath);
        originalDB.setTableName(COURSES);
        currentDB.setDatabasePath(currentDatabasePath);
        currentDB.setTableName(COURSES);
        String[] codes = originalDB.selectAll("code");
        for (String code : codes) {
            String[] info = originalDB.select(OriginalCourseCol, "code", code);
            String[] newInfo = new String[17];
            //code、name、period、credits
            System.arraycopy(info, 0, newInfo, 0, 3);
            newInfo[3] = String.valueOf(Double.valueOf(info[3]));
            //times
            if(info[4]==null){
                System.out.println(Arrays.toString(info));
                continue;
            }
            String[] day = TimeUtil.getDays(info[4]);
            //去除没有固定时间的课程
            if(day.length==0)
            {
//                System.out.println(Arrays.toString(info));
                continue;
            }
            day = DaysUtil.getDaysFromTimes(info[4]);



            newInfo[4] = DaysUtil.pack(day);
            //stdCount,limitCount,classType
            System.arraycopy(info, 5, newInfo, 5, 3);
            //courseType
            newInfo[8] = info[13];
            //school,campus,examMode,language,education
            System.arraycopy(info, 8, newInfo, 9, 5);
            //teachers
            newInfo[14] = info[14];
            //full
            newInfo[15] = "未满";
            //place
            Pattern p = Pattern.compile("(?<=周\\s).*?(?=\\s:)");
            Matcher m = p.matcher(info[4]);
            if (m.find()){
                newInfo[16] = m.group();
            } else {
                throw new RuntimeException();
            }
            currentDB.insert(CourseCol,newInfo);

            //initCodeTeacher
            String[] names = NameUtil.getNames(info[14]);
            for (int i = 0; i < names.length; i++) {
                String[] teachersID = Teachers.getSameNameID(names[i]);
                String teacherID;
                if(teachersID.length==1)
                    teacherID = teachersID[0];
                else
                    throw new RuntimeException();
                String[] data = {newInfo[0],teacherID};
                CodeTeacher.addInfo(data);
            }
        }
    }
    public void addManager(){
        String[] zero = {"0","张义","0"};
        Managers.addInfo(zero);
        String[] one = {"1","陈润隆","1"};
        Managers.addInfo(one);
        String[] two = {"2","崔闰麟","2"};
        Managers.addInfo(two);
        String[] three = {"3","李存淳","3"};
        Managers.addInfo(three);
    }
    //生成随机学生
    public void addStudent(int num){
        UserType[] userTypes = UserType.values();
        Gender[] genders = Gender.values();
        StudentSchool[] schools = StudentSchool.values();
        Grade[] grades = Grade.values();
        Random random = new Random();

        for (int i = 0; i < num; i++) {
            UserType userType = UserType.Student;
            String name = NameUtil.getRandomName();
            String password = PasswordUtil.getRandomPassword(6);
            RegisterServ.regist(userType,name,password,password);
            Gender gender = genders[random.nextInt(genders.length)];
            School school = schools[random.nextInt(schools.length)].toSchool();
            Grade grade = grades[random.nextInt(grades.length)];
            RegisterServ.store(userType,name,password,password,gender,school,grade);
        }
    }
    //挑选随机学生随机选课
    //TODO

    public static void main(String[] args) {
        Tables tables = new Tables();
        tables.createTables();
        tables.initTeachersTable();
        tables.initCoursesTable();
//        tables.courseDataProcess();
//        tables.teacherDataProcess();
//        tables.initCodeTeacher();
//        tables.courseDataUpdate();
//        tables.addManager();
//        tables.addStudent(100);
    }
}
