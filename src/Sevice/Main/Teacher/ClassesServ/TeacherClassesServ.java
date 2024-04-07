package Sevice.Main.Teacher.ClassesServ;

import GUI.Data.DataPackage.Classes.ClassesSet;
import GUI.Data.DataPackage.Classes.CourseCodeSet;
import GUI.Data.Enum.Error.Main.Student.ClassesServ.PickClassesError;

public class TeacherClassesServ {
    public static ClassesSet getTeacherClassesSet(String ID) {
        return new ClassesSet();
    }//TODO

    public static CourseCodeSet getTeacherCourseCodeSet() {
        return new CourseCodeSet();
    }//TODO
}
