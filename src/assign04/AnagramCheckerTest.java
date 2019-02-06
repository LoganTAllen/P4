package assign04;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Comparator;
import java.util.NoSuchElementException;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

/**
 * This program runs a series of JUnit tests for the AnagramChecker Class
 * 
 * @author Logan Allen and Parker Nilson
 */
class AnagramCheckerTest {
	
	/*
	 * The following JUnit tests check the public static String sort(String) method
	 */
	@Nested
	@DisplayName("sort(String) Method Tests")
	class StringSortTests {
		
		@Test
		@DisplayName("All Lowercase")
		void sortAllLowercaseTest() {
			String allLowercase = "apple";
			assertEquals("aelpp", AnagramChecker.sort(allLowercase));
		}
	
		@Test
		@DisplayName("All Uppercase")
		void sortAllUppercaseTest() {
			String allUppercase = "BANANA";
			assertEquals("AAABNN", AnagramChecker.sort(allUppercase));
		}
	
		@Test
		@DisplayName("Capital Words")
		void sortCapitalTest() {
			String capital = "SaltLakeCity";
			assertEquals("CLSaaeikltty", AnagramChecker.sort(capital));
		}
	
		@Test
		@DisplayName("Already Sorted String")
		void sortSortedStringTest() {
			String sorted = "abcde";
			assertEquals("abcde", AnagramChecker.sort(sorted));
		}
	
		@Test
		@DisplayName("Reverse Sorted String")
		void sortReverseSortedStringTest() {
			String reverse = "edcba";
			assertEquals("abcde", AnagramChecker.sort(reverse));
		}
	
		@Test
		@displayName("Null String")
		void sortNullStringTest() {
			String empty = null;
			assertThrows(NullPointerException.class, () -> {
				AnagramChecker.sort(empty);
			});
		}
	}
	
	/*
	 * The following JUnit tests check the public static <T> void insertionSort(T[], Comparator<? super T>) method
	 */
	@Nested
	@DisplayName("insertionSort Method Tests")
	class InsertionSortTests {
		
		@Nested
		@DisplayName("Char Array Tests")
		class CharArrayTests {
		
			@Test
			@DisplayName("Basic Array")
			void sortBasicCharArrayTest() {
				char[] basicCharArray = {'p', 'm', 'l', 'o', 'n'};
				char[] sortedCharArray = {'l', 'o', 'n', 'm', 'p'};
				AnagramChecker.insertionSort(basicCharArray, Comparator.naturalOrder());
				assertEquals(sortedCharArray, basicCharArray);
			}
	
			@Test
			@DisplayName("Upper and Lowercase")
			void sortUpperAndLowercaseCharArrayTest() {
				char[] upperAndLowercase = {'b', 'A', 'B', 'c', 'a', 'C'};
				char[] sortedCase = {'A', 'B', 'C', 'a', 'b', 'c'};
				AnagramChecker.insertionSort(upperAndLowercase, Comparator.naturalOrder());
				assertEquals(sortedCase, upperAndLowercase);
			}
	
			@Test
			@DisplayName("With Duplicates")
			void sortDuplicateCharArrayTest() {
				char[] withDuplicates = {'a', 'c', 'd', 'f', 'c', 'a'};
				char[] sortedDuplicates = {'a', 'a', 'c', 'c', 'd', 'f'};
				AnagramChecker.insertionSort(withDuplicates, Comparator.naturalOrder());
				assertEquals(sortedDuplicates, withDuplicates);
			}
		}
		
		@Nested
		@DisplayName("String Array Tests")
		class StringArrayTests {
		
			@Test
			@DisplayName("Basic Array")
			void sortBasicStringArrayTest() {
				String[] basicStringArray = {"orange", "banana", "strawberry", "apple"};
				String[] sortedStringArray = {"apple", "banana", "orange", "strawberry"};
				AnagramChecker.insertionSort(basicStringArray, Comparator.naturalOrder());
				assertEquals(sortedStringArray, basicStringArray);
			}
			
			@Test
			@DisplayName("With Capitals")
			void sortStringArrayWithCapitalsTest() {
				String[] stringArrayWithCapitals = {"Orange", "apple", "Apple", "orange"};
				String[] sortedStringArray = {"Apple", "Orange", "apple", "orange"};
				assertEquals(sortedStringArray, AnagramChecker.insertionSort(stringArrayWithCapitals, Comparator.naturalOrder()));
			}
		
			@Test
			@DisplayName("With Duplicates")
			void sortStringArrayWithDuplicatesTest() {
				String[] duplicateStringArray = {"orange", "strawberry", "apple", "strawberry", "apple"};
				String[] sortedStringArray = {"apple", "apple", "orange", "strawberry", "strawberry"};
				assertEquals(sortedStringArray, AnagramChecker.insertionSort(duplicateStringArray, Comparator.naturalOrder()));
			}
			
			@Test
			@DisplayName("Already Sorted")
			void sortAlreadySortedStringArrayTest() {
				String[] stringArray = {"Apple", "Orange", "apple", "orange"};
				String[] sortedStringArray = {"Apple", "Orange", "apple", "orange"};
				assertEquals(sortedStringArray, AnagramChecker.insertionSort(stringArray, Comparator.naturalOrder()));
			}
		
			@Test
			@DisplayName("Backwards Sorted")
			void sortBackwardsSortedStringArray() {
				String[] backwardsStringArray = {"orange", "apple", "Orange",  "Apple"};
				String[] sortedStringArray = {"Apple", "Orange", "apple", "orange"};
				assertEquals(sortedStringArray, AnagramChecker.insertionSort(backwardsStringArray, Comparator.naturalOrder()));
			}
		
			@Test
			@DisplayName("Null Array")
			void sortNullArrayTest() {
				String[] empty;
				assertThrows(NullPointerException.class, () -> {
					AnagramChecker.insertionSort(empty, Comparator.NaturalOrder());
				});
			}
		
			@Test
			@DisplayName("Null Comparator")
			void sortArrayWithNullComparatorTest() {
				String[] basicStringArray = {"orange", "banana", "strawberry", "apple"};
				//TODO: Implement null comparator correctly
				Comparator nullComparator;
				assertThrows(NullPointerException.class, () -> {
					AnagramChecker.insertionSort(basicStringArray, nullComparator);
				});
			}
		}
	}
	
	/*
	 * The following JUnit tests check the public static boolean areAnagrams(String, String) method
	 */
	@Nested
	@DisplayName("areAnagrams Method Tests")
	class AreAnagramsTests {
	
		@Test
		@DisplayName("Two Anagrams")
		void compareTwoAnagramsTest() {
			String wordOne = "abcd";
			String wordTwo = "dcba";
			assertTrue(AnagramChecker.areAnagrams(wordOne, wordTwo));
		}
	
		@Test
		@DisplayName("Lower and Uppercase Anagrams")
		void compareTwoAnagramsUpperAndLowercaseTest() {
			String wordOne = "aBcD";
			String wordTwo = "dcba";
			assertTrue(AnagramChecker.areAnagrams(wordOne, wordTwo));
		}
	
		@Test
		@DisplayName("Two Non-Anagrams Same Size")
		void compareTwoNonAnagramsDifferentLettersTest() {
			String wordOne = "abcd";
			String wordTwo = "cdef";
			assertFalse(AnagramChecker.areAnagrams(wordOne, wordTwo));
		}
		
		@Test
		@DisplayName("Two Non-Anagrams Different Sizes")
		void compareTwoNonAnagramsDifferentLengthsTest() {
			String wordOne = "abcd";
			String wordTwo = "abcde";
			assertFalse(AnagramChecker.areAnagrams(wordOne, wordTwo));
		}
	
		@Test
		@DisplayName("Null Strings")
		void compareNullStringsTests() {
			String wordOne = null;
			String wordTwo = null;
			assertThrows(NullPointerException.class, () -> {
				AnagramChecker.areAnagrams(wordOne, wordTwo);
			});
		}
	}
	
	/*
	 * The following JUnit tests check the public static String[] getLargestAnagramGroup(String[]) method
	 */
	@Nested
	@DisplayNames("getLargestAnagramGroup(String[]) Method Tests")
	class LargestAnagramGroupStringArrayTests {
		
		@Test
		@DisplayName("One Anagram Group")
		void oneAnagramGroupStringArrayTest() {
			String[] list = {"rental", "abets", "baste", "bead", "betas", "beast", "beats", "mane"};
			//TODO: The largestAnagramGroup array order may change based on how we implement the method
			String[] largestAnagramGroup = {"abets", "baste", "betas", "beast", "beats"};
			assertEquals(largestAnagramGroup, AnagramChecker.getLargestAnagramGroup(list));
		}
	
		@Test
		@DisplayName("No Anagram Groups")
		void noAnagramTest() {
			String[] list = {"apple", "banana", "orange", "pear"};
			String[] largestAnagramGroup = {};
			assertEquals(largestAnagramGroup, AnagramChecker.getLargestAnagramGroup(list));
		}
	
		@Test
		@DisplayName("Three Equal Size Anagram Groups")
		void threeEqualAnagramGroupsTest() {
			String[] list = {"abut", "race", "tabu", "care", "tuba", "acme", "came", "mace", "acre"};
			//TODO: The output array could change depending on implementation of the method in addition to the order of the arrays
			String[] possibilityOne = {"abut", "tabu", "tuba"};
			String[] possibilityTwo = {"acme", "came", "mace"};
			String[] possibilityThree = {"acre", "care", "race"};
			assertEquals(possibilityOne, AnagramChecker.getLargestAnagramGrou(list));
		}
	
		@Test
		@DisplayName("Null String Array")
		void largestAnagramGroupNullStringTest() {
			String[] empty = {};
			assertThrows(NullPointerException.class, () -> {
				AnagramChecker.getLargestAnagramGroup(empty);
			});
		}
	}
	
	/*
	 * The following JUnit tests test the public static String[] getLargestAnagramGroup(String filename) method
	 */
	@Nested
	@DisplayName("getLargestAnagramGroup(String filename) Method Tests")
	class GetLargestAnagramGroupInputStringFilenameTests {
		
		@Test
		@DisplayName("One Anagram Group")
		void oneAnagramGroupFromFileTest() {
			String fileName = "insertName";
			//TODO: Put these strings in a text file and replace fileName
			// Add to a text file: rental abets baste bead betas beast beats mane
			//TODO: The largestAnagramGroup array order may change based on how we implement the method
			String[] largestAnagramGroup = {"abets", "baste", "betas", "beast", "beats"};
			assertEquals(largestAnagramGroup, AnagramChecker.getLargestAnagramGroup(fileName));
		}
	
		@Test
		@DisplayName("No Anagrams")
		void noAnagramFromFileTest() {
			String fileName = "InsertName";
			//TODO: Put these strings in a text file and replace fileName
			// Add to a text file: apple banana orange pear
			String[] largestAnagramGroup = {};
			assertEquals(largestAnagramGroup, AnagramChecker.getLargestAnagramGroup(fileName));
		}
	
		@Test
		@DisplayName("Three Equal Size Anagram Groups")
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
	
		@Test
		@DisplayName("File Does Not Exist")
		void fileDoesNotExistTest() {
			String fileName = "FakeName";
			String[] largestAnagramGroup = {};
			assertEquals(largestAnagramGroup, AnagramChecker.getLargestAnagramGroup(fileName));
		}
	
		@Test
		@DisplayName("Empty File")
		void fileIsEmptyTest() {
			//TODO: Create a blank text file and replace fileName
			String fileName = "InsertName";
			String[] largestAnagramGroup = {};
			assertEquals(largestAnagramGroup, AnagramChecker.getLargestAnagramGroup(fileName));
		}
	}
}
