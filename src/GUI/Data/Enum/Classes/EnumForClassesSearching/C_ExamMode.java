package GUI.Data.Enum.Classes.EnumForClassesSearching;

import GUI.Data.Enum.Classes.ExamMode;

public enum C_ExamMode {
    None(""),
    PE("体育测试"),
    WrittenOpen("笔试(开卷)"),
    WrittenHalf("笔试(半开卷)"),
    WrittenClose("笔试(闭卷)"),
    Project("大作业"),
    Computer("机考"),
    Oral("口试"),
    Experiment("实验操作");

    private final String name;

    C_ExamMode(String name) {
        this.name = name;
    }

    public String toString(){
        return this.name;
    }
    public ExamMode toExamMode(){
        if(this==C_ExamMode.None){
            return null;
        }
        else{
            return ExamMode.valueOf(this.name());
        }
    }
}
