package SnakeLogic;

import SnakeGUI.Position;
import javafx.scene.paint.Color;

import java.util.LinkedList;
import java.util.Stack;

public class DFSObject extends Ghost implements GameObject {


    public DFSObject(int x, int y, Color color, Goal goal, Position[][] maze) {
        this.setColor(color);
        this.setGoal(goal);
        this.setMaze(maze);
        this.setPosition(maze[x][y]);



    }



    @Override
    public void update() {
        if (go != goPath.size()-1) {
            go++;
          this.position = goPath.get(go);
        }

    }


    public boolean dfs(Position root, Position goal) {

        Stack path = new Stack();
        LinkedList<Position> visited = new LinkedList<>();
        int steps = 0;
        path.push(root);
        Position current = (Position) path.peek();

        while (!path.empty()){

            if (visited.contains(goal)){
                goPath = visited;
                System.out.println("MAZE SOLVED WITH dfs - STEPS TAKEN: " + steps );
                return true;
            }

            while (!visited.contains(goal)){
                visited.add(current);

                if (canMove(current, "west")&& !visited.contains(maze[current.getX()-1][current.getY()])){
                    Position temp = maze[current.getX()-1][current.getY()];
                    current = temp;
                    steps++;
                    path.push(current);

                }

                else if (canMove(current, "south") && !visited.contains(maze[current.getX()][current.getY()+1])){
                    Position temp = maze[current.getX()][current.getY() +1];
                    current = temp;
                    steps++;
                    path.push(current);

                }
                else  if (canMove(current, "east") && !visited.contains(maze[current.getX()+1][current.getY()])){
                    Position temp = maze[current.getX()+1][current.getY()];
                    current = temp;
                    steps++;
                    path.push(current);

                }
                 else if (canMove(current, "north")&& !visited.contains(maze[current.getX()][current.getY()-1])){
                    Position temp = maze[current.getX()][current.getY()-1];
                    current = temp;
                    steps++;
                    path.push(current);

                } else if (cantMove(visited,current) )
                {


                    steps++;
                    current = (Position) path.pop();


                }
            }

        }
        System.out.println("Not Solvable");
        goPath = visited;
        return false;



}

    @Override
    public Position getPosition() {
        return position;
    }
    @Override
    public void setPosition(Position position) {
        this.position = position;
    }


    public void setX(int x) {
        setX(x);
    }





    }




