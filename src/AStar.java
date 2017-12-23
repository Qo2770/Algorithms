// MIT License

// Copyright (c) 2017 Quentin Oschatz

import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

// package src;

public class AStar {

	// Cost for straight movement
	public static final int cCost = 75;

	// Cost for diagonal movement
	public static final int cdCost = 140;

	// Tweaking the above values may increase/decrease the efficiency of the algorithm

  public static void main(String[] args) {

	  /*
	   * A1 B1 C1
	   * A2 B2 C2
	   * A3 B3 C3
	   */

	  // Create a map
      Node a1 = new Node("A1", 0, 0);
      Node a2 = new Node("A2", 0, 1);
      Node a3 = new Node("A3", 0, 2);
      Node b1 = new Node("B1", 1, 0);
      Node b2 = new Node("B2", 1, 1);
      Node b3 = new Node("B3", 1, 2);
      Node c1 = new Node("C1", 2, 0);
      Node c2 = new Node("C2", 2, 1);
      Node c3 = new Node("C3", 2, 2);

      // The goal node
      Node goal = c3;

      // Init the edges and gen hCost

      // A1
      a1.adjacencies = new Edge[] {
              new Edge(a2, cCost),
              new Edge(b1, cCost),
              new Edge(b2, cdCost)
      };
      a1.genHCost(goal);

       // A2
      a2.adjacencies = new Edge[] {
              new Edge(a1, cCost),
              new Edge(b1, cdCost),
              new Edge(b2, cCost),
              new Edge(b3, cdCost),
              new Edge(a3, cCost)
      };
       a2.genHCost(goal);

       // A3
      a3.adjacencies = new Edge[] {
              new Edge(a2, cCost),
              new Edge(b2, cdCost),
              new Edge(b3, cCost)
      };
      a3.genHCost(goal);

       // B1
      b1.adjacencies = new Edge[] {
              new Edge(a1, cCost),
              new Edge(a2, cdCost),
              new Edge(b2, cCost),
              new Edge(c2, cdCost),
              new Edge(c1, cCost)
      };
       b1.genHCost(goal);

       // B2
      b2.adjacencies = new Edge[]{
              new Edge(a1, cdCost),
              new Edge(a2, cCost),
              new Edge(a3, cdCost),
              new Edge(b3, cCost),
              new Edge(c3, cdCost),
              new Edge(c2, cCost),
              new Edge(c1, cdCost),
              new Edge(b1, cCost)
      };
      b2.genHCost(goal);

       // B3
      b3.adjacencies = new Edge[]{
              new Edge(a2, cdCost),
              new Edge(a3, cCost),
              new Edge(c3, cCost),
              new Edge(c2, cdCost),
              new Edge(b2, cCost)
      };
      b3.genHCost(goal);

       // C1
      c1.adjacencies = new Edge[]{
              new Edge(b1, cCost),
              new Edge(b2, cdCost),
              new Edge(c2, cCost)
      };
      c1.genHCost(goal);

       // C2
      c2.adjacencies = new Edge[]{
              new Edge(b1, cdCost),
              new Edge(b2, cCost),
              new Edge(b3, cdCost),
              new Edge(c3, cCost),
              new Edge(c1, cCost)
      };
      c2.genHCost(goal);

       // C3
      c3.adjacencies = new Edge[]{
              new Edge(b2, cdCost),
              new Edge(b3, cCost),
              new Edge(c2, cCost)
      };
      c3.genHCost(goal);

      AStar as = new AStar();

      as.path(a1, goal);

      List<Node> path = as.reconstructPath(goal);

      System.out.println("Path: " + path);

  }


  /*
  * The main function of the A* Algorithm. Finds a path between start and goal.
  * Path can be retrieved by calling reconstructPath()
  *
  */

  public void path(Node start, Node goal) {

	  Set<Node> explored = new HashSet<Node>();

      PriorityQueue<Node> queue = new PriorityQueue<Node>(20, new Comparator<Node>() {

    	  //override compare method
	       public int compare(Node i, Node j) {

	          if(i.fScores > j.fScores)
	              return 1;
	          else if (i.fScores < j.fScores)
	              return -1;
	          else
	              return 0;

	       }

      });

      //cost from start
      start.gScores = 0;

      queue.add(start);

      boolean found = false;

      while((!queue.isEmpty()) && (!found)) {

              //the node in having the lowest f_score value
              Node current = queue.poll();

              explored.add(current);

              //goal found
              if(current.value.equals(goal.value))
                      found = true;

              //check every child of current node
              for(Edge e : current.adjacencies) {

                      Node child = e.target;
                      double cost = e.cost;
                      double tempGScores = current.gScores + cost;
                      double tempFScores = tempGScores + child.hScores;


                      /*if child node has been evaluated and
                      the newer f_score is higher, skip*/

                      if((explored.contains(child)) && (tempFScores >= child.fScores))
                          continue;

                      /*else if child node is not in queue or
                      newer f_score is lower*/

                      else if((!queue.contains(child)) || (tempFScores < child.fScores)) {

                              child.parent = current;
                              child.gScores = tempGScores;
                              child.fScores = tempFScores;

                              if(queue.contains(child))
                                  queue.remove(child);

                              queue.add(child);

                      }

              }

      }

  }

  /*
   * A function to reconstruct the path to goal. Used once path is found
   *
   * @param target The node from which to begin working backwards
   *
   */
  	public List<Node> reconstructPath(Node target) {

  		List<Node> path = new ArrayList<Node>();

		for(Node node = target; node != null; node = node.parent)
		  path.add(node);

		Collections.reverse(path);

		return path;

	}

  	/*
  	  * A function to determine the heuristic cost between a node and the goal. Supports horizontal, vertical and diagonal movement.
  	  *
  	  * @param current The current node
  	  * @param goal The goal node
  	  *
  	  */

  	  public static int getHeuristicCost(Node current, Node goal) {

  	    int d_max = Math.max(Math.abs(current.x - goal.x), Math.abs(current.y - goal.y));
  	    int d_min = Math.min(Math.abs(current.x - goal.x), Math.abs(current.y - goal.y));

  	    return cdCost + cCost * (d_max - d_min);

  	  }

}

class Node{

    public final String value;

    public double gScores;
    public double hScores;
    public double fScores = 0;

    public int x;
    public int y;

    public Edge[] adjacencies;
    public Node parent;

    public Node(String val, int x, int y) {

        value = val;

        this.x = x;
        this.y = y;

    }

    public void genHCost(Node goal) {

    	hScores = AStar.getHeuristicCost(this, goal);

    }

    public String toString() {

        return value;

    }

}

class Edge {

    public final double cost;
    public final Node target;

    public Edge(Node targetNode, double costVal) {

        target = targetNode;
        cost = costVal;

    }

}
