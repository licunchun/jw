import Data.DataBase;

public class APP {
    public static void main(String[] args) throws Exception {
        DataBase dataBase = new DataBase();
        System.out.println(dataBase.keyOfStudent("PB22061222"));
        System.out.println(dataBase.keyOfTeacher("00001"));
        System.out.println(dataBase.keyOfManager("1"));
        dataBase.close();
    }

}
