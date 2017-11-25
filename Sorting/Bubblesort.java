package Sorting;

public class Bubblesort {
	
	public void bubblesort(int[] array) {
		
		// Check if sort is actually still sorting
		boolean switched = false;
		
		// Temporary value used for switching elements
		int temp;
		
		// Main loop
		while(true) {
			
			// In each loop, move through array
			for(int i = 1; i < array.length; i++) {
				
				// If previous element is smaller than current element, switch them
				if(array[i - 1] > array[i]) {
					
					// Switch array[i - 1] and array[i]
					temp = array[i];
					array[i] = array[i - 1];
					array[i - 1] = temp;
					
					// Confirm that a switch was made
					switched = true;
					
				}
				
			}
			
			// If nothing has been switched, the array is sorted
			if(!switched) 
				break;
			
			switched = false;
			
		}
		
	}
	
}
