package Sevice.Main.Components.ClassServ;

import GUI.Data.DataPackage.Classes.Classes;
import GUI.Data.DataPackage.Classes.ClassesSet;
import GUI.Data.DataPackage.Classes.CourseTimeSet;
import GUI.Data.DataPackage.Classes.IDSet;

public class ClassesServ {
    public static ClassesSet searchClasses(Classes classes){
        return new ClassesSet();
    }
    public static IDSet getStudentSet(String classesCode){
        return new IDSet();
    }
    public static double getStudentGrade(String classesCode,String ID){
        return 0;
    }
    public static boolean setStudentGrade(String classesCode,String ID,double grade){
        return true;
    }
    public static String toStringTime(CourseTimeSet courseTimeSet){
        return "";
    }
}
