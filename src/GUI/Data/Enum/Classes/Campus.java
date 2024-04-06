package GUI.Data.Enum.Classes;

public enum Campus {
    West("东区"),
    East("西区"),
    Middle("中区"),
    South("南区"),
    HighTech("高新区"),
    Else("其他");
    private final String name;

    Campus(String name) {
        this.name = name;
    }

    public String toString() {
        return this.name;
    }
}
