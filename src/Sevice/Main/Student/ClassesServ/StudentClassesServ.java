package Sevice.Main.Student.ClassesServ;

import GUI.Data.DataPackage.Classes.ClassesSet;
import GUI.Data.DataPackage.Classes.CourseCodeSet;
import GUI.Data.Enum.Error.Main.Student.ClassesServ.DropClassesError;
import GUI.Data.Enum.Error.Main.Student.ClassesServ.PickClassesError;

public class StudentClassesServ {
    public static ClassesSet getStudentClassesSet(String ID) {
        return new ClassesSet();
    }//TODO

    public static CourseCodeSet getStudentCourseCodeSet(String ID) {
        return new CourseCodeSet();
    }//TODO

    public static double getStudentTotalCredits(String ID) {
        //返回总学分by lcc,可以参考，但不能直接用，请对比文档或者全部重写
//        ClassesSet classesSet = getStudentClassesSet(ID);
//        Iterable<Classes> classesSetIterable = classesSet.getClassesIterable();
//        double totalCredits = 0;
//        for(Classes studentClass : classesSetIterable) {
//            totalCredits = totalCredits + studentClass.getCredits();
//        }
//        return Math.round(totalCredits * 10) / 10.0;
        return 0;
    }//TODO

    public static double getStudentReceivedCredits(String ID) {
        //返回获得学分by lcc,可以参考，但不能直接用，请对比文档或者全部重写
//        ClassesSet classesSet = getStudentClassesSet(ID);
//        Iterable<Classes> classesSetIterable = classesSet.getClassesIterable();
//        double studentReceivedCredits = 0;
//        for(Classes studentClass : classesSetIterable) {
//            if(getStudentGrade(studentClass.getCode(), ID) >= 60) {
//                studentReceivedCredits = studentReceivedCredits + studentClass.getCredits();
//            }
//        }
//        return Math.round(studentReceivedCredits * 10) / 10.0;
        return 0;
    }//TODO

    public static double getStudentFailedCredits(String ID) {
        //返回不及格学分by lcc,可以参考，但不能直接用，请对比文档或者全部重写
//        ClassesSet classesSet = getStudentClassesSet(ID);
//        Iterable<Classes> classesSetIterable = classesSet.getClassesIterable();
//        double studentFailedCredits = 0;
//        for(Classes studentClass : classesSetIterable) {
//            if(getStudentGrade(studentClass.getCode(), ID) < 60) {
//                studentFailedCredits = studentFailedCredits + studentClass.getCredits();
//            }
//        }
//        return Math.round(studentFailedCredits * 10) / 10.0;
        return 0;
    }//TODO

    public static double getStudentAverageGrade(String ID) {
        //返回算术平均分by lcc,可以参考，但不能直接用，请对比文档或者全部重写
//        ClassesSet classesSet = getStudentClassesSet(ID);
//        Iterable<Classes> classesSetIterable = classesSet.getClassesIterable();
//        double studentAverageGrade = 0;
//        int countStudentClass = 0;//计算课程数
//        for(Classes studentClass : classesSetIterable) {
//            studentAverageGrade += getStudentGrade(studentClass.getCode(), ID);
//            countStudentClass ++;
//        }
//        studentAverageGrade /= countStudentClass;
//        return (Math.round(studentAverageGrade * 100) / 100.0);
        return 0;
    }//TODO

    public static double getStudentWeightedAverageGrade(String ID) {
        //返回加权平均分by lcc,可以参考，但不能直接用，请对比文档或者全部重写
//        ClassesSet classesSet = getStudentClassesSet(ID);
//        Iterable<Classes> classesSetIterable = classesSet.getClassesIterable();
//        double studentWeightedAverageGrade = 0;
//        double countStudentCredits = 0;//计算总学分
//        for(Classes studentClass : classesSetIterable) {
//            studentWeightedAverageGrade += getStudentGrade(studentClass.getCode(), ID) * studentClass.getCredits();
//            countStudentCredits += studentClass.getCredits();
//        }
//        studentWeightedAverageGrade /= countStudentCredits;
//        return (Math.round(studentWeightedAverageGrade * 100) / 100.0);
        return 0;
    }//TODO

    public static double getStudentGPA(String ID) {
        //返回GPA by lcc,可以参考，但不能直接用，请对比文档或者全部重写
//        ClassesSet classesSet = getStudentClassesSet(ID);
//        Iterable<Classes> classesSetIterable = classesSet.getClassesIterable();
//        double studentGPA = 0;
//        double countStudentCredits = 0;//计算总学分
//        for(Classes studentClass : classesSetIterable) {
//            studentGPA += getStudentClassGPA(studentClass.getCode(), ID) * studentClass.getCredits();
//            countStudentCredits += studentClass.getCredits();
//        }
//        studentGPA /= countStudentCredits;
//        return (Math.round(studentGPA * 100) / 100.0);
        return 0;
    }//TODO

    public static PickClassesError pickClasses(String studentID, String classesCode) {
        return PickClassesError.Success;
    }//TODO

    public static DropClassesError dropClasses(String studentID, String classesCode) {
        return DropClassesError.Success;
    }//TODO

    public static Boolean isPicked(String studentID, String classesCode) {
        return Boolean.TRUE;
    }//TODO
}
