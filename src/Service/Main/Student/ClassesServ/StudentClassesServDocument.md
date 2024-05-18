# StudentClassesServ Document

简介：服务于学生课程信息的类

## +getStudentClassesSet(String ID)

参数：

    ID(String):搜索用的ID

返回：

    classesSet(ClassesSet):搜索到的对应的所选的课程集合

说明：

    搜索学生所选的课程集合，如果ID不存在则返回null

## +getStudentCourseCodeSet(String ID)

参数：

    ID(String):搜索用的ID

返回：

    courseCodeSet(CourseCodeSet):搜索到的对应的所选的课程集合

说明：

    搜索学生所选的课程集合，如果ID不存在则返回null

## +getStudentTotalCredits(String ID)

参数：

    ID(String):学生的ID

返回：

    Credits(double)

说明：

    查询学生总学分，如果查询失败则返回-1

## +getStudentReceivedCredits(String ID)

参数：

    ID(String):学生的ID

返回：

    Credits(double)

说明：

    查询学生已获得学分（已打分的学分），如果查询失败则返回-1

## +getStudentFailedCredits(String ID)

参数：

    ID(String):学生的ID

返回：

    Credits(double)

说明：

    查询学生挂科，如果查询失败则返回-1

## +getStudentAverageGrade(String ID)

参数：

    ID(String):学生的ID

返回：

    Grade(int)

说明：

    查询学生平均的成绩，如果查询失败则返回-1

## +getStudentWeightedAverageGrade(String ID)

参数：

    ID(String):学生的ID

返回：

    Grade(int)

说明：

    查询学生加权平均的成绩，如果查询失败则返回-1

## +getStudentGPA(String ID)

参数：

    ID(String):学生的ID

返回：

    GPA(double)

说明：

    查询学生的平均绩点，如果查询失败则返回-1

## +pickClasses(String studentID,String classesCode)

参数：

    studentID(String):选课学生的ID
    classesCode(String):所选课程的课程代码

返回：

    pickClassesError(PickClassesError):选课的错误类型

说明：

    选课的方法，注意了这个不要返回null

## +dropClasses(String studentID,String classesCode)

参数：

    studentID(String):学生的ID
    classesCode(String):课程的课程代码

返回：

    dropClassesError(DropClassesError):退课的错误类型

说明：

    退课的方法，注意了这个不要返回null

## +isPicked(String studentID,String classesCode)

参数：

    studentID(String):学生的ID
    classesCode(String):课程代码

返回：

    isChosen(Boolean):是否选择了该课程

说明：

    查询课程是否被选择，如果查询失败（ID不存在或者Code不存在等等）则返回null