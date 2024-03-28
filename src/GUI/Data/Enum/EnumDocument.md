# Enum Document
简介：枚举类的说明文档
注：类里面有注释/中文，就不写进一步的文档了
## Classes
简介：跟课堂有关的枚举类

    Campus:校区
    ClassType:理论/实验课
    CourseTime:时间
    CourseType:课程种类
    Education:本科/研究生
    ExamMode:考试方式
    Full:课堂是否是满的
    Language:教学语言
    Week:星期一 ~ 星期日
## Error
简介：各种报错信息，或者说是结果信息

进一步详细文档见/GUI/Enum/Error/ErrorDocument.md

    Login:登录/注册界面的报错
    UserServ:和账户基本信息的报错
## ObservableList
简介：给GUI内部使用的ObservableList，请勿删除，不熟悉性质请勿使用，和List不同

    UserObservableList:用户基本信息的OList，他是枚举类的List所以放在这里
## User
简介：各种用户的基本信息的枚举类

    Gender:性别
    Grade:年级
    StudentSchool:学生的院校枚举类（注：学生给到中间层的是School，这个只是GUI内部使用）
    UserType:账号类型
## School
简介：全部院校，字面意思