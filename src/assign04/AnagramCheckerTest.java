package assign04;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Comparator;
import java.util.NoSuchElementException;

import org.junit.jupiter.api.Test;

class AnagramCheckerTest {
	
	//TODO: Add JavaDoc
	//TODO: Maybe Nest these test

	// public static String sort(String)
	
		// sort all lowercase
		@Test
		void sortAllLowercaseTest() {
			String allLowercase = "apple";
			assertEquals("aelpp", AnagramChecker.sort(allLowercase));
		}
	
		// sort all uppercase
		@Test
		void sortAllUppercaseTest() {
			String allUppercase = "BANANA";
			assertEquals("AAABNN", AnagramChecker.sort(allUppercase));
		}
	
		// sort capital word
		@Test
		void sortCapitalTest() {
			String capital = "SaltLakeCity";
			assertEquals("CLSaaeikltty", AnagramChecker.sort(capital));
		}
	
		// sort an already sorted string
		@Test
		void sortSortedStringTest() {
			String sorted = "abcde";
			assertEquals("abcde", AnagramChecker.sort(sorted));
		}
	
		// sort a reverse sorted string
		@Test
		void sortReverseSortedStringTest() {
			String reverse = "edcba";
			assertEquals("abcde", AnagramChecker.sort(reverse));
		}
	
		// sort a null string
		@Test
		void sortNullStringTest() {
			String empty = null;
			assertThrows(NullPointerException.class, () -> {
				AnagramChecker.sort(empty);
			});
		}
	
	// public static <T> void insertionSort(T[], Comparator<? super T>)
	
		// sort a basic char array
		@Test
		void sortBasicCharArrayTest() {
			char[] basicCharArray = {'p', 'm', 'l', 'o', 'n'};
			char[] sortedCharArray = {'l', 'o', 'n', 'm', 'p'};
			AnagramChecker.insertionSort(basicCharArray, Comparator.naturalOrder());
			assertEquals(sortedCharArray, basicCharArray);
		}

		// sort a char array with uppercase and lowercase letters
		@Test
		void sortUpperAndLowercaseCharArrayTest() {
			char[] upperAndLowercase = {'b', 'A', 'B', 'c', 'a', 'C'};
			char[] sortedCase = {'A', 'B', 'C', 'a', 'b', 'c'};
			AnagramChecker.insertionSort(upperAndLowercase, Comparator.naturalOrder());
			assertEquals(sortedCase, upperAndLowercase);
		}

		// sort a duplicate char array
		@Test
		void sortDuplicateCharArrayTest() {
			char[] withDuplicates = {'a', 'c', 'd', 'f', 'c', 'a'};
			char[] sortedDuplicates = {'a', 'a', 'c', 'c', 'd', 'f'};
			AnagramChecker.insertionSort(withDuplicates, Comparator.naturalOrder());
			assertEquals(sortedDuplicates, withDuplicates);
		}
		
		// sort a basic string array
		@Test
		void sortBasicStringArrayTest() {
			String[] basicStringArray = {"orange", "banana", "strawberry", "apple"};
			String[] sortedStringArray = {"apple", "banana", "orange", "strawberry"};
			AnagramChecker.insertionSort(basicStringArray, Comparator.naturalOrder());
			assertEquals(sortedStringArray, basicStringArray);
		}
		
		// sort a string array with capitals
		@Test
		void sortStringArrayWithCapitalsTest() {
			String[] stringArrayWithCapitals = {"Orange", "apple", "Apple", "orange"};
			String[] sortedStringArray = {"Apple", "Orange", "apple", "orange"};
			assertEquals(sortedStringArray, AnagramChecker.insertionSort(stringArrayWithCapitals, Comparator.naturalOrder()));
		}
	
		// sort a duplicate string array
		@Test
		void sortStringArrayWithDuplicatesTest() {
			String[] duplicateStringArray = {"orange", "strawberry", "apple", "strawberry", "apple"};
			String[] sortedStringArray = {"apple", "apple", "orange", "strawberry", "strawberry"};
			assertEquals(sortedStringArray, AnagramChecker.insertionSort(duplicateStringArray, Comparator.naturalOrder()));
		}
		
		// sort an already sorted array
		@Test
		void sortAlreadySortedStringArrayTest() {
			String[] stringArray = {"Apple", "Orange", "apple", "orange"};
			String[] sortedStringArray = {"Apple", "Orange", "apple", "orange"};
			assertEquals(sortedStringArray, AnagramChecker.insertionSort(stringArray, Comparator.naturalOrder()));
		}
	
		// sort a backwards sorted array
		@Test
		void sortBackwardsSortedStringArray() {
			String[] backwardsStringArray = {"orange", "apple", "Orange",  "Apple"};
			String[] sortedStringArray = {"Apple", "Orange", "apple", "orange"};
			assertEquals(sortedStringArray, AnagramChecker.insertionSort(backwardsStringArray, Comparator.naturalOrder()));
		}
	
		// sort a null array
		@Test
		void sortNullArrayTest() {
			String[] empty;
			assertThrows(NullPointerException.class, () -> {
				AnagramChecker.insertionSort(empty, Comparator.NaturalOrder());
			});
		}
	
		// test null comparator
		@Test
		void sortArrayWithNullComparatorTest() {
			String[] basicStringArray = {"orange", "banana", "strawberry", "apple"};
			//TODO: Implement null comparator correctly
			Comparator nullComparator;
			assertThrows(NullPointerException.class, () -> {
				AnagramChecker.insertionSort(basicStringArray, nullComparator);
			});
		}
	
	// public static boolean areAnagrams(String, String)
	
		// compare two anagrams
		@Test
		void compareTwoAnagramsTest() {
			String wordOne = "abcd";
			String wordTwo = "dcba";
			assertTrue(AnagramChecker.areAnagrams(wordOne, wordTwo));
		}
	
		// compare lower case anagram with a capital anagram
		@Test
		void compareTwoAnagramsUpperAndLowercaseTest() {
			String wordOne = "aBcD";
			String wordTwo = "dcba";
			assertTrue(AnagramChecker.areAnagrams(wordOne, wordTwo));
		}
	
		// compare two non anagrams (same size different letters)
		@Test
		void compareTwoNonAnagramsDifferentLettersTest() {
			String wordOne = "abcd";
			String wordTwo = "cdef";
			assertFalse(AnagramChecker.areAnagrams(wordOne, wordTwo));
		}
		
		// compare two non anagrams (different lengths)
		@Test
		void compareTwoNonAnagramsDifferentLengthsTest() {
			String wordOne = "abcd";
			String wordTwo = "abcde";
			assertFalse(AnagramChecker.areAnagrams(wordOne, wordTwo));
		}
	
		// compare null strings
		@Test
		void compareNullStringsTests() {
			String wordOne = null;
			String wordTwo = null;
			assertThrows(NullPointerException.class, () -> {
				AnagramChecker.areAnagrams(wordOne, wordTwo);
			});
		}
	
	// public static String[] getLargestAnagramGroup(String[])
	
		// one anagram group test
		@Test
		void oneAnagramGroupStringArrayTest() {
			String[] list = {"rental", "abets", "baste", "bead", "betas", "beast", "beats", "mane"};
			//TODO: The largestAnagramGroup array order may change based on how we implement the method
			String[] largestAnagramGroup = {"abets", "baste", "betas", "beast", "beats"};
			assertEquals(largestAnagramGroup, AnagramChecker.getLargestAnagramGroup(list));
		}
	
		// no anagram test
		@Test
		void noAnagramTest() {
			String[] list = {"apple", "banana", "orange", "pear"};
			String[] largestAnagramGroup = {};
			assertEquals(largestAnagramGroup, AnagramChecker.getLargestAnagramGroup(list));
		}
	
		// three equal size anagram groups test
		@Test
		void threeEqualAnagramGroupsTest() {
			String[] list = {"abut", "race", "tabu", "care", "tuba", "acme", "came", "mace", "acre"};
			//TODO: The output array could change depending on implementation of the method in addition to the order of the arrays
			String[] possibilityOne = {"abut", "tabu", "tuba"};
			String[] possibilityTwo = {"acme", "came", "mace"};
			String[] possibilityThree = {"acre", "care", "race"};
			assertEquals(possibilityOne, AnagramChecker.getLargestAnagramGrou(list));
		}
	
		// null string array test
		@Test
		void largestAnagramGroupNullStringTest() {
			String[] empty = {};
			assertThrows(NullPointerException.class, () -> {
				AnagramChecker.getLargestAnagramGroup(empty);
			});
		}
	
	// public static String[] getLargestAnagramGroup(String filename)
	
		// one anagram group test
		@Test
		void oneAnagramGroupFromFileTest() {
			String fileName = "insertName";
			//TODO: Put these strings in a text file and replace fileName
			// Add to a text file: rental abets baste bead betas beast beats mane
			//TODO: The largestAnagramGroup array order may change based on how we implement the method
			String[] largestAnagramGroup = {"abets", "baste", "betas", "beast", "beats"};
			assertEquals(largestAnagramGroup, AnagramChecker.getLargestAnagramGroup(fileName));
		}
	
		// no anagram test
		@Test
		void noAnagramFromFileTest() {
			String fileName = "InsertName";
			//TODO: Put these strings in a text file and replace fileName
			// Add to a text file: apple banana orange pear
			String[] largestAnagramGroup = {};
			assertEquals(largestAnagramGroup, AnagramChecker.getLargestAnagramGroup(fileName));
		}
	
		// three equal size anagram groups test
		@Test
		void threeEqualAnagramGroupsFromFileTest() {
			String fileName = "insertName";
			//TODO: Put these strings in a text file and replace fileName
			// Add to a text file: abut race tabu care tuba acme came mace acre
			//TODO: The output array could change depending on implementation of the method in addition to the order of the arrays
			String[] possibilityOne = {"abut", "tabu", "tuba"};
			String[] possibilityTwo = {"acme", "came", "mace"};
			String[] possibilityThree = {"acre", "care", "race"};
			assertEquals(possibilityOne, AnagramChecker.getLargestAnagramGroup(fileName));
		}
	
		// file does not exist test
		@Test
		void fileDoesNotExistTest() {
			String fileName = "FakeName";
			String[] largestAnagramGroup = {};
			assertEquals(largestAnagramGroup, AnagramChecker.getLargestAnagramGroup(fileName));
		}
	
		// file is empty test
		@Test
		void fileIsEmptyTest() {
			//TODO: Create a blank text file and replace fileName
			String fileName = "InsertName";
			String[] largestAnagramGroup = {};
			assertEquals(largestAnagramGroup, AnagramChecker.getLargestAnagramGroup(fileName));
		}
}
