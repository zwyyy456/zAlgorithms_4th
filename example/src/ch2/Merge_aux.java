package ch2;

public class Merge_aux {
    //private static Comparable[] aux;
    public static void merge(Comparable[] a, Comparable[] aux,  int lo, int mid, int hi) {

        for (int k = lo; k <= hi; k++) {
            aux[k] = a[k];
        }
        int i = lo, j = mid + 1;
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
        Comparable[] aux = new Comparable[a.length];
        sort(a, aux, 0, a.length - 1);
    }
    public static void sort(Comparable[] a, Comparable[] aux,  int lo, int hi) {
        if (lo >= hi)
            return;
        int mid = (hi - lo) / 2;
        sort(a, aux, lo, mid);
        sort(a, aux, mid + 1, hi);
        merge(a, aux, lo, mid, hi);
    }
    private static boolean less(Comparable v, Comparable w) {
        return v.compareTo(w) < 0;
    }
}

