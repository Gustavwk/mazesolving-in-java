package SnakeLogic;

import SnakeGUI.Position;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.util.LinkedList;

public class BFSObject extends Ghost implements GameObject {

    private LinkedList<Position> visited = new LinkedList<>();
    private LinkedList <Position> bfsPath = new LinkedList<>();


    public BFSObject(int X, int Y, Color color, Goal goal, Position[][] maze) {
        this.setPosition(new Position(X, Y));
        this.setColor(color);
        this.setGoal(goal);
        this.setMaze(maze);


    }

    public boolean mapToRoot(Position here){
        if (here.getParent() != null){
            bfsPath.add(here.getParent());
            mapToRoot(here.getParent());

        }

        return true;
    }

    public boolean bfs(Position current){



        visited.add(current);
        LinkedList<Position> split = new LinkedList<>();

        if (visited.contains(goal.getPosition())){

            mapToRoot(current);
            goPath = bfsPath;
            System.out.println("MAZE SOLVED WITH BREATH FIRST SEARCH - STEPS TAKEN: " + bfsPath.size());
            return true;

    } else {

            if (current.getEast() != null) {
                split.add(current.getEast());
            }
            if (current.getSouth() != null) {
                split.add(current.getSouth());
            }
            if (current.getWest() != null) {
                split.add(current.getWest());
            }
            if (current.getNorth() != null) {
                split.add(current.getNorth());
            }

            for (Position pos : split) {

                bfs(pos);

                if (visited.contains(goal.getPosition())) {
                    split.clear();
                }


            }


}

return false;
    }

    @Override
    public void update() {
        if (visualPosition != goPath.size()-1) {
            visualPosition++;
            this.position = goPath.get(visualPosition);
        }


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

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public Goal getGoal() {
        return goal;
    }

    public void setGoal(Goal goal) {
        this.goal = goal;
    }

    public Position[][] getMaze() {
        return maze;
    }

    public void setMaze(Position[][] maze) {
        this.maze = maze;
    }


    public LinkedList<Position> getGoPath() {
        return goPath;
    }

    public void setGoPath(LinkedList<Position> goPath) {
        this.goPath = goPath;
    }

    public int getVisualPosition() {
        return visualPosition;
    }

    public void setVisualPosition(int visualPosition) {
        this.visualPosition = visualPosition;
    }
}
