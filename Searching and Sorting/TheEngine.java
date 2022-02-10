package SearchSort;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Random;

public class TheEngine {
	
	static int array [] = new int [100];

	public static void main(String [] args) {
		
		int userChoice;
		Scanner input = new Scanner(System.in);
		int num;
		
		do {
			
			System.out.println("Please enter a choice");
			System.out.println("0. Exit");
			System.out.println("1. Populate Sequentially");
			System.out.println("2. Populate Randomly");
			System.out.println("3. Is Sorted?");
			System.out.println("4. Display");
			System.out.println("5. Shuffle");
			System.out.println("6. Linear Search");
			System.out.println("7. Binary Search");
			System.out.println("8. Bubble Sort");
			System.out.println("9. Selection Sort");
			System.out.println("10. Insertion Sort");
			System.out.println("11. Quick Sort");
			System.out.println("12. Radix Sort");
			System.out.println("13. Merge Sort");
			
			userChoice = input.nextInt();
		
			if(userChoice == 1) {
				
				popSeq();
				
			}
			else if (userChoice == 2) {
				
				popRand();
				
			}
			else if (userChoice == 3) {
				
				boolean check = IsSorted();
				
				if(check)
					System.out.println("The array is sorted");
				else
					System.out.println("The array is not sorted");
			}
			if(userChoice == 4) {
				
				display();
	
					
			}
			else if (userChoice == 5) {
				
				shuffle();
			}
			else if (userChoice == 6) {
				
				System.out.println("What number do you want to search for");
				num = input.nextInt();
				int temp = linearSearch(num);
				
				if(temp == -1)
					System.out.println("The number is not in the array");
				else
					System.out.println("The number is in position " + (temp +1));	
			}
			else if(userChoice == 7) {
				
				System.out.println("What number do you want to search for");
				num = input.nextInt();
				int temp = binarySearch(num);
				
				if(temp == -1)
					System.out.println("The number is not in the array");
				else if(temp == -2)
					System.out.println("The array is not sorted");
				else
					System.out.println("The number is in position " + (temp +1));	
				
			}
			else if(userChoice == 8)
				bubbleSort();
			else if (userChoice == 9)
				selectionSort();
			else if(userChoice == 10) 
				insertionSort();
			else if(userChoice == 11)
				quickSort(0,array.length-1);
			else if(userChoice == 12)
				radixSort();
			else if(userChoice == 13)
				mergeSort(0,(array.length-1));
		
		}while(userChoice != 0);
		
	}
	
	public static void swap(int x, int y) {
		
		int temp = array[x];
		array[x] = array[y];
		array[y] = temp;
	}
	
	public static void popSeq() {
		
		for(int i =0; i <100; i++)
			array[i] = i+1;
	}
	
	public static void popRand() {
		
		for(int i =0; i<100; i++)
			array[i] = (int) (Math.random()*(100) + 1);
		
	}
	
	public static boolean IsSorted() {
		
		boolean check = true;
		
		for(int i =0; i<99; i++) {
			
			if(array[i] > array[i+1]) {
				
				check = false;
				break;
			}
		}
		
		return check;
	}
	
	public static void display() {
		
		int x =0;
		
		for(int i =0; i <100; i++) {
			
			System.out.print(array[i] + "\t");
			x++;
			
			if(x == 10) {
				
				System.out.println();
				x = 0;
			}
		}
	}
	
	public static void shuffle () {
		
		Random rand = new Random();
		
		for( int y =0; y<10; y++)
			for(int i =0; i < 100; i ++) {
				
				int x = rand.nextInt(100);
				swap(x,i);
				
			}
	}
	
	public static int linearSearch(int num) {
		
		int value = 0;
		
		for(int i = 0; i<100; i++) {
			
			if(num == array[i]) {
				value = i;
				break;
			}
			else
				value = -1;
		}
		
		return value;
	}
	
	public static int binarySearch(int num) {
		
		boolean check = IsSorted();
		
		int low = 0;
		int high = 99;
		int mid = 0;
		
		if(check) {
			do {
				mid = (high+low)/2;
				
				if(array[mid] > num)
					high = mid-1;
				else
					low = mid +1;
				
				if((high-low) == 0 && array[mid] != num)
					break;
	
			}while(array[mid] != num);	
		}
		else
			mid = -2;
		return mid;
	}
	
	public static void bubbleSort() {
		
		for(int i = 0; i <99; i++)	
			for(int x =0; x < 99-i; x++) {
				
				if(array[x] > array[x+1]) {
					
					swap(x,x+1);
				}	
			}
	}
	
	public static void selectionSort() {
		
		int temp = 0;
		
		for(int i =0; i <50; i++) {
			
			int minposition = i;
			int maxposition = i;
			
			for(int x = i+1; x<(array.length-i); x++) {
				
				if(array[x] > array[maxposition])
					maxposition = x;
				if(array[x] < array[minposition])
					minposition = x;		
			}
			
			swap(minposition, i);
			
			if(maxposition == i) {
				swap(minposition,array.length-(1+i));
			}
			else
				swap(maxposition, array.length-(1+i));		
		}
	}
	
	public static void insertionSort() {
		
		for(int i = 1; i <100; i++) {
			
			int position = 0;
			int temp = array[i];
			
			for(int x =i-1; x >= 0; x--) {
			
				if(temp < array[x])
					array[x+1] = array[x];
				else {
					array[x] = temp;
					break;
				}
			}
			
		}
	}
	
	public static void quickSort(int lowBound, int highBound) {
		
		if(lowBound < highBound) {
			
			int middle = partition(lowBound, highBound);
	
			quickSort(lowBound, middle -1);
			quickSort(middle+1, highBound);
		
		}
	}
	
	public static int partition(int lowBound, int highBound) {
		
		int position = lowBound;
		
		for(int i = lowBound; i <highBound; i++) {
			
			if(array[i] <= array[highBound]) {
				swap(i,position);
				position++;
			}
		}
		
		swap(position,highBound);
		return position;
	}
	
	public static void radixSort() {
		
		int max = getMax();
		
		for(int i =1; max/i > 0; i *= 10)
			placeSort(i);
	}
	
	public static int getMax() {
		
		int max = array[0];
		
		for(int i=1; i<100; i++)
			if(array[i] > max)
				max = array[i];
		
		return max;
	}
	
	public static void placeSort(int place) {
		
		int returnValue [] = new int [100];
		int temp [] = new int [10];
	
		for(int i =0; i <100; i ++)
			temp[(array[i]/place)%10]++;
		
		for(int i =1; i<10; i++)
			temp[i] = temp[i] + temp[i-1];
		
		for(int i =99; i >=0; i--) {
			returnValue[temp[(array[i]/place)%10]-1] = array[i];
			temp[(array[i]/place)%10]--;
		}
	
		for(int i =0; i<100; i++) 
			array[i] = returnValue[i];		
		
		display();
		
	}
	
	public static void mergeSort(int leftIndex, int rightIndex) {
		
		if(leftIndex < rightIndex) {
			
			int middle = (leftIndex+rightIndex)/2;
			
			int temp1 [] = new int[rightIndex -middle+1];
			int temp2[] = new int[middle-leftIndex];
			
			mergeSort(leftIndex,middle);
			mergeSort(middle+1, rightIndex);
			
			merge(leftIndex, middle, rightIndex);
			
		}
	}
	public static void merge(int leftIndex, int middle, int rightIndex) {
		
		
		int i = rightIndex - middle;
		int j = middle - leftIndex + 1;
		
		int leftArray []= new int [j];
		int rightArray [] = new int[i];
		
		for(int x =0; x <i; x++)
			rightArray[x] = array[middle+1+x];
		for(int x=0; x<j; x++)
			leftArray[x] = array[leftIndex+x];
		
		int k = leftIndex;
		int x = 0,y=0;
		
		while(x<i && y<j) {
			
			if(rightArray[x] > leftArray[y]) {
				array[k] = leftArray[y];
				y++;
				k++;
			}
			else {
				array[k] = rightArray[x];
				x++;
				k++;
			}	
		}
		
		while(y <j) {
			
			array[k] = leftArray[y];
			k++;
			y++;
		}
		while(x<i) {
			
			array[k] = rightArray[x];
			k++;
			x++;
		}
		
	}
	
}