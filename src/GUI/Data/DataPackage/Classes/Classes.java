package GUI.Data.DataPackage.Classes;

import Data.DataBase;
import GUI.Data.Enum.Classes.*;
import GUI.Data.Enum.School;

public class Classes {
    private String code;               //课堂编号
    private String name;               //课堂名称
    private Integer period;            //学时
    private Double credits;            //学分
    private CourseTimeSet time;        //时间
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
    private String place;

    public Classes() {

    }

    public Classes(String code,
                   String name,
                   Integer period,
                   Double credits,
                   CourseTimeSet time,
                   int stdCount,
                   int limitCount,
                   ClassType classType,
                   CourseType courseType,
                   School school,
                   Campus campus,
                   ExamMode examMode,
                   Language language,
                   Education education,
                   IDSet teacher,
                   Full full,
                   String place) {
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
        this.place = place;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
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

    public Integer getPeriod() {
        return period;
    }

    public void setPeriod(Integer period) {
        this.period = period;
    }

    public Double getCredits() {
        return credits;
    }

    public void setCredits(Double credits) {
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

    public static Classes fromArray(String[] classesInfo){
        String code = classesInfo[0];
        String name = classesInfo[1];
        Integer period = Integer.valueOf(classesInfo[2]);
        Double credits = Double.valueOf(classesInfo[3]);
        CourseTimeSet time = CourseTimeSet.fromString(classesInfo[4]);
        int stdCount = Integer.parseInt(classesInfo[5]);
        int limitCount = Integer.parseInt(classesInfo[6]);
        ClassType classType = ClassType.fromString(classesInfo[7]);
        CourseType courseType = CourseType.fromString(classesInfo[8]);
        School school = School.fromString(classesInfo[9]);
        Campus campus = Campus.fromString(classesInfo[10]);
        ExamMode examMode = ExamMode.fromString(classesInfo[11]);
        Language language = Language.fromString(classesInfo[12]);
        Education education = Education.fromString(classesInfo[13]);
        IDSet teacher = IDSet.fromTeacherString(classesInfo[14]);
        Full full = Full.fromString(classesInfo[15]);
        String place = classesInfo[16];
        return new Classes(
                code,
                name,
                period,
                credits,
                time,
                stdCount,
                limitCount,
                classType,
                courseType,
                school,
                campus,
                examMode,
                language,
                education,
                teacher,
                full,
                place
        );
    }

    public void print(){
        DataBase db = new DataBase();
        System.out.println("课堂编号\t"+code);
        System.out.println("课堂名称\t"+name);
        System.out.println("学时\t"+period);
        System.out.println("学分\t"+credits);
        System.out.println("时间\t");
        for (CourseTime courseTime : time.getCourseTimeIterable()) {
            System.out.println(courseTime);
        }
        System.out.println("当前学生人数\t"+stdCount+'\n'+"最大学生人数\t"+limitCount);
        System.out.println("课程类型\t"+classType);
        System.out.println("课程种类\t"+courseType);
        System.out.println("所属院校\t"+school);
        System.out.println("校区\t"+campus);
        System.out.println("考试方式\t"+examMode);
        System.out.println("教学语言\t"+language);
        System.out.println("本科/研究生\t"+education);
        System.out.println("教师ID\t");
        for (String s: teacher.getStudentIDSetIterable()) {
            System.out.println(s+ db.infoOfTeacher(s).name);
        }
        System.out.println("课堂状况\t"+full);
        System.out.println("教室位置\t"+place);
    }
}
