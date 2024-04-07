package Sevice.Main.Student.ClassesServ;

import GUI.Data.DataPackage.Classes.ClassesSet;
import GUI.Data.DataPackage.Classes.CourseCodeSet;
import GUI.Data.Enum.Error.Main.Student.ClassesServ.DropClassesError;
import GUI.Data.Enum.Error.Main.Student.ClassesServ.PickClassesError;

public class StudentClassesServ {
    public static ClassesSet getStudentClassesSet(String ID) {
        return new ClassesSet();
    }//TODO

    public static CourseCodeSet getStudentCourseCodeSet() {
        return new CourseCodeSet();
    }//TODO

    public static double getStudentTotalCredits() {
        return 0;
    }//TODO

    public static double getStudentReceivedCredits() {
        return 0;
    }//TODO

    public static double getStudentFailedCredits() {
        return 0;
    }//TODO

    public static int getStudentAverageGrade(String ID) {
        return 0;
    }//TODO

    public static int getStudentWeightedAverageGrade(String ID) {
        return 0;
    }//TODO

    public static double getStudentGPA(String classesCode, String ID) {
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
