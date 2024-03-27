package GUI.Data.Enum.Classes;

public enum CourseType {
    None(""),
    General("通识"),
    Else("其他"),
    BasicPE("基础体育"),
    PE("体育"),
    MoocGeneral("慕课通识"),
    Preparatory("预科"),
    English("英语"),
    EnglishExpansion("英语拓展"),
    Plan("计划"),
    ScienceAndSociety("科学与社会"),
    History("四史"),
    Basic("基础"),
    Profession("专业"),
    Politics("政治及其他"),
    EnglishCourse("英语课"),
    PublicCourse("公选课");

    private final String name;

    CourseType(String name) {
        this.name = name;
    }

    public String toString() {
        return this.name;
    }
}
