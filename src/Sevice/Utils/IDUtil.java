package Sevice.Utils;


import Data.DataBase;

public class IDUtil{

    final private static int EMPTY = 0;
    final private static int PASS = 1;
    final private static int INVALID_LENGTH = 2;
    private final String ID;
    private final int type;
    public IDUtil(String ID) {
        this.ID = ID;
        type = UserTypeUtil.IDToAccount(this.ID);
    }

    public String getID() {
        return ID;
    }

    public int getType() {
        return type;
    }

    private int checkLength(){
        if(this.ID.isEmpty())
            return EMPTY;
        else if(this.type== DataBase.INVALID)//
            return INVALID_LENGTH;
        else
            return PASS;
    }
    private boolean checkChar(){
        //目前只有学生ID合法
        switch (type){
            case DataBase.INVALID -> {
                return false;
            }
            case DataBase.STUDENT -> {
                return this.ID.matches("^PB[0-9]+$");
            }
            case DataBase.TEACHER -> {
                return false;
            }
            case DataBase.MANAGER -> {
                return false;
            }
            default -> {
                return false;
            }
        }
    }
    public boolean checkValid(){
        if(checkLength()!=PASS)
            return false;
        return checkChar();
    }
}
