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
