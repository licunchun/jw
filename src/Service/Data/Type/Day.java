package Service.Data.Type;

public class Day {
    private final int whichDay;
    private final int[] sections;

    public Day(int whichDay, int[] sections) {
        this.whichDay = whichDay;
        this.sections = sections;
    }

    public int getWhichDay() {
        return whichDay;
    }

    public int[] getSections() {
        return sections;
    }
}
