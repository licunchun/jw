package GUI.Data.Enum.Classes;

public enum ClassType {
    Theory("理论课"),
    Experiment("实验课"),
    Practice("实践课"),
    TheoryExperiment("理论实验课"),
    TheoryPractice("理论实践课");

    private final String name;

    ClassType(String name) {
        this.name = name;
    }

    public static ClassType fromString(String s) {
        for (int i = 0; i < values().length; i++) {
            String s_t = values()[i].toString();
            if (s.compareTo(s_t) == 0)
                return values()[i];
        }
        throw new IllegalArgumentException("Invalid input: " + s);
    }

    public String toString() {
        return this.name;
    }
}
