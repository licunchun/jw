# ClassesServ Document
简介：服务于通用课程信息的类

## +searchClasses(Classes classes)
参数：

    classes(Classes):检索用的课程标准
返回：

    classesSet(ClassesSet):所有满足条件的课程集合
说明：

    枚举类只能选一个，
    数据库中检索所有满足条件且时间包含于classes.CourseTimeSet的Classes
    如果该项没有被选择则classes对应的值则返回空的集合类

## +toStringTime(CourseTimeSet courseTimeSet)
参数：

    courseTimeSet(CourseTimeSet):时间类
返回：
    
    timeString(String):字符串形式的时间
说明：

    将时间集合转换为字符串形式的时间