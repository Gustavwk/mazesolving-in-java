package SnakeLogic;

import SnakeGUI.Position;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.util.LinkedList;

public class BFSObject implements GameObject {
    private Position position;
    private Color color;
    private Goal goal;
    private Position[][] maze;
    private LinkedList<Position> goPath = new LinkedList<>();
    private int go = 0;
    private LinkedList<Position> visited = new LinkedList<>();


    public BFSObject(int X, int Y, Color color, Goal goal, Position[][] maze) {
        this.setPosition(new Position(X, Y));
        this.setColor(color);
        this.setGoal(goal);
        this.setMaze(maze);
        System.out.println(this.getGoal().getPosition());


    }

    public boolean bfs(Position current){
        visited.add(current);
if (visited.contains(goal.getPosition())){
    System.out.println("Juhu BFS virker");
    goPath = visited;
return true;
} else {
    if (current.getEast() != null) {
        bfs(current.getEast());

    }
    if (current.getNorth() != null) {
        bfs(current.getNorth());
    }
    if (current.getSouth() != null) {
        bfs(current.getSouth());
    }
    if (current.getWest() != null) {
        bfs(current.getWest());
    }
}

return false;
    }

    @Override
    public void update() {

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

    public int getGo() {
        return go;
    }

    public void setGo(int go) {
        this.go = go;
    }
}
