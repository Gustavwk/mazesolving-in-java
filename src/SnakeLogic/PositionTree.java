package SnakeLogic;

import SnakeGUI.Position;

public class PositionTree<T extends Comparable<T>> {

    Position[][] maze;
    public Position rootNode = null;


    public PositionTree(Position[][] maze) {
        this.maze = maze;
    }


    public boolean addRoot(Position newPosition) {

        if (rootNode == null) {
            rootNode = newPosition;
            return true;
        } else {
            return false;
        }
    }

        public Position addChild (Position addedPosition, Position parent){

            if (addedPosition.equals(maze[parent.getX() + 1][parent.getY()])) {
                parent.setEast((addChild(addedPosition, parent.getEast()))); //east

            } else if (addedPosition.equals(maze[parent.getX() - 1][parent.getY()])) {
                parent.setEast((addChild(addedPosition, parent.getEast()))); //west


            } else if (addedPosition.equals(maze[parent.getX()][parent.getY() + 1])) {
                parent.setEast((addChild(addedPosition, parent.getEast()))); //south

            } else if ((addedPosition.equals(maze[parent.getX()][parent.getY() - 1]))) {
                parent.setEast((addChild(addedPosition, parent.getEast()))); //north

            }
            return parent;
        }


    }

