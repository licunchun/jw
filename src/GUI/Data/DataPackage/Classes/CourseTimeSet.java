package GUI.Data.DataPackage.Classes;

import GUI.Data.Enum.Classes.CourseTime;

import java.util.HashSet;
import java.util.Set;

public class CourseTimeSet {
    private Set<CourseTime> courseTimeSet=new HashSet<>();

    public CourseTimeSet(){

    }
    public void add(CourseTime courseTime){
        courseTimeSet.add(courseTime);
    }

    public void show(){
        for(CourseTime item:courseTimeSet){
            System.out.println(courseTimeSet.toString());
        }
    }
}
