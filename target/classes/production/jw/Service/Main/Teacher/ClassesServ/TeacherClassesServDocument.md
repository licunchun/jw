# Teacher Classes Serv Document

简介：服务于学生课程信息的类

## +getTeacherClassesSet(String ID)

参数：

    ID(String):搜索用的ID

返回：

    classesSet(ClassesSet):搜索到的对应的所选的课程集合

说明：

    搜索教师所选的课程集合，如果ID不存在则返回null

## +getTeacherCourseCodeSet(String ID)

参数：

    ID(String):搜索用的ID

返回：

    courseCodeSet(CourseCodeSet):搜索到的对应的所选的课程集合

说明：

    搜索教师所教的课程集合，如果ID不存在则返回null