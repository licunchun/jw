package Service.Main.Student.ClassesServ;

import GUI.Data.DataPackage.Classes.Classes;
import GUI.Data.DataPackage.Classes.ClassesSet;
import GUI.Data.DataPackage.Classes.CourseCodeSet;
import GUI.Data.DataPackage.Classes.StudentCourseScoreTable;
import GUI.Data.Enum.Error.Main.Student.ClassesServ.DropClassesError;
import GUI.Data.Enum.Error.Main.Student.ClassesServ.PickClassesError;
import Service.Data.Tables.Courses;
import Service.Data.Tables.Points;
import Service.Data.Tables.Students;
import Service.Data.Utils.CodeUtil;
import Service.Data.Utils.PointUtil;
import Service.Data.Utils.TimeUtil;

import java.util.Arrays;


public class StudentClassesServ {
    private static final Students student = new Students();
    private static final Courses course = new Courses();
    public static ClassesSet getStudentClassesSet(String ID) {
        ClassesSet classesSet = new ClassesSet();
        String[] codes = Points.getAllCode(ID);
        for (String code:codes){
            String[] classInfo = Courses.getInfo(code);
            Classes c = Classes.fromArray(classInfo);
            classesSet.add(c);
        }
        return classesSet;
    }

    public static CourseCodeSet getStudentCourseCodeSet(String ID) {
        CourseCodeSet courseCodeSet = new CourseCodeSet();
        String[] codes = Points.getAllCode(ID);
        for (String code:codes){
            //获得课程信息
            String[] classInfo = Courses.getInfo(code);
            Classes classes = Classes.fromArray(classInfo);
            //获得学生分数
            String point = Points.getScore(code,ID);
            int score = Integer.parseInt(point);
            double GPA = PointUtil.pointToGPA(point);
            StudentCourseScoreTable studentCourseScoreTable = new StudentCourseScoreTable(classes,GPA,score);
            courseCodeSet.add(studentCourseScoreTable);
        }
        return courseCodeSet;
    }

    public static double getStudentTotalCredits(String ID) {
        if(!Points.isIDExist(ID))
            return -1.0;
        double totalCredits = 0.0;
        String[] codes = Points.getAllCode(ID);
        for (String code:codes){
            double credits = Double.parseDouble(course.getCredits(code));
            totalCredits += credits;
        }
        return totalCredits;
    }

    public static double getStudentReceivedCredits(String ID) {
        if(!Points.isIDExist(ID))
            return -1.0;
        double receivedCredits = 0.0;
        String[] codes = Points.getAllCode(ID);
        for (String code:codes){
            String point = Points.getScore(code,ID);
            if(point.isEmpty())
                continue;
            double credits = Double.parseDouble(course.getCredits(code));
            receivedCredits += credits;
        }
        return receivedCredits;
    }

    public static double getStudentFailedCredits(String ID) {
        if(!Points.isIDExist(ID))
            return -1.0;
        double failedCredits = 0.0;
        String[] codes = Points.getAllCode(ID);
        for (String code:codes){
            String point = Points.getScore(code,ID);
            if(point.isEmpty())
                continue;
            if(Double.parseDouble(point)>=60)
                continue;
            double credits = Double.parseDouble(course.getCredits(code));
            failedCredits += credits;
        }
        return failedCredits;
    }

    public static double getStudentAverageGrade(String ID) {
        if(!Points.isIDExist(ID))
            return -1.0;
        int totalScore = 0;
        int num = 0;
        String[] codes = Points.getAllCode(ID);
        for (String code:codes){
            String point = Points.getScore(code,ID);
            if(point.isEmpty())
                continue;
            totalScore += Integer.parseInt(point);
            num += 1;
        }
        return ((double)totalScore)/num;
    }

    public static double getStudentWeightedAverageGrade(String ID) {
        if(!Points.isIDExist(ID))
            return -1.0;
        double totalScore = 0.0;
        double totalCredits = 0.0;
        String[] codes = Points.getAllCode(ID);
        for (String code:codes){
            String point = Points.getScore(code,ID);
            if(point.isEmpty())
                continue;
            double credits = Double.parseDouble(course.getCredits(code));
            totalScore += Integer.parseInt(point)*credits;
            totalCredits += credits;
        }
        return totalScore/totalCredits;
    }

    public static double getStudentGPA(String ID) {
        if(!Points.isIDExist(ID))
            return -1.0;
        double totalGPA = 0.0;
        double totalCredits = 0.0;
        String[] codes = Points.getAllCode(ID);
        for (String code:codes){
            String point = Points.getScore(code,ID);
            if(point.isEmpty())
                continue;
            double credits = Double.parseDouble(course.getCredits(code));
            totalGPA += PointUtil.pointToGPA(point)*credits;
            totalCredits += credits;
        }
        return totalGPA/totalCredits;
    }


    public static PickClassesError pickClasses(String studentID, String classesCode) {
        if(!Students.isIDExist(studentID))
            return PickClassesError.IDNotFind;
        if(!Courses.isCodeExist(classesCode))
            return PickClassesError.ClassesCodeNotFind;
        if(isPicked(studentID,classesCode)==Boolean.TRUE)
            return PickClassesError.ClassesISChosen;
        if(Courses.isCourseFull(classesCode))
            return PickClassesError.ClassesIsFull;
        //时间处理
        String studentTimes = student.getTimes(studentID);
        String courseTimes = course.getTimes(classesCode);
        String[] studentDays = TimeUtil.getDay(studentTimes);
        String[] courseDays = TimeUtil.getDay(courseTimes);
        if(!TimeUtil.isTimeFree(studentDays,courseDays))
        {
            System.out.println(TimeUtil.isTimeFree(studentDays,courseDays));
            return PickClassesError.ClassesIsFull;

        }

        //TODO:时间冲突的枚举类型

        //学生时间添加
        for (String day:courseDays){
            TimeUtil.addDayInDays(day,studentDays);
        }
        String newTimes = Arrays.toString(studentDays);
        student.setTimes(studentID,newTimes);
        //学生课程添加
        String classes = student.getClasses(studentID);
        CodeUtil.addCodeInClasses(classesCode,classes);
        student.setClasses(studentID,classes);
        //分数添加
        Points.addPoints(classesCode,studentID,"");
        //课程人数增加
        int stdCount = Integer.parseInt(course.getStdCount(classesCode));
        int limitCount = Integer.parseInt(course.getLimitCount(classesCode));
        course.setStdCount(classesCode,String.valueOf(stdCount+1));
        course.setFull(classesCode,limitCount==(stdCount+1)?"已满":"未满");
        return PickClassesError.Success;
    }

    public static DropClassesError dropClasses(String studentID, String classesCode) {
        if(!Students.isIDExist(studentID))
            return DropClassesError.IDNotFind;
        if(!Courses.isCodeExist(classesCode))
            return DropClassesError.ClassesCodeNotFind;
        if(isPicked(studentID,classesCode)==Boolean.FALSE)
            return DropClassesError.IDNotFind;
        //TODO:该课程已选的枚举类型

        String studentTimes = student.getTimes(studentID);
        String courseTimes = course.getTimes(classesCode);
        String[] studentDays = TimeUtil.getDay(studentTimes);
        String[] courseDays = TimeUtil.getDay(courseTimes);
        //学生时间删除
        for (String day:courseDays){
            TimeUtil.deleteDayInDays(day,studentDays);
        }
        String newTimes = Arrays.toString(studentDays);
        student.setTimes(studentID,newTimes);
        //学生课程删除
        String classes = student.getClasses(studentID);
        CodeUtil.deleteCodeInClasses(classesCode,classes);
        student.setClasses(studentID,classes);
        //分数删除
        Points.deletePoints(classesCode,studentID);
        //课程人数减少
        int stdCount = Integer.parseInt(course.getStdCount(classesCode));
        course.setStdCount(classesCode,String.valueOf(stdCount-1));
        course.setFull(classesCode,"未满");
        return DropClassesError.Success;
    }

    public static Boolean isPicked(String studentID, String classesCode) {
        if(!Points.isCodeIDExist(classesCode,studentID))
            return Boolean.FALSE;
        return Boolean.TRUE;
    }
}
