package Data.Type;

public class Point {
    public String code;
    public String account;
    public String point;

    public Point(String[] info) {
        code = info[0];
        account = info[1];
        point = info[2];
    }
}
