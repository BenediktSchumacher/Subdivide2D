public class Point implements Cloneable {

    private double x;
    private double y;

    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public void setX(double x) { this.x = x; }

    public void setY(double y) {
        this.y = y;
    }

    public Point clone() {
        return new Point(this.x, this.y);
    }

    @Override
    public String toString() {
        return "X: " + x + ", Y: " + y;
    }
}
