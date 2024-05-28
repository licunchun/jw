package Service.Data.Utils;

import Service.Data.Tables.User;

public class IDUtil {
    final private static int STUDENT_ID_LENGTH = 10;
    final private static int TEACHER_ID_LENGTH = 5;
    final private static int MANAGER_ID_LENGTH = 1;

    public static int getUserType(String ID){
        return switch (ID.length()){
            case (STUDENT_ID_LENGTH)-> User.STUDENT;
            case (TEACHER_ID_LENGTH)-> User.TEACHER;
            case (MANAGER_ID_LENGTH)-> User.MANAGER;
            default -> User.INVALID;
        };
    }
    public static boolean check(String ID){
        if(ID==null||ID.isEmpty())
            return false;
        return isIDExist(ID);
    }
    public static boolean isIDExist(String ID){
        return User.isIDExist(ID);
    }
    public static String getAvailableID(String grade){
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
    public static String getAvailableID(int userType){
        switch (userType){
            case User.TEACHER -> {
                for (int i = 0; i < 100000; i++) {
                    if(!isIDExist(String.format("%05d", i)))
                        return String.format("%05d", i);
                }
                throw new RuntimeException();
            }
            case User.MANAGER -> {
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
