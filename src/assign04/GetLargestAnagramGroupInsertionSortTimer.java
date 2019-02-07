package assign04;

import java.util.Arrays;
import java.util.Random;

/**
 * This program collects the running times of the AnagramChecker getLargestAnagramGroup method that
 * implements an insertion sort.
 * 
 * This is modified code from lecture 07.
 * 
 * @author Logan Allen and Parker Nilson
 * @version February 6, 2019
 */
public class GetLargestAnagramGroupInsertionSortTimer {

	public static void main(String[] args) {

		final int NSTART = 1000;
		final int NSTOP = 20000;
		final int NINCR = 1000;

		Random rng = new Random();
		
		System.out.println("getLargestAnagramGroup (Insertion Sort Method)");
		System.out.println("N\ttime(ns)");

		for(int N = NSTART; N <= NSTOP; N += NINCR) {
			System.out.print(N + "\t");
		
			// Build a String array of size N
			String[] inputStringArray = new String[N];
			String inputString = new String();
			
			// populate the array with random Strings of size 4 - 8
			for(int i = 0; i < inputStringArray.length; i++) {
				String inputStringCopy = inputString;
				int stringLength = rng.nextInt(5) + 4;
				
				for(int j = 1; j <= stringLength; j++) {
					char a = (char) (rng.nextInt(26) + 97);
					inputStringCopy += a;
				}
				
				inputStringArray[i] = inputStringCopy;
			}
			
			System.out.println(getTime(inputStringArray));
		}

	}

	@SuppressWarnings("unused")
	private static long getTime(String[] inputStringArray) {
				
		final int TIMES_TO_LOOP = 10;

		// Let things stabilize
		long startTime = System.nanoTime();
		while(System.nanoTime() - startTime < 1000000000);

		// Time getLargestAnagramGroup
		startTime = System.nanoTime();
		for(int i = 0; i < TIMES_TO_LOOP; i++) {
			String[] temp = Arrays.copyOf(inputStringArray, inputStringArray.length);
			AnagramChecker.getLargestAnagramGroup(inputStringArray);
		}

		long midTime = System.nanoTime();

		// Remove the cost of running the loop and making copies of string arrays
		for(int i = 0; i < TIMES_TO_LOOP; i++) {
			String[] temp = Arrays.copyOf(inputStringArray, inputStringArray.length);
		}

		long endTime = System.nanoTime();

		return ((midTime - startTime) - (endTime - midTime)) / TIMES_TO_LOOP;
	}
}