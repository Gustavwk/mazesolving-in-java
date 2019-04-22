package SnakeLogic;

import SnakeGUI.Position;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.List;

/**
 * This is the desired goal in that path finding algorithms!
 */
public class Goal implements GameObject {
    private Position position;
    private Color color;
    private Position[][] maze;
    public ArrayList<Item> items;



    public Goal(Color color, int X, int Y, Position[][] maze, List item) {
        this.setPosition(new Position(X,Y));
        this.setColor(color);
        this.maze = maze;



        items = (ArrayList<Item>) item;



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

    /**
     * This method calculates the cost (or weight) of each of the nodes in the maze.
     * This is done with the method "calculateDistance()" that runs in a nested for loop on each of the positions in the maze relative to the goals position
     * @param maze is the 2D array that represents the maze
     * @return Returns true when the operation is done.
     */
    public boolean initMazeCost(Position[][]maze){
        for (int i = 0; i <maze.length ; i++) {
            for (int j = 0; j <maze[i].length ; j++) {
                maze[i][j].setCost(calculateDistance(this.position,maze[i][j]));
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

    /**
     * calculates the distance between 2 positions using the "Distance formula" derived from pythagoras. This distance is
     * used throughout the program as a cost or weight, making it possible to make educated guesses on ho to progress through the maze.
     *
     * This cost should be assigned to the position that is not the goal. The goals own cost should always be zero.
     *
     * @param posOne Position 1
     * @param posTwo Position 2
     * @return the distance from posOne to posTwo as a double.
     */
    private double calculateDistance(Position posOne, Position posTwo) {

        return Math.sqrt(               ((posTwo.getX() - posOne.getX()) * (posTwo.getX() - posOne.getX())) +
                                        ((posTwo.getY() - posOne.getY())*  (posTwo.getY() - posOne.getY()))             );
    }
}
