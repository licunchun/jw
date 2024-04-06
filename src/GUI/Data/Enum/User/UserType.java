package GUI.Data.Enum.User;

public enum UserType {
    None(""), Student("学生"), Teacher("教师"), Admin("管理员");

    private final String name;

    UserType(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return this.name;
    }
}
