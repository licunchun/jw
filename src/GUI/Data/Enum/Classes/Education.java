package GUI.Data.Enum.Classes;

public enum Education {
    Preparatory("预科"),
    Undergraduate("本科"),
    Graduate("研究生");

    private final String name;

    Education(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return this.name;
    }

    public static Education fromString(String s){
        for (int i = 0; i < values().length; i++) {
            String s_t = values()[i].toString();
            if(s.compareTo(s_t)==0)
                return values()[i];
        }
        throw new IllegalArgumentException("Invalid input: " + s);
    }
}
