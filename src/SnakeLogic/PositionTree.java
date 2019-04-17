package SnakeLogic;

import SnakeGUI.Position;

import java.util.LinkedList;

public class PositionTree<T extends Comparable<T>> {

    Position[][] maze;
    private Position rootNode = null;
    private LinkedList<Position> marked = new LinkedList();
    private int size = 0;


    public PositionTree(Position[][] maze, Position rootNode) {
        this.maze = maze;
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

                if (west != null && !marked.contains(west)){
                    adjacent.add(west);
                }
                if (east != null && !marked.contains(east)){
                    adjacent.add(east);
                }
                if (north != null && !marked.contains(north)){
                    adjacent.add(north);
                }
                if (south != null && !marked.contains(south)){
                    adjacent.add(south);
                }


                for (Position pos: adjacent) {
                    addChild(pos,position);
                }
                /* StackOverflow 
                for (Position pos: adjacent) {
                    addChildrenToAdjacent(pos);
                }
                */

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

                            System.out.println(child + " and is East to " + start);
                            if (!marked.contains(start)){
                                marked.add(start);
                            }
                        }

                        if (child.equals(parentWest)){
                            start.setWest(child);
                            child.setParent(start);
                            marked.add(child);

                            System.out.println(child + " and is West to"  + start);
                            if (!marked.contains(start)){
                                marked.add(start);
                            }
                        }

                        if (child.equals(parentNorth)){
                            start.setNorth(child);
                            child.setParent(start);
                            marked.add(child);

                            System.out.println(child + " and is North to " + start);
                            if (!marked.contains(start)){
                                marked.add(start);
                            }
                        }

                        if (child.equals(parentSouth)){
                            start.setSouth(child);
                            child.setParent(start);
                            marked.add(child);

                            System.out.println(child + " and is South to " + start);
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

