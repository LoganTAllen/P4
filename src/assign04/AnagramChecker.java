package assign04;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

/**
 * This class contains methods for sorting strings and determining whether they are
 * anagrams of eachother.
 * It also provides methods for find the largest group of anagrams in containers
 * of words.
 * 
 * @author Parker Nilson and Logan Allen
 *
 */
public class AnagramChecker {

	//TODO: test code
	@SuppressWarnings("unused")
	public static void main(String[] args) {

		String[] sample = new String[] {
				"alpha",
				"phlaa",
				"hapla",
				"bird",
				"ribd",
				"irdb",
				"palha",
				"args",
				"rags",
				"RasG",
				"bdir",
				"Alpha",
				"RAGS",
				"GARS",
				"SArg"
		};
		
		String[] largestGroup = AnagramChecker.getLargestAnagramGroup(sample);
		
		System.out.println(largestGroup);
	}

	/**
	 * This method returns the lexicographically-sorted version of
	 * the input string
	 * 
	 * @param str - the input string
	 * @return - the lexicographically sorted version of the input string
	 */
	public static String sort(String str) {
		//generate an object array of the characters in the input string (as individual String objects)
		//	we want the characters as String objects so that we can compare them in the comparator
		String[] chars = str.split("");
		
		//perform an insertionSort on the array of Characters
		insertionSort(chars, (lhs, rhs)->lhs.toLowerCase().compareTo(rhs.toLowerCase()));
		
		//convert Character array back to a string
		String ret = "";
		for(int i = 0; i < chars.length; ++i) {
			ret += chars[i];
		}

		//return the sorted String
		return ret;
	}
	
	/**
	 * This generic method sorts the input array using an insertion sort
	 * and the input Comparator object
	 * 
	 * @param arr - the array to be sorted
	 * @param comparator - the comparator to use for comparing
	 */
	public static <T> void insertionSort(T[] arr, Comparator<? super T> comparator) {
		//the index of the current minimum value of focus
		int minIndex = 0;
		
		//the current index to search for a new minimum value from
		int currentIndex = 0;
		
		//flag if a swap needs to happen
		boolean swap = false;
		
		/*
		 * the insertion sort algorithm goes as follows:
		 * 
		 * search the array from each index finding the minimum value,
		 * then swapping the new minimum with the current index
		 */
		while(currentIndex < arr.length) {
			//set the new minIndex to the current index for this next minimizing search
			//	(now that the min value from the previous search is before this new scope)
			minIndex = currentIndex;

			//search from the current index and set the new minimum value
			for(int i = currentIndex; i < arr.length; ++i) {
				if(comparator.compare(arr[i], arr[minIndex]) < 0) {
					minIndex = i;
					swap = true; //set true to tell the program a swap needs to happen
				}
			}
			
			//swap the element at the currentIndex with the new minimum
			if(swap) {
				swapElements(arr, currentIndex, minIndex);
				swap = false;
			}


			//move the current index up
			currentIndex++;

		}
		
	}
	
	/**
	 * This generic method swaps the elements at index1 and index2 in the array arr
	 * 
	 * @param arr - the array to perform the swap on
	 * @param index1 - the first index
	 * @param index2 - the second index
	 */
	public static <T> void swapElements(T[] arr, int index1, int index2) {
		T temp = arr[index1];
		arr[index1] = arr[index2];
		arr[index2] = temp;
	}
	
	/**
	 * Returns true if the two input strings are anagrams of eachother
	 * 
	 * @param str0 - First String
	 * @param str1 - Second String
	 * @return - whether the two input strings are anagrams of eachother
	 */
	public static boolean areAnagrams(String str0, String str1) {

		//if the lexicographically sorted versions of both strings are equal, 
		//	the strings are anagrams of eachother
		//use toLowerCase() in order to compare the strings, ignoring capitalization
		return sort(str0).toLowerCase().equals(sort(str1).toLowerCase());
	}
	
	/**
	 * This method returns the largest group of anagrams in the input
	 * array of words, in no particular order.
	 * It returns an empty array if there are no anagrams in the input array.
	 * 
	 * @param arr - the array to check for anagrams in 
	 * @return - an array of the largest group of anagrams contained in the input array
	 */
	public static String[] getLargestAnagramGroup(String[] arr) {
		String[] arrCopy = arr.clone();
		
		/*
		 * perform an insertion sort on the array of strings which compares
		 * them based on the lexigraphical order of their sorted versions, ignoring capitalization.
		 * 
		 * this will sort the strings, as they are, in groups of strings which are anagrams
		 * of eachother.
		 */
		insertionSort(arrCopy, (lhs, rhs)->sort(lhs).toLowerCase().compareTo(sort(rhs).toLowerCase()));
		
		/* 
		 * find the largest group of anagrams in arr by passing arrCopy to the 
		 * getLargestConsecutiveGroupInArray.
		 * Because arrCopy is sorted based on the strings' sorted versions, the
		 * largest consecutive group of equivalent elements in the array will be the largest group of
		 * anagrams contained within arr.
		 * 
		 * use areAnagrams() in the comparator.
		 * if they are anagrams, the comparator returns 0 (meaning that they are equal),
		 * if they are not anagrams, the comparator returns -1 indicating that they are not equal.
		 */
		return getLargestConsecutiveGroupInArray(arrCopy, (lhs, rhs)->areAnagrams(lhs, rhs) ? 0 : -1);
	}
	
	/**
	 * this method returns an array of the largest consecutive group of equivalent
	 * Strings in the array.
	 * 
	 * if the largest group is a group consisting of only one element, the method returns an
	 * empty array.
	 * 
	 * @param arr - the array to search for the largest consecutive group of equivalent strings
	 * @param comparator - the comparator to use for comparing elements in the array
	 * @return - an array of the largest consecutive group of equivalent elements in the input array
	 */
	public static String[] getLargestConsecutiveGroupInArray(String[] arr, Comparator<String> comparator) {
		String[] largestGroup = {};
		
		//the current size of the current group. (the number of elements contained in the group)
		int currentGroupSize = 1;
		//the index of the first element of the current group
		int currentGroupIndex = 0;
		//the size of the largest group. (the number of elements contained in the group)
		int largestGroupSize = 1;
		//the index of the first element of the largest group
		int largestGroupIndex = 0;

		/*
		 * search for the largest group of consecutive and equivalent elements:
		 * if the current element is equal to the previous element, it is a continuation of the current group
		 * if the current element is not equal to the previous element, it is the beginning of a new group
		 */
		for(int i = 1; i < arr.length; ++i) {
			
			//the current group is continuing if this element is equal to the previous element
			if(comparator.compare(arr[i], arr[i-1]) == 0) {
				//increment the current group size
				currentGroupSize++;

				//set the largestGroupIndex to the index of this group, if it is larger than the largest group index
				largestGroupIndex = currentGroupSize > largestGroupSize ? currentGroupIndex : largestGroupIndex;

				//if this group size is larger than the largest, update the largestGroupSize value
				largestGroupSize = Math.max(currentGroupSize, largestGroupSize);
			}
			//this is now a new group
			else {
				//set the new current index, (because this is a new group)
				currentGroupIndex = i;
				currentGroupSize = 1;
			}
			
		}
		
		//if the largest group contains more than one element, fill it.
		if(largestGroupSize > 1) {
			//fill the largest group array with the size and index information of the largest group
			largestGroup = new String[largestGroupSize];
			for(int i = 0; i < largestGroupSize; ++i) {
				//fill this array starting from the largestGroupIndex in the arr
				largestGroup[i] = arr[i + largestGroupIndex];
			}
		}
		
		//if the largest group contains only one element, then return an empty array
		return largestGroup;
	}
	
	/**
	 * This method returns the largest group of anagrams contained in the input
	 * file.
	 * The file is assumed to contain a single word per line.
	 * returns an empty array if the file does not exist or is empty, or does not contain any groups
	 * of anagrams larger than a single element.
	 * 
	 * @param filename - the path to the file to search for anagrams
	 * @return - an array of the largest group of anagrams contained in the file.
	 */
	public static String[] getLargestAnagramGroup(String filename) {
		
		//read all of the words from the file into an ArrayList
		Scanner file = new Scanner(filename); //use a scanner to read from the file.
		ArrayList<String> words = new ArrayList<String>();
		while(file.hasNextLine()) {
			words.add(file.nextLine());
		}
		
		/*
		 * the emptyStringArray will either be used to indicate the type of array for ArrayList.toArray() to return
		 * or it will be returned in case of a container with no anagrams in it.
		 */
		String[] emptyStringArray = new String[0];
		return words.size() > 1 ? getLargestAnagramGroup(words.toArray(emptyStringArray)) : emptyStringArray;
	}
	
}





























