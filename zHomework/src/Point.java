import edu.princeton.cs.algs4.StdDraw;

import java.util.Comparator;



public class Point implements Comparable<Point> {
    private final int x;
    private final int y;
    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }
    public void draw() {
        StdDraw.point(x, y);
    }
    public void drawTo(Point that) {
        StdDraw.line(this.x, this.y, that.x, that.y);
    }
    public String toString() {
        /* DO NOT MODIFY */
        return "(" + x + ", " + y + ")";
    }
    public int compareTo(Point that) {
        if (this.y < that.y)
            return  -1;
        else if (this.y == that.y) {
            if (this.x < that.x)
                return -1;
            else if (this.x == that.y)
                return 0;
            else
                return 1;
        }
        else
            return 1;
    }
    public double slopeTo(Point that) {
        if (this.x == that.x) {
            if (this.y == that.y)
                return Double.NEGATIVE_INFINITY;
            else
                return Double.POSITIVE_INFINITY;
        }
        else {
            if (that.y == this.y)
                return +0.0;
            else {
                double slope = (double)(that.y - this.y) / (that.x - this.x);
                return slope;
            }
        }
    }
    public Comparator<Point> slopeOrder() {
        return new ComparatorSlope();
    }
    private class ComparatorSlope implements Comparator<Point> {
        public int compare(Point a, Point b) {
            double s1 = slopeTo(a);
            double s2 = slopeTo(b);
            if (s1 < s2)
                return -1;
            else if (s1 > s2)
                return 1;
            else
                return 0;
        }
    }
}
