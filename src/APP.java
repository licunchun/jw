import Data.DataBase;

public class APP {
    public static void main(String[] args) throws Exception {
        DataBase dataBase = new DataBase();
        System.out.println(dataBase.keyOfStudent("PB22061222"));
        System.out.println(dataBase.keyOfTeacher("00001"));
        System.out.println(dataBase.keyOfManager("1"));
        System.out.println(dataBase.key("PB22061222", DataBase.STUDENT));
        System.out.println(dataBase.key("00001", DataBase.TEACHER));
        System.out.println(dataBase.key("1", DataBase.MANAGER));
        dataBase.close();

    }

}
