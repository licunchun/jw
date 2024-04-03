package Sevice.Main.Student.ClassesServ;

import GUI.Data.DataPackage.Classes.ClassesSet;
import GUI.Data.Enum.Error.Main.Student.ClassesServ.PickClassesError;

public class StudentClassesServ {
    public static ClassesSet getStudentClassesSet(String ID){
        return new ClassesSet();
    }
    public static PickClassesError pickClasses(String studentID,String classesCode){
        return PickClassesError.Success;
    }
}
