package assign04;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.DecimalFormat;
import java.util.Scanner;

/**
 * This program checks the collected run times of the AnagramChecker's areAnagrams
 * method by conducting the following tests. This is done to ensure that the expected
 * Big-O behavior matches the behavior of the collected run times to check correct
 * implementation of the method:

 * Let T(N) be the running time observed, and let F(N) be the Big-O expected.
 *
 * If T(N) / F(N) converges to a positive value, then F(N) correctly represents
 * the growth rate of the running times.
 *
 * If T(N) / F(N) converges to 0, then F(N) is an overestimate of the growth
 * rate of the running times.
 *
 * If T(N) / F(N) converges to infinity, then F(N) is an underestimate of the
 * growth rate of the running times.
 *
 * This is modified code from lecture 06.
 *
 * @author Logan Allen and Parker Nilson
 * @version February 6, 2019
 */
public class AreAnagramsCheckAnalysis {

	public static void main(String[] args) {

		DecimalFormat formatter = new DecimalFormat("00000E00");

		System.out.println("\nN\t|  T(N)/1\tT(N)/logN\tT(N)/N\t\tT(N)/N^2");
		System.out.println("-----------------------------------------------------------------------------------");

		String fileName = "src/assign04/areAnagramsRunTimes.txt";
		Scanner fileRead;
		try {
			fileRead = new Scanner(new File(fileName));

			while(fileRead.hasNextLine()) {
				long N = fileRead.nextLong();
				long runTime = fileRead.nextLong();

				System.out.print(N + "\t|  ");
				System.out.println(formatter.format(runTime) + "\t" +
						formatter.format(runTime / (Math.log10(N) / Math.log10(2))) + "\t" +
						formatter.format(runTime / N) + "\t" +
						formatter.format(runTime / (N * N)));
			}

			fileRead.close();
		}

		catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
}
