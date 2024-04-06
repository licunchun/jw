package GUI.Data.Enum.Classes;

public enum Language {
    Chinese("中文"),
    English("英文"),
    Double("双语"),
    French("法语"),
    EnglishEdu("外语类英语"),
    GermanEdu("外语类德语"),
    FrenchEdu("外语类法语"),
    Japanese("外语类日语");

    private final String name;

    Language(String name) {
        this.name = name;
    }

    public String toString() {
        return this.name;
    }
}
