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


    Stack path = new Stack();
    LinkedList<Position> visited = new LinkedList<>();

    /**
     * 1. Add root node to the stack.
     * 2. Loop on the stack as long as it's not empty.
     *     1. Get the node at the top of the stack(current), mark it as visited, and remove it.
     *     2. For every non-visited child of the current node, do the following:
     *         1. Check if it's the goal node, If so, then return this child node.
     *          2. Otherwise, push it to the stack.
     * 3. If stack is empty, then goal node was not found!
     */

    public DFSObject(int X, int Y, Color color, goal goal, Position[][] maze, int width, int height){
        this.position = new Position(X,Y);
        this.color = color;
        this.goal = goal;
        this.maze = maze;
        path.push(this.position);



    }



    @Override
    public void update() {
if (!occupiedAhead()){
    this.position.setX(this.position.getX()+1);
    this.position.setX(this.position.getX()+1);
} else {
    this.position.setX(this.position.getX()-1);
    this.position.setX(this.position.getX()-1);

}

        while (!path.isEmpty()){
            visited.add((Position) path.pop());
            this.position = (Position) path.pop();
            for (int i = 0; i < path.size() ; i++) {
                if (!visited.contains(path.peek())){
                    if (this.position.equals(goal.getPosition())){
                        System.out.println("Succes");
                        this.position = goal.getPosition();
                    } else if(!this.position.equals(goal.getPosition())) {
                        path.push(this.position);
                    } else{
                        System.out.println("path not available");
                    }
                }
            }

        }






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

    private boolean occupiedAhead(){

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
