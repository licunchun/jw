package GUI.Data.Enum.Classes;

public enum Campus {
    West("东区"),
    East("西区"),
    Middle("中区"),
    South("南区"),
    HighTech("高新区"),
    Else("其他研院");
    private final String name;

    Campus(String name) {
        this.name = name;
    }

    public String toString() {
        return this.name;
    }

    public static Campus fromString(String s){
        for (int i = 0; i < values().length; i++) {
            String s_t = values()[i].toString();
            if(s.compareTo(s_t)==0)
                return values()[i];
        }
        throw new IllegalArgumentException("Invalid input: " + s);
    }
}
