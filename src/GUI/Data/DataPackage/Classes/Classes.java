package GUI.Data.DataPackage.Classes;

import GUI.Data.Enum.Classes.*;
import GUI.Data.Enum.School;

public class Classes {
    private String code;               //课堂编号
    private String name;               //课堂名称
    private int period;                //学时
    private double credits;            //学分
    private CourseTime time;           //时间
    private int stdCount;              //当前学生人数
    private int limitCount;            //最大学生人数
    private ClassType classType;       //理论/实验课
    private CourseType courseType;     //课程种类
    private School school;             //所属院系
    private Campus campus;             //校区
    private ExamMode examMode;         //考试方式
    private Language language;         //教学语言
    private Education education;       //本科/研究生
    private IDSet teacher;             //教师ID的
    private Full full;            //课堂是否满了
}
