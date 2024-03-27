package GUI.Data.Enum.User;

public enum Grade {
    Grade1("大一",1),
    Grade2("大二",2),
    Grade3("大三",3),
    Grade4("大四",4);

    private final String name;
    private final int index;
    Grade(String name,int index){
        this.name=name;
        this.index=index;
    }

    @Override
    public String toString(){
        return this.name;
    }
}
