import edu.princeton.cs.algs4.StdOut;

public class zDq<Item> {
    private Node<Item> first;
    private Node<Item> last;
    private int n; // number of the elements on the deque

    private static class Node<Item> {
        private Item item;
        private Node<Item> next;
        private Node<Item> prev;
    }
    public static void main(String[] args) {
        Node zNd = new Node();
        Node A = new Node();
        Node B = new Node();
        zNd.item = 1;
        A.item = 2;
        zNd.next = A;
        A = B;
        StdOut.println(A.item);
    }
}