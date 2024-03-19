package Sevice;

import Data.DataBase;

public class UserService extends User{
    final static int STUDENT = 10;
    final static int TEACHER = 5;
    final static int MANAGER = 1;
    DataBase db;
    public int login(String ID_login,String password_login){
        String password;
        int permission = ID_login.length();
        switch (permission){
            case STUDENT:
                password = db.keyOfStudent(ID_login);
                return password.compareTo(password_login)==0?1:0;
            case TEACHER:
                password = db.keyOfTeacher(ID_login);
                return password.compareTo(password_login)==0?2:0;
            case MANAGER:
                password = db.keyOfManager(ID_login);
                return password.compareTo(password_login)==0?3:0;
            default:
                return 0;
        }
    }
}
