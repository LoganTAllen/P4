package assign04;

import java.util.Arrays;
import java.util.Random;

/**
 * This program collects the running times of the AnagramChecker getLargestAnagramGroup method that
 * implements Java's sort routine.
 * 
 * This is modified code from lecture 07.
 * 
 * @author Logan Allen and Parker Nilson
 * @version February 6, 2019
 */
public class GetLargestAnagramGroupJavaSortTimer {
	
	//TODO: This code is copied directly from the in class example. Modify Accordingly

	public static void main(String[] args) {

		final int NSTART = 10000;
		final int NSTOP = 100000;
		final int NINCR = 10000;

		Random rng = new Random();
		
		System.out.println("N\tBest case\tAverage case\tWorst case\n");

		for(int N = NSTART; N <= NSTOP; N += NINCR) {
			System.out.print(N + "\t");

			// Build three arrays of N integers, two sorted and one
			// reverse-sorted.
			int[] sortedArr = new int[N];
			int[] permutedArr = new int[N];
			int[] reverseSortedArr = new int[N];
			for(int i = 0; i < N; i++) {
				sortedArr[i] = i;
				permutedArr[i] = i;
				reverseSortedArr[i] = N - i - 1;
			}

			// Randomly permute one of the arrays.
			for(int i = 0; i < N; i++) {
				int randIdx = rng.nextInt(N);
				int temp = permutedArr[i];
				permutedArr[i] = permutedArr[randIdx];
				permutedArr[randIdx] = temp;
			}

			System.out.println(getTime(sortedArr) + "\t" + getTime(permutedArr) + "\t" + 
					getTime(reverseSortedArr));
		}

	}

	@SuppressWarnings("unused")
	private static long getTime(int[] arr) {
				
		final int TIMES_TO_LOOP = 100;

		// Let things stabilize
		long startTime = System.nanoTime();
		while(System.nanoTime() - startTime < 1000000000)
			;

		// Time the sort
		startTime = System.nanoTime();
		for(int i = 0; i < TIMES_TO_LOOP; i++) {
			// Don't sort an already sorted array!
			int[] temp = Arrays.copyOf(arr, arr.length);
			sort(temp);
		}

		long midTime = System.nanoTime();

		// Time the "overhead"
		for(int i = 0; i < TIMES_TO_LOOP; i++) {
			int[] temp = Arrays.copyOf(arr, arr.length); 
		}

		long endTime = System.nanoTime();

		return ((midTime - startTime) - (endTime - midTime)) / TIMES_TO_LOOP;
	}

	/**
	 * Sorts the given array, using the insertion sort algorithm.
	 * 
	 * @param arr
	 */
	public static void sort(int[] arr) {
		for(int i = 1; i < arr.length; i++) {
			int val = arr[i];
			int j;
			for(j = i - 1; j >= 0 && arr[j] > val; j--)
				arr[j + 1] = arr[j];
			arr[j + 1] = val;
		}
	}
}