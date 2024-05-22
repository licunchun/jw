package GUI.Data.Enum.Classes;

public enum Full {
    Full("已满"),
    NotFull("未满");

    private final String name;

    Full(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return this.name;
    }

    public static Full fromString(String s){
        for (int i = 0; i < values().length; i++) {
            String s_t = values()[i].toString();
            if(s.compareTo(s_t)==0)
                return values()[i];
        }
        throw new IllegalArgumentException("Invalid input: " + s);
    }
}
