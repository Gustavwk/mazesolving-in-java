package SnakeLogic;

import SnakeGUI.Position;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;

import java.util.LinkedList;
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

            if (visited.contains(goal)){
                System.out.println("MAZE SOLVED WITH GREED - STEPS TAKEN: " + steps);
                goPath = visited;
                return true;
            }

            while(!visited.contains(goal)){
                visited.add(current);

                if (    canMove(current,"west") &&
                        !visited.contains(maze[current.getX()-1][current.getY()]) &&
                        maze[current.getX()-1][current.getY()].getCost() < current.getCost()){

                    Position temp = maze[current.getX()-1][current.getY()];
                    current = temp;
                    steps++;
                    path.push(current);
                }

                else if (    canMove(current,"south") &&
                             !visited.contains(maze[current.getX()][current.getY()+1]) &&
                             maze[current.getX()][current.getY()+1].getCost() < maze[current.getX()][current.getY()].getCost()){

                    Position temp = maze[current.getX()][current.getY()+1];
                    current = temp;
                    steps++;
                    path.push(current);
                }

                else if (    canMove(current,"east") &&
                             !visited.contains(maze[current.getX()+1][current.getY()]) &&
                             maze[current.getX()+1][current.getY()].getCost() < maze[current.getX()][current.getY()].getCost()){

                    Position temp = maze[current.getX()+1][current.getY()];
                    current = temp;
                    steps++;
                    path.push(current);
                }

                else if (    canMove(current,"north") &&
                             !visited.contains(maze[current.getX()][current.getY()-1]) &&
                             maze[current.getX()][current.getY()+1].getCost() < maze[current.getX()][current.getY()].getCost()){

                    Position temp = maze[current.getX()+1][current.getY()];
                    current = temp;
                    steps++;
                    path.push(current);

                }
                    else if (      (!canMove(current, "west") || visited.contains(maze[current.getX()-1][current.getY()]))
                                && (!canMove(current, "south")|| visited.contains(maze[current.getX()][current.getY()+1]))
                                && (!canMove(current, "east") || visited.contains(maze[current.getX()+1][current.getY()]))
                                && (!canMove(current, "north")|| visited.contains(maze[current.getX()][current.getY()-1])) )
                {


                    current = (Position) path.pop();

                }
                    {

                }


            }



        }


        return true;
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
