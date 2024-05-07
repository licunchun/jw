package GUI.Data.Enum.Classes;

public enum ExamMode {
    PE("体育测试"),
    WrittenOpen("笔试（开卷）"),
    WrittenHalf("笔试（半开卷）"),
    WrittenClose("笔试（闭卷）"),
    Project("大作业"),
    Computer("机考"),
    Oral("口试"),
    Experiment("实验操作");

    private final String name;

    ExamMode(String name) {
        this.name = name;
    }

    public String toString() {
        return this.name;
    }

    public static ExamMode fromString(String s){
        for (int i = 0; i < values().length; i++) {
            String s_t = values()[i].toString();
            if(s.compareTo(s_t)==0)
                return values()[i];
        }
        throw new IllegalArgumentException("Invalid input: " + s);
    }
}
