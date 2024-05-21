package GUI.Data.Enum.Classes;

public enum Language {
    Chinese("中文"),
    English("英语"),
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

    public static Language fromString(String s){
        for (int i = 0; i < values().length; i++) {
            String s_t = values()[i].toString();
            if(s.compareTo(s_t)==0)
                return values()[i];
        }
        throw new IllegalArgumentException("Invalid input: " + s);
    }
}
