package SnakeLogic;

import SnakeGUI.Position;
import javafx.scene.paint.Color;
import javafx.scene.canvas.GraphicsContext;

import java.util.Stack;

public class DFSObject implements GameObject {
    private Position position;
    private Color color;
    private goal goal;
    Position[][] maze;

    Stack path = new Stack();
    Stack visitid = new Stack();

    public DFSObject(int X, int Y, Color color, goal goal, Position[][] maze){
        this.position = new Position(X,Y);
        this.color = color;
        this.goal = goal;
        this.maze = maze;
    }


    @Override
    public void update() {
        this.maze[0][0].getX();
            this.position.setX(this.position.getX() + 1);

    }

    @Override
    public boolean drawObject(GraphicsContext g, double fieldWidth, double fieldHeight) {
        g.setFill(this.color);
        g.fillRoundRect(this.position.getX() * fieldWidth, this.position.getY() * fieldHeight, fieldWidth, fieldHeight, 3, 3);
        return true;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public int getX() {
        return getX();
    }

    public void setX(int x) {
        setX(x);
    }

    public int getY() {
        return getY();
    }

    public void setY(int y) {
        setY(y);
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
}
