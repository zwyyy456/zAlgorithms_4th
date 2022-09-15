package ch2;

public class Merge {
    private static Comparable[] aux;
    public static void merge(Comparable[] a, int lo, int mid, int hi) {
        int i = lo, j = mid + 1;
        for (int k = lo; k <= hi; k++) {
            aux[k] = a[k];
        }
        for (int k = lo; k <= hi; k++) {
            if (i > mid)
                a[k++] = aux[j++];
            else if (j > hi)
                a[k++] = aux[i++];
            else if (less(aux[j], aux[i]))
                a[k++] = a[j++];
            else
                a[k++] = a[i++];
        }
    }
    public static void sort(Comparable[] a) {
        sort(a, 0, a.length - 1);
    }
    public static void sort(Comparable[] a, int lo, int hi) {
        if (lo >= hi)
            return;
        int mid = (hi - lo) / 2;
        sort(a, lo, mid);
        sort(a, mid + 1, hi);
        merge(a, lo, mid, hi);
    }
}
