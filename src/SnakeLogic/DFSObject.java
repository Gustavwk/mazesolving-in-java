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
    private Position[][] maze;
    private int witdh;
    private int height;








    public DFSObject(int X, int Y, Color color, goal goal, Position[][] maze, int width, int height){
        this.setPosition(new Position(X,Y));
        this.setColor(color);
        this.setGoal(goal);
        this.setMaze(maze);
        this.setWitdh(width);
        this.setHeight(height);
        System.out.println(this.getGoal().getPosition());



    }





    @Override
    public void update() {

    dsf(this.position,this.goal.getPosition());





    }

    /**
     * 1. Add root node to the stack.
     * 2. Loop on the stack as long as it's not empty.
     *     1. Get the node at the top of the stack(current), mark it as visited, and remove it.
     *     2. For every non-visited child of the current node, do the following:
     *         1. Check if it's the goal node, If so, then return this child node.
     *          2. Otherwise, push it to the stack.
     * 3. If stack is empty, then goal node was not found!
     */


public boolean dsf(Position root, Position goal) {
    LinkedList<Position> visited = new LinkedList<>();
    Stack path = new Stack();

    for (int i = 0; i < getWitdh(); i++) {
        for (int j = 0; j < getHeight(); j++) {

            if (!getMaze()[i][j].isOccupied()){


                getMaze()[i][j] = root;

                System.out.println(root + " 1");

                path.push(root);

            while (!path.empty()) {

                root = (Position) path.peek();
                visited.add(root);
                path.pop();

                System.out.println(root + " 2");


                if (!visited.contains(root)) {
                    if (root.equals(goal)){

                        System.out.println(root + " 3");

                    } else {
                        path.push(root);
                        this.position = root;

                        System.out.println(root + " 4");
                    }


                }

            }
            }
        }

    }
return false;
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


    public void setX(int x) {
        setX(x);
    }



    private boolean occupied(){

    if(this.getMaze()[this.getPosition().getX()][this.getPosition().getY()].isOccupied()){

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

    public Position[][] getMaze() {
        return this.maze;
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


}
