# User Service Error

简介：关于UserServ的报错类型

## EditError

简介：关于Edit报错的类

    Success:编译成功
    IDNotFound:未找到ID
    Invalid:数据不合法
    {
    如名字检查，密码检查；
    如果输入为枚举类可以不设检查，
    这里不讨论为什么不合法，懒得再开一堆错误类然后每个写tips了
    }

## ChangePasswordError

简介：关于用户更改自身密码报错的类

    Success:修改成功
    IDNotFound:未找到ID
    EmptyInput:originPassword，newPassword，newConfirmPassword有任何一个为空都返回这个
    WrongOriginPassword：原始密码错误
    InvalidChar:newPassword不合法字符
    OverLength:newPassword过长
    NotMatch:两次输入的密码不匹配

    