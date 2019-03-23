package SnakeLogic;

import SnakeGUI.Position;
import javafx.scene.paint.Color;
import javafx.scene.canvas.GraphicsContext;

import java.util.LinkedList;
import java.util.Stack;

public class DFSObject implements GameObject {
    private Position position;
    private Color color;
    private goal goal;
    Position[][] maze;
    private int witdh;
    private int height;


    public DFSObject(int X, int Y, Color color, goal goal, Position[][] maze, int width, int height){
        this.position = new Position(X,Y);
        this.color = color;
        this.goal = goal;
        this.maze = maze;
        this.witdh = width;
        this.height = height;
        System.out.println(dsf(this.position, goal.getPosition()));



    }



    @Override
    public void update() {


    }


public boolean dsf(Position root, Position goal) {

    LinkedList<Position> visited = new LinkedList<>();
    Stack path = new Stack();
    for (int i = 0; i < witdh; i++) {
        for (int j = 0; j < height; j++) {
            
            if (!maze[i][j].isOccupied()){

                maze[i][j] = root;
                path.push(root);



            while (!path.empty()) {

                root = (Position) path.pop();
                visited.add(root);

                if (!visited.contains(goal)) {

                    System.out.println(root);

                } else {
                    return true;

                }

            }
            }
        }

    }
return false;
}









    /*for (int i = 0; i < witdh ; i++) {
        for (int j = 0; j < height; j++) {

            if (!maze[i][j].isOccupied()) {

                path.push(maze[i][j]);

                while (!path.isEmpty()) {

                    visited.add((Position) path.pop());
                    this.position = (Position) path.pop();

                    for (int k = 0; k < path.size(); k++) {

                        if (!visited.contains(path.peek())) {

                            if (this.position.equals(goal.getPosition())) {
                                System.out.println("Succes");
                                this.position = goal.getPosition();
                                return true;


                            } else if (!this.position.equals(goal.getPosition())) {

                                path.push(this.position);

                            } else {
                                System.out.println("path not available");
                            }
                        }
                    }

                }

            }
        }*/








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

    private boolean occupied(){

    if(this.maze[this.position.getX()][this.position.getY()].isOccupied()){

    return true;


    } else {

        return false;
    }

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
