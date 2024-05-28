# Interface

## Package

### Data:

#### DataBase:

##### String keyOfStudent(String account);

​ arguments: 学生账号

​ return: 学生账号密码

##### String keyOfTeacher(String account);

​ arguments: 老师账号

​ return: 老师账号密码

##### String keyOfManager(String account);

​ arguments: 管理员账号

​ return: 管理员账号密码

##### void changeKeyOfStudent(String key);

​ arguments: 学生账号

##### void changeKeyOfTeacher(String key);

​ arguments: 老师账号

##### void changeKeyOfManager(String key);

​ arguments: 管理员账号

##### boolean registerStudent(String name, String account, String key);

​ arguments: ...

##### boolean registerTeacher(String name, String account, String key);

​ arguments: ...

##### boolean registerManager(String name, String account, String key);

​ arguments: ...

##### ClassSet check();

​ return: 课程集

##### boolean addClassOfStudent(String account, String code);

​ arguments: 学生账号, 课程代号

​ return: 操作结果

##### boolean setMoney(String account, int money);

​ arguments: 学生账号, 现金

​ return: 操作结果

##### boolean cancelClass(String account, String code);

​ arguments: 学生账号, 课程代号

​ return: 操作结果

##### StudentSet checkStudent(String code);

​ arguments: 课程代号

​ return: 学生集

##### PointSet studentCheckPoints(String account);

​ arguments: 学生账号

​ return: 分数集

##### PointSet teacherCheckPoints(String code);

​ argument: 课程代号

​ return: 分数集

boolean setPoint(String code, String account, int point);

​ argument: 课程代号, 学生账号, 分数

​ return: 操作结果

#### Class:

##### code // 课程代码

##### name

##### period

##### credits

##### time

##### stdCount

##### limitCount

##### courseType

##### department

##### campus

##### examMode

##### Language

##### education

##### classType

##### teachers

##### admin

#### ClassSet:

##### Class index(int n);

​ argument: 角标

​ return: 课程内容

##### int length();

​ return: 课程个数

#### Student:

##### name

##### account

##### key

##### classes

##### money

#### StudentSet:

##### Student index(int n);

​ argument: 角标

​ return: 学生信息

##### int length();

​ return: 学生个数

#### Point:

##### code

##### course

##### point

#### PointSet:

##### Point index(int n);

##### int length();