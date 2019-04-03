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

        maxCoordinateX = this.maze.length -1 ;
        maxCoordinateY = this.maze.length -11; // fixer vi serenere

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
    public boolean initMazeCost(Position goal, Position[][]maze){
        for (int i = 0; i <maze.length ; i++) {
            for (int j = 0; j <maze[i].length ; j++) {
                maze[i][j].setCost(calculateDistance(goal,maze[i][j]));
            }
        }
        return true;
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

    private double calculateDistance(Position posOne, Position posTwo) {

        return Math.sqrt(               ((posTwo.getX() - posOne.getX()) * (posTwo.getX() - posOne.getX())) +
                ((posTwo.getY() - posOne.getY())*  (posTwo.getY() - posOne.getY()))             );
    }
}
