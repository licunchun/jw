package Service.Data.Utils;

import GUI.Data.DataPackage.Classes.Classes;
import Service.Data.Tables.Courses;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CodeUtil {

    public static final Pattern pattern = Pattern.compile("[0-9A-Z.]+");//课程号规则
    public static Matcher matcher;

    public static String[] getCode(String classes){
        matcher = pattern.matcher(classes);
        ArrayList<String> code = new ArrayList<>();
        while (matcher.find()) {
            code.add(matcher.group());
        }
        return code.toArray(new String[0]);
    }


    //默认不重复
    public static String addCodeInClasses(String code,String classes){
        String[] codes = getCode(classes);
        String[] newClasses = new String[codes.length+1];
        System.arraycopy(codes, 0, newClasses, 0, codes.length);
        newClasses[codes.length] = code;
        return Arrays.toString(newClasses);
    }
    //默认必须存在
    public static String deleteCodeInClasses(String code,String classes){
        String[] codes = getCode(classes);
        String[] newClasses = new String[codes.length-1];
        int i = 0;
        for (String code_t:codes){
            if(Objects.equals(code_t, code))
                continue;
            newClasses[i] = code_t;
            i++;
        }
        return Arrays.toString(newClasses);
    }


}
