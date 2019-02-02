
public class Merge {
	public static void merge(Comparable[] src, Comparable[] dst, int lo, int mid, int hi) {
		assert isSorted(src, lo, mid);
		assert isSorted(src, mid + 1, hi);
		
		int i = lo, j = mid + 1;

		for (int k = lo; k <= hi; k++) {
			if (i > mid)  					dst[k] = src[j++];			
			else if (j > hi)  				dst[k] = src[i++];			
			else if (less(src[j], src[i]))	dst[k] = src[j++];			
			else  							dst[k] = src[i++];			
		}
	}
	

    private static void mergeBU(Comparable[] a, Comparable[] aux, int lo, int mid, int hi) {

    	for (int k = lo; k <= hi; k++) {
            aux[k] = a[k]; 
        }
    	
        int i = lo, j = mid+1;
        
        for (int k = lo; k <= hi; k++) {
            if      (i > mid)              a[k] = aux[j++];  // this copying is unneccessary
            else if (j > hi)               a[k] = aux[i++];
            else if (less(aux[j], aux[i])) a[k] = aux[j++];
            else                           a[k] = aux[i++];
        }

    }

	private static void sort(Comparable[] src, Comparable[] dst, int lo, int hi) {
		if (hi <= lo) return;

        int mid = lo + (hi - lo) / 2;
        sort(dst, src, lo, mid);
        sort(dst, src, mid+1, hi);

        if (!less(src[mid+1], src[mid])) {
            System.arraycopy(src, lo, dst, lo, hi - lo + 1);
            return;
        }

        merge(src, dst, lo, mid, hi);
	}

	public static void sort(Comparable[] a) {
		Comparable[] aux = a.clone();
        sort(aux, a, 0, a.length-1);  
        assert isSorted(a);
	}

	public static void sortBU(Comparable[] a) {
		int n = a.length;
        Comparable[] aux = new Comparable[a.length];
        for (int len = 1; len < n; len *= 2) {
            for (int lo = 0; lo < n-len; lo += len+len) {
                int mid  = lo+len-1;
                int hi = Math.min(lo+len+len-1, n-1);
                mergeBU(a, aux, lo, mid, hi);
            }
        }
        assert isSorted(a);
	}

	private static boolean isSorted(Comparable[] src) {
        return isSorted(src, 0, src.length - 1);
    }

    private static boolean isSorted(Comparable[] src, int lo, int hi) {
        for (int i = lo + 1; i <= hi; i++)
            if (less(src[i], src[i-1])) return false;
        return true;
    }

	private static boolean less(Comparable a, Comparable b) {
		return a.compareTo(b) < 0;
	}

	public static void main(String[] args) {
		Comparable[] list = { 9, 87, 6, 45, 7, 3, 1, 2, 0, 3, 4, 6, 5, 9, 8, 3, 45 };
		Merge.sortBU(list);
		for (Comparable i : list) {
			System.out.println(i);
		}
	}
}
