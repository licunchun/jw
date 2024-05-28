package Service.Test;

import Service.Main.Student.ClassesServ.StudentClassesServ;

public class StudentClassesServ_t {
    public static void main(String[] args) {
        StudentClassesServ.getStudentCourseCodeSet("PB22061222");
        System.out.println(StudentClassesServ.pickClasses("PB22061222","YK008.01"));
        System.out.println(StudentClassesServ.dropClasses("PB22061222","fdfs"));
        System.out.println(StudentClassesServ.isPicked("PB22061222","YK008.01"));
//        System.out.println(StudentClassesServ.getStudentClassesSet());
//        System.out.println(StudentClassesServ.getStudentCourseCodeSet());

    }
}
