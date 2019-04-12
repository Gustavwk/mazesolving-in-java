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



    public boolean addRoot(Position newPosition) {

        if (rootNode == null) {
            rootNode = newPosition;
            return true;
        } else {
            return false;
        }
    }

        public Position addChild (Position child, Position parent){
            if(!marked.contains(child)) {

                    //East
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


            }

            return parent;
        }

    public int getSize() {
        return size;
    }

}

