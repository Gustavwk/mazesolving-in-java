package SnakeLogic;

import SnakeGUI.Position;
import javafx.geometry.Pos;
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
    private LinkedList<Position> goPath = new LinkedList<>();
    int go = 0;


    public DFSObject(int X, int Y, Color color, goal goal, Position[][] maze, int width, int height) {
        this.setPosition(new Position(X, Y));
        this.setColor(color);
        this.setGoal(goal);
        this.setMaze(maze);
        this.setWitdh(width);
        this.setHeight(height);
        System.out.println(this.getGoal().getPosition());


    }
    public boolean canMove(Position currentPosition, String s) {

        Position north = maze[currentPosition.getX()][currentPosition.getY() - 1];

        Position east = maze[currentPosition.getX() + 1][currentPosition.getY()];

        Position south = maze[currentPosition.getX()][currentPosition.getY() + 1];

        Position west = maze[currentPosition.getX() - 1][currentPosition.getY()];

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





    @Override
    public void update() {



        if (!this.position.equals(goal.getPosition()) && go != goPath.size()-1) {
            go++;
          System.out.println(goPath.get(go));
          this.position = goPath.get(go);
        }








    }

    /**
     * 1. Add root node to the stack.
     * 2. Loop on the stack as long as it's not empty.
     * 1. Get the node at the top of the stack(current), mark it as visited, and remove it.
     * 2. For every non-visited child of the current node, do the following:
     * 1. Check if it's the goal node, If so, then return this child node.
     * 2. Otherwise, push it to the stack.
     * 3. If stack is empty, then goal node was not found!
     */


    public boolean DFS(Position root, Position goal) {

        Stack path = new Stack();
        LinkedList<Position> visited = new LinkedList<>();


        path.push(root);
        //System.out.println("1");

        while (!path.empty()){
            //System.out.println(path.peek());
            Position current = (Position) path.pop();



            if (current == goal){
                this.position  = goal;
                System.out.println("Succes");
                goPath = visited;
                return true;
            }

            if (!visited.contains(current)){
                visited.add(current);
                //System.out.println("3");


                if (canMove(current, "west")&& !visited.contains(maze[current.getX()-1][current.getY()])){
                    Position temp = maze[current.getX()-1][current.getY()];
                    path.push(temp);
                    //visited.add(temp);
                    //System.out.println("4");

                }

                else if (canMove(current, "south") && !visited.contains(maze[current.getX()][current.getY()+1])){
                    Position temp = maze[current.getX()][current.getY() +1];
                    path.push(temp);
                    //visited.add(temp);
                    //System.out.println("7");

                }
                else  if (canMove(current, "east")&& !visited.contains(maze[current.getX()+1][current.getY()])){
                    Position temp = maze[current.getX()+1][current.getY()];
                    path.push(temp);
                    //visited.add(temp);
                    //System.out.println("5");

                }

                 else if (canMove(current, "north")&& !visited.contains(maze[current.getX()][current.getY()-1])){
                    Position temp = maze[current.getX()][current.getY()-1];
                    path.push(temp);
                    //visited.add(temp);
                    //System.out.println("6");

                }



                else {

                }


            }

            else return false;
            goPath = visited;

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
