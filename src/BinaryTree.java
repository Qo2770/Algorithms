import java.util.Vector;

// MIT License

// Copyright (c) 2017 Quentin Oschatz

public class BinaryTree {

	public static void main(String[] args) {

		Vector<NodeBin> v = new Vector<NodeBin>();

		v.add(new NodeBin(7, 3));
		v.add(new NodeBin(3, 3));
		v.add(new NodeBin(0, 3));
		v.add(new NodeBin(2, 3));
		v.add(new NodeBin(1, 3));
		v.add(new NodeBin(5, 3));

		BinaryTree tree = new BinaryTree(v);

		tree.printTree();


	}

	Vector<NodeBin> tree = new Vector<NodeBin>();

	public BinaryTree(Vector<NodeBin> rootNodes) {

		tree = rootNodes;
		generateMaxHeap();

	}

	public BinaryTree() {}

	/*
	 * A function to add a node to tree
	 * @param key The key of the node
	 * @param value The value of the node
	 *
	 */

	public void addNode(int key, int value) {

		NodeBin n = new NodeBin(key, value);

		tree.addElement(n);
		this.heapify(tree.indexOf(n), tree.size());

	}

	/*
	 * A function to remove a node from tree
	 * @param key The key of the node
	 *
	 */

	public void removeNode(int key) {

		int index = -1;

		for(int i = 0; i < tree.size(); i++) {

			if(tree.get(i).getKey() == key) {

				index = i;
				break;

			}

		}

		tree.remove(index);
		this.heapify(index-1, tree.size());

	}

	/*
	 * Function to regenerate heap after each switch
	 *
	 * @param array The array to be sorted
	 * @param i The element to be heapified
	 * @param len The last element to be heapified (all non-sorted elements)
	 *
	 */

	public void heapify(int i, int len) {

		// Main loop
		while(i <= (len / 2) - 1) {

			// Calculate the index of left child
			int indexChild = ((i+1) * 2) - 1;

			// Check if right child exists
			if(indexChild + 1 <= len - 1) {

				// Check if right child is larger than left child, if yes, choose right child to switch
				if(tree.get(indexChild+1).getVal() > tree.get(indexChild).getVal())
					indexChild++;

			}

			// Check if heapify is necessary
			if(tree.get(i).getVal() < tree.get(indexChild).getVal()) {

				// If yes, switch this element with larger child
				switchElements(i, indexChild);
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

	 public void generateMaxHeap() {

		 // Start from the middle and loop through elements to heapify
		 for(int i = (tree.size() / 2) - 1; i >= 0 ; i--)
			 heapify(i, tree.size());

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

	  public void switchElements(int i, int j) {

		  NodeBin temp = tree.get(i);
		  tree.set(i, tree.get(j));
		  tree.set(j, temp);

	  }

	  /*
	   *
	   * Helper function to print out binary tree
	   *
	   */

	  public void printTree() {

		  // Vars
		  int spaces = 0;
		  int indent;

		  int index;
		  int itemNum;
		  int maxIndex;

		  // Loop through items
		  for(int i = 1; i < tree.size(); i++) {

			  // Calculate indent, index, number of items to print and the highest index
			  indent = (int) Math.abs(Math.pow(-2, -i) * (-16 + Math.pow(2, i)));

			  index = (int) (0.5 * (-2 + Math.pow(2, i)));
			  itemNum = (int) Math.pow(2, i-1);
			  maxIndex = index + itemNum;

			  if(maxIndex >= tree.size())
				  maxIndex = tree.size() - 1;

			  // Add indent
			  for(int j = 0; j < indent; j++)
				  System.out.print(" ");

			  // Loop through current layer of tree
			  for(int k = index; k < maxIndex; k++) {

				  // Print node
				  System.out.print(tree.get(k).getKey());

				  // Print spaces
				  for(int l = 0; l < spaces; l++)
					  System.out.print(" ");

			  }

			  spaces = indent;
			  System.out.println();

		  }

	  }


}

class NodeBin {

	private int key;
	private int value;

	public NodeBin(int key, int value) {

		this.key = key;
		this.value = value;

	}

	public int getVal() {
		return value;
	}

	public int getKey() {
		return key;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public void setKey(int key) {
		this.key = key;
	}

}
