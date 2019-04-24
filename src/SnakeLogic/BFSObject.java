package SnakeLogic;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.util.LinkedList;

/**
 * This ghost can find a way true a maze be traversing through a tree of positions. it does this be checking each layer of childs
 */
public class BFSObject extends Ghost implements GameObject {

    private LinkedList<Position> visited = new LinkedList<>();
    private LinkedList <Position> bfsPath = new LinkedList<>();
    private PositionTree<Position> tree;


    public BFSObject(int X, int Y, Color color, Goal goal, Position[][] maze) {
        this.setColor(color);
        this.setGoal(goal);
        this.setMaze(maze);
        this.setPosition(maze[X][Y]);



    }


    /**
     * This methods traverses a tree of positions be recursively asking each of its children for the goal position.
     * When the goal is found, a path form made recursively from marking the goal parent and then that Positions parent and so on.
     *
     * The tree is initialized
     * the current position is added to the visited list.
     * a new list "split" is made.
     *      if visited contains goal
     *          the search is done and a path is formed using the mapToRoot() method
     *              else
     *                  if the current position's east/south/west/north position is not null
     *                      add it to the split list
     *                          for Position pos in split
     *                              bfs(pos)
     *                                  if visited contains goal, clear the split list.
     *
     *
     * @param current the current position of the ghost
     * @return return true when the task is done.
     */

    public boolean bfs(Position current){

        this. tree = new PositionTree<>(this.maze, this.getPosition(), goal.getPosition());



        visited.add(current);
        LinkedList<Position> split = new LinkedList<>();

        if (visited.contains(goal.getPosition())) {
            this.setPossible(true);
            mapToRoot(current);
            goPath = bfsPath;
            System.out.println("MAZE SOLVED WITH BREATH FIRST SEARCH - STEPS TAKEN: " + bfsPath.size());
            return true;

        } else if (!visited.contains(goal.getPosition())) {

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



return true;

    }

    /**
     * Recursively adds a given positions parent to a list.
     *
     *  if here's parent is not null
     *      add it to the bfsPath list
     *          mapToRoot(here.getParent)
     *
     * @param here the place from where you want you mapping to begin
     * @return Returns true when the task is done.
     */

    public boolean mapToRoot(Position here){
        if (here.getParent() != null){
            bfsPath.add(here.getParent());
            mapToRoot(here.getParent());

        }

        return true;
    }

    /**
     * Dette skal forklares
     */
    @Override
    public void update() {
if (visualPositionIndex != -(bfsPath.size())) {
    this.position = bfsPath.get(visualPositionIndex + bfsPath.size() - 1);
    visualPositionIndex--;
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
        return visualPositionIndex;
    }

    public void setVisualPosition(int visualPosition) {
        this.visualPositionIndex = visualPosition;
    }

    public PositionTree<Position> getTree() {
        return tree;
    }

    public void setTree(PositionTree<Position> tree) {
        this.tree = tree;
    }
}
