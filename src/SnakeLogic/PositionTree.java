package SnakeLogic;

import SnakeGUI.Position;

public class PositionTree<T extends Comparable<T>> {

    Position[][] maze;
    public Position rootNode = null;


    public PositionTree(Position[][] maze){
        this.maze = maze;
    }



    public boolean add(Position newPosition) {
        add(newPosition, rootNode);
        System.out.println(rootNode);
        return true;
    }

    public Position add(Position addedPosition, Position root) {

        if (root == null) {

            return (addedPosition);

        }

        if (addedPosition.equals(maze[root.getX()+1][root.getY()])) {
            root.setEast((add(addedPosition, root.getEast()))); //east

        } else if (addedPosition.equals(maze[root.getX()-1][root.getY()])) {
            root.setEast((add(addedPosition, root.getEast()))); //west


        } else if (addedPosition.equals(maze[root.getX()][root.getY()+1])){
            root.setEast((add(addedPosition, root.getEast()))); //south

        } else if ((addedPosition.equals(maze[root.getX()][root.getY()-1]))){
            root.setEast((add(addedPosition, root.getEast()))); //north

        }
        return root;
    }




}
