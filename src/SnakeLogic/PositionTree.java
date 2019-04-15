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

        Position parent = start;


            for (int i = 0; i < this.maze.length -1; i++) {
                for (int j = 0; j < this.maze[i].length-1; j++) {

                    addChild(maze[i][j], parent);
                    parent = maze[i][j];

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


                    //find på en bedre måde at lave det her å . de skal udeligeres på en meningsfuld måde
                    //East
                /*
                if (child.equals(maze[parent.getX() + 1][parent.getY()]) && !maze[parent.getX() + 1][parent.getY()].isOccupied()) {

                    parent.setEast(child);
                    marked.add(child);
                    size++;

                    addChild(maze[parent.getEast().getX() + 1]      [parent.getEast().getY()], parent.getEast());
                    addChild(maze[parent.getEast().getX()]          [parent.getEast().getY() - 1], parent.getEast());
                    addChild(maze[parent.getEast().getX()]          [parent.getEast().getY() + 1], parent.getEast());
                    addChild(maze[parent.getEast().getX() - 1]      [parent.getEast().getY()], parent.getEast());
                }

                //West
                if (child.equals(maze[parent.getX() - 1][parent.getY()]) && !maze[parent.getX() - 1][parent.getY()].isOccupied()) {

                    parent.setWest(child);
                    marked.add(child);
                    size++;

                    addChild(maze[parent.getWest().getX() + 1]      [parent.getWest().getY()], parent.getWest());
                    addChild(maze[parent.getWest().getX()]          [parent.getWest().getY() - 1], parent.getWest());
                    addChild(maze[parent.getWest().getX()]          [parent.getWest().getY() + 1], parent.getWest());
                    addChild(maze[parent.getWest().getX() - 1]      [parent.getWest().getY()], parent.getWest());

                }

                //South
                if (child.equals(maze[parent.getX()][parent.getY() + 1]) && !maze[parent.getX()][parent.getY() + 1].isOccupied()) {

                    parent.setSouth(child);
                    marked.add(child);
                    size++;

                    addChild(maze[parent.getSouth().getX() +1 ]     [parent.getSouth().getY()], parent.getSouth());
                    addChild(maze[parent.getSouth().getX()]         [parent.getSouth().getY() - 1], parent.getSouth());
                    addChild(maze[parent.getSouth().getX()]         [parent.getSouth().getY() +1], parent.getSouth());
                    addChild(maze[parent.getSouth().getX() - 1]     [parent.getSouth().getY()], parent.getSouth());

                }

                //North
                if (child.equals(maze[parent.getX()][parent.getY() - 1]) && !maze[parent.getX()][parent.getY() - 1].isOccupied()) {

                    parent.setNorth(child);
                    marked.add(child);
                    size++;

                    addChild(maze[parent.getNorth().getX()+1]     [parent.getNorth().getY()], parent.getNorth());
                    addChild(maze[parent.getNorth().getX()]       [parent.getNorth().getY() -1], parent.getNorth());
                    addChild(maze[parent.getNorth().getX()]       [parent.getNorth().getY()+1], parent.getNorth());
                    addChild(maze[parent.getNorth().getX() - 1]   [parent.getNorth().getY()], parent.getNorth());

                }
                */


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

