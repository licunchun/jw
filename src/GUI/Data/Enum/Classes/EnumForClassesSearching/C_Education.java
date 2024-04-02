package GUI.Data.Enum.Classes.EnumForClassesSearching;

import GUI.Data.Enum.Classes.Campus;
import GUI.Data.Enum.Classes.Education;

public enum C_Education {
    None(""),
    Preparatory("预科"),
    Undergraduate("本科"),
    Graduate("研究生");

    private final String name;

    C_Education(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return this.name;
    }
    public Education toEducation(){
        if(this==C_Education.None){
            return null;
        }
        else{
            return Education.valueOf(this.name());
        }
    }
}
