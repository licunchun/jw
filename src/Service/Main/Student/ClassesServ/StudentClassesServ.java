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


public class StudentClassesServ {
    public static ClassesSet getStudentClassesSet(String ID) {
        ClassesSet classesSet = new ClassesSet();
        String[] codes = Points.getAllCode(ID);
        for (String code:codes){
            String[] classInfo = Courses.geInfo(code);
            Classes c = Classes.fromArray(classInfo);
            classesSet.add(c);
        }
        return classesSet;
    }

    public static CourseCodeSet getStudentCourseCodeSet(String ID) {
        CourseCodeSet courseCodeSet = new CourseCodeSet();
        String[] codes = Points.getAllCode(ID);
        for (String code:codes){
            String[] classInfo = Courses.geInfo(code);
            Classes classes = Classes.fromArray(classInfo);
            String point = Points.getScore(code,ID);
            int score = Integer.parseInt(point);
            double GPA = Points.getGPA(code,ID);
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
            String[] courseInfo = Courses.geInfo(code);
            double credits = Double.parseDouble(courseInfo[Courses.credits_C]);
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
            String[] courseInfo = Courses.geInfo(code);
            double credits = Double.parseDouble(courseInfo[Courses.credits_C]);
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
            String[] courseInfo = Courses.geInfo(code);
            double credits = Double.parseDouble(courseInfo[Courses.credits_C]);
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
            String score = Points.getScore(code,ID);
            if(score.isEmpty())
                continue;
            totalScore += Integer.parseInt(score);
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
            String[] courseInfo = Courses.geInfo(code);
            double credits = Double.parseDouble(courseInfo[Courses.credits_C]);
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
            String[] courseInfo = Courses.geInfo(code);
            double credits = Double.parseDouble(courseInfo[Courses.credits_C]);
            totalGPA += Points.pointToGPA(point)*credits;
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
        Points.addPoints(classesCode,studentID);
        Courses.increaseStdCount(classesCode);
        Students.courseSelection(studentID, classesCode);
        return PickClassesError.Success;
    }

    public static DropClassesError dropClasses(String studentID, String classesCode) {
        if(!Students.isIDExist(studentID))
            return DropClassesError.IDNotFind;
        if(!Courses.isCodeExist(classesCode))
            return DropClassesError.ClassesCodeNotFind;
        if(isPicked(studentID,classesCode)==Boolean.FALSE)
            return DropClassesError.IDNotFind;

        Points.deletePoints(classesCode,studentID);
        Courses.decreaseStdCount(classesCode);
        Students.courseWithdrawal(studentID, classesCode);
        return DropClassesError.Success;
    }

    public static Boolean isPicked(String studentID, String classesCode) {
        if(!Points.isCodeIDExist(classesCode,studentID))
            return Boolean.FALSE;
        return Boolean.TRUE;
    }
}
