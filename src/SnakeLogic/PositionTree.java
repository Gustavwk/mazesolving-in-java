package SnakeLogic;

import SnakeGUI.Position;

import java.util.LinkedList;

public class PositionTree<T extends Comparable<T>> {

    Position[][] maze;
    private Position rootNode = null;
    private LinkedList<Position> marked = new LinkedList();
    private int size = 0;


    public PositionTree(Position[][] maze, Position root) {
        this.maze = maze;

        addRoot(root);
        addChild(maze[root.getX()-1][root.getY()],root);
        addChild(maze[root.getX()+1][root.getY()],root);
        addChild(maze[root.getX()][root.getY()-1],root);
        addChild(maze[root.getX()][root.getY()+1],root);
    }

    public boolean initTree(Position start) {

        


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

        public Position addChild (Position child, Position parent) {


            if (child.getX() > 0 && child.getY() > 0 && parent.getY() > 0 && parent.getX() > 0) {


                Position parentWest = maze[parent.getX() - 1][parent.getY()];
                Position parentEast = maze[parent.getX() + 1][parent.getY()];
                Position parentNorth = maze[parent.getX()][parent.getY() - 1];
                Position parentSouth = maze[parent.getX()][parent.getY() + 1];

                if (!marked.contains(child)) {


                    if ((child.equals(parentEast) || child.equals(parentWest) || child.equals(parentSouth) || child.equals(parentNorth)) && !child.isOccupied() ){

                        if (child.equals(parentEast)){
                            parent.setEast(child);
                            child.setParent(parent);
                            marked.add(child);
                            System.out.println(child + " East set to " + parent);
                        }
                        if (child.equals(parentWest)){
                            parent.setWest(child);
                            child.setParent(parent);
                            marked.add(child);
                            System.out.println(child + " West set to"  + parent);
                        }
                        if (child.equals(parentNorth)){
                            parent.setNorth(child);
                            child.setParent(parent);
                            marked.add(child);
                            System.out.println(child + " Nort set to " + parent);
                        }
                        if (child.equals(parentSouth)){
                            parent.setSouth(child);
                            child.setParent(parent);
                            marked.add(child);
                            System.out.println(child + " South set to " + parent);
                        }
                    }



                }
            }

                return parent;
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

