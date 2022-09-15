import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

import java.util.Arrays;

public class BruteCollinearPoints {
    private int num;
    private Node tail;
    private Node head;

    private class Node {
        private LineSegment item;
        private Node next;

        public Node(LineSegment l) {
            item = l;
            next = null;
        }
    }

    public BruteCollinearPoints(Point[] points) {
        if (points == null)
            throw new IllegalArgumentException();
        num = 0;
        int sum = points.length;
        Point[] temp = new Point[sum];
        for (int p = 0; p < sum; p++) {
            if (points[p] == null)
                throw new IllegalArgumentException();
            for (int q = p + 1; q < sum; q++) {
                if (points[q] == null)
                    throw new IllegalArgumentException();
                if (points[p].compareTo(points[q]) == 0)
                    throw new IllegalArgumentException();
            }
            temp[p] = points[p];
        }
        Arrays.sort(temp);
        for (int i = 0; i < sum - 3; i++) {
            for (int j = i + 1; j < sum - 2; j++) {
                for (int k = j + 1; k < sum - 1; k++) {
                    for (int l = k + 1; l < sum; l++) {
                        if (temp[i].slopeTo(temp[j]) == temp[j].slopeTo(temp[k]) && temp[j].slopeTo(temp[k]) == temp[k].slopeTo(temp[l])) {
                            num++;
                            if (tail == null) {
                                tail = new Node(new LineSegment(temp[i], temp[l]));
                                head = tail;
                            } else {
                                Node temp1 = new Node(new LineSegment(temp[i], temp[l]));
                                tail.next = temp1;
                                tail = temp1;
                            }

                        }
                    }
                }
            }
        }

    }

    public int numberOfSegments() {
        return num;
    }
    public LineSegment[] segments() {
        LineSegment[] result = new LineSegment[num];
        Node cur = head;
        for (int i = 0; i < num; i++) {
            result[i] = cur.item;
            cur = cur.next;
        }
        return result;
    }
    public static void main(String[] args) {
        In in = new In(args[0]);
        int n = in.readInt();
        Point[] points = new Point[n];
        for (int i = 0; i < n; i++) {
            int x = in.readInt();
            int y = in.readInt();
            points[i] = new Point(x, y);
        }
        StdDraw.enableDoubleBuffering();
        StdDraw.setXscale(0, 32768);
        StdDraw.setYscale(0, 32768);
        for (Point p : points) {
            p.draw();
        }
        StdDraw.show();
        BruteCollinearPoints collinear = new BruteCollinearPoints(points);
        for (LineSegment segment : collinear.segments()) {
            StdOut.println(segment);
            segment.draw();;
        }
        StdDraw.show();
    }
}
