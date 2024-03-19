package Sevice;

import Data.DataBase;

public class System {


    User user;
    public boolean login(String ID_login,String password_login){
        if(!check(ID_login,password_login))
            return false;
        else {
            user
        }
    }
    public int register(String ID_register,String password_register0,String password_register1){
        if(password_register0.compareTo(password_register1)!=0)
            return 0;
        if()

    }
    public boolean exit(){
        user = null;
        return true;
    }
}
