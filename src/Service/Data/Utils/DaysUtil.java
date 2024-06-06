package Service.Data.Utils;

import GUI.Data.DataPackage.Classes.CourseTimeSet;
import GUI.Data.Enum.Classes.CourseTime;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DaysUtil {
    public static int daysNum = 7;
    public static int sectionsNum = 13;
    public static final Pattern pattern = Pattern.compile("[0-9]\\.[0-9]{13}");
    public static Matcher mather;
    public static final String[] emptyDays = {
            "1.0000000000000",
            "2.0000000000000",
            "3.0000000000000",
            "4.0000000000000",
            "5.0000000000000",
            "6.0000000000000",
            "7.0000000000000",
    };
    public static boolean isFree(String days,String inDays){
        String[] day = getDaysFromDays(days);
        String[] inDay = getDaysFromDays(inDays);
        for (int i = 0; i < daysNum; i++) {
            String sum = BAND(day[i],inDay[i]).substring(day[i].length()-sectionsNum);
            int s = Integer.parseInt(sum);
            if(s==0)
                continue;
            return false;
        }
        return true;
    }
    public static String pack(String[] days){
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < daysNum; i++) {
            sb.append(days[i]);
        }
        return sb.toString();
    }
    public static char[] getEmptyDay(){
        return new char[]{'0','.','0','0','0','0','0','0','0','0','0','0','0','0','0'};
    }
//    public static String[] getEmptyDays(){
//        return emptyDays;
//    }
    public static String getEmptyDays(){
            return pack(emptyDays);
    }

    //
    public static String[] getDaysFromDays(String days){
        mather = pattern.matcher(days);
        ArrayList<String> day = new ArrayList<>();
        while (mather.find()){
            day.add(mather.group());
        }
        return day.toArray(new String[0]);
    }
    //用于将课程times里的时间转换成数据库时间
    public static String[] getDaysFromTimes(String times){
        String[] days = emptyDays;
        String[] timeDays = TimeUtil.getDays(times);
        for (int i = 0; i < timeDays.length; i++) {
            int whichDay = Integer.parseInt(String.valueOf(timeDays[i].charAt(0)));

            int[] sec = TimeUtil.getSection(timeDays[i]);
            char[] day = {timeDays[i].charAt(0),'.','0','0','0','0','0','0','0','0','0','0','0','0','0'};
            for (int j = 1; j < sec.length; j++) {
                day[sec[j]+1] = '1';
            }
            days[whichDay-1] = new String(day);
        }
        return days;
    }
    //用于将枚举类型转换成数据库类型
    private static String getDayFromCourseTime(CourseTime courseTime){
        char[] day = getEmptyDay();
        char c = switch (courseTime.getWeek().getIndex()){
            case 1 -> '1';
            case 2 -> '2';
            case 3 -> '3';
            case 4 -> '4';
            case 5 -> '5';
            case 6 -> '6';
            case 7 -> '7';
            default -> throw new RuntimeException();
        };
        day[0] = c;
        day[courseTime.getSection()+1] = '1';
        return new String(day);
    }
    public static String getDaysFromCourseTimeSet(CourseTimeSet courseTimeSet){
        String[] days = emptyDays;
        for (CourseTime courseTime:courseTimeSet.getCourseTimeIterable()){
            int whichDay = courseTime.getWeek().getIndex();
            String day = getDayFromCourseTime(courseTime);
            days[whichDay-1] = BOR(day,days[whichDay-1]);
        }
        return pack(days);
    }
    //
    public static CourseTimeSet getCourseTimeSetFromDays(String days){
        CourseTimeSet courseTimeSet = new CourseTimeSet();
        String[] day = getDaysFromDays(days);
        for (int i = 0; i < daysNum; i++) {
            for (int j = 0; j < sectionsNum; j++) {
                if(day[i].charAt(j+2)=='1')
                    courseTimeSet.add(CourseTime.fromInt(i+1,j+1));
            }
        }
        return courseTimeSet;
    }

    //用于查找
    public static String getLikeDays(String days){
        String[] day = getDaysFromDays(days);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < daysNum; i++) {
            sb.append(BLIKE(day[i]));
        }
        return sb.toString();
    }
    //改
    //用于添加
    public static String addDaysInDays(String days,String inDays){
        String[] day = getDaysFromDays(days);
        String[] inDay = getDaysFromDays(inDays);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < daysNum; i++) {
            sb.append(BOR(day[i],inDay[i]));
        }
        return sb.toString();
    }
    //用于删除
    public static String deleteDaysInDays(String days,String inDays){
        String[] day = getDaysFromDays(days);
        String[] inDay = getDaysFromDays(inDays);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < daysNum; i++) {
            sb.append(BAND(BNOT(day[i]),inDay[i]));
        }
        return sb.toString();
    }
    public static String BAND(String day1,String day2) {
        char[] newStr = new char[sectionsNum+2];
        newStr[0] = day1.charAt(0);
        newStr[1] = '.';
        for (int i = 2; i < sectionsNum+2; i++) {
            newStr[i] = AND(day1.charAt(i),day2.charAt(i));
        }
        return new String(newStr);
    }
    public static String BOR(String day1,String day2) {
        char[] newStr = new char[sectionsNum+2];
        newStr[0] = day1.charAt(0);
        newStr[1] = '.';
        for (int i = 2; i < sectionsNum+2; i++) {
            newStr[i] = OR(day1.charAt(i),day2.charAt(i));
        }
        return new String(newStr);
    }
    public static String BNOT(String day){
        char[] newStr = new char[sectionsNum+2];
        newStr[0] = day.charAt(0);
        newStr[1] = '.';
        for (int i = 2; i < sectionsNum+2; i++) {
            newStr[i] = NOT(day.charAt(i));
        }
        return new String(newStr);
    }
    public static String BLIKE(String day){
        char[] newStr = new char[sectionsNum+2];
        newStr[0] = day.charAt(0);
        newStr[1] = '.';
        for (int i = 2; i < sectionsNum+2; i++) {
            newStr[i] = LIKE(day.charAt(i));
        }
        return new String(newStr);
    }
    public static char AND(char c1,char c2){
        if(c1=='1'&&c2=='1')
            return '1';
        else
            return '0';
    }
    public static char OR(char c1,char c2){
        if(c1=='0'&&c2=='0')
            return '0';
        else
            return '1';
    }
    public static char NOT(char c){
        if(c=='0')
            return '1';
        else
            return '0';
    }
    public static char LIKE(char c){
        if(c=='0')
            return '_';
        else
            return '1';
    }

}
