package assign04;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
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
	
	public static void addRandomStrings(int startIndex, int amt, Random rand, String[] arr) {
		for(int i = startIndex; i < startIndex + amt; ++i) {
			//create a new empty string
			String str = "";
			//the string will be random length from 4 to 8 characters
			int strLength = rand.nextInt(5) + 4;
			
			//generate random characters for the string
			for(int j = 0; j < strLength; j++) {
				char a = (char) (rand.nextInt(26) + 97);
				str += a;
			}
			
			//put the string at its place between N and N + NINCR
			arr[i] = str;
		}
	}

	public static void main(String[] args) throws IOException {

		final int NSTART = 10000;
		final int NSTOP = 100000;
		final int NINCR = 10000;
		
		Random rng = new Random();
		
		//open a file to output the runtimes to
		BufferedWriter file  = new BufferedWriter(new FileWriter("src/assign04/getLargestAnagramGroupJavaSortRunTimes.txt"));
		
		System.out.println("getLargestAnagramGroup (Java's Sort Method)");
		System.out.println("N\ttime(ns)");
		
		//create a problem set capable of holding the largest possible N
		String[] problemSet = new String[NSTOP];
		//fill it with the number of elements we need for the first problemSet size
		addRandomStrings(0, NSTART, rng, problemSet);

		for(int N = NSTART; N <= NSTOP; N += NINCR) {
			//output which problem set this is
			System.out.print(N + "\t");
			file.write(N + "\t");
			
			//output the running time it took for that problem set.
			//	(pass N as a parameter so that the getTime method can truncate the null values after the Nth element)
			long runningTime = getTime(problemSet, N);
			System.out.println(runningTime);
			file.write(runningTime + ""); 
			file.newLine();
			file.flush(); //write the contents of this test to the file
			
			//fill the problemSet with NINCR more random strings
			//	(start at index N, because N-1 would give the index of the 1000th element at index 999
			//	we are starting from the 1001th element at index 1000)
			if(N != NSTOP) addRandomStrings(N, NINCR, rng, problemSet);
		}
		
		//close the file after the tests are done
		file.close();
	}

	@SuppressWarnings("unused")
	private static long getTime(String[] inputStringArray, int N) {
				
		final int TIMES_TO_LOOP = 100;

		// Let things stabilize
		long startTime = System.nanoTime();
		while(System.nanoTime() - startTime < 1000000000);


		// Time javaGetLargestAnagramGroup (this is the version that uses the Arrays.sort() method)
		startTime = System.nanoTime();
		for(int i = 0; i < TIMES_TO_LOOP; i++) {
			String[] temp = Arrays.copyOf(inputStringArray, N);
			AnagramChecker.javaGetLargestAnagramGroup(temp);
		}

		long midTime = System.nanoTime();

		// Remove the cost of running the loop and making copies of string arrays
		for(int i = 0; i < TIMES_TO_LOOP; i++) {
			String[] temp = Arrays.copyOf(inputStringArray, N);
		}

		long endTime = System.nanoTime();

		return ((midTime - startTime) - (endTime - midTime)) / TIMES_TO_LOOP;
	}
}