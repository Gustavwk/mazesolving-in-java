package SnakeLogic;

import SnakeGUI.Position;
import javafx.geometry.Pos;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;


import java.util.*;


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

    public boolean bestFirst(Position root, Position goal) {

        Stack path = new Stack();
        LinkedList<Position> visited = new LinkedList<>();
        int steps = 0;
        path.push(root);
        Position current = (Position) path.peek();

        while (!path.empty()) {


            if (visited.contains(goal)) {
                System.out.println("MAZE SOLVED WITH BEST FIRST SEARCH - STEPS TAKEN: " + steps);
                goPath = visited;
                return true;
            }


            while (!visited.contains(goal)) {

                if (!cantMove(visited,current)) {

                    //System.out.println("My Current Position: " + current);
                    current = (nextStep(current, visited));
                    visited.add(current);
                    path.push(current);
                    steps++;
                } else {
                    //System.out.println("peek & pop: " + path.peek() + "\n");
                    current = (Position) path.pop();
                }



            }


        }


        return false;
    }

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

            //System.out.println("Going for:           " +options.get(0) + "\n");

            return options.get(0);


    }

    private boolean outOfGoodChoices(Position current) {
        return maze[current.getX() - 1][current.getY()].getCost() > maze[current.getX()][current.getY()].getCost() || !canMove(current, "west") &&
                maze[current.getX()][current.getY() + 1].getCost() > maze[current.getX()][current.getY()].getCost() || !canMove(current, "south") &&
                maze[current.getX() + 1][current.getY()].getCost() > maze[current.getX()][current.getY()].getCost() || !canMove(current, "east") &&
                maze[current.getX()][current.getY() - 1].getCost() > maze[current.getX()][current.getY()].getCost() || !canMove(current, "north");


    }


    @Override
    public void update() {
        if (go != goPath.size() - 1) {
            go++;
            this.position = goPath.get(go);
        }


    }


    @Override
    public boolean drawObject(GraphicsContext g, double fieldWidth, double fieldHeight) {
        g.setFill(this.getColor());
        g.fillRoundRect(this.getPosition().getX() * fieldWidth, this.getPosition().getY() * fieldHeight, fieldWidth, fieldHeight, 3, 3);
        return true;

    }

    public static void print2D(Position mat[][]) {
        // Loop through all rows
        for (int i = 0; i < mat.length; i++)

            // Loop through all elements of current row
            for (int j = 0; j < mat[i].length; j++)


                System.out.println(mat[i][j] + " ");


    }
}
