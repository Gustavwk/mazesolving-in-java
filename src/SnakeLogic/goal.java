package SnakeLogic;

import SnakeGUI.Position;
import javafx.geometry.Pos;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import sun.misc.PostVMInitHook;

import java.util.LinkedList;

public class goal implements GameObject {
    private Position position;
    private Color color;
    private Position[][] maze;
    private LinkedList<Position> hasSetCost = new LinkedList<>();
    private int maxCoordinat;
    private int checkerValue;


    private Position north;
    private Position east;
    private Position south;
    private Position west;



    public goal(Color color, int X, int Y, Position[][] maze) {
        this.setPosition(new Position(X,Y));
        this.setColor(color);
        this.maze = maze;

        north = maze[this.position.getX()][this.position.getY() - 1];
        east = maze[this.position.getX() + 1][this.position.getY()];
        south = maze[this.position.getX()][this.position.getY() + 1];
        west = maze[this.position.getX() - 1][this.position.getY()];

        maxCoordinat = this.maze.length - 2;

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

        goal.setCost(cost);

        north.setCost(1);

        east.setCost(1);

        west.setCost(1);

        south.setCost(1);


        mazeCost(north,south,west,east,cost);
        System.out.println("CHECKER VAL: " + checkerValue);


        return true;
    }

    public boolean mazeCost(Position north, Position south, Position west, Position east, int cost){



    if (north.getX() < maxCoordinat && north.getX() < maxCoordinat) {
        Position nextNorth = maze[north.getX()][north.getY() - 1];
        expandCost(north, cost, nextNorth);

    }

    if (north.getX() < maxCoordinat && north.getX() < maxCoordinat) {
        Position nextSouth = maze[south.getX()][south.getY() + 1];
        expandCost(south, cost, nextSouth);


    }
    if (east.getX() < maxCoordinat && east.getX() < maxCoordinat) {
        Position nextEast = maze[east.getX() + 1][east.getY()];
        expandCost(east, cost, nextEast);

    }

    if (west.getX() < maxCoordinat && west.getX() < maxCoordinat) {

        Position nextWest = maze[west.getX() - 1][west.getY()];
        expandCost(west, cost, nextWest);
    }

      return true;
    }

    private void expandCost(Position position, int cost, Position nextPosition) {
        cost++;
        Position left = maze[position.getX()-1][position.getY()];
        Position right = maze[position.getX()+1][position.getY()];

        if(nextPosition.getCost() == 0 ){

            nextPosition.setCost(cost);
            left.setCost(cost);
            right.setCost(cost);

        }
while (cost < 10) {
    expandCost(nextPosition, cost, left);
    expandCost(nextPosition, cost, right);
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
