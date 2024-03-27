# ClassesServ Document
简介：服务于课程信息的类
## +searchClasses(Classes classes)
参数：

    classes(Classes):检索用的课程标准
返回：

    classesSet(ClassesSet):所有满足条件的课程集合
说明：

    枚举类只能选一个，
    CourseTimeSet中检索所有时间包含于classes.CourseTimeSet的Classes
    如果该项没有被选择则classes对应的值为null
## +getStudentClassesSet(String ID)
参数：

    ID(String):搜索用的ID
返回：

    classesSet(ClassesSet):搜索到的对应的所选的课程集合
说明：

    搜索学生所选的课程集合，如果ID不存在则返回null
## +pickClasses(String studentID,String classesCode)
参数：

    studentID(String):选课学生的ID
    classesCode(String):所选课程的课程代码
返回：

    pickClassesError(PickClassesError):选课的错误类型
说明：

    选课的方法，注意了这个不要返回null