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
}
