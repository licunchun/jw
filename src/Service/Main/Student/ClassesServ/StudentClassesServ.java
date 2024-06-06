package Service.Main.Student.ClassesServ;

import GUI.Data.DataPackage.Classes.Classes;
import GUI.Data.DataPackage.Classes.ClassesSet;
import GUI.Data.DataPackage.Classes.CourseCodeSet;
import GUI.Data.DataPackage.Classes.StudentCourseScoreTable;
import GUI.Data.Enum.Error.Main.Student.ClassesServ.DropClassesError;
import GUI.Data.Enum.Error.Main.Student.ClassesServ.PickClassesError;
import Service.Data.Tables.CodeStudent;
import Service.Data.Tables.Courses;

import Service.Data.Tables.Students;

import Service.Data.Utils.DaysUtil;
import Service.Data.Utils.PointUtil;


import java.util.Objects;


public class StudentClassesServ {
    public static ClassesSet getStudentClassesSet(String ID) {
        Students students = new Students(ID);
        ClassesSet classesSet = new ClassesSet();
        for (String code: students.codes){
            String[] classInfo = Courses.getInfo(code);
            Classes c = Classes.fromArray(classInfo);
            classesSet.add(c);
        }
        return classesSet;
    }

    public static CourseCodeSet getStudentCourseCodeSet(String ID) {
        Students students = new Students(ID);
        CourseCodeSet courseCodeSet = new CourseCodeSet();
        for (String code: students.codes){
            //获得课程信息
            String[] classInfo = Courses.getInfo(code);
            Classes classes = Classes.fromArray(classInfo);
            //获得学生分数
            String point = CodeStudent.getPoint(code,ID);
            int score = Integer.parseInt(point);
            double GPA = PointUtil.pointToGPA(point);
            StudentCourseScoreTable studentCourseScoreTable = new StudentCourseScoreTable(classes,GPA,score);
            courseCodeSet.add(studentCourseScoreTable);
        }
        return courseCodeSet;
    }

    public static double getStudentTotalCredits(String ID) {
        Students students = new Students(ID);
        double totalCredits = 0.0;
        for (String code: students.codes){
            Courses courses = new Courses(code);
            double credits = Double.parseDouble(courses.credits);
            totalCredits += credits;
        }
        return totalCredits;
    }

    public static double getStudentReceivedCredits(String ID) {
        Students students = new Students(ID);
        double receivedCredits = 0.0;
        for (String code: students.codes){
            String point = CodeStudent.getPoint(code,ID);
            if(point.isEmpty())
                continue;
            Courses courses = new Courses(code);
            double credits = Double.parseDouble(courses.credits);
            receivedCredits += credits;
        }
        return receivedCredits;
    }

    public static double getStudentFailedCredits(String ID) {
        Students students = new Students(ID);
        double failedCredits = 0.0;
        for (String code:students.codes){
            String point = CodeStudent.getPoint(code,ID);
            if(point.isEmpty())
                continue;
            if(Double.parseDouble(point)>=60)
                continue;
            Courses courses = new Courses(code);
            double credits = Double.parseDouble(courses.credits);
            failedCredits += credits;
        }
        return failedCredits;
    }

    public static double getStudentAverageGrade(String ID) {
        Students students = new Students(ID);
        int totalScore = 0;
        int num = 0;
        for (String code: students.codes){
            String point = CodeStudent.getPoint(code,ID);
            if(point.isEmpty())
                continue;
            totalScore += Integer.parseInt(point);
            num += 1;
        }

        return num==0?0:((double)totalScore)/num;
    }

    public static double getStudentWeightedAverageGrade(String ID) {
        Students students = new Students(ID);
        double totalScore = 0.0;
        double totalCredits = 0.0;
        for (String code: students.codes){
            String point = CodeStudent.getPoint(code,ID);
            if(point.isEmpty())
                continue;
            Courses courses = new Courses(code);
            double credits = Double.parseDouble(courses.credits);
            totalScore += Integer.parseInt(point)*credits;
            totalCredits += credits;
        }
        return totalCredits==0.0?0:(totalScore/totalCredits);
    }

    public static double getStudentGPA(String ID) {
        Students students = new Students(ID);
        double totalGPA = 0.0;
        double totalCredits = 0.0;
        for (String code: students.codes){
            String point = CodeStudent.getPoint(code,ID);
            if(point.isEmpty())
                continue;
            Courses courses = new Courses(code);
            double credits = Double.parseDouble(courses.credits);
            totalGPA += PointUtil.pointToGPA(point)*credits;
            totalCredits += credits;
        }
        return totalGPA/totalCredits;
    }


    public static PickClassesError pickClasses(String studentID, String classesCode) {
        Students students = new Students(studentID);
        if(!students.IDExist)
            return PickClassesError.IDNotFind;
        Courses courses = new Courses(classesCode);
        if(!courses.codeExist)
            return PickClassesError.ClassesCodeNotFind;
        if(isPicked(studentID,classesCode)==Boolean.TRUE)
            return PickClassesError.ClassesISChosen;
        if(Objects.equals(courses.full, "已满"))
            return PickClassesError.ClassesIsFull;;
        if(!students.isFree(courses.times))
            return PickClassesError.TimeCrash;
        //学生时间添加
        String newDays = DaysUtil.addDaysInDays(courses.times,students.days);
        students.setDays(newDays);
        //学生课程添加
        String[] data = {classesCode,studentID,""};
        CodeStudent.addInfo(data);
        //课程人数增加
        int stdCount = Integer.parseInt(courses.stdCount);
        int limitCount = Integer.parseInt(courses.limitCount);
        courses.setStdCount(String.valueOf(stdCount+1));
        courses.setFull(limitCount==(stdCount+1)?"已满":"未满");
        return PickClassesError.Success;
    }

    public static DropClassesError dropClasses(String studentID, String classesCode) {
        Students students = new Students(studentID);
        if(!students.IDExist)
            return DropClassesError.IDNotFind;
        Courses courses = new Courses(classesCode);
        if(!courses.codeExist)
            return DropClassesError.ClassesCodeNotFind;
        if(isPicked(studentID,classesCode)==Boolean.FALSE)
            return DropClassesError.IDNotFind;
        // TODO:该课程已选的枚举类型

        //学生时间删除
        String newDays = DaysUtil.deleteDaysInDays(courses.times,students.days);
        students.setDays(newDays);
        //学生课程删除
        CodeStudent.deleteInfo(classesCode,studentID);
        //课程人数减少
        int stdCount = Integer.parseInt(courses.stdCount);
        courses.setStdCount(String.valueOf(stdCount-1));
        courses.setFull("未满");
        return DropClassesError.Success;
    }

    public static Boolean isPicked(String studentID, String classesCode) {
        if(CodeStudent.isStudentPickedCode(studentID,classesCode))
            return Boolean.TRUE;
        else
            return Boolean.FALSE;
    }
}
