package SnakeLogic;

import SnakeGUI.Position;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.util.LinkedList;

public abstract class Ghost implements GameObject {
    protected  Position position;
    protected  Color color;
    protected Goal goal;
    protected  Position[][] maze;
    protected  int width;
    protected  int height;
    protected  LinkedList<Position> goPath = new LinkedList<>();
    protected  int go = 0;

    @Override
    public void update() {

    }

    @Override
    public boolean drawObject(GraphicsContext g, double fieldWidth, double fieldHeight) {
        g.setFill(this.getColor());
        g.fillRoundRect(this.getPosition().getX() * fieldWidth, this.getPosition().getY() * fieldHeight, fieldWidth, fieldHeight, 3, 3);
        return true;
    }



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

    public Goal getGoal() {
        return goal;
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

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public LinkedList<Position> getGoPath() {
        return goPath;
    }

    public void setGoPath(LinkedList<Position> goPath) {
        this.goPath = goPath;
    }

    public int getGo() {
        return go;
    }

    public void setGo(int go) {
        this.go = go;
    }
}
