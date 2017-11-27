// MIT License

// Copyright (c) 2017 Quentin Oschatz

package Sorting;

public class BinaryTree {

    Node root;

    public BinaryTree(int rootKey, String rootName) {

        root = new Node(rootKey, rootName);

    }

    public BinaryTree() {

        root = new Node(0, null);

    }

    public void addNode(int key, String name) {

        Node newNode = new Node(key, name);

        Node focusNode = this.root;

        Node parent;

        while(true) {

            parent = focusNode;

            if (key < focusNode.getKey()) {

                focusNode = focusNode.getLeft();

                if(focusNode == null) {

                    newNode.setParent(parent);
                    parent.setLeft(newNode);
                    return;

                }

            } else {

                focusNode = focusNode.getRight();

                if(focusNode == null) {

                  newNode.setParent(parent);
                  parent.setRight(newNode);
                  return;

                }

            }

        }

    }

    public void inOrderTraverseTree(Node focusNode) {

        if (focusNode != null) {

            // Traverse the left node
            inOrderTraverseTree(focusNode.getLeft());

            // Visit the currently focused on node
            System.out.println(focusNode);

            // Traverse the right node
            inOrderTraverseTree(focusNode.getRight());

        }

    }

    public void postOrderTraverseTree(Node focusNode) {

  		if (focusNode != null) {

  			postOrderTraverseTree(focusNode.getLeft());
  			postOrderTraverseTree(focusNode.getRight());

  			System.out.println(focusNode);

  		}

  	}

    public Node findNode(int key) {

		// Start at the top of the tree

		Node focusNode = root;

		// While we haven't found the Node
		// keep looking

		while (focusNode.getKey() != key) {

			// If we should search to the left

			if (key < focusNode.getKey()) {

				// Shift the focus Node to the left child

				focusNode = focusNode.getLeft();

			} else {

				// Shift the focus Node to the right child

				focusNode = focusNode.getRight();

			}

			// The node wasn't found

			if (focusNode == null)
				return null;

		}

		return focusNode;

	}

}

class Node {
    private int key;
    private Node left;
    private Node right;
    private String name;
    private Node parent;

    Node (int key, String name) {
        this.key = key;
        right = null;
        left = null;
        this.name = name;
    }

    public void setKey(int key) {
        this.key = key;
    }

    public int getKey() {
        return key;
    }

    public void setLeft(Node left) {
        this.left = left;
    }

    public Node getLeft() {
        return left;
    }

    public void setRight(Node right) {
        this.right = right;
    }

    public Node getRight() {
        return right;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public String toString() {
    	return "Node \"" + name + "\" (Key: " + key + ").";
    }

    public void setParent(Node parentNode) {
      parent = parentNode;
    }

    public Node getParent() {
      return parent;
    }

}
