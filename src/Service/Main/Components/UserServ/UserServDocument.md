# UserServ Document

简介：服务于用户信息的类

注意！！所有Edit开头的都是覆盖，而ChangePassword是要检验原密码的

## +editName(String ID，String name)

参数:

    ID(String):用户的ID
    name(String):覆盖的姓名

返回:

    editError(EditError):（覆盖性）用户信息编译错误
    详情请见/Data/Enum/Error/UserServ/UserServError.md

描述：

    用户姓名的覆盖器,需要检查用户名是否合法并返回结果
    如果ID=null请报错
    Throw Exeption要包含函数名，函数位置，报错原因

## +editPassword(String ID，String password)

参数:

    ID(String):用户的ID
    password(String):覆盖的密码

返回:

    editError(EditError):（覆盖性）用户信息编译错误
    详情请见/Data/Enum/Error/UserServ/UserServError.md

描述：

    注意！！这里是Admin强制覆盖！
    用户密码的覆盖器,需要检查用户名是否合法并返回结果
    如果ID=null请报错
    Throw Exeption要包含函数名，函数位置，报错原因

## +editMoney(String ID，double money)

参数:

    ID(String):用户的ID
    money(double):覆盖的money

返回:

    editError(EditError):（覆盖性）用户信息编译错误
    详情请见/Data/Enum/Error/UserServ/UserServError.md

描述：

    用户money的覆盖器,记得检查用户类型，
    Throw Exeption
    如果用户类型没有money请报错，如果ID=null也请报错
    Throw Exeption要包含函数名，函数位置，报错原因

## +editAddMoney(String ID，double addMoney)

参数:

    ID(String):用户的ID
    addMoney(double):增加的money

返回:

    editError(EditError):（覆盖性）用户信息编译错误
    详情请见/Data/Enum/Error/UserServ/UserServError.md

描述：

    用户money的增加器,记得检查用户类型，
    Throw Exeption
    如果用户类型没有money请报错，如果ID=null也请报错
    Throw Exeption要包含函数名，函数位置，报错原因

## +editGrade(String ID，Grade grade)

参数:

    ID(String):用户的ID
    Grade(grade):覆盖的年级

返回:

    editError(EditError):（覆盖性）用户信息编译错误
    详情请见/Data/Enum/Error/UserServ/UserServError.md

描述：

    用户年级的覆盖器,记得检查用户类型，
    Throw Exeption
    如果用户类型没有年级请报错，如果ID=null也请报错
    Throw Exeption要包含函数名，函数位置，报错原因

## +editSchool(String ID，School school)

参数:

    ID(String):用户的ID
    School(school):覆盖的院校

返回:

    editError(EditError):（覆盖性）用户信息编译错误
    详情请见/Data/Enum/Error/UserServ/UserServError.md

描述：

    注意！！学生和老师存储用的都是School，所以在这里是一样的，我会在学生选择完之后转为School再输入
    用户院校的覆盖器,记得检查用户类型，
    Throw Exeption
    如果用户类型没有院校请报错，如果ID=null也请报错
    Throw Exeption要包含函数名，函数位置，报错原因

## +changePassword(String ID,String originPassword,String newPassword,String newConfirmPassword)

参数：

    ID(String):用户的ID
    originPassword(String):原来的密码
    newPassword(String):新的密码
    newConfirmPassword(String):新的密码确认

返回:

    changePasswordError(ChangePasswordError):修改（自身）密码错误
    详情请见/Data/Enum/Error/UserServ/UserServError.md

描述：

    用户修改自身密码的函数,返回详细信息
    (为什么这里需要返回详细信息？因为输入的密码是不可视的。)
    如果ID=null请报错
    Throw Exeption要包含函数名，函数位置，报错原因

## +(Getter)(String ID)

参数：

    ID(String):用户的ID

返回：

    对应的数据类型

描述：

    用户基本信息的Getter，有哪些见UserServ
    如果该ID没有对应数据类型或者ID=null请报错，Throw Exeption同上
    如果未查询到该ID请返回null

## +findStudent(String name)

参数：

    name(String):学生的名字，不参与搜索可能为“”或者null

返回：

    studentIDSet(IDSet):搜索到的学生的ID集合

描述：

    根据名字搜索学生，重名一起返回
    注意：没找到返回空集。不要返回null！不要返回null！不要返回null！

## +findTeacher(String name)

参数：

    name(String):老师的名字，不参与搜索可能为“”或者null

返回：

    teacherIDSet(IDSet):搜索到的老师的ID集合

描述：

    根据名字搜索老师，重名一起返回
    注意：没找到返回空集。不要返回null！不要返回null！不要返回null！

## +findAdmin(String name)

参数：

    name(String):管理员的名字，不参与搜索可能为“”或者null

返回：

    adminIDSet(IDSet):搜索到的老师的ID集合

描述：

    根据名字搜索管理员，重名一起返回
    注意：没找到返回空集。不要返回null！不要返回null！不要返回null！

## +findUser(UserType userType,String ID,String Name)

参数：

    userType(UserType):用户的类型，不参与搜索可能为None或者null
    ID(String):用户的ID，不参与搜索可能为“”或者null
    name(String):用户的名字，不参与搜索可能为“”或者null

返回：

    IDSet(IDSet):搜索到的用户的ID集合

描述：

    搜索用户，重名一起返回
    注意：没找到返回空集。不要返回null！不要返回null！不要返回null！

## +isIDExist(String ID)

参数：

    ID(String)：用户的ID

返回：

    result(boolean):结果（用户是否存在）

描述：

    查询对应ID是否存在