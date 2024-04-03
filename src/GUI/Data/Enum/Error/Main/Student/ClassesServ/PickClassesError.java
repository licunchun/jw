package GUI.Data.Enum.Error.Main.Student.ClassesServ;

public enum PickClassesError {
    IDNotFind,//没有找到对应的ID
    ClassesCodeNotFind,//没有找到对应的课程代码
    ClassesIsFull,//课程已满
    ClassesISChosen,//（同类）课程已被该学生选择过
    Success//选课成功
}
