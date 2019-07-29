package SnakeLogic;

import javafx.scene.paint.Color;

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
        if (visualPositionIndex != goPath.size()-1) {
            visualPositionIndex++;
          this.position = goPath.get(visualPositionIndex);
        }

    }


    /**
     *  The methods creates a stack and pushes the position of the ghost.
     *          while the stack is not empty:
     *              if visited contain goal - the search is done
     *              else if the ghost can move either west/south/east/north(in that order), it moves there and pushes that direction on the stack
     *              else if it cant move - pops the stack and moves to that place - successfully backtracking
     *
     * @param goal is the desired goal of the Depth first Search.
     * @return return true when the task is done, false is its impossible.
     *
     *
     */

    public boolean dfs(Position goal) {

        Stack path = new Stack();
        int steps = 0;
        path.push(this.position);
        Position current = (Position) path.peek();



        while (!path.empty()){



            if (getVisited().contains(goal)){
                this.setPossible(true);
                goPath = getVisited();
                System.out.println("MAZE SOLVED WITH DEPTH FIST SEARCH - STEPS TAKEN: " + steps );
                return true;
            }

            while (!getVisited().contains(goal)){
                getVisited().add(current);

                if (canMove(current, "west")&& !getVisited().contains(maze[current.getX()-1][current.getY()])){
                    Position temp = maze[current.getX()-1][current.getY()];
                    current = temp;
                    steps++;
                    path.push(current);

                }

                else if (canMove(current, "south") && !getVisited().contains(maze[current.getX()][current.getY()+1])){
                    Position temp = maze[current.getX()][current.getY() +1];
                    current = temp;
                    steps++;
                    path.push(current);

                }
                else  if (canMove(current, "east") && !getVisited().contains(maze[current.getX()+1][current.getY()])){
                    Position temp = maze[current.getX()+1][current.getY()];
                    current = temp;
                    steps++;
                    path.push(current);

                }
                 else if (canMove(current, "north")&& !getVisited().contains(maze[current.getX()][current.getY()-1])){
                    Position temp = maze[current.getX()][current.getY()-1];
                    current = temp;
                    steps++;
                    path.push(current);

                } else if (cantMove(getVisited(),current) )
                {

                    if (path.empty()){
                        this.setPossible(false);
                        System.out.println("STACK EMPTY - DFS PATH CANNOT BE FOUND");
                        return false;
                    }

                    steps++;
                    current = (Position) path.pop();






                }

            }

        }



return true;
}
    @Override
    public String toString() {
        return "DFS";
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




