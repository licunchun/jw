import Data.DataBase;
import Data.Type.ClassInfoSet;
import Data.Type.Point;
import Data.Type.PointSet;

public class TestBench {
    public static void main(String[] args) {
        /* ClassInfoSet.find() 系列函数用法
        DataBase dataBase = new DataBase();

        ClassInfoSet classInfoSet = dataBase.check();
        classInfoSet.findName("量子物理");

        System.out.println(classInfoSet.length());

        dataBase.close();
        */

        DataBase dataBase = new DataBase();
        PointSet pointSet;

        dataBase.setPoint("011103.01", "PB23000000", "100");

        pointSet = dataBase.points();
        pointSet.findCode("011103.01");
        for (int i = 0; i < pointSet.length(); i++) {
            System.out.println("code: " + pointSet.get(i).code + ", account: " + pointSet.get(i).account + ", point: " + pointSet.get(i).point);
        }

        dataBase.setPoint("011103.01", "PB23000000", "null");

        pointSet = dataBase.points();
        for (int i = 0; i < pointSet.length(); i++) {
            System.out.println("code: " + pointSet.get(i).code + ", account: " + pointSet.get(i).account + ", point: " + pointSet.get(i).point);
        }

        dataBase.close();
    }

}
