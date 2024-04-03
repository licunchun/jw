import Data.DataBase;

public class APP {
    public static void main(String[] args) {
        DataBase dataBase = new DataBase();

//        System.out.println(dataBase.availableAccount("大二"));
//        String [] info = new String[15];
//        info[0] = "hello, world!";
//        System.out.println(dataBase.addCourse(info));
        dataBase.close();
    }

}
