// MIT License

// Copyright (c) 2017 Quentin Oschatz

public class Heapsort {

  public void heapsort(int[] array) {

    generateMaxHeap(array);

    for(int i = array.length - 1; i > 0; i--) {

    	switchElements(array, i, 0);
    	heapify(array, 0, i);

    }

  }

  /*
   * Function to regenerate heap after each switch
   *
   * @param array The array to be sorted
   * @param i The element to be heapified
   * @param len The last element to be heapified (all non-sorted elements)
   *
   */

  private void heapify(int[] array, int i, int len) {

	  // Main loop
	  while(i <= (len / 2) - 1) {

		  // Calculate the index of left child
		  int indexChild = ((i+1) * 2) - 1;

		  // Check if right child exists
		  if(indexChild + 1 <= len - 1) {

			  // Check if right child is larger than left child, if yes, choose right child to switch
			  if(array[indexChild+1] > array[indexChild])
				  indexChild++;

		  }

		  // Check if heapify is necessary
		  if(array[i] < array[indexChild]) {

			  // If yes, switch this element with larger child
			  switchElements(array, i, indexChild);
			  i = indexChild;

		  } else break; // No change necessary

	  }

  }
  /*
   * Function to generate inital heap
   *
   *@param array The array to generate a heap from
   *
   */

  private void generateMaxHeap(int[] array) {

	  // Start from the middle and loop through elements to heapify
	  for(int i = (array.length / 2) - 1; i >= 0 ; i--)
		  heapify(array, i, array.length);

  }

  /*
   *
   * Helper function which switches elements in array
   *
   * @param array The array in which to sort
   * @param i The first element to be switched
   * @param j The second element to be switched
   *
   */

  private void switchElements(int[] array, int i, int j) {

	  int temp = array[i];
	  array[i] = array[j];
	  array[j] = temp;

  }

}
