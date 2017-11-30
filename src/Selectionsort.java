// MIT License

// Copyright (c) 2017 Quentin Oschatz

public class Selectionsort {

  public void sort(int[] array) {

    int n = array.length;
    int low = 0;
    int min;
    int temp;

    do {

      min = low;
      for(int i = low + 1; i < n; i++) {

        if(array[i] < array[min])
          min = i;

      }

      temp = array[min];
      array[min] = array[low];
      array[low] = temp;

      low++;

    } while(low < n)

  }

}
