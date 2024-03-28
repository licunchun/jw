# ClassesServ Document
简介：服务于通用课程信息的类

## +searchClasses(Classes classes)
参数：

    classes(Classes):检索用的课程标准
返回：

    classesSet(ClassesSet):所有满足条件的课程集合
说明：

    枚举类只能选一个，
    CourseTimeSet中检索所有时间包含于classes.CourseTimeSet的Classes
    如果该项没有被选择则classes对应的值为null