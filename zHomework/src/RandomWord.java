import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

public class RandomWord {
    public static void main(String[] args) {
        if (args.length == 0) {
            int cnt = 0;
            String[] str = new String[8];
            while (!StdIn.isEmpty()) {
                str[cnt] = StdIn.readString();
                cnt++;
            }
            StdOut.println(str[StdRandom.uniform(cnt)]);
        }
        else {
            StdOut.println(args[StdRandom.uniform(args.length)]);
        }

    }
}
