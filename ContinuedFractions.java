package ContinuedFractions;
import java.util.Arrays;
public class ContinuedFractions {
	public static void main(String[] args) {
		int [] sequence = {5, 2, 8, 4};
		ContinuedFraction cf = new ContinuedFraction(sequence);
		System.out.println(cf.calculateValueRecursively());
		System.out.println(cf.calculateValueIteratively());
		System.out.println(cf.approximateSquareRootOf2(4));
		System.out.println(cf.approximateE(10));
		System.out.println(Arrays.toString(cf.representSequenceRoot2(10)));
		System.out.println(Arrays.toString(cf.representSequenceE(6)));

	}
}

class ContinuedFraction{

	// the linear representation of the continued fraction
	private int[] sequence;

	/**
	 * create a continued fraction out of the given linear representation
	 * @param sequence
	 */
	public ContinuedFraction(int[] sequence) {
		this.sequence = sequence;
	}
	
	/**
	 * calculate recursively the value of the continued fraction
	 * @return the recursively calculated value of the continued fraction, when no sequense provided, the value is 0
	 */
	public double calculateValueRecursively() {
		if (sequence == null || sequence.length == 0) {
			return 0;
		}
		if (sequence.length == 1) {
			return sequence[0];
		}
		int first = sequence[0];
		int n = sequence.length-1;
		int[] newSequence = new int[n];
		System.arraycopy(sequence, 1, newSequence, 0, n);
		sequence = newSequence;
		return first + 1/(calculateValueRecursively());

	}
	
	/**
	 * calculate iteratively the value of the continued fraction
	 * @return den iterativ berechneten Wert des Kettenbruchs, bei fehlenden Werten 0
	 */
	public double calculateValueIteratively() {
		if (sequence == null || sequence.length == 0) {
			return 0;
		}
		if (sequence.length == 1) {
			return sequence[0];
		}
		double fraction = (double)1/(sequence[sequence.length-1]);		
		    for(int i = sequence.length-2; i > 0; i--)
		    { 
		        fraction = 1 /(sequence[i] + fraction);
		    }
		return sequence[0] + fraction;
	}
	
	/**
	 * approximtate the value of the square root of 2 with the aid of continued fraction 
	 * represented as an array of n elements 
	 * @param n the number of the elements used for the approximation 
	 * @return the approximated value of the square root of 2
	 */
	public static double approximateSquareRootOf2(int n) {
		int[] sequence2 = representSequenceRoot2(n);
		if (sequence2.length == 1) {
			return 1.0;
		}
		if (sequence2.length == 2) {
			return (1+ (double)1/2);
		}
		double fraction = (double)1/(sequence2[sequence2.length-1]);
		    for(int i = sequence2.length-2; i > 0; i--)
		    { 
		        fraction = (double)1 /(sequence2[i] + fraction);
		    }
		return sequence2[0] + fraction;		
	}

	/**
	 * approximate the value of the natural number or Euler's number with the aid of 
	 * the aid of continued fraction represented as an array of n elements
	 * @param n the number of the elements in the array used for the approximation 
	 * @return the approximate value of Euler's or natural number e
	 */
	public static double approximateE(int n) {
		//approximiert den Wert der eulerschen Zahl mit Hilfe der Kettenbruchdarstellung mit n Elementen
		int [] sequenceE = representSequenceE(n);
		if (sequenceE.length == 1) {
			return sequenceE[0];
		} 
		if (sequenceE.length == 2) {
			return (double)2 + (double)1/1;
		}
		if (sequenceE.length == 3) {
			return (double)2 + (double)1/(1+(double)1/2);
		}
		
		double fraction = (double)1/(sequenceE[sequenceE.length-1]);
		    for(int i = sequenceE.length-2; i > 1; i--)
		    { 
		        fraction = (float)1 /(sequenceE[i] + fraction);
		    }
			fraction = (float)1/(1+fraction);
		return sequenceE[0] + fraction;
	}

	/**
	 * represent the sequence of the elements of the continued fraction for square root of 2 
	 * with the first n elements of the array
	 * @param n the number of elements in the sequence array
	 * @return the sequence as an array, if n <= 0 - an empty array
	 */
	public static int[] representSequenceRoot2(int n) {
		int [] sequence2 = new int[n];
		sequence2[0] = 1;
		for (int i = 1; i< sequence2.length; i ++) {
			sequence2[i] = 2;
		}
		return sequence2;
	}
	
	/**
	 * represent the sequence of the elements of the continued fraction for Euler's or natural number e 
	 * with the first n elements of the array
	 * @param n the number of elements in the sequence array
	 * @return the sequence as an array, if n <= 0 - an empty array
	 */
	public static int[] representSequenceE(int n) {
		int [] sequenceE = new int[n];
		sequenceE[0] = 2;
		if (n == 1) {
			return sequenceE;
		}
		sequenceE[1] = 1; 
		if (n == 2) {
			return sequenceE;
		}
		sequenceE[2] = 2;
		if (n == 3) {
			return sequenceE;
		}

		int cycle = 1;
		for (int i = 2; i < sequenceE.length-2; i += 3) {
			sequenceE[i] = 2 * cycle;
			sequenceE[i + 1] = 1;
			sequenceE[i + 2] = 1;
			cycle++;
		}
		// addiert die lätzte Elemente wenn die Länge nicht durch 3 teilbar
		if ((sequenceE.length - 2) % 3 != 0) {
			int rest = (sequenceE.length - 2) % 3;
			if (rest == 1) {
				sequenceE[sequenceE.length-1] = 2*cycle;
			} else {
				sequenceE[sequenceE.length-2] = 2*cycle;
				sequenceE[sequenceE.length-1] = 1;
			}
		}
		return sequenceE;
	}

}