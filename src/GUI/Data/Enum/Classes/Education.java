package GUI.Data.Enum.Classes;

public enum Education {
    Preparatory("预科"),
    Undergraduate("本科"),
    Graduate("研究生");

    private final String name;

    Education(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return this.name;
    }
}
