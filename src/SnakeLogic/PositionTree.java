package SnakeLogic;

import SnakeGUI.Position;

import java.util.LinkedList;

public class PositionTree<T extends Comparable<T>> {

    Position[][] maze;
    public Position rootNode = null;
    public LinkedList<Position> marked = new LinkedList();
    public int size = 0;


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


                if (child.equals(maze[parent.getX() + 1][parent.getY()]) && !maze[parent.getX() + 1][parent.getY()].isOccupied()) {

                    parent.setEast(child);
                    marked.add(child);
                    size++;

                    System.out.println(parent.getEast() + " is east of " + parent + "\n");

                    addChild(maze[parent.getEast().getX() + 1][parent.getEast().getY()], parent.getEast());

                    addChild(maze[parent.getEast().getX()][parent.getEast().getY() - 1], parent.getEast());
                    addChild(maze[parent.getEast().getX()][parent.getEast().getY() + 1], parent.getEast());
                    addChild(maze[parent.getEast().getX() - 1][parent.getEast().getY()], parent.getEast());
                    addChild(maze[parent.getEast().getX() + 1][parent.getEast().getY()], parent.getEast());
                }

                if (child.equals(maze[parent.getX() - 1][parent.getY()]) && !maze[parent.getX() - 1][parent.getY()].isOccupied()) {

                    parent.setWest(child);
                    marked.add(child);
                    size++;

                    System.out.println(parent.getWest() + " is west of " + parent + "\n");

                    addChild(maze[parent.getWest().getX() - 1][parent.getWest().getY()], parent.getWest());
                    addChild(maze[parent.getWest().getX()][parent.getWest().getY() - 1], parent.getWest());
                    addChild(maze[parent.getWest().getX()][parent.getWest().getY() + 1], parent.getWest());
                    addChild(maze[parent.getWest().getX() - 1][parent.getWest().getY()], parent.getWest());
                    addChild(maze[parent.getWest().getX() + 1][parent.getWest().getY()], parent.getWest());

                }

                if (child.equals(maze[parent.getX()][parent.getY() + 1]) && !maze[parent.getX()][parent.getY() + 1].isOccupied()) {

                    parent.setSouth(child);
                    marked.add(child);
                    size++;

                    System.out.println(parent.getSouth() + " is south of " + parent + "\n");

                    addChild(maze[parent.getSouth().getX()][parent.getSouth().getY() + 1], parent.getSouth());
                    addChild(maze[parent.getSouth().getX()][parent.getSouth().getY() - 1], parent.getSouth());
                    addChild(maze[parent.getSouth().getX()][parent.getSouth().getY() + 1], parent.getSouth());
                    addChild(maze[parent.getSouth().getX() - 1][parent.getSouth().getY()], parent.getSouth());
                    addChild(maze[parent.getSouth().getX() + 1][parent.getSouth().getY()], parent.getSouth());

                }

                if (child.equals(maze[parent.getX()][parent.getY() - 1]) && !maze[parent.getX()][parent.getY() - 1].isOccupied()) {

                    parent.setNorth(child);
                    marked.add(child);
                    size++;
                    System.out.println(parent.getNorth() + " is north of " + parent + "\n");

                    addChild(maze[parent.getNorth().getX()][parent.getNorth().getY() - 1], parent.getNorth());
                    addChild(maze[parent.getNorth().getX()][parent.getNorth().getY() + 1], parent.getNorth());
                    addChild(maze[parent.getNorth().getX() - 1][parent.getNorth().getY()], parent.getNorth());
                    addChild(maze[parent.getNorth().getX() + 1][parent.getNorth().getY()], parent.getNorth());

                }


            }

            return parent;
        }

    public int getSize() {
        return size;
    }

}

