package SnakeLogic;

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
    private LinkedList<Position> availablePositions = new LinkedList();
    private boolean impossible = false;



    public PositionTree(Position[][] maze, Position rootNode, Position goal) {
        this.maze = maze;
        this.goal = goal;

        addAvailablePositions(maze);
        addRoot(rootNode);
        addChildrenToAdjacent(rootNode);

        if (!marked.contains(goal)){
            impossible = true;
            size = marked.size();
        }



    }

    private void addAvailablePositions(Position[][] maze) {
        for (int i = 0; i <maze.length ; i++) {
            for (int j = 0; j <maze[i].length ; j++) {
                if (!maze[i][j].isOccupied()){
                    availablePositions.add(maze[i][j]);
                }
            }
        }
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
     * if  x and y of the child and parent is above 0
     *      assign which direction the adjacent positions of the "parent" position has - this node is to become the parent.
     *
     *              if child is equal to the parent/parents north/south/east/west
     *
     *                     set parent north/south/east/west equal to the child position,
     *                     set child parent to be parents position
     *                     add child to the marked list.
     *
     *                      if marked does not contain parent
     *                          add parent to the marked list.
     *
     * @param child the Position that should become the child.
     * @param parent the Position that should become the parent.
     * @return returns the parent position.
     */

    public Position addChild (Position child, Position parent) {




        if (child.getX() > 0 && child.getY() > 0 && parent.getY() > 0 && parent.getX() > 0) {


            Position parentWest = maze[parent.getX() - 1][parent.getY()];
            Position parentEast = maze[parent.getX() + 1][parent.getY()];
            Position parentNorth = maze[parent.getX()][parent.getY() - 1];
            Position parentSouth = maze[parent.getX()][parent.getY() + 1];



                if ((child.equals(parentEast) || child.equals(parentWest) || child.equals(parentSouth) || child.equals(parentNorth)) && !child.isOccupied() && !parent.isOccupied()){

                    if (child.equals(parentEast)){
                        parent.setEast(child);
                        child.setParent(parent);

                        if (!marked.contains(child)){
                        marked.add(child);
                        }

                        if (!marked.contains(parent)){
                            marked.add(parent);
                        }
                    }

                    if (child.equals(parentWest)){
                        parent.setWest(child);
                        child.setParent(parent);

                        if (!marked.contains(child)){
                            marked.add(child);
                        }

                        if (!marked.contains(parent)){
                            marked.add(parent);
                        }
                    }

                    if (child.equals(parentNorth)){
                        parent.setNorth(child);
                        child.setParent(parent);

                        if (!marked.contains(child)){
                            marked.add(child);
                        }

                        if (!marked.contains(parent)){
                            marked.add(parent);
                        }
                    }

                    if (child.equals(parentSouth)){
                        parent.setSouth(child);
                        child.setParent(parent);

                        if (!marked.contains(child)){
                            marked.add(child);
                        }

                        if (!marked.contains(parent)){
                            marked.add(parent);
                        }
                    }
                }



            }


        return parent;
    }

    /**
     * This method recursively adds children to the start Position until the goal is in the marked list.
     *
     *
     *  if x and y of the start startingPosition is above 0
     *
     *     creates a new list "adjacent".
     *      assign directions according to startingPosition
     *
     *         if south/west/east/north is not null and marked does not contain them and they are not occupied
     *              add them to the adjacent list
     *
     *              Sort adjacent according to the cost / weight of each Position to avoid "left-turning" and increase accuracy/efficiency
     *
     *                  for pos Position in adjacent
     *                      addChild(startingPosition, pos)
     *
     *                  if marked does not contain goal - this is done to avoid endless children/parent adding & reduce inefficiency
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

              if (!marked.contains(this.goal)) {
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

    public LinkedList<Position> getAvailablePositions() {
        return availablePositions;
    }

    public void setAvailablePositions(LinkedList<Position> availablePositions) {
        this.availablePositions = availablePositions;
    }

    public boolean isImpossible() {
        return impossible;
    }

    public void setImpossible(boolean impossible) {
        this.impossible = impossible;
    }
}

