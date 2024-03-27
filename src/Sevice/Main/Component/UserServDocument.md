# UserServ Document
简介：服务于用户信息的类

注意！！所有Edit开头的都是覆盖，而ChangePassword是要检验原密码的


## +EditName(String ID，String name)
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
## +EditPassword(String ID，String password)
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
## +EditMoney(String ID，double money)
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
## +EditAddMoney(String ID，double addMoney)
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
## +EditGrade(String ID，Grade grade)
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
## +EditSchool(String ID，School school)
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
## +ChangePassword(String ID,String originPassword,String newPassword,String newConfirmPassword)
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
