package SnakeLogic;

import SnakeGUI.Position;

import java.util.Collections;
import java.util.LinkedList;

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

    public boolean addChildrenToAdjacent(Position position) {

            if (position.getY() > 0 && position.getX() > 0) {

                LinkedList<Position> adjacent = new LinkedList<>();

                Position west = maze[position.getX() - 1][position.getY()];
                Position east = maze[position.getX() + 1][position.getY()];
                Position south = maze[position.getX()][position.getY() - 1];
                Position north = maze[position.getX()][position.getY() + 1];




                if (south != null && !marked.contains(south) && !south.isOccupied()){
                    adjacent.add(south);
                }
                if (west != null && !marked.contains(west) && !west.isOccupied()){
                    adjacent.add(west);
                }
                if (east != null && !marked.contains(east) && !east.isOccupied()){
                    adjacent.add(east);
                }
                if (north != null && !marked.contains(north) && !north.isOccupied()){
                    adjacent.add(north);
                }


                Collections.sort(adjacent);

                    for (Position pos : adjacent) {
                        addChild(pos, position);
                    }


                if (!marked.contains(goal)){
                    for (Position pos : adjacent) {
                        addChildrenToAdjacent(pos);
                    }
                }


        }






        return true;
    }




    public boolean addRoot(Position newPosition) {

        if (rootNode == null) {
            rootNode = newPosition;
            return true;
        } else {
            return false;
        }
    }

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

