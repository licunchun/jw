import Data.Type.ClassInfoSet;
import Data.DataBase;

public class APP {
    public static void main(String[] args) {
        DataBase dataBase = new DataBase();

        System.out.println(dataBase.availableAccount("大一"));

        dataBase.close();
    }

}
