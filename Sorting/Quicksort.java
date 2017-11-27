// MIT License

// Copyright (c) 2017 Quentin Oschatz

package Sorting;

public class Quicksort {

	private int partition(int[] array, int low, int high) {

		int pivot = array[high];
		int i = low - 1;

		for(int j = low; j < high; j++) {

			//If selected element is <= to pivot
			if(array[j] <= pivot) {

				i++;

				//Swap array[i] and array[j]
				int temp = array[i];
				array[i] = array[j];
				array[j] = temp;

			}

		}

		//Swap array[i + 1] and array[high] (or pivot)
		int temp = array[i + 1];
		array[i + 1] = array[high];
		array[high] = temp;

		return i + 1;

	}

	public void quicksort(int[] array, int low, int high) {

		if(low < high) {

			int pivot = partition(array, low, high);

			//Recursively sort elements before partition and after partition
			quicksort(array, low, pivot - 1);
			quicksort(array, pivot + 1, high);

		}

	}

}
