import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

public class Permutation {
    public static void main(String[] args) {
        RandomizedQueue<String> rq = new RandomizedQueue<String>();
        int num = Integer.parseInt(args[0]);

        String item;
        while (!StdIn.isEmpty()) {
            item = StdIn.readString();
            rq.enqueue(item);
        }
        for (int i = 0; i < num; i++)
            StdOut.println(StdRandom.uniform())
    }
}
