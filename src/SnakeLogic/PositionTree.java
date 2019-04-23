package SnakeLogic;

import SnakeGUI.Position;
import javafx.geometry.Pos;

import java.util.Collections;
import java.util.LinkedList;

/**
 * This class arranges a tree of positions used by the BFS ghost.
 * @param <T>
 */
public class PositionTree<T extends Comparable<T>> {

    Position[][] maze;
    private Position rootNode = null;
    private LinkedList<Position> marked = new LinkedList();
    private int size = 0;
    private Position goal;


    public PositionTree(Position[][] maze, Position rootNode, Position goal) {
        this.maze = maze;
        this.goal = goal;
        addRoot(rootNode);
        addChildrenToAdjacent(rootNode);


    }

    /**
     * Add a root to the tree of Positions
     */
    public boolean addRoot(Position newPosition) {

        if (rootNode == null) {
            rootNode = newPosition;
            return true;
        } else {
            return false;
        }
    }

    /**
     * This method makes a positions a child to another positions and them makes that position its parent, at last it add the child to the marked list.
     * This can only occur if the positions is next to each other in the 2D maze and if they are not occupied.
     *
     * if  x and y of the child and start is above 0
     *      assign which direction the adjacent positions of the "start" position has - this node is to become the parent.
     *          if marked does not contain the to-be child of start
     *              if child is equal to the start/parents north/south/east/west
     *                  set starts north/south/east/west equal to the child position,
     *                  set child parent to be starts position
     *                  add child to the marked list.
     *                      if marked does not contain start
     *                          add start to the marked list.
     *
     * @param child the Position that should become the child.
     * @param start the Position that should become the parent.
     * @return returns the start position.
     */
    public Position addChild (Position child, Position start) {




        if (child.getX() > 0 && child.getY() > 0 && start.getY() > 0 && start.getX() > 0) {


            Position parentWest = maze[start.getX() - 1][start.getY()];
            Position parentEast = maze[start.getX() + 1][start.getY()];
            Position parentNorth = maze[start.getX()][start.getY() - 1];
            Position parentSouth = maze[start.getX()][start.getY() + 1];

            if (!marked.contains(child)) {

                if ((child.equals(parentEast) || child.equals(parentWest) || child.equals(parentSouth) || child.equals(parentNorth)) && !child.isOccupied() && !start.isOccupied() ){

                    if (child.equals(parentEast)){
                        start.setEast(child);
                        child.setParent(start);

                        marked.add(child);

                        if (!marked.contains(start)){
                            marked.add(start);
                        }
                    }

                    if (child.equals(parentWest)){
                        start.setWest(child);
                        child.setParent(start);

                        marked.add(child);

                        if (!marked.contains(start)){
                            marked.add(start);
                        }
                    }

                    if (child.equals(parentNorth)){
                        start.setNorth(child);
                        child.setParent(start);

                        marked.add(child);

                        if (!marked.contains(start)){
                            marked.add(start);
                        }
                    }

                    if (child.equals(parentSouth)){
                        start.setSouth(child);
                        child.setParent(start);

                        marked.add(child);

                        if (!marked.contains(start)){
                            marked.add(start);
                        }
                    }
                }



            }
        }

        return start;
    }

    /**
     * This method recursively adds children to the start Position until the goal is in the marked list.
     *
     *
     *  if x and y of the start startingPosition is above 0
     *      creates a new list "adjacent".
     *      assign directions according to startingPosition
     *          if south/west/east/north is not null and marked does not contain them and they are not occupied
     *              add them to the adjacent list
     *
     *              Sort adjacent according to the cost / weight of each Position
     *
     *                  for pos Position in adjacent
     *                      addChild(startingPosition, pos)
     *
     *                  if marked does not contain goal
     *                      for pos Position in adjacent
     *                          addChildrenToAdjacent(pos)
     *
     * @param startingPosition, the position from which the maze should be mapped by the tree.
     * @return true when the task is done.
     */


    public boolean addChildrenToAdjacent(Position startingPosition) {

            if (startingPosition.getY() > 0 && startingPosition.getX() > 0) {

                LinkedList<Position> adjacent = new LinkedList<>();

                Position west = maze[startingPosition.getX() - 1][startingPosition.getY()];
                Position east = maze[startingPosition.getX() + 1][startingPosition.getY()];
                Position south = maze[startingPosition.getX()][startingPosition.getY() - 1];
                Position north = maze[startingPosition.getX()][startingPosition.getY() + 1];


                if (west != null && !marked.contains(west) && !west.isOccupied()) {
                    adjacent.add(west);
                }
                if (south != null && !marked.contains(south) && !south.isOccupied()) {
                    adjacent.add(south);
                }
                if (east != null && !marked.contains(east) && !east.isOccupied()) {
                    adjacent.add(east);
                }
                if (north != null && !marked.contains(north) && !north.isOccupied()) {
                    adjacent.add(north);
                }


                Collections.sort(adjacent);

                if (!marked.contains(goal)) {
                    for (Position pos : adjacent) {
                        addChild(pos, startingPosition);
                        addChildrenToAdjacent(pos);

                    }

                }
            }

             return true;
            }


    public int getSize() {
        return size;
    }

    public LinkedList<Position> getMarked() {
        return marked;
    }

    public void setMarked(LinkedList<Position> marked) {
        this.marked = marked;
    }
}

