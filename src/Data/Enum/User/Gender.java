package Data.Enum.User;

public enum Gender {
    Male("男",true),Female("女",false);

    private final String name;
    private final boolean sign;

    Gender(String name,boolean sign){
        this.name=name;
        this.sign=sign;
    }
    public String toString(){	//生成中文字符串
        return this.name;
    }
    public boolean getSign(){
        return this.sign;
    }
}
