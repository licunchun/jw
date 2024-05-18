package Service.Utils;

import Service.Data.DataBase;
import Service.Data.SQLiteJDBC;

public class IDManager {
    final private static int STUDENT_ID_LENGTH = 10;
    final private static int TEACHER_ID_LENGTH = 5;
    final private static int MANAGER_ID_LENGTH = 1;
    public static int getUserType(String ID){
        return switch (ID.length()){
            case (STUDENT_ID_LENGTH)-> DataBase.STUDENT;
            case (TEACHER_ID_LENGTH)->DataBase.TEACHER;
            case (MANAGER_ID_LENGTH)->DataBase.MANAGER;
            default -> DataBase.INVALID;
        };
    }
    public static boolean check(String ID){
        if(ID==null||ID.isEmpty())
            return false;
        return isIDExist(ID);
    }
    public static boolean isIDExist(String ID){
        return DataBase.isIDExist(ID);
    }
    public static String getavailableID(String grade){
        String ID = switch (grade) {
            case "大一" -> "PB23";
            case "大二" -> "PB22";
            case "大三" -> "PB21";
            case "大四" -> "PB20";
            default -> throw new RuntimeException();
        };
        for (int i = 0; i < 1000000; i++) {
            if(!isIDExist(ID+String.format("%06d", i)))
                return ID+String.format("%06d", i);
        }
        throw new RuntimeException();
    }
    public static String getavailableID(int userType){
        switch (userType){
            case DataBase.TEACHER -> {
                for (int i = 0; i < 100000; i++) {
                    if(!isIDExist(String.format("%05d", i)))
                        return String.format("%05d", i);
                }
                throw new RuntimeException();
            }
            case DataBase.MANAGER -> {
                for (int i = 0; i < 10; i++) {
                    if(!isIDExist(String.format("%01d", i)))
                        return String.format("%01d", i);
                }
                throw new RuntimeException();
            }
            default -> throw new RuntimeException();
        }
    }
}
