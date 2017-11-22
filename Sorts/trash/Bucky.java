/**compile Multiple: javac -cp .;algs4_sts.jar; Bucky.java
  *run: java -cp ../;.;algs4_sts.jar; sorts.Bucky*/
package sorts;  
import java.util.*;
public class Bucky{
     public static void main(String[] args){
     	System.out.println(max(23, 42, 1));
		System.out.println(max("apples", "tots", "chicken"));
     }
    //Only generic objects <T> that inherit from the Comparable class can be used in this method.
	public static <T extends Comparable<T>> T max(T a, T b, T c){
	T m = a;
	if(b.compareTo(a) > 0)
		m = b;

	if(c.compareTo(m) > 0)
		m = c;
	//Return type of T (generic) data.
	return m;
     }
}