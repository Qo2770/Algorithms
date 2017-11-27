// MIT License

// Copyright (c) 2017 Quentin Oschatz

package Sorting;

public class Heapsort {

    public heapsort(int[] array, int count) {

      //BinaryTree tree = new BinaryTree();
      buildMaxHeap(array, count);

      int end = count - 1;
      int temp;

      while(end > 0) {

        temp = array[end];
        array[end] = array[0];
        array[0] = temp;

        end--;

        siftUp(array, 0, end);

      }

    }

    public buildMaxHeap(int[] array, count) {

      int end = 1;

      while(end < count) {

        siftUp(array, 0, end);
        end++;

      }

    }

    public void siftUp(int[] array, int start, int end) {

      int iParent = ((end - start))-1)/2;

      int child = end;
      int temp;
      while(child > start) {

        if(array[iParent] < array[child]) {

          temp = array[iParent];
          array[iParent] = array[child];
          array[child] = temp;

          child = iParent;

        } else {

          return;

        }

      }

    }

}
