package Sevice.Main.Components.ClassServ;

import GUI.Data.DataPackage.Classes.Classes;
import GUI.Data.DataPackage.Classes.ClassesSet;
import GUI.Data.DataPackage.Classes.CourseTimeSet;
import GUI.Data.DataPackage.Classes.IDSet;
import GUI.Data.Enum.Classes.*;
import GUI.Data.Enum.Error.Main.Components.ClassesServ.DeleteClassesError;
import GUI.Data.Enum.Error.Main.Components.ClassesServ.NewClassesError;
import GUI.Data.Enum.School;

public class ClassesServ {
    public static Classes getClasses(String classesCode) {
        return new Classes();
    }//TODO

    public static ClassesSet searchClasses(Classes classes) {
        return new ClassesSet();
    }//TODO

    public static IDSet getStudentSet(String classesCode) {
        return new IDSet();
    }//TODO

    public static int getStudentGrade(String classesCode, String ID) {
        return 0;
    }//TODO

    public static boolean setStudentGrade(String classesCode, String ID, int grade) {
        return true;
    }//TODO

    public static double getStudentGPA(String classesCode, String ID) {
        return 0;
    }//TODO

    public static String toStringTime(CourseTimeSet courseTimeSet) {
        return "";
    }//TODO

    public static DeleteClassesError deleteClasses(String classesCode) {
        return DeleteClassesError.Success;
    }//TODO

    public static NewClassesError newClasses(
            String code,
            String name,
            String period,
            String credits,
            CourseTimeSet time,
            String maxCount,
            ClassType classType,
            CourseType courseType,
            School school,
            Campus campus,
            ExamMode examMode,
            Language language,
            Education education,
            String teacher
    ) {
        return NewClassesError.Success;
    }//TODO
}
