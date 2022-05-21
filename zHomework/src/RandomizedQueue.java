import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueue<Item> implements Iterable<Item> {
    private Item[] randomizedQueue;
    private int n;

    public RandomizedQueue() {
        randomizedQueue = (Item[]) new Object[2];
        n = 0;
    }
    public boolean isEmpty() {
        return n == 0;
    }
    public int size() {
        return n;
    }
    private void resize(int capacity) {
        Item[] temp = (Item[]) new Object[capacity];
        for (int i = 0; i < n; i++) {
            temp[i] = randomizedQueue[i];
        }
        randomizedQueue = temp;
    }

    public void enqueue(Item item) {
        if (item == null)
            throw new IllegalArgumentException();
        if (n == randomizedQueue.length)
            resize(2 * randomizedQueue.length);
        randomizedQueue[n++] = item;
    }

    public Item dequeue() {
        if (isEmpty())
            throw new NoSuchElementException();
        int j = StdRandom.uniform(n);
        Item item = randomizedQueue[j];
        if (j != n - 1)
            randomizedQueue[j] = randomizedQueue[n - 1];
        randomizedQueue[n - 1] = null;
        n--;
        if (n > 0 && n == randomizedQueue.length / 4)
            resize(randomizedQueue.length / 2);
        return item;
    }
    public Item sample() {
        if (isEmpty())
            throw new NoSuchElementException();
        int j = StdRandom.uniform(n);
        return randomizedQueue[j];
    }

    public Iterator<Item> iterator() {
        return new arrayIterator();
    }

    private class arrayIterator implements Iterator<Item> {
        private int i = 0;
        public boolean hasNext() {
            return i < n;
        }
        public void remove()      { throw new UnsupportedOperationException();  }
        public Item next() {
            if(!hasNext())
                throw new NoSuchElementException();
            Item item = randomizedQueue[i++];
            return item;
        }
    }
    public static void main(String[] args) {
        RandomizedQueue<String> randomizedQueue = new RandomizedQueue<String>();
        StdOut.println("size:" + randomizedQueue.size());
        randomizedQueue.enqueue("Hello");
        StdOut.println("size:" + randomizedQueue.size());
        randomizedQueue.enqueue("World");
        StdOut.println("size:" + randomizedQueue.size());
        randomizedQueue.enqueue("!");
        StdOut.println("size:" + randomizedQueue.size());
        StdOut.println(randomizedQueue.sample());
        randomizedQueue.dequeue();
        StdOut.println("size:" + randomizedQueue.size());
        Iterator<String> it = randomizedQueue.iterator();
        while (it.hasNext()) {
            String elt = it.next();
            System.out.print(elt + " ");
        }
    }
}
