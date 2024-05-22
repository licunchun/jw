package GUI.Data.Enum.User;

public enum Gender {
    Male("男", true), Female("女", false);

    private final String name;
    private final boolean sign;

    Gender(String name, boolean sign) {
        this.name = name;
        this.sign = sign;
    }

    public static Gender fromString(String s) {
        for (int i = 0; i < values().length; i++) {
            String s_t = values()[i].toString();
            if (s.compareTo(s_t) == 0)
                return values()[i];
        }
        throw new IllegalArgumentException("Invalid input: " + s);
    }

    public String toString() {    //生成中文字符串
        return this.name;
    }

    public boolean getSign() {
        return this.sign;
    }
}
