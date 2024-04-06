package GUI.Data.Enum.Classes.EnumForClassesSearching;

import GUI.Data.Enum.Classes.Campus;

public enum C_Campus {
    None(""),
    West("东区"),
    East("西区"),
    Middle("中区"),
    South("南区"),
    HighTech("高新区"),
    Else("其他");
    private final String name;

    C_Campus(String name) {
        this.name = name;
    }

    public String toString() {
        return this.name;
    }

    public Campus toCampus() {
        if (this == C_Campus.None) {
            return null;
        } else {
            return Campus.valueOf(this.name());
        }
    }
}
