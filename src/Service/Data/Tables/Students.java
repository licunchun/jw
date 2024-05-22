package Service.Data.Tables;


import Service.Data.SQLiteJDBC;

public class Students {
    public static final int ID_C = 0;
    public static final int name_C = 1;
    public static final int password_C = 2;
    public static final int grade_C = 3;
    public static final int gender_C = 4;
    public static final int school_C = 5;
    public static final int classes_C = 6;
    public static final int money_C = 7;
    private static final String tableName = "students";
    private static final SQLiteJDBC studentsTable = new SQLiteJDBC(tableName);
    private static final String[] StudentCol = {
            "ID",
            "name",
            "password",
            "grade",
            "gender",
            "school",
            "classes",
            "money"
    };

<<<<<<< HEAD
    public static boolean isIDExist(String ID){
        return studentsTable.isColValueExist("ID",ID);
    }
    public static boolean isNameExist(String name){
        return studentsTable.isColValueExist("name",name);
    }
    public static String[] getInfo(String ID){
        return studentsTable.select(StudentCol,"ID",ID);
    }
    public static void addInfo(String[] info){
        studentsTable.insert(StudentCol,info);
=======
    public static boolean isIDExist(String ID) {
        return studentsTable.isColValueExist("ID", ID);
>>>>>>> a55caa14a831e51f048c088b9e1ebd00f97bc239
    }

    public static boolean isNameExist(String name) {
        return studentsTable.isColValueExist("name", name);
    }

    public static String[] getInfo(String ID) {
        return studentsTable.select(StudentCol, "ID", ID);
    }

    public static void addInfo(String[] info) {
        studentsTable.insert(StudentCol, info);
    }

    public static void setName(String ID, String name) {
        studentsTable.update("name", name, "ID", ID);
    }

    public static void setPassword(String ID, String password) {
        studentsTable.update("password", password, "ID", ID);
    }

    public static void setGrade(String ID, String grade) {
        studentsTable.update("grade", grade, "ID", ID);
    }

    public static void setGender(String ID, String gender) {
        studentsTable.update("gender", gender, "ID", ID);
    }

    public static void setSchool(String ID, String school) {
        studentsTable.update("school", school, "ID", ID);
    }

    public static void setClasses(String ID, String classes) {
        studentsTable.update("classes", classes, "ID", ID);
    }

    public static void courseSelection(String ID, String code) {
        String classes = getInfo(ID)[classes_C];
        if (classes.isEmpty())
            setClasses(ID, code);
        else
            setClasses(ID, classes + "," + code);
    }

    public static void courseWithdrawal(String ID, String code) {
        String classes = getInfo(ID)[classes_C];
        String classesProcess = classes.replaceAll(code + "(,)?", "");
        setClasses(ID, classesProcess);
    }

    public static void setMoney(String ID, double money) {
        studentsTable.update("money", String.valueOf(money), "ID", ID);
    }


    public static String[] getSameNameID(String name) {
        return studentsTable.selectAll("ID", "name", name);
    }
}
