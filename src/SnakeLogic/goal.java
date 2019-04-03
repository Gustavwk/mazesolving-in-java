package SnakeLogic;

import SnakeGUI.Position;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Goal implements GameObject {
    private Position position;
    private Color color;
    private Position[][] maze;
    public LinkedList<Position> hasSetCost = new LinkedList<>();
    public ArrayList<Item> items;
    private int maxCoordinateX;
    private int maxCoordinateY;
    private int checkerValue;



    private Position north;
    private Position east;
    private Position south;
    private Position west;



    public Goal(Color color, int X, int Y, Position[][] maze, List item) {
        this.setPosition(new Position(X,Y));
        this.setColor(color);
        this.maze = maze;

        north = maze[this.position.getX()][this.position.getY() - 1];
        east = maze[this.position.getX() + 1][this.position.getY()];
        south = maze[this.position.getX()][this.position.getY() + 1];
        west = maze[this.position.getX() - 1][this.position.getY()];

        items = (ArrayList<Item>) item;

        maxCoordinateX = this.maze.length - 2 ;
        maxCoordinateY = this.maze.length -12; // fixer vi serenere

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
        setMarkedTile(north);
        hasSetCost.add(north);

        east.setCost(1);
        hasSetCost.add(east);
        setMarkedTile(east);

        west.setCost(1);
        hasSetCost.add(west);
        setMarkedTile(west);

        south.setCost(1);
        hasSetCost.add(south);
        setMarkedTile(south);

        mazeCost(north, south, west, east, cost);
        return true;
    }

    public void mazeCost(Position north, Position south, Position west, Position east, int cost) {




                Position nextNorth = maze[north.getX()][north.getY() - 1];
                expandCost(north, cost, nextNorth);


                Position nextSouth = maze[south.getX()][south.getY() + 1];
                expandCost(south, cost, nextSouth);



                Position nextEast = maze[east.getX() + 1][east.getY()];
                expandCost(east, cost, nextEast);



                Position nextWest = maze[west.getX() - 1][west.getY()];
                expandCost(west, cost, nextWest);

        }

    private void expandCost(Position position, int cost, Position nextPosition) {

        cost++;

        if (position.getX() < maxCoordinateX && position.getY() < maxCoordinateY &&
                nextPosition.getX() < maxCoordinateX && nextPosition.getY() < maxCoordinateY &&
                position.getX() > -1 && position.getY() > -1) {

            Position left = maze[position.getX() - 1][position.getY()];
            Position right = maze[position.getX() + 1][position.getY()];    // Find ud af en meningsfuldt if statement der sørge for at den ikke går out of bounds
            Position up = maze[position.getX()][position.getY() - 1];         // if (x || y = 0) så stop!
            Position down = maze[position.getX()][position.getY() + 1];

            if (left.getX() > 0 && left.getY() > 0 &&
                    right.getX() > 0 && right.getY() > 0 &&
                    up.getX() > 0 && up.getY() > 0 &&
                    down.getX() > 0 && down.getY() > 0) {

                Position leftNext = maze[left.getX() - 1][left.getY()];
                Position rightNext = maze[right.getX() + 1][right.getY()];
                Position upNext = maze[up.getX()][up.getY() - 1];
                Position downNext = maze[down.getX()][down.getY() + 1];


                if (!hasSetCost.contains(left)) {
                    left.setCost(cost);
                    //setMarkedTile(left);
                    hasSetCost.add(left);
                    expandCost(left, cost, leftNext);


                }
                if (!hasSetCost.contains(right)) {
                    right.setCost(cost);
                    //setMarkedTile(right);
                    hasSetCost.add(right);
                    expandCost(right, cost, rightNext);



                }
                if (!hasSetCost.contains(up)) {
                    up.setCost(cost);
                    //setMarkedTile(up);
                    hasSetCost.add(up);
                    expandCost(up, cost, upNext);


                }
                if (!hasSetCost.contains(down)) {
                    down.setCost(cost);
                    //setMarkedTile(down);
                    hasSetCost.add(down);
                    expandCost(down, cost, downNext);

                }
            }


        }


    }

    public void setMarkedTile(Position position){
        items.add(new Tile(position.getX(), position.getY(), Color.DARKRED));
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
