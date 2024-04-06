package Sevice.Main.Components.ClassServ;

import GUI.Data.DataPackage.Classes.Classes;
import GUI.Data.DataPackage.Classes.ClassesSet;
import GUI.Data.DataPackage.Classes.CourseTimeSet;
import GUI.Data.DataPackage.Classes.IDSet;
import GUI.Data.Enum.Error.Main.Components.ClassesServ.DeleteClassesError;

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

    public static double getStudentGrade(String classesCode, String ID) {
        return 0;
    }//TODO

    public static boolean setStudentGrade(String classesCode, String ID, double grade) {
        return true;
    }//TODO

    public static String toStringTime(CourseTimeSet courseTimeSet) {
        return "";
    }//TODO

    public static DeleteClassesError deleteClasses(String classesCode) {
        return DeleteClassesError.Success;
    }//TODO
}
