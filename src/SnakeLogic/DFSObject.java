package SnakeLogic;

import SnakeGUI.Position;
import javafx.scene.paint.Color;

import java.util.LinkedList;
import java.util.Stack;

public class DFSObject extends Ghost implements GameObject {


    public DFSObject(int X, int Y, Color color, goal goal, Position[][] maze, int width, int height) {
        this.setPosition(new Position(X, Y));
        this.setColor(color);
        this.setGoal(goal);
        this.setMaze(maze);
        this.setWidth(width);
        this.setHeight(height);
        System.out.println(this.getGoal().getPosition());


    }



    @Override
    public void update() {

        if (go != goPath.size()-1) {
            go++;
          //System.out.println(goPath.get(go));
          this.position = goPath.get(go);
        }

    }


    public boolean DFS(Position root, Position goal) {

        Stack path = new Stack();
        LinkedList<Position> visited = new LinkedList<>();
        int steps = 0;
        path.push(root);
        Position current = (Position) path.peek();
        while (!path.empty()){

            if (visited.contains(goal)){
                goPath = visited;
                System.out.println("MAZE SOLVED WITH DFS - STEPS TAKEN: " + steps );
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
                else  if (canMove(current, "east")&& !visited.contains(maze[current.getX()+1][current.getY()])){
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

                } else if (    (!canMove(current, "west") || visited.contains(maze[current.getX()-1][current.getY()]))
                            && (!canMove(current, "south")|| visited.contains(maze[current.getX()][current.getY()+1]))
                            && (!canMove(current, "east") || visited.contains(maze[current.getX()+1][current.getY()]))
                            && (!canMove(current, "north")|| visited.contains(maze[current.getX()][current.getY()-1])) )
                {
                    if (path.size() != 0){
                    if (!visited.contains(current)){
                        visited.add(current);
                    }
                    current = (Position) path.pop();

                }}
            }

        }
        System.out.println("Not Solvable");
        goPath = visited;
        return false;



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



}
