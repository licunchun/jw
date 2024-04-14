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

    public String toString() {
        return this.name;
    }
    public int getIndex() {
        return index;
    }
}
