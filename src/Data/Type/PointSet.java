package Data.Type;

import java.util.ArrayList;

public class PointSet {
    ArrayList<Point> points;

    public PointSet() {
        points = new ArrayList<>();
    }

    public Point get(int i) {
        return points.get(i);
    }

    public int length(int i) {
        return points.size();
    }
}
