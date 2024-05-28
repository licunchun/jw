import Data.DataBase;


public class TestBench {
    String studentTable = "students";
    String teacherTable = "teachers";
    String manageTable = "managers";
    String sql = "";
    static DataBase dataBase = new DataBase();

    private boolean addStudents100() {
        String [] gradePool = {"大一", "大二", "大三", "大四"};
        String availableAccount = "";
        for (int i = 0; i < 100; i++) {
            availableAccount = dataBase.availableAccountOfStudent(gradePool[i%4]);
            dataBase.addStudent("测试", availableAccount, "test", gradePool[i%4], "男", "少年班学院");
        }
        return true;
    }
    private boolean addTeachers100() {
        String [] ChinesePool = {"零", "一", "二", "三", "四", "五", "六", "七", "八", "九"};
        String availableAccount = "";
        for (int i = 0; i < 10; i++) {
            availableAccount = dataBase.availableAccountOfTeacher();
            dataBase.addTeacher(ChinesePool[i], availableAccount, "test");
        }
        return true;
    }

}
