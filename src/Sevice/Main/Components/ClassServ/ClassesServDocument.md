# ClassesServ Document

简介：服务于通用课程信息的类

## +getClasses(String classesCode)

参数：

    classesCode(String)：该课程的课程编码

返回：

    classes(Classes):对应课程的类

说明：

    获取对应的Classes类，如果没找到则返回null

## +searchClasses(Classes classes)

参数：

    classes(Classes):检索用的课程标准

返回：

    classesSet(ClassesSet):所有满足条件的课程集合

说明：

    枚举类只能选一个，
    数据库中检索所有满足条件且时间包含于classes.CourseTimeSet的Classes
    如果该项没有被选择则classes对应的值则返回空的集合类

## +getStudentSet(String classesCode)

参数：

    classesCode(String)：该课程的课程编码

返回：

    StudentIDSet(IDSet):该课程学生集合

说明：

    查询课程里有的学生，若课程不存在则返回null，若课程存在但是无学生则返回空的集合

## +getStudentGrade(String classesCode,String ID)

参数：

    classesCode(String)：该课程的课程编码
    ID(String):学生的ID

返回：

    Grade(double)

说明：

    查询学生的成绩，如果查询失败则返回-1

## +setStudentGrade(String classesCode,String ID,double grade)

参数：

    classesCode(String)：该课程的课程编码
    ID(String):学生的ID
    grade(double):成绩，百分制,如果非法直接报错

返回：

    isSuccessful(boolean):是否修改成功

说明：

    修改学生的成绩，如果修改失败则返回false

## +toStringTime(CourseTimeSet courseTimeSet)

参数：

    courseTimeSet(CourseTimeSet):时间类
返回：

    timeString(String):字符串形式的时间
说明：

    将时间集合转换为字符串形式的时间

## +deleteClasses(String classesCode)

参数：

    classesCode(String)：该课程的课程编码

返回：

    deleteClassesError(DeleteClassesError):删除课程的报错
说明：

    删除课程的方法，不要返回null！！！！

## +newClasses(String code,String name,String period,String credits,CourseTimeSet time,String maxCount,ClassType classType,CourseType courseType,School school,Campus campus,ExamMode examMode,Language language,Education education,String teacher)

参数：

    code(String):课程代码
    name(String):课程名称
    period(String):课程学时
    credits(String):课程学分
    time(CourseTimeSet):课程时间
    maxCount(String):课程容量
    classType(ClassType):课堂类型
    courseType(CourseType):课程种类
    school(School):课程院系
    campus(Campus):课程校区
    examMode(ExamMode):考核方式
    language(Language):教学语言
    education(Education):教育阶段
    teacher(String):教师

返回：

    newClassesError(NewClassesError):新建课程的报错

说明：

    为了GUI界面体验，请按照参数顺序逐个验证错误