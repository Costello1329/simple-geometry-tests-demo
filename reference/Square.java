package reference;

import java.util.ArrayList;
import java.util.List;

import reference.Point;

public class Square {
    public Square (final Point first, final Point second) {
        this.first = first;
        this.second = second;
    }

    public Point center () { return first.add(second).scale(.5); }
    public double perimeter () { return 4 * first.subtract(second).length(); }
    public double area () { return Math.pow(first.subtract(second).length(), 2.); }

    public void translate (final double dx, final double dy) {
        first = first.add(new Point(dx, dy));
        second = second.add(new Point(dx, dy));
    }

    public void rotate (final double angle) {
        final Point center = center();
        first = first.subtract(center).rotate(angle).add(center);
        second = second.subtract(center).rotate(angle).add(center);
    }

    public void scale (final double scale) {
        final Point center = center();
        first = first.subtract(center).scale(scale).add(center);
        second =second.subtract(center).scale(scale).add(center);
    }

    public List<Point> vertices () {
        final Point center = center();
        return new ArrayList<>() {{
            add(first.subtract(center).rotate(Math.PI / 2.).add(center));
            add(second.subtract(center).rotate(- Math.PI / 2.).add(center));
            add(second.subtract(center).rotate(Math.PI / 2.).add(center));
            add(first.subtract(center).rotate(- Math.PI / 2.).add(center));
        }};
    }

    private Point first;
    private Point second;
}
