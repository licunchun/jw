package GUI.Data.DataPackage.Classes;

import GUI.Data.Enum.Classes.*;
import GUI.Data.Enum.School;

public class Classes {
    private String code;               //课堂编号
    private String name;               //课堂名称
    private int period;                //学时
    private double credits;            //学分
    private CourseTimeSet time;           //时间
    private int stdCount;              //当前学生人数-
    private int limitCount;            //最大学生人数-
    private ClassType classType;       //理论/实验课
    private CourseType courseType;     //课程种类
    private School school;             //所属院系
    private Campus campus;             //校区
    private ExamMode examMode;         //考试方式
    private Language language;         //教学语言
    private Education education;       //本科/研究生
    private IDSet teacher;             //教师ID的
    private Full full;                 //课堂是否满了

    public Classes(){

    }

    public Classes(String code, String name, int period, double credits, CourseTimeSet time, int stdCount, int limitCount, ClassType classType, CourseType courseType, School school, Campus campus, ExamMode examMode, Language language, Education education, IDSet teacher, Full full) {
        this.code = code;
        this.name = name;
        this.period = period;
        this.credits = credits;
        this.time = time;
        this.stdCount = stdCount;
        this.limitCount = limitCount;
        this.classType = classType;
        this.courseType = courseType;
        this.school = school;
        this.campus = campus;
        this.examMode = examMode;
        this.language = language;
        this.education = education;
        this.teacher = teacher;
        this.full = full;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPeriod() {
        return period;
    }

    public void setPeriod(int period) {
        this.period = period;
    }

    public double getCredits() {
        return credits;
    }

    public void setCredits(double credits) {
        this.credits = credits;
    }

    public CourseTimeSet getTime() {
        return time;
    }

    public void setTime(CourseTimeSet time) {
        this.time = time;
    }

    public int getStdCount() {
        return stdCount;
    }

    public int getLimitCount() {
        return limitCount;
    }

    public ClassType getClassType() {
        return classType;
    }

    public void setClassType(ClassType classType) {
        this.classType = classType;
    }

    public CourseType getCourseType() {
        return courseType;
    }

    public void setCourseType(CourseType courseType) {
        this.courseType = courseType;
    }

    public School getSchool() {
        return school;
    }

    public void setSchool(School school) {
        this.school = school;
    }

    public Campus getCampus() {
        return campus;
    }

    public void setCampus(Campus campus) {
        this.campus = campus;
    }

    public ExamMode getExamMode() {
        return examMode;
    }

    public void setExamMode(ExamMode examMode) {
        this.examMode = examMode;
    }

    public Language getLanguage() {
        return language;
    }

    public void setLanguage(Language language) {
        this.language = language;
    }

    public Education getEducation() {
        return education;
    }

    public void setEducation(Education education) {
        this.education = education;
    }

    public IDSet getTeacher() {
        return teacher;
    }

    public void setTeacher(IDSet teacher) {
        this.teacher = teacher;
    }

    public Full getFull() {
        return full;
    }

    public void setFull(Full full) {
        this.full = full;
    }
}
