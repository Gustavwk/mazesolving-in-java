package SnakeLogic;

import SnakeGUI.Position;
import javafx.scene.paint.Color;

import java.util.LinkedList;
import java.util.Stack;

/**
 * This Object can find through a maze without any prior knowledge -
 * it is appropriate for finding a solution to a maze, not always (almost never) the fastest.
 */

public class DFSObject extends Ghost implements GameObject {


    public DFSObject(int x, int y, Color color, Goal goal, Position[][] maze) {
        this.setColor(color);
        this.setGoal(goal);
        this.setMaze(maze);
        this.setPosition(maze[x][y]);



    }

    /**
     * The Pathfinder method return a list of all the visited positions in the maze.
     * This update method makes a nice visual representation of the ghost walking through the maze
     *
     *
     * Update runs continuously
     * The visual Position is the drawn position of the ghost.
     * If the Visual Position is not the same position as the last element in goPath, the Visual Position
     * becomes the position on the next index in the list.
     *
     */

    @Override
    public void update() {
        if (visualPosition != goPath.size()-1) {
            visualPosition++;
          this.position = goPath.get(visualPosition);
        }

    }


    /**
     *  The methods creates a stack and pushes the position of the ghost.
     *          while the stack is not empty:
     *              if visited contain goal - the search is done
     *              else if the ghost can move either west/south/east/north(in that order), it moves there pushed that direction on the stack
     *              else if it cant move - pops the stack and moves to that place - successfully backtracking
     *
     * @param goal is the desired goal of the Depth first Search.
     * @return This method return True if the path is Possible, and false if its not possible.
     *
     *
     */

    public boolean dfs(Position goal) {

        Stack path = new Stack();
        int steps = 0;
        path.push(this.position);
        Position current = (Position) path.peek();

        while (!path.empty()){

            if (visited.contains(goal)){
                goPath = visited;
                System.out.println("MAZE SOLVED WITH DEPTH FIST SEARCH - STEPS TAKEN: " + steps );
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




