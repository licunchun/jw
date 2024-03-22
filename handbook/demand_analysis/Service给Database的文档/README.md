

+Store(UserType userType, String name, String ID, String password, Grade grade, School school, Gender gender)
+Store(UserType userType, String name, String ID, String password, School school, Gender gender)
+Store(UserType userType, String name, String ID, String password)



String GetID(UserType userType);

参数：

    UserType

返回：

>     String描述：
> 传递用户类型，返回当前最新用户的的ID
