package assign04;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
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
	
	public static void addRandomCharacters(int amt, Random rand, StringBuilder str) {
		for(int i = 0; i < amt; ++i) {
			str.append((char) (rand.nextInt(26) + 97));
		}
	}
	
	public static void main(String[] args) throws IOException {

		final int NSTART = 1000;
		final int NSTOP = 20000;
		final int NINCR = 1000;

		Random rng = new Random();
		
		//open a file to write the output of the timings to
		BufferedWriter file = new BufferedWriter(new FileWriter("src/assign04/areAnagramsRunTimes.txt"));
		
		StringBuilder input1 = new StringBuilder();
		StringBuilder input2 = new StringBuilder();
		addRandomCharacters(NSTART, rng, input1);
		addRandomCharacters(NSTART, rng, input2);
		
		System.out.println("areAnagrams");
		System.out.println("N\ttime(ns)");

		for(int N = NSTART; N <= NSTOP; N += NINCR) {
			System.out.print(N + "\t");
			file.write(N + "\t");
			
			long runningTime = getTime(input1.toString(), input2.toString());
			System.out.println(runningTime);
			file.write(runningTime + "");
			if(N != NSTOP) file.newLine();
			file.flush();
			
			if(N != NSTOP) {
				addRandomCharacters(NINCR, rng, input1);
				addRandomCharacters(NINCR, rng, input2);
			}
		}
		
		file.close();

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
			AnagramChecker.areAnagrams(input1, input2);
		}

		long midTime = System.nanoTime();

		// Remove the cost of running the loop
		for(int i = 0; i < TIMES_TO_LOOP; i++) {
		}

		long endTime = System.nanoTime();

		return ((midTime - startTime) - (endTime - midTime)) / TIMES_TO_LOOP;
	}
}