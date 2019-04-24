package SnakeLogic;

import SnakeGUI.Position;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;


import java.util.*;


/**
 * This object can find a way through a maze by calculating the best local step based on how the positions in the maze are weighted according to the goal.
 */
public class GreedObject extends Ghost implements GameObject {


    private Position west;
    private Position south;
    private Position east;
    private Position north;

    public GreedObject(int X, int Y, Color color, Goal goal, Position[][] maze) {
        this.setColor(color);
        this.setGoal(goal);
        this.setMaze(maze);
        this.setPosition(maze[X][Y]);
    }

    /**
     * The method creates a stack and pushed the ghost's position onto it.
     * The current position in then set to be equal the the top of the stack.
     *
     *  while the stack is not empty:
     *      if visited contains goal - The search is done and the visited list is set equal the the gopPath list.
     *
     *      while visited does not contain goal
     *      if the ghost can move, the next step will be calculated with the nextStep() method.
     *      current will be set to nextStep() and current will then be pushed onto the stack.
     *
     *      else (if the ghost cantMove) : the ghost will backtrack by popping the stack and setting the current position equal to the popped element.
     *
     * @param goal the desired end Position of the ghost.
     * @return Returns true if visited contains the desired end position "goal".
     */


    public boolean bestFirst(Position goal) {

        Stack path = new Stack();
        LinkedList<Position> visited = new LinkedList<>();
        int steps = 0;
        path.push(this.position);
        Position current = (Position) path.peek();

        while (!path.empty()) {


            if (visited.contains(goal)) {
                this.setPossible(true);
                System.out.println("MAZE SOLVED WITH BEST FIRST SEARCH - STEPS TAKEN: " + steps);
                goPath = visited;
                return true;
            }


            while (!visited.contains(goal)) {

                if (!cantMove(visited,current)) {

                    current = (nextStep(current, visited));
                    visited.add(current);
                    path.push(current);
                    steps++;
                } else {
                    steps++;
                    if (path.isEmpty()){
                        this.setPossible(false);
                        System.out.println("STACK EMPTY - GREEDY / BEST FIRST PATH CANNOT BE FOUND");
                        return false;
                    }
                    current = (Position) path.pop();
                    visited.add(current);
                }

            }


        }


        return false;
    }

    /**
     * This method takes the adjacent positions west/south/east/north and add them to a list "options" if the ghost can move there.
     * the list is then sorted according to the weight or cost of the Positions according to the desired goal.
     * After the list has been sorted the method return the 0th index of the list "options".
     *
     * @param current The current position of the ghost.
     * @param visited The list of visited Positions
     * @return Returns the best suitable Positions for the "next step".
     */

    public Position nextStep(Position current, List visited) {


            List<Position> options = new LinkedList<>();

            west = maze[current.getX() - 1][current.getY()];
            south = maze[current.getX()][current.getY() + 1];
            east = maze[current.getX() + 1][current.getY()];
            north = maze[current.getX()][current.getY() - 1];


            if (canMove(current, "west") && !visited.contains(west)) {
                options.add(west);

            }

            if (canMove(current, "south") && !visited.contains(south)) {
                options.add(south);
            }

            if (canMove(current, "east") && !visited.contains(east)) {
                options.add(east);
            }

            if (canMove(current, "north") && !visited.contains(north)) {
                options.add(north);
            }

            Collections.sort(options);
            return options.get(0);


    }


    @Override
    public void update() {
        if (visualPositionIndex != goPath.size() - 1) {
            visualPositionIndex++;
            this.position = goPath.get(visualPositionIndex);
        }


    }


    @Override
    public boolean drawObject(GraphicsContext g, double fieldWidth, double fieldHeight) {
        g.setFill(this.getColor());
        g.fillRoundRect(this.getPosition().getX() * fieldWidth, this.getPosition().getY() * fieldHeight, fieldWidth, fieldHeight, 3, 3);
        return true;

    }

}
