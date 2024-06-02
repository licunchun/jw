package Service.Data.Tables;

import Service.Data.SQLiteJDBC;

import java.util.ArrayList;

public class Courses {
    public static final String tableName = Tables.COURSES;
    public static final String[] CourseCol = Tables.CourseCol;
    public static final String PRIMARY_KEY = "code";
    private static final SQLiteJDBC coursesTable = new SQLiteJDBC(tableName);



    public static boolean isCodeExist(String code){
        return coursesTable.isColValueExist("code",code);
    }
    public static boolean isCourseFull(String code){
        String full = coursesTable.select("full","code",code);
        return full.compareTo("已满") == 0;
    }
    public static String[] getAllCode(){
        return coursesTable.selectAll("code");
    }
    public static void addInfo(String[] info){
        coursesTable.insert(CourseCol,info);
    }
    public static void deleteInfo(String code){
        coursesTable.delete(PRIMARY_KEY,code);
    }


    public static String[] getInfo(String code){
        return coursesTable.select(CourseCol,"code",code);
    }
    //所有属性的get方法
    public String getName(String code) {
        return coursesTable.select("name", PRIMARY_KEY, code);
    }
    public String getPeriod(String code) {
        return coursesTable.select("period", PRIMARY_KEY, code);
    }
    public String getCredits(String code) {
        return coursesTable.select("credits", PRIMARY_KEY, code);
    }
    public String getTimes(String code) {
        return coursesTable.select("times", PRIMARY_KEY, code);
    }
    public String getStdCount(String code) {
        return coursesTable.select("stdCount", PRIMARY_KEY, code);
    }
    public String getLimitCount(String code) {
        return coursesTable.select("limitCount", PRIMARY_KEY, code);
    }
    public String getClassType(String code) {
        return coursesTable.select("classType", PRIMARY_KEY, code);
    }
    public String getCourseType(String code) {
        return coursesTable.select("courseType", PRIMARY_KEY, code);
    }
    public String getSchool(String code) {
        return coursesTable.select("school", PRIMARY_KEY, code);
    }
    public String getCampus(String code) {
        return coursesTable.select("campus", PRIMARY_KEY, code);
    }
    public String getExamMode(String code) {
        return coursesTable.select("examMode", PRIMARY_KEY, code);
    }
    public String getLanguage(String code) {
        return coursesTable.select("language", PRIMARY_KEY, code);
    }
    public String getEducation(String code) {
        return coursesTable.select("education", PRIMARY_KEY, code);
    }
    public String getTeachers(String code) {
        return coursesTable.select("teachers", PRIMARY_KEY, code);
    }
    public String getFull(String code) {
        return coursesTable.select("full", PRIMARY_KEY, code);
    }
    //部分属性的set方法
    public void setStdCount(String code,String stdCount){
        coursesTable.update("stdCount",stdCount,PRIMARY_KEY,code);
    }
    public void setFull(String code,String full){
        coursesTable.update("full",full,PRIMARY_KEY,code);
    }


    public static String[] findCode(String[] conditions){
        ArrayList<String> valueName = new ArrayList<>();
        ArrayList<String> value = new ArrayList<>();
        for (int i = 0; i < conditions.length; i++) {
            if (!conditions[i].isEmpty()) {
                valueName.add(CourseCol[i]);
                value.add(conditions[i]);
            }
        }
        return coursesTable.selectAll("code",valueName.toArray(new String[0]),value.toArray(new String[0]));
    }
}
