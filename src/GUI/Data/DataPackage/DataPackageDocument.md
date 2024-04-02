# Data Package Document
简介：GUI和中间用的数据包的文档
## Classes
简介：班级相关的数据

    CourseTimeSet:
    上课时间，这个只是CourseTime枚举类的一个集合，不考虑中间某周调课
    
    IDSet:用户ID的集合，这样存储快速而且信息少
    
    Classes:班级的类
    这个里面你也别放方法(要用的构造方法和Getter&Setter除外)捏

    ClassesForTable:给GUI的TableView提供服务的类，不熟悉性质请勿使用
    
    ClassesSet:
    Classes的set（别问我为啥不用Set，因为前端懒得搞逻辑，直接打包给我）
    对了里面不要放方法(要用的构造方法和Getter&Setter除外)，
    功能实现用单独的Serv工具类，这个我可能要放一些自己的格式处理方法
    