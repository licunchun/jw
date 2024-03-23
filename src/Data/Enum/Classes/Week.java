package Data.Enum.Classes;

public enum Week {
    Monday("星期一"),
    Tuesday("星期二"),
    Wednesday("星期三"),
    Thursday("星期四"),
    Friday("星期五"),
    Saturday("星期六"),
    Sunday("星期日");

    private final String name;

    Week(String name) {
        this.name = name;
    }

    public String toString(){
        return this.name;
    }
}
