import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

import java.util.Arrays;

public class FastCollinearPoints {
    private int num;
    private Node tail;
    private Node head;

    private class Node {
        private LineSegment item;
        private FastCollinearPoints.Node next;

        public Node(LineSegment l) {
            item = l;
            next = null;
        }
    }

    public FastCollinearPoints(Point[] points) {
        if (points == null)
            throw new IllegalArgumentException();
        num = 0;
        int sum = points.length;
        Point[] temp = new Point[sum];
        Point[] sortTemp = new Point[sum];
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
            sortTemp[p] = temp[p];
        }
        Arrays.sort(temp);
        Arrays.sort(sortTemp);
        for (int q = 0; q < sum - 3; q++) {
            findCollinearPoints(sortTemp, q, sum);
        }
    }
//重复问题，当q0,q1,q2,q3,q4均在同一线段时；在这个循环中，q1,q2,q3,q4也会被重新计算
    private void findCollinearPoints(Point[] points, int q, int sum) {
        Point[] findTemp = new Point[sum - q - 1];
        for (int i = 0; i < sum - q - 1; i++)
            findTemp[i] = points[q + i + 1];
        Arrays.sort(findTemp, points[q].slopeOrder());
        double[] slopeTemp = new double[sum - q - 1];
        for (int i = 0; i < sum - q - 1; i++)
            slopeTemp[i] = points[q].slopeTo(findTemp[i]);
        for (int i = 0; i < sum - q - 3; ) {
            int zi = i + 1; // zi is the max index which findTemp[zi] = findTemp[i]
            while (zi < sum - q - 1 && slopeTemp[i] == slopeTemp[zi]) {
                zi++;
            }
            if (zi - i > 2) {
                num++;
                Point result = new Point(0, 0);
                result = findTemp[zi - 1];
                if (tail == null) {
                    tail = new Node(new LineSegment(points[q], result));
                    head = tail;
                } else {
                    Node temp1 = new Node(new LineSegment(points[q], result));
                    tail.next = temp1;
                    tail = temp1;
                }
            }
            i = zi;
        }
    }

    public int numberOfSegments() {
        return num;
    }

    public LineSegment[] segments() {
        LineSegment[] lineResult = new LineSegment[num];
        Node cur = head;
        for (int i = 0; i < num; i++) {
            lineResult[i] = cur.item;
            cur = cur.next;
        }
        return lineResult;
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
            segment.draw();
            ;
        }
        StdDraw.show();
    }
}