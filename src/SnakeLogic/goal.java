package SnakeLogic;

import SnakeGUI.Position;
import javafx.geometry.Pos;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import sun.misc.PostVMInitHook;

import java.util.LinkedList;

public class Goal implements GameObject {
    private Position position;
    private Color color;
    private Position[][] maze;
    private LinkedList<Position> hasSetCost = new LinkedList<>();
    private int maxCoordinatX;
    private int maxCoordinatY;
    private int checkerValue;


    private Position north;
    private Position east;
    private Position south;
    private Position west;



    public Goal(Color color, int X, int Y, Position[][] maze) {
        this.setPosition(new Position(X,Y));
        this.setColor(color);
        this.maze = maze;

        north = maze[this.position.getX()][this.position.getY() - 1];
        east = maze[this.position.getX() + 1][this.position.getY()];
        south = maze[this.position.getX()][this.position.getY() + 1];
        west = maze[this.position.getX() - 1][this.position.getY()];

        maxCoordinatX = this.maze.length - 2 ;
        maxCoordinatY = this.maze.length -12; // fixer vi serenere

    }





    public void setX(int x) {
        this.getPosition().setX(x);
    }


    public int getY() {
        return this.getPosition().getY();
    }

    public void setY(int y) {
       this.getPosition().setY(y);
    }


    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    @Override
    public void update() {

    }


    public boolean mazeCost(Position goal){

        int cost = 1;
        goal.setCost(0);
        hasSetCost.add(goal);

        north.setCost(1);
        hasSetCost.add(north);

        east.setCost(1);
        hasSetCost.add(east);

        west.setCost(1);
        hasSetCost.add(west);

        south.setCost(1);
        hasSetCost.add(south);

        mazeCost(north, south, west, east, cost);
        return true;
    }

    public void mazeCost(Position north, Position south, Position west, Position east, int cost) {



            if (north.getX() < maxCoordinatX && north.getY() < maxCoordinatY) {
                Position nextNorth = maze[north.getX()][north.getY() - 1];
                expandCost(north, cost, nextNorth);
            }
            if (north.getX() < maxCoordinatX && north.getY() < maxCoordinatY) {
                Position nextSouth = maze[south.getX()][south.getY() + 1];
                expandCost(south, cost, nextSouth);

            }
            if (east.getX() < maxCoordinatX && east.getY() < maxCoordinatY) {
                Position nextEast = maze[east.getX() + 1][east.getY()];
                expandCost(east, cost, nextEast);
            }

            if (west.getX() < maxCoordinatX && west.getY() < maxCoordinatY) {
                Position nextWest = maze[west.getX() - 1][west.getY()];
                expandCost(west, cost, nextWest);
            }
        }

    private void expandCost(Position position, int cost, Position nextPosition) {

       cost++;

        if (    position.getX() < maxCoordinatX && position.getY() < maxCoordinatY &&
                nextPosition.getX() < maxCoordinatX && nextPosition.getY() < maxCoordinatY ) {

                Position left = maze[position.getX()][position.getY()];
                Position right = maze[position.getX() + 1][position.getY()]; // Find ud af en meningsfuldt if statement der sørge for at den ikke går out of bounds
                Position up = maze[position.getX()][position.getY()];        // if (x || y = 0) så stop!
                Position down = maze[position.getX()][position.getY() + 1];


                if (!hasSetCost.contains(nextPosition) && nextPosition.getX() < maxCoordinatX && nextPosition.getY() < maxCoordinatY) {
                    nextPosition.setCost(cost);
                    hasSetCost.add(nextPosition);

                }
                if (!hasSetCost.contains(left) && left.getX() < maxCoordinatX && left.getY() < maxCoordinatY) {
                    left.setCost(cost);
                    hasSetCost.add(left);
                    expandCost(nextPosition, cost, left);

                }
                if (!hasSetCost.contains(right) && right.getX() < maxCoordinatX && right.getY() < maxCoordinatY) {
                    right.setCost(cost);
                    hasSetCost.add(right);
                    expandCost(nextPosition, cost, right);


                }
                if (!hasSetCost.contains(up) && up.getX() < maxCoordinatX && up.getY() < maxCoordinatY) {
                    up.setCost(cost);
                    hasSetCost.add(up);
                    expandCost(nextPosition, cost, up);

                }
                if (!hasSetCost.contains(down) && down.getX() < maxCoordinatX && down.getY() < maxCoordinatY) {
                    down.setCost(cost);
                    hasSetCost.add(down);
                    expandCost(nextPosition, cost, down);
                }



        }

        
    }



    @Override
    public boolean drawObject(GraphicsContext g, double fieldWidth, double fieldHeight) {
        g.setFill(this.getColor());
        g.fillRoundRect(this.getPosition().getX() * fieldWidth, this.getPosition().getY() * fieldHeight, fieldWidth, fieldHeight, 3, 3);
        return true;
    }

    public Position getPosition() {
        return position;
    }
    public void setPosition(Position position) {
        this.position = position;
    }
    public Position[][] getMaze() {
        return maze;
    }
    public void setMaze(Position[][] maze) {
        this.maze = maze;
    }


}
