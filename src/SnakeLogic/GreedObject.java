package SnakeLogic;

import SnakeGUI.Position;
import javafx.geometry.Pos;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;


public class GreedObject extends Ghost implements GameObject {




    public GreedObject(int X, int Y, Color color, Goal goal, Position[][] maze, int width, int height) {
        this.setPosition(new Position(X, Y));
        this.setColor(color);
        this.setGoal(goal);
        this.setMaze(maze);
        this.setWidth(width);
        this.setHeight(height);
        System.out.println(this.getGoal().getPosition());


    }

    public boolean bestFirst(Position root, Position goal){

        Stack path = new Stack();
        LinkedList<Position> visited = new LinkedList<>();
        int steps = 0;
        path.push(root);
        Position current = (Position) path.peek();

        while(!path.empty()){



            while(!visited.contains(goal)){
                visited.add(current);


            }



        }


        return true;
    }

    public Position nextStep(Position current, List visited){
        List<Position> options = new LinkedList<>();
        if (canMove(current, "west") && !visited.contains(maze[current.getX()-1][current.getY()]))
        {
            options.add(maze[current.getX()-1][current.getY()]);
        }
        if (canMove(current, "south") && !visited.contains(maze[current.getX()][current.getY()+1]))
        {
            options.add(maze[current.getX()][current.getY()+1]);
        }
        if (canMove(current, "east") && !visited.contains(maze[current.getX()+1][current.getY()]))
        {
            options.add(maze[current.getX()+1][current.getY()]);
        }
        if (canMove(current, "north") && !visited.contains(maze[current.getX()][current.getY()-1]))
        {
            options.add(maze[current.getX()][current.getY()-1]);
        }

        return null;
    }

    private boolean outOfGoodChoices(Position current) {
                return  maze[current.getX()-1][current.getY()].getCost() > maze[current.getX()][current.getY()].getCost() || !canMove(current, "west")  &&
                        maze[current.getX()][current.getY()+1].getCost() > maze[current.getX()][current.getY()].getCost() || !canMove(current, "south") &&
                        maze[current.getX()+1][current.getY()].getCost() > maze[current.getX()][current.getY()].getCost() || !canMove(current, "east") &&
                        maze[current.getX()][current.getY()-1].getCost() > maze[current.getX()][current.getY()].getCost() || !canMove(current, "north");


    }

    private boolean cantMove(LinkedList<Position> visited, Position current) {
                return (!canMove(current, "west") || visited.contains(maze[current.getX()-1][current.getY()]))
                    && (!canMove(current, "south")|| visited.contains(maze[current.getX()][current.getY()+1]))
                    && (!canMove(current, "east") || visited.contains(maze[current.getX()+1][current.getY()]))
                    && (!canMove(current, "north")|| visited.contains(maze[current.getX()][current.getY()-1]));
    }

    @Override
    public void update() {
        if (go != goPath.size()-1) {
            go++;
            //System.out.println(goPath.get(go));
            this.position = goPath.get(go);
        }

    }



    @Override
    public boolean drawObject(GraphicsContext g, double fieldWidth, double fieldHeight) {
        g.setFill(this.getColor());
        g.fillRoundRect(this.getPosition().getX() * fieldWidth, this.getPosition().getY() * fieldHeight, fieldWidth, fieldHeight, 3, 3);
        return true;

    }


}
