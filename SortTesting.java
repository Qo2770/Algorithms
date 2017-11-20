// MIT License

// Copyright (c) 2017 Quentin Oschatz

public class SortTesting {
	
	public static void main(String[] args) {
		
		// Disired size of array to be sorted
		int size = 30;

		RandomArray ra = new RandomArray();
		
		// Create new randomized array
		int[] random = ra.random_array(size);
		
		// Create a class of the wanted sorting algorithm
		Quicksort qs = new Quicksort();
		
		// Time the algorithm
		long startTime = System.nanoTime();
		
		// Sort array
		qs.quicksort(random, 0, size - 1);
		
		// Calculate elapsed time
		long elapsedTime = System.nanoTime() - startTime;
		
		System.out.println("Elapsed time: " + (elapsedTime / 1000) + "ms");
		
		// Output sorted array
		for(int i = 0; i < random.length; i++) {
			
			if(i == random.length - 1)
				System.out.println(random[i]);
			else
				System.out.print(random[i] + ", ");
			
		}
		
	}
	
}

class RandomArray {
	
	// Fill given array with random numbers between 1 and 100
	public int[] random_array(int size) {
		
		int[] array = new int[size];
		
		for(int i = 0; i < size; i++) {
			
			array[i] = (int) (Math.random() * 100);
			
		}
		
		return array;
		
	}
	
}
