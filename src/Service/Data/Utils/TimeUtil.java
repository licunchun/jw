package Service.Data.Utils;

import Service.Data.Tables.Students;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TimeUtil {
//    public static final Pattern dayPattern = Pattern.compile("\\d\\((\\d+,)*\\)");//时间规则
    public static final Pattern dayPattern = Pattern.compile("\\d\\((\\d+)?(,\\d+)*\\)");//时间规则
    public static final Pattern secPattern = Pattern.compile("\\d+");
    public static Matcher matcher;
    public static String[] getTimes(){
        String[] days = new String[7];
        for (int i = 0; i < 7; i++) {
            days[i] = i+1 +"()";
        }
        return days;
    }
    //将一天的安排从times检索出
    public static String[] getDay(String times){
        matcher = dayPattern.matcher(times);
        ArrayList<String> day = new ArrayList<>();
        while (matcher.find()) {
            day.add(matcher.group());
        }
        return day.toArray(new String[0]);
    }

    //第一个为周x，后面为节数
    public static int[] getSection(String day){
        matcher = secPattern.matcher(day);
        ArrayList<Integer> section = new ArrayList<>();
        while (matcher.find()) {
            section.add(Integer.parseInt(matcher.group()));
        }
        int[] sec = new int[section.size()];
        for (int i = 0; i < sec.length; i++) {
            sec[i] = section.get(i);
        }
        return sec;
    }
    private static int[] getSection(int[] section) {
        int[] sec = new int[section.length-1];
        for (int i = 0; i < sec.length; i++) {
            sec[i] = section[i+1];
        }
        return sec;
    }
    public static String getSetDay(int[] section) {
        StringBuilder sb = new StringBuilder(String.valueOf(section[0]));
        sb.append("(");
        int[] sec = getSection(section);
        for (int i = 0; i < sec.length; i++) {
            if(i==sec.length-1)
                sb.append(sec[i]);
            else
                sb.append(sec[i]).append(",");
        }
        sb.append(")");
        return sb.toString();
    }
    public static boolean isSecFreeInDay(int section,String day){
        int[] sec = getSection(day);
        for (int sect:getSection(sec)){
            if(sect==section)
                return false;
        }
        return true;
    }
    public static String addSecInDay(int[] section,String day) {
        int[] sec = getSection(day);
        ArrayList<Integer> newSec = new ArrayList<>();
        newSec.add(0);
        for (int sect:getSection(sec)) {
            newSec.add(sect);
        }
        for (int sect:getSection(section)) {
            if(isSecFreeInDay(sect,day)){
                newSec.add(sect);
            }
        }
        newSec.sort(Integer::compareTo);
        int[] intArray = new int[newSec.size()];
        intArray[0] = section[0];
        for (int i = 1; i < newSec.size(); i++) {
            intArray[i] = newSec.get(i);
        }
        return getSetDay(intArray);
    }
    public static String deleteSecInDay(int[] section,String day) {
        int[] sec = getSection(day);
        int[] newSec = new int[section.length-sec.length+1];
        int i = 0;
        newSec[i] = sec[0];
        i++;
        for (int sect:getSection(section)) {
            if(sec[i]!=sect) {
                newSec[i] = sect;
                i++;
            }
        }
        return getSetDay(newSec);
    }

    public static void addDayInDays(String day,String[] days){
        int[] sections = getSection(day);
        if(sections.length==1)
            return;
        int whichDay = sections[0]-1;
        days[whichDay] = addSecInDay(sections,days[whichDay]);
    }
    public static void deleteDayInDays(String day,String[] days){
        int[] sections = getSection(day);
        if(sections.length==1)
            return;
        int whichDay = sections[0]-1;
        days[whichDay] = deleteSecInDay(sections,days[whichDay]);
    }


    //检测是否时间冲突
    public static boolean isTimeFree(String[] days1,String[] days2){
        for (String day:days1){
            if(isTimeFree(day,days2))
                continue;
            else
                return false;
        }
        return true;
    }
    private static boolean isTimeFree(String day,String[] days){
        int[] section = getSection(day);
        return isSecFree(section,days[section[0]-1]);
    }
    private static boolean isSecFree(int[] section,String day) {
        int[] sec = getSection(day);
        for (int j = 1; j < section.length; j++) {
            for (int i = 1; i < sec.length; i++) {
                if(sec[i]==section[j])
                    return false;
            }
        }
        return true;
    }
}
