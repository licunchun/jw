



+storeData(UserType userType, String name, String ID, String password, Grade grade, School school, Gender gender)

存储学生数据

+storeData(UserType userType, String name, String ID, String password, School school, Gender gender)

存储教师数据
+storeData(UserType userType, String name, String ID, String password)

存储管理员数据

---

String getAvailableID(UserType userType);

参数：UserType

返回：String

描述：传递用户类型，返回当前该类型可用ID

---

public static List<String> getAllID()


