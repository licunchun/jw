import Data.DataBase;
import Data.Type.ClassInfoSet;

public class TestBench {
    public static void main(String[] args) {
        DataBase dataBase = new DataBase();

        ClassInfoSet classInfoSet = dataBase.check();
        classInfoSet.findName("量子物理");

        System.out.println(classInfoSet.length());

        dataBase.close();
    }

}
