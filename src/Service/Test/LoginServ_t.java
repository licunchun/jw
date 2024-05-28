package Service.Test;

import Service.Login.LoginServ;
//不允许传null
public class LoginServ_t {
    public static void main(String[] args) {
        String[] ID = {"PB22061222","PB22061222","1234567","00000","00000",""};
        String[] password = {"275873","123","sas","00000000","","dsd"};
        for (int i = 0; i < ID.length; i++) {
            System.out.println(LoginServ.checkIDAndPassword(ID[i],password[i]));
        }

    }
}
