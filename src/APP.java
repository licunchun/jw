import Data.DataBase;

public class APP {
    public static void main(String[] args) throws Exception {
        DataBase dataBase = new DataBase();
        if (!dataBase.addStudent("陈润隆", "PBxxxxxxxx", "10086", "男", "计算机科学与技术"))
            System.out.println("false");
        System.out.println(dataBase.keyOfStudent("PBxxxxxxxx"));
        dataBase.close();

    }

}
