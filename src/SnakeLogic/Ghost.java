package SnakeLogic;

import SnakeGUI.Position;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.util.LinkedList;

/**
 * This is the superclass of the ghosts, it hold the methods and fields that all the ghost needs
 * Each ghost will be more specified, and contain specific path finding algorithms.
 */
public abstract class Ghost implements GameObject {
    protected  Color color;
    protected Goal goal;
    protected  Position position;
    protected  int visualPositionIndex = 0;
    protected  Position[][] maze;
    protected LinkedList<Position> visited = new LinkedList<>();
    protected  LinkedList<Position> goPath = new LinkedList<>();

    @Override
    public void update() {

    }

    @Override
    public boolean drawObject(GraphicsContext g, double fieldWidth, double fieldHeight) {
        g.setFill(this.getColor());
        g.fillRoundRect(this.getPosition().getX() * fieldWidth, this.getPosition().getY() * fieldHeight, fieldWidth, fieldHeight, 3, 3);
        return true;
    }


    /**
     * This is a way to tell if the adjacent position is occupied or not. I found it on gitHub and tuned it to fir this program!
     * @param currentPosition is the Position that the ghost occupies
     * @param s is a string parameter used to navigate in what direction we want check.
     * @return True if the space is not occupied, false if it is!
     */

    public boolean canMove(Position currentPosition, String s) {

        Position north = getMaze()[currentPosition.getX()][currentPosition.getY() - 1];

        Position east = getMaze()[currentPosition.getX() + 1][currentPosition.getY()];

        Position south = getMaze()[currentPosition.getX()][currentPosition.getY() + 1];

        Position west = getMaze()[currentPosition.getX() - 1][currentPosition.getY()];


        if (s == "west") {
            if (!west.isOccupied()) {
                return true;

            } else return false;
        }
        if (s == "north") {
            if (!north.isOccupied()) {
                return true;

            } else return false;
        }
        if (s == "east") {
            if (!east.isOccupied()) {
                return true;
            } else return false;
        }

        if (s == "south") {
            if (!south.isOccupied()) {
                return true;

            }
            else return false;
        }
        else return false;
    }

    /**
     * @param visited A list of visited Positions
     * @param current The current position of the ghost
     * @return returns True if the ghost cant move!
     * - if all the spaces around the ghost either have already been visited or are occupied by walls
     */
    public boolean cantMove(LinkedList<Position> visited, Position current) {
            return (!canMove(current, "west") || visited.contains(maze[current.getX() - 1][current.getY()]))
                && (!canMove(current, "south") || visited.contains(maze[current.getX()][current.getY() + 1]))
                && (!canMove(current, "east") || visited.contains(maze[current.getX() + 1][current.getY()]))
                && (!canMove(current, "north") || visited.contains(maze[current.getX()][current.getY() - 1]));
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public void setGoal(Goal goal) {
        this.goal = goal;
    }

    public Position[][] getMaze() {
        return maze;
    }

    public void setMaze(Position[][] maze) {
        this.maze = maze;
    }


    public LinkedList<Position> getGoPath() {
        return goPath;
    }

}
