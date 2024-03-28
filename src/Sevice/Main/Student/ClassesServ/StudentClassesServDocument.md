# StudentClassesServ Document
简介：服务于学生课程信息的类
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