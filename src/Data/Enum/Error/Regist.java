package Data.Enum.Error;

public enum Regist {
    NameEmpty,//名字为空
    NameOverLength,//名字超长
    NameInvalidChar,//名字含非法字符
    PasswordEmpty,//密码为空
    PasswordOverLength,//密码超长
    PasswordInvalidChar,//密码含非法字符
    PasswordNotMatch,//两次输入的密码不一致
    Pass;//注册通过
}
