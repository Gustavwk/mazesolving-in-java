package SnakeLogic;

import SnakeGUI.Position;
import javafx.geometry.Pos;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class goal implements GameObject {
    private Position position;
    private Color color;
    private Position[][] maze;

    Position north = maze[this.position.getX()][this.position.getY() - 1];
    Position east = maze[this.position.getX() + 1][this.position.getY()];
    Position south = maze[this.position.getX()][this.position.getY() + 1];
    Position west = maze[this.position.getX() - 1][this.position.getY()];


    public goal(Color color, int X, int Y, Position[][] maze) {
        this.setPosition(new Position(X,Y));
        this.setColor(color);
        this.maze = maze;

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

        int cost = 0;

        goal.setCost(cost);
        this.north.setCost(this.north.getCost()+1);
        this.east.setCost(this.north.getCost()+1);
        this.west.setCost(this.north.getCost()+1);
        this.south.setCost(this.north.getCost()+1);
        cost++;
        return true;
    }

    public boolean mazeCost(Position goal, int cost){

        goal.setCost(cost);
        this.north.setCost(this.north.getCost()+1);
        this.east.setCost(this.north.getCost()+1);
        this.west.setCost(this.north.getCost()+1);
        this.south.setCost(this.north.getCost()+1);
        return true;
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
