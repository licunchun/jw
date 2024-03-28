import Data.Enum.User.Grade;
import Data.Type.ClassInfoSet;
import Data.DataBase;

public class APP {
    public static void main(String[] args) {
        DataBase dataBase = new DataBase();

        System.out.println(dataBase.availableAccount(Grade.Grade1));

        dataBase.close();
    }

}
