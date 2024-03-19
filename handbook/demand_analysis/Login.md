登录/注册功能：

![](file:///D:/Projects/jw/demand_analysis/picures/1.png)

前端创建UserService对象us

us.login()

***现在不确定前端怎么实现从键盘录入的，所以是传形参，还是这个函数内实现键盘录入待商量***

密码错误返回false

密码正确返回true

us.register()

***键盘录入的问题同上***

两次密码不一样返回0

用户已创建/存在返回1

创建成功返回2



us.login()返回true之后

根据us.getPermission()返回值（权限类型）确定要创建的服务对象

StudentService/TeacherService/AdministratorService

***创建完服务对象，删除UserService对象us***





对后端需求：

一个搜索函数

能够根据ID找到对应的数据并以User格式返回

***不知道这个应该是接口还是类继承，只有方法的类？***

一个存储函数

传递User能够将其数据存储起来，返回值标志存储成功/失败




