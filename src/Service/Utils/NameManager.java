package Service.Utils;

import Service.Data.Database.Teachers;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class NameManager {
    final public static int MAX_NAME_LENGTH = 10;
    public static boolean check(String name){
        if(name==null||name.isEmpty())
            return false;
        if(!checkLength(name))
            return false;
        if(!checkChar(name))
            return false;
        return true;
    }
    public static String[] teachersNameToID(String teachers){
        ArrayList<String> ID = new ArrayList<String>();
        Pattern pattern = Pattern.compile("[\\u4e00-\\u9fff]+");
        Matcher matcher = pattern.matcher(teachers);
        while (matcher.find()) {
            String name = matcher.group(); // 获取匹配结果
            ID.add(Teachers.getID(name));
        }
        return ID.toArray(new String[0]);
    }
    public static boolean checkLength(String name) {
        return name.length() <= MAX_NAME_LENGTH;
    }
    public static boolean checkChar(String name) {
        return name.matches("^[一-龥·]+$");
    }
}
