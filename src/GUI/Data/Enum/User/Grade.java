package GUI.Data.Enum.User;

public enum Grade {
    Grade1("大一", 1),
    Grade2("大二", 2),
    Grade3("大三", 3),
    Grade4("大四", 4);

    private final String name;
    private final int index;

    Grade(String name, int index) {
        this.name = name;
        this.index = index;
    }

    public static Grade fromString(String s) {
        for (int i = 0; i < values().length; i++) {
            String s_t = values()[i].toString();
            if (s.compareTo(s_t) == 0)
                return values()[i];
        }
        throw new IllegalArgumentException("Invalid input: " + s);
    }

    @Override
    public String toString() {
        return this.name;
    }
}
