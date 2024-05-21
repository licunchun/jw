package Service.Data.Tables;


import Service.Data.SQLiteJDBC;

public class Teachers {
    public static final int ID_C = 0;
    public static final int name_C = 1;
    public static final int password_C = 2;
    public static final int classes_C = 3;
    private static final String tableName = "teachers";
    private static final SQLiteJDBC teachersTable = new SQLiteJDBC(tableName);
    private static final String[] TeacherCol = {"ID", "name", "password", "classes"};

    public static boolean isIDExist(String ID) {
        return teachersTable.isColValueExist("ID", ID);
    }

    public static boolean isNameExist(String name) {
        return teachersTable.isColValueExist("name", name);
    }

    public static String getID(String name) {
        return teachersTable.select("ID", "name", name);
    }

    public static String[] getInfo(String ID) {
        return teachersTable.select(TeacherCol, "ID", ID);
    }

    public static void addInfo(String[] info) {
        teachersTable.insert(TeacherCol, info);
    }

    public static void setName(String ID, String name) {
        teachersTable.update("name", name, "ID", ID);
    }

    public static void setPassword(String ID, String password) {
        teachersTable.update("password", password, "ID", ID);
    }

    public static void setClasses(String ID, String classes) {
        teachersTable.update("classes", classes, "ID", ID);
    }

    public static void addClasses(String ID, String code) {
        String classes = getInfo(ID)[classes_C];
        if (classes.isEmpty())
            setClasses(ID, code);
        else
            setClasses(ID, classes + "," + code);
    }

    public static void deleteClasses(String ID, String code) {
        String classes = getInfo(ID)[classes_C];
        String classesProcess = classes.replaceAll(code + "(,)?", "");
        setClasses(ID, classesProcess);
    }


    public static String[] getSameNameID(String name) {
        return teachersTable.selectAll("ID", "name", name);
    }
}
