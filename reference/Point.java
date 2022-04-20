package reference;

public record Point (double x, double y) {
    public Point add (final Point other) { return new Point(x + other.x, y + other.y); }
    public Point subtract (final Point other) { return new Point(x - other.x, y - other.y); }
    public Point scale (final double scale) { return new Point(x * scale, y * scale); }

    public Point rotate (final double angle) {
        return new Point(
            x * Math.cos(angle) - y * Math.sin(angle),
            x * Math.sin(angle) + y * Math.cos(angle)
        );
    }

    public double length () { return Math.sqrt(Math.pow(x, 2.) + Math.pow(y, 2.)); }
}
