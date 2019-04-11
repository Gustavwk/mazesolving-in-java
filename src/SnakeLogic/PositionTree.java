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

        public Position addChild (Position child, Position parent){


        if (child.equals(maze[parent.getX()+1][parent.getY()]) && !maze[parent.getX()+1][parent.getY()].isOccupied()){

            parent.setEast(child);

            System.out.println(parent.getEast() + " is east of " + parent+ "\n");

            addChild(maze[parent.getEast().getX()+1][parent.getEast().getY()], parent.getEast());

            addChild(maze[parent.getEast().getX()][parent.getEast().getY()-1],     parent.getEast());
            addChild(maze[parent.getEast().getX()][parent.getEast().getY()+1],    parent.getEast());
            addChild(maze[parent.getEast().getX()-1][parent.getEast().getY()],    parent.getEast());
            addChild(maze[parent.getEast().getX()+1][parent.getEast().getY()],    parent.getEast());
        }

        if (child.equals(maze[parent.getX()-1][parent.getY()])&& !maze[parent.getX()-1][parent.getY()].isOccupied()){

            parent.setWest(child);

            System.out.println(parent.getWest()+ " is west of " + parent+ "\n");

            addChild(maze[parent.getWest().getX()-1][parent.getWest().getY()], parent.getWest());
            addChild(maze[parent.getWest().getX()][parent.getWest().getY()-1],     parent.getWest());
            addChild(maze[parent.getWest().getX()][parent.getWest().getY()+1],    parent.getWest());
            addChild(maze[parent.getWest().getX()-1][parent.getWest().getY()],    parent.getWest());
            addChild(maze[parent.getWest().getX()+1][parent.getWest().getY()],    parent.getWest());

            }

        if (child.equals(maze[parent.getX()][parent.getY()+1])&& !maze[parent.getX()][parent.getY()+1].isOccupied()){

            parent.setSouth(child);

            System.out.println(parent.getSouth()+ " is south of " + parent+ "\n");

            addChild(maze[parent.getSouth().getX()][parent.getSouth().getY()+1], parent.getSouth());
            addChild(maze[parent.getSouth().getX()][parent.getSouth().getY()-1],     parent.getSouth());
            addChild(maze[parent.getSouth().getX()][parent.getSouth().getY()+1],    parent.getSouth());
            addChild(maze[parent.getSouth().getX()-1][parent.getSouth().getY()],    parent.getSouth());
            addChild(maze[parent.getSouth().getX()+1][parent.getSouth().getY()],    parent.getSouth());

            }

        if (child.equals(maze[parent.getX()][parent.getY()-1])&& !maze[parent.getX()][parent.getY()-1].isOccupied()){

            parent.setNorth(child);

            System.out.println(parent.getNorth()+ " is north of " + parent+ "\n");

            addChild(maze[parent.getNorth().getX()][parent.getNorth().getY()-1],     parent.getNorth());
            addChild(maze[parent.getNorth().getX()][parent.getNorth().getY()+1],    parent.getNorth());
            addChild(maze[parent.getNorth().getX()-1][parent.getNorth().getY()],    parent.getNorth());
            addChild(maze[parent.getNorth().getX()+1][parent.getNorth().getY()],    parent.getNorth());

            }





            return parent;
        }




    }

