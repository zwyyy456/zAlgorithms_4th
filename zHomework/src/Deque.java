import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {
    private Node<Item> first;
    private Node<Item> last;
    private int n; // number of the elements on the deque

    private static class Node<Item> {
        private Item item;
        private Node<Item> next;
        private Node<Item> prev;
    }

    public Deque() {
        first = null;
        last = null;
        n = 0;
    }

    public boolean isEmpty() {
        return n == 0;
    }

    public int size() {
        return n;
    }

    public void addFirst(Item item) {
        if (item == null)
            throw new IllegalArgumentException("argument can't be null");
        Node oldfirst = first;
        Node first = new Node();
        first.item = item;
        first.next = oldfirst;
        first.prev = null;
        if (isEmpty())
            last = first;
        else
            oldfirst.prev = first;
        n++;
    }

    public void addLast(Item item) {
        if (item == null)
            throw new IllegalArgumentException("argument can't be null");
        Node oldlast = last;
        Node last = new Node();
        last.item = item;
        last.next = null;
        if (isEmpty())
            first = last;
        else
            oldlast.next = last;
        last.prev = oldlast;
        n++;
    }

    public Item removeFirst() {
        if (isEmpty())
            throw new java.util.NoSuchElementException("deque is empty!");
        Item item = first.item;
        first = first.next;
        n--;
        if (isEmpty())
            last = null;
        else {
            if (n == 1)
                last.prev = null;
            first.prev = null;
        }
        return item;
    }

    public Item removeLast() {
        if (isEmpty())
            throw new java.util.NoSuchElementException("deque is empty!");
        Item item = last.item;
        last = last.prev;
        n--;
        if (isEmpty())
            first = null;
        else {
            if (n == 1)
                first.next = null;
            last.next = null;
        }
        return item;
    }

    public Iterator<Item> iterator()  {
        return new Deque.LinkedIterator(first);
    }

    // an iterator, doesn't implement remove() since it's optional
    private class LinkedIterator implements Iterator<Item> {
        private Deque.Node<Item> current;

        public LinkedIterator(Deque.Node<Item> first) {
            current = first;
        }

        public boolean hasNext()  { return current != null;                     }
        public void remove()      { throw new UnsupportedOperationException();  }

        public Item next() {
            if (!hasNext()) throw new NoSuchElementException();
            Item item = current.item;
            current = current.next;
            return item;
        }
    }
    public static void main(String[] args) {
        Deque<String> myDeque = new Deque<String>();
        while (!StdIn.isEmpty()) {
            String item = StdIn.readString();
            if (!item.equals("-")) {
                myDeque.addFirst(item);
                myDeque.addLast(item);
            }
            else if (!myDeque.isEmpty())
                StdOut.print(myDeque.removeFirst() + " " + myDeque.removeLast() + " ");
        }
        StdOut.println("(" + myDeque.size() + " left on queue)");
        for (String s : myDeque)
            StdOut.print(s + " ");
    }
}
