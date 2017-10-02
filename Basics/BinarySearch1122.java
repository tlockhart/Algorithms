import java.util.Arrays;

//Compile: javac -cp .;algs4_ts.jar BinarySearch1122.java
//Run:java -cp .;algs4_ts.jar BinarySearch1122 < mediumDG.txt
public class BinarySearch1122 {

    public static int rankIT(int key, int[] a) {
		int depth=0;
		return rankIT(key, a, 0, a.length-1, depth);
	}
	public static int rankIT(int key, int[] a, int lo, int hi, int depth){
        
            //int depth;
			StdOut.printf("%-10d", lo);
			StdOut.printf("%-10d", hi);
			StdOut.printf("%-10d\n", depth);
			// Not present condition.
			if(lo > hi) return -1;
            int mid = lo + (hi - lo) / 2;
			//Key is in a[lo..hi]
            if      (key < a[mid]) return rankIT(key, a, lo, mid-1, ++depth);
            else if (key > a[mid]) return rankIT(key, a, mid+1, hi, ++depth);
            else return mid;
    }

    public static void main(String[] args) {

        int[] whitelist = StdIn.readAllInts();

        // sort the array
        Arrays.sort(whitelist);

        // read integer key from standard input; print if not in whitelist
        //while (!StdIn.isEmpty()) {
            //int key = StdIn.readInt();
			int key = 4325;
            if (rankIT(key, whitelist) == -1)
                StdOut.println(key);
        //}
    }
}


