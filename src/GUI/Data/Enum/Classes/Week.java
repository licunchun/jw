package GUI.Data.Enum.Classes;

public enum Week {
    Monday(1, "星期一"),
    Tuesday(2, "星期二"),
    Wednesday(3, "星期三"),
    Thursday(4, "星期四"),
    Friday(5, "星期五"),
    Saturday(6, "星期六"),
    Sunday(7, "星期日");

    private final int index;
    private final String name;

    Week(int index, String name) {
        this.index = index;
        this.name = name;
    }//内部使用，外部请勿使用!!!

    public static Week fromString(int n) {
        for (int i = 0; i < values().length; i++) {
            int n_t = values()[i].getIndex();
            if (n == n_t)
                return values()[i];
        }
        throw new IllegalArgumentException("Invalid input: " + n);
    }

    public static int formWeek(Week w) {
        return switch (w) {
            case Monday -> 1;
            case Tuesday -> 2;
            case Wednesday -> 3;
            case Thursday -> 4;
            case Friday -> 5;
            case Saturday -> 6;
            case Sunday -> 7;
        };
    }

    public String toString() {
        return this.name;
    }

    public int getIndex() {
        return index;
    }
}
