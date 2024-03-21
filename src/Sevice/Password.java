package Sevice;

import Data.Enum.PasswordState;

public class Password {
    private String password;

    public Password() {
    }
    public Password(String password) {
        this.password = password;
    }

    final static int MAX_LENGTH = 10;
    private boolean lengthCheck(){
        int length = this.password.length();
        if(length<=MAX_LENGTH&&length>0)
            return true;
        else
            return false;
    }
    /*
    可以用正则优化
     */
    private boolean charCheck(){
        for (int i = 0; i < this.password.length(); i++) {
            char ch = this.password.charAt(i);
            if((ch>='a'&&ch<='z') ||(ch>='A'&&ch<='Z') ||(ch>='0'&&ch<='9'))
                continue;
            else
                return false;
        }
        return true;
    }
    public boolean compareTo(String anotherPassword){
        if(this.password.compareTo(anotherPassword)==0)
            return true;
        else
            return false;
    }
    public PasswordState checkValid(){
        if (this.password.isEmpty())
            return PasswordState.EMPTY;
        else if (!this.lengthCheck())
            return PasswordState.OVERLENGTH;
        else if(!this.charCheck())
            return PasswordState.INVALIDCHAR;
        else
            return PasswordState.NOTMATCH;
    }


















    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}




