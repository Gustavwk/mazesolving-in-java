package SnakeLogic;

import SnakeGUI.Position;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.util.LinkedList;

public class GreedObject implements GameObject {


    private Position position;
    private Color color;
    private goal goal;
    private Position[][] maze;
    private int witdh;
    private int height;
    private LinkedList<Position> goPath = new LinkedList<>();
    private int go = 0;

    public GreedObject(int X, int Y, Color color, goal goal, Position[][] maze, int width, int height) {
        this.setPosition(new Position(X, Y));
        this.setColor(color);
        this.setGoal(goal);
        this.setMaze(maze);
        this.setWitdh(width);
        this.setHeight(height);
        System.out.println(this.getGoal().getPosition());


    }

    @Override
    public void update() {

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

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public SnakeLogic.goal getGoal() {
        return goal;
    }

    public void setGoal(SnakeLogic.goal goal) {
        this.goal = goal;
    }

    public Position[][] getMaze() {
        return maze;
    }

    public void setMaze(Position[][] maze) {
        this.maze = maze;
    }

    public int getWitdh() {
        return witdh;
    }

    public void setWitdh(int witdh) {
        this.witdh = witdh;
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
