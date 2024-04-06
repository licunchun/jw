package Data.Type;

public class Point {
    String code;
    String account;
    String point;

    public Point(String[] info) {
        code = info[0];
        account = info[1];
        point = info[2];
    }
}
