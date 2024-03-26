import Data.Type.ClassInfoSet;
import Data.DataBase;

public class APP {
    public static void main(String[] args) throws Exception {
        DataBase dataBase = new DataBase();
//        if (dataBase.addClassOfStudent("PB22061222", "NS")) {
//            System.out.println("成功");
//        }
        System.out.println(dataBase.infoOfStudent("PB22061222").major);
        ClassInfoSet classInfoSet = dataBase.check();
        System.out.println(classInfoSet.index(0).name);
        dataBase.close();

    }

}
