package Utils;

import java.util.ArrayList;
import java.util.List;

public class IDUtil{
    public static int isIDExist(String ID){
        List<String> allID = Get.getAllID();
        for (int i = 0; i < allID.size(); i++) {
            if(ID.compareTo(allID.get(i))==0)
                return i;
            else
                continue;
        }
        return -1;
    }

}
