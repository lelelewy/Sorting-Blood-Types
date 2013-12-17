/**
* Assignment: Project 4
* Due date: 12/6/13
* Instructor: Dr. DePasquale 
* Submitted by: Leah Lewy
*/

package csc250; 

import java.util.*;
import java.net.*;
import java.io.*;
import java.text.*;

/**
* The Driver class acts as a container for the main method. There is not a
* specific inherent purpose for the class.
* @author Leah Lewy
*/
public class Driver {
	/**
	* All functionality of the Driver application exists within 
	* main method. There are comments within the method that explains the process.
	* @param args Permits functionality of the command line	
	* @throws IOException This exception might be thrown if there are issues in locating the input file.
	*/
	public static void main (String[] args) throws IOException {
		String givenName;
		String surname;
		String streetAddress;
		String city;
		String state;
		int zipCode;
		String blood;
		String bloodType;
		String rhesus;
		DecimalFormat fmt1 = new DecimalFormat("###,##0"); 
		long startTime, bubbleSortTime, selectSortTime, insertSortTime, mergeSortTime, quickSortTime;
		String bubble, selection, insertion, quick, merge;
		final int lineCount = 70000;

		URL myURL = new URL ("http://s3.amazonaws.com/depasquale/datasets/namesWithBloodType.txt");
		Scanner fileScan, lineScan;
		fileScan = new Scanner(myURL.openStream());

		FileWriter fw = new FileWriter("output.txt");
		BufferedWriter bw = new BufferedWriter(fw);
		PrintWriter outFile = new PrintWriter(bw);

		Person[] personList = new Person[lineCount];
		

		int position = 0;

		fileScan.nextLine();
		/** A scanner processes each line of the file, parses it into the variable, and then
		* creates a person object and places it into an array.
		*/
		while(position < lineCount) {
			String line = fileScan.nextLine();
			lineScan = new Scanner (line);
			lineScan.useDelimiter("\\|");

			givenName = lineScan.next();
			surname = lineScan.next();
			streetAddress = lineScan.next();
			city = lineScan.next();
			state = lineScan.next();
			zipCode = lineScan.nextInt();
			blood = lineScan.next();

			 
			if (blood.length()==2){
				bloodType = blood.substring(0, 1);
				rhesus = blood.substring(1);
			}
			else {
				bloodType = blood.substring(0, 2);
				rhesus = blood.substring(2);
			}

			Person p1 = new Person (givenName, surname, streetAddress, city, state, zipCode, bloodType, rhesus); 
			personList[position] = p1;
			
			position++;
		}

		Scanner scan = new Scanner(System.in);
		System.out.println("Enter the number of objects you want to be sorted");
		int sortSize = scan.nextInt();

		outFile.println("Sort Name \t # Comparisons \t # Nanoseconds");
		outFile.println("---------------------------------------------------");

		/** 
		* After prompting the user about how many objects should be sorted, a new array is created
		* for each sort. In this instance, an array is created for the bubble sort and then the bubble sort
		* method is called. The time is taken before and after the sort finishes. A string is made that contains
		* info on the amount of comparisons made and the total time the sort took. After completing, the counter is 
		* reset and the array is nullified. This process is copied for each of the 5 sort methods. 
		*/
		Person[] personListBubble = new Person[sortSize];
		for(int i = 0; i<sortSize; i++) {
			personListBubble[i] = personList[i];
		}
		startTime = System.nanoTime();
		Sorting.bubbleSort(personListBubble);
		bubbleSortTime = System.nanoTime() - startTime;
		bubble = "bubble \t \t" + fmt1.format(Person.getCounter()) + "\t \t" + fmt1.format(bubbleSortTime);
		Person.setCounter(0);
		Arrays.fill(personListBubble, null);

		Person[] personListSelection = new Person[sortSize];
		for(int i = 0; i<sortSize; i++) {
			personListSelection[i] = personList[i];
		}
		startTime = System.nanoTime();
		Sorting.selectionSort(personListSelection);
		selectSortTime = System.nanoTime() - startTime;
		selection = "selection \t" + fmt1.format(Person.getCounter()) + "\t \t" + fmt1.format(selectSortTime);
		Person.setCounter(0);
		Arrays.fill(personListSelection, null);

		Person[] personListInsertion = new Person[sortSize];
		for(int i = 0; i<sortSize; i++) {
			personListInsertion[i] = personList[i];
		}
		startTime = System.nanoTime();
		Sorting.insertionSort(personListInsertion);
		insertSortTime = System.nanoTime() - startTime;
		insertion = ("insertion \t" + fmt1.format(Person.getCounter()) + "\t \t" + fmt1.format(insertSortTime));
		Person.setCounter(0);
		Arrays.fill(personListInsertion, null);

		Person[] personListMerge = new Person[sortSize];
		for(int i = 0; i<sortSize; i++) {
			personListMerge[i] = personList[i];
		}
		startTime = System.nanoTime();
		Sorting.mergeSort(personListMerge);
		mergeSortTime = System.nanoTime() - startTime;
		merge = "merge \t \t" + fmt1.format(Person.getCounter()) + "\t \t" + fmt1.format(mergeSortTime);
		Person.setCounter(0);
		Arrays.fill(personListMerge, null);

		Person[] personListQuick = new Person[sortSize];
		for(int i = 0; i<sortSize; i++) {
			personListQuick[i] = personList[i];
		}
		startTime = System.nanoTime();
		Sorting.quickSort(personListQuick);
		quickSortTime = System.nanoTime() - startTime;
		quick = "quick \t \t" + fmt1.format(Person.getCounter()) + "\t \t" + fmt1.format(quickSortTime);
		Arrays.fill(personListQuick, null);

		
		/** 
		* The primitive type time variables are wrapped so they can be sorted using the sorting class.  
		*/
		Long bubbleSortTime1 = new Long(bubbleSortTime);
		Long quickSortTime1 = new Long(quickSortTime);
		Long mergeSortTime1 = new Long(mergeSortTime);
		Long selectSortTime1 = new Long(selectSortTime);
		Long insertSortTime1 = new Long(insertSortTime);

		/**
		* An array of longs is created for all of the sort times so that the quick sort method can be called.
		* This places all of the times in ascending order. 
		*/
		Long[] longs = {bubbleSortTime1, quickSortTime1, mergeSortTime1, selectSortTime1, insertSortTime1};
		Sorting.quickSort(longs);

		/** 
		* Another array is created that places the sort times in descending order.
		*/
		Long[] reversedLongs = new Long[longs.length];
		int j = 0;
		for (int i = longs.length -1; i >= 0; i--){
    	reversedLongs[j++] = longs[i];
		}

		/**
		* Here, for each sort time, the corresponding string with all the sort information is found 
		* and printed to the output file. 
		*/
		for(int i= 0; i<5; i++){
			if(reversedLongs[i]==bubbleSortTime)
				outFile.println(bubble);
			else if(reversedLongs[i]==selectSortTime)
				outFile.println(selection);
			else if(reversedLongs[i]==insertSortTime)
				outFile.println(insertion);
			else if(reversedLongs[i]==mergeSortTime)
				outFile.println(merge);
			else {
				outFile.println(quick);
			}
		}
		outFile.close();
	}
}