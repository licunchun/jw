# Data

+ Enum

常用枚举类型：报错返回等



+ （

数据库的一些属性类

PS：通用的可以修改到JavaBean类中

---

# GUI

不懂，待补充

---

# Interface

似乎是没用了

---

# JavaBean

一些公用扩展类，作为数据打包传输/储存

但是数据传输未必一定要以类的形式传输，看需求

传输一般要什么给什么，一个界面的信息内容一次获得

---

# MainPackage



---

# Service

+ GUI给中间层的文档

用于写GUI给Service的需求



set函数禁用，需要修改什么加函数传形参

+ UserService

用于提供用户注册和登录的一些函数



+ StudentService

和学生相关的函数

+ TeacherService

和教师相关的函数

+ AdminService

和管理员相关的函数

---

# Service.Utils

工具类，用于辅助Service

+ DataBase

+ + Service给Database的文档
  
  + 

这个里面希望实现数据库与Service接口的重写/改造，也会有一些对数据库的要求

---

# APP

---

# 后端：

Database:

+ public String key(String account, int type) //传入账户及其类型，返回密码

+ + public String keyOfStudent(String account) 
+ + public String keyOfTeacher(String account) 
+ + public String keyOfManager(String account) //传入账户，返回密码（key也可以用，看哪个合适方便 自取)

+ public boolean changeKey(String account, String key, int type) //传入账户类型密码，返回是否change成功

+ + public boolean changeKeyOfStudent(String account,String key)
+ + public boolean changeKeyOfTeacher(String account,String key)
+ + public boolean changeKeyOfManager(String account,String key)//同上

+ public Student infoOfStudent(String account) //查询Student信息

+ public Student infoOfTeacher(String account) //查询Teacher信息

+ public boolean addStudent(String name, String account, String key, String gender, String major)

+ public boolean addTeacher(String name, String account, String key, String major)

+ public boolean addManager(String name, String account, String key) //加入新学生，老师，管理员

+ public boolean setMoney(String account, int money) //充值

+ public ClassInfoSet check() // 查看总课程

+ public boolean addClassOfStudent(String account, String code) //学生加入新课程

# 中间层

？？

# 数据

+ Students

public String name;

public String account;

public String gender;

public String major;

public String classes;

public String money;

public String key;

+ Teacher

public String name;

public String account;

public String classes;

public String key;

+ manager

?

+ ClassInfo //课程信息

public String code;

public String name;

public int period;

public double credits;

public String time;

public int stdCount;

public int limitCount;

public String courseType;

public String department;

public String campus;

public String examMode;

public String Language;

public String education;

public String classType;

public String teachers;

+ Point //给分,教师查看选课等等

String code;

String course;

String account;

String name;

String point;

# Enum

前端专用（