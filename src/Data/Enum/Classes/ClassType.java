package Data.Enum.Classes;

public enum ClassType {
    None(""),
    Theory("理论课"),
    Experiment("实验课"),
    Practice("实践课"),
    TheoryExperiment("理论实验课"),
    TheoryPractice("理论实践课");

    private final String name;

    ClassType(String name) {
        this.name = name;
    }

    public String toString(){
        return this.name;
    }
}
