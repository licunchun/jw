package Service.Data;

import Service.Utils.IDManager;

public class UserDatabase {
    private String user = null;
    public static final int STUDENT = 0;
    public static final int TEACHER = 1;
    public static final int MANAGER = 2;
    private static void getUser()
    public static void setUserName(String ID, String name){
        int userType = IDManager.getUserType(ID);
        db.update(TableName[userType],"name",name,"ID",ID);
    }
}
