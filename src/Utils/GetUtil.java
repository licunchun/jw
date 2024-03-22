package Utils;

import Data.Enum.User.UserType;

import java.util.ArrayList;
import java.util.List;

public class GetUtil {
    /*
    返回所有的用户ID，用于遍历用户ID是否存在
     */
    public static List<String> getAllID(){
        List<String> strList = new ArrayList<>();
        return strList;
    }
    /*
    获得索引的办法
     */






    /*
    通过用户类型，以及该用户对应索引来获得数据，后续可能都靠这样来获取数据
     */
    public static String getPassword(UserType userType,int index){
        String password = new String();
        return password;
    }
    /*
    获得一个当前可用的ID
     */
    public static String getAvailableID(UserType userType){
        String ID = new String();
        return ID;
    };
}
