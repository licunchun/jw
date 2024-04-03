package GUI.Data.Enum.Classes.EnumForClassesSearching;

import GUI.Data.Enum.Classes.Language;

public enum C_Language {
    None(""),
    Chinese("中文"),
    English("英文"),
    Double("双语"),
    French("法语"),
    EnglishEdu("外语类英语"),
    GermanEdu("外语类德语"),
    FrenchEdu("外语类法语"),
    Japanese("外语类日语");

    private final String name;

    C_Language(String name) {
        this.name = name;
    }

    public String toString(){
        return this.name;
    }
    public Language toLanguage(){
        if(this==C_Language.None){
            return null;
        }
        else{
            return Language.valueOf(this.name());
        }
    }
}
