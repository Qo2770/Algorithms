// MIT License

// Copyright (c) 2017 Quentin Oschatz

public class Insertionsort {

  public void insertionsort(int[] array) {

    for(int i = 2; i < array.length; i++) {

      int currentVal = array[i];
      int j = i;

      while(array[j-1] < currentVal) {
        array[j] = array[j-1];
        j--;
      }

      array[j] = currentVal;

    }

  }

}
