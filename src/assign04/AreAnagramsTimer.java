package assign04;

import java.util.Arrays;
import java.util.Random;

/**
 * This program collects the running times of the AnagramChecker areAnagrams method.
 * 
 * This is modified code from lecture 07.
 * 
 * @author Logan Allen and Parker Nilson
 * @version February 6, 2019
 */
public class AreAnagramsTimer {
	
	public static void main(String[] args) {

		final int NSTART = 1000;
		final int NSTOP = 20000;
		final int NINCR = 1000;

		Random rng = new Random();
		
		System.out.println("areAnagrams");
		System.out.println("N\ttime(ns)");

		for(int N = NSTART; N <= NSTOP; N += NINCR) {
			System.out.print(N + "\t");
		
			// Build two random strings of length N
			String input1 = new String();
			String input2 = new String();
			
			for(int i = 1; i <= N; i++) {
				char a = (char) (rng.nextInt(26) + 97);
				char b = (char) (rng.nextInt(26) + 97);
				input1 += a;
				input2 += b;
			}
			
			System.out.println(getTime(input1, input2));
		}

	}

	@SuppressWarnings("unused")
	private static long getTime(String input1, String input2) {
				
		final int TIMES_TO_LOOP = 100;

		// Let things stabilize
		long startTime = System.nanoTime();
		while(System.nanoTime() - startTime < 1000000000);

		// Time areAnagrams
		startTime = System.nanoTime();
		for(int i = 0; i < TIMES_TO_LOOP; i++) {
			String copy1 = input1;
			String copy2 = input2;
			AnagramChecker.areAnagrams(copy1, copy2);
		}

		long midTime = System.nanoTime();

		// Remove the cost of running the loop and making copies of strings
		for(int i = 0; i < TIMES_TO_LOOP; i++) {
			String copy1 = input1;
			String copy2 = input2;
		}

		long endTime = System.nanoTime();

		return ((midTime - startTime) - (endTime - midTime)) / TIMES_TO_LOOP;
	}
}