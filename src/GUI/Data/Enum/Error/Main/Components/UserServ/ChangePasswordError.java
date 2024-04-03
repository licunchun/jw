package GUI.Data.Enum.Error.Main.Components.UserServ;

public enum ChangePasswordError {
    Success,//修改成功
    IDNotFound,//未找到ID
    EmptyInput,//originPassword，newPassword，newConfirmPassword有任何一个为空都返回这个
    WrongOriginPassword,//原始密码错误
    InvalidChar,//newPassword不合法字符
    OverLength,//newPassword过长
    NotMatch//两次输入的密码不匹配
}
