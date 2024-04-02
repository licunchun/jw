package GUI.Data.Enum.Classes.EnumForClassesSearching;

import GUI.Data.Enum.Classes.Campus;
import GUI.Data.Enum.Classes.ClassType;

public enum C_ClassType {
    None(""),
    Theory("理论课"),
    Experiment("实验课"),
    Practice("实践课"),
    TheoryExperiment("理论实验课"),
    TheoryPractice("理论实践课");

    private final String name;

    C_ClassType(String name) {
        this.name = name;
    }

    public String toString(){
        return this.name;
    }
    public ClassType toClassType(){
        if(this==C_ClassType.None){
            return null;
        }
        else{
            return ClassType.valueOf(this.name());
        }
    }
}
