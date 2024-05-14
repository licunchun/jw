import Data.DataBase;
import Data.Type.ClassInfo;
import Data.Type.ClassInfoSet;
import Data.Type.StudentSet;
import GUI.Data.DataPackage.Classes.Classes;
import GUI.Data.DataPackage.Classes.CourseTimeSet;
import GUI.Data.DataPackage.Classes.IDSet;
import GUI.Data.Enum.Classes.*;
import GUI.Data.Enum.School;
import Sevice.Main.Components.ClassServ.ClassesServ;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class TestBench {
    public static void main(String[] args) {
        DataBase dataBase = new DataBase();
        StudentSet studentSet = dataBase.infoOfStudents();
        studentSet.findName("crl");
        System.out.println(studentSet.get(0).account);


//        String code = "000";
//        String name = "测试课程";
//        String period = "40";
//        String credits = "3";
//        CourseTimeSet time = new CourseTimeSet();
//        time.add(CourseTime.Section3);
//        time.add(CourseTime.Section4);
//        time.add(CourseTime.Section5);
//        time.add(CourseTime.Section20);
//        time.add(CourseTime.Section21);
//        String maxCount = "100";
//        ClassType classType = ClassType.Theory;
//        CourseType courseType = CourseType.PE;
//        School school = School.ComputerScienceAndTechnology;
//        Campus campus = Campus.East;
//        ExamMode examMode = ExamMode.Computer;
//        Language language = Language.Chinese;
//        Education education = Education.Preparatory;
//        String teacher = "1234";
//        DataBase db = new DataBase();
//        String[] info = {"000","测试课程","40","3","3(1,2,3)","0","100","理论课","体育","计算机科学与技术学院","西区","机考","中文","预科","1234"};
//        if(db.addCourse(info))
//        {
//            System.out.println("yes");
//        }
//        else {
//            System.out.println("no");
//        }
//        Classes c = ClassesServ.getClasses("000");
//        c.print();
//        System.out.println(ClassesServ.toStringTime(c.getTime()));

    }
}
