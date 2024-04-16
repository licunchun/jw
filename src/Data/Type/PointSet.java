package Data.Type;

import java.util.ArrayList;

public class PointSet {
    public ArrayList<Point> points;

    public PointSet() {
        points = new ArrayList<>();
    }
    public Point get(int i) {
        return points.get(i);
    }
    public int length() {
        return points.size();
    }
    public void findAccount(String account) {
        for (int i = 0; i < this.length(); i++) {
            if (!this.get(i).account.equals(account)) points.remove(i--);
        }
    }
    public void findCode(String code) {
        for (int i = 0; i < this.length(); i++) {
            if (!this.get(i).code.equals(code)) points.remove(i--);
        }
    }
}
