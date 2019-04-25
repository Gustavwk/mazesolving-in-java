package SnakeLogic;

import javafx.scene.paint.Color;

import java.util.List;

/**
 * This class is responsible for all the "structures" in the maze, walls, tiles and the maze building.
 * It is also responsible for marking the path to give a nice visual representation of the algorithms
 */
public class Room {

    private int whichMaze;


    public Position[][] populate(List<Item> objects, int width, int height, int which) {

        Position[][] maze = new Position[width][height];
        maze = initMazArray(maze);
        layFloor(objects, maze);

        this.whichMaze = which;

        selectMaze(objects, width, height, maze);


        return maze;
    }

    /**
     * This method make it possible to change witch maze you want to run the algorithms in! There are currently 4 different mazes.
     * This is really good for testing since it makes me able to test all the different mazes at once
     *
     * @param objects The list in witch every item is drawn
     * @param width the width of the maze
     * @param height the height of the maze
     * @param maze the 2D array of positions
     */
    private void selectMaze(List<Item> objects, int width, int height, Position[][] maze) {
        if (this.whichMaze == 0){

            mazeBoarders(width, height, objects, maze);

        } else if (this.whichMaze == 1){

            createPacManMaze(objects,maze);

        } else if (this.whichMaze == 2){

            createHardManMaze(objects,maze);

        } else if (this.whichMaze == 3){

            createSimpleMaze(objects,maze);

        } else if (this.whichMaze == 4){

            createImpossibleMaze(objects,maze);

        }
    }

    /**
     * This method takes a list of Positions, a list of items and a color,
     * then creates a Tile-objects at each of the positions in the marked list at the desired color.
     *
     * Used to give a visual path
     *
     * @param marked List of positions to be marked
     * @param objects List of Items (the tiles needs to be added here to be drawn)
     * @param color The desired color of the Tile-Objects
     * @return True when the task is done
     */
    public boolean markPath(List<Position> marked, List<Item> objects, Color color){

        for (Position pos: marked) {
            addMarkedTileToRoom(pos.getX(),pos.getY(),objects, color);
        }


        return true;
    }


    /**
     * This method combines all the steps to create a wall-object in the maze. It adds a new wall object to the list of Items "Objects"
     * on the desired x and y coordinate. then it sets the same position in the 2D array to occupied.
     * @param x desired x-coordinate
     * @param y desired y-coordinate
     * @param objects List of items called objects
     * @param maze the 2D array of positions resembling the maze.
     * @return True when the task is done
     */

    public boolean addWallToRoom(int x, int y, List<Item> objects, Position[][] maze) {

        Wall wall = new Wall(x, y);
        objects.add(wall);


        maze[x][y].setOccupied(true);



        return true;
    }


    /**
     * Very much the same as "addWallToRoom()", this just adds a tile object. The main difference is that the tiles
     * does not set the corresponding x and y coordinate in the 2D array of positions to "occupied".
     * This is also why this method does not take a 2D array as a parameter
     *
     * @param x desired x-coordinate
     * @param y desired y-coordinate
     * @param objects List of items called objects
     * @return True when the task is done
     */

    public boolean addTileToRoom(int x, int y, List<Item> objects) {

        Tile tile = new Tile(x, y, Color.BLACK);
        objects.add(tile);


        return true;
    }

    /**
     * This is very much the same as the method above, but with a color parameter.
     * You can decide what color the tiles should be - used in "markPath()"
     *
     * @param x desired x-coordinate
     * @param y desired y-coordinate
     * @param objects List of items called objects
     * @param color The desired color of the tile
     * @return True when the task is done
     */

    public boolean addMarkedTileToRoom(int x, int y, List<Item> objects, Color color) {

        Tile tile = new Tile(x, y, color);
        objects.add(tile);


        return true;
    }

    /**
     *This method uses a nested for loop and the "addWallToRoom()" method to create boarders of wall-objects in the maze.
     * @param width the width of the 2D array
     * @param height the height of the 2D array
     * @param objects List if Items that should be drawn
     * @param maze The 2D array of positions resembling the maze
     * @return
     */

    public boolean mazeBoarders(int width, int height, List objects, Position[][] maze) {

        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {

                if (i == 0 || i == width - 1) {

                    addWallToRoom(i, j, objects, maze);

                }

                if (j == 0 || j == height - 1) {

                    addWallToRoom(i, j, objects, maze);
                }
            }
        }
        return true;
    }

    /**
     *This method initializes the 2D array of positions and returns a 2D array that is with the where each element has the right corresponding x and y value.
     * @param maze The 2D array that is to be initialized.
     * @return Returns true when the task is done.
     */
    public Position[][] initMazArray(Position[][] maze) {
        for (int i = 0; i < maze.length; i++) {
            for (int j = 0; j < maze[i].length; j++) {
                maze[i][j] = new Position(i, j);
            }


        }
        return maze;
    }

    /**
     * Much like the "mazeBoarders()" this method uses a nested for loop to add tiles to the maze.
     * @param objects List of items.
     * @param maze The 2D array of positions
     * @return Returns True when the task is done.
     */
    public boolean layFloor(List objects, Position[][] maze) {
        for (int i = 0; i < maze.length; i++) {
            for (int j = 0; j < maze[i].length; j++) {
                addTileToRoom(i, j, objects);
            }
        }
        return true;
    }


    /**
     * This method allows to make a maze based on a integer 2D array. This is very handy for checking the path in various situations.
     *
     * The methods traverses the 2D array and sets a wall in the maze at the coordinates corresponding to i and j in the nested for loop.
     * @param objects the list of Items the wall is added to
     * @param maze The 2D array of positions
     */
    public void createHardManMaze( List<Item> objects, Position[][] maze) {
        int [][] HomemadeArray = {
                {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
                {1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,1,0,1},
                {1,0,1,1,1,1,1,1,1,1,1,1,1,1,0,1,0,1,0,1},
                {1,0,1,0,0,0,0,0,0,0,0,0,0,1,0,1,0,1,0,1},
                {1,0,1,0,1,1,1,1,1,1,1,1,0,1,0,1,0,1,0,1},
                {1,0,1,0,1,1,0,0,0,0,0,1,0,1,0,1,0,1,0,1},
                {1,0,1,0,1,1,1,1,1,1,0,1,0,1,0,1,0,1,0,1},
                {1,0,0,0,0,0,0,0,0,1,0,1,0,1,0,1,0,1,0,1},
                {1,0,1,1,0,1,0,1,1,1,0,1,0,1,0,0,0,1,0,1},
                {1,0,1,0,0,1,0,0,0,0,0,1,0,1,1,1,1,1,0,1},
                {1,0,1,0,1,1,1,1,1,1,1,1,0,0,0,0,0,0,0,1},
                {1,0,1,0,0,0,0,0,0,0,0,1,1,1,1,1,1,1,0,1},
                {1,0,1,1,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
                {1,1,1,0,0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
                {1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
                {1,0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
                {1,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
                {1,0,1,0,1,0,1,1,1,1,1,1,1,1,0,1,1,1,1,1},
                {1,0,1,0,1,0,1,0,0,0,0,0,0,1,0,0,0,0,0,1},
                {1,0,1,0,1,0,1,0,1,1,0,1,0,1,1,1,1,1,1,1},
                {1,0,1,0,1,0,1,0,1,0,0,1,0,1,0,0,0,0,0,1},
                {1,0,1,0,1,0,1,0,1,0,1,1,0,1,0,1,1,1,0,1},
                {1,0,1,0,1,0,1,0,1,0,1,1,0,1,0,1,0,0,0,1},
                {1,0,1,0,1,0,1,0,1,0,0,1,0,1,0,1,0,1,1,1},
                {1,0,0,0,1,0,0,0,0,0,0,1,0,0,0,1,0,0,0,1},
                {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,0,1},
                {1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
                {1,0,1,0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
                {1,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
                {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},};

        for (int i = 0; i < HomemadeArray.length ; i++) {
            for (int j = 0; j < HomemadeArray[i].length ; j++) {
                if (HomemadeArray[i][j] == 1){
                    addWallToRoom(i,j,objects,maze);
                }
            }
        }






    }



    /**
     * Same as above, but the maze if different. This is an open pacMan Like maze, the one above is closed.
     */

    public void createPacManMaze( List<Item> objects, Position[][] maze) {
        int [][] PacManMaze = {
                {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
                {1,0,0,0,0,0,0,1,0,0,0,1,0,0,0,1,0,0,0,1},
                {1,0,1,1,0,1,0,1,0,1,0,1,0,1,0,1,0,1,0,1},
                {1,0,1,1,0,1,0,1,0,1,0,1,0,1,0,0,0,1,0,1},
                {1,0,1,1,0,1,0,1,0,1,0,1,0,1,1,1,0,1,0,1},
                {1,0,1,1,0,1,0,1,0,1,0,1,0,0,0,0,0,1,0,1},
                {1,0,0,0,0,0,0,0,0,0,0,0,0,1,0,1,1,1,0,1},
                {1,1,1,1,0,1,1,1,1,1,0,1,0,1,0,0,0,1,0,1},
                {1,0,0,0,0,0,0,1,0,0,0,0,0,1,0,1,0,1,0,1},
                {1,0,1,1,0,1,0,1,0,1,0,1,0,1,0,1,0,1,0,1},
                {1,0,1,1,0,1,0,1,0,1,0,1,0,1,0,1,0,1,0,1},
                {1,0,1,1,0,1,0,1,0,1,0,1,0,1,0,1,0,1,0,1},
                {1,0,1,1,0,1,0,1,0,1,0,1,0,1,0,1,0,1,0,1},
                {1,0,1,1,0,1,0,1,0,1,0,1,0,1,0,1,0,1,0,1},
                {1,0,0,0,0,1,0,0,0,1,0,1,0,0,0,1,0,0,0,1},
                {1,1,1,1,0,1,1,0,1,1,0,1,1,0,1,1,1,1,1,1},
                {1,0,0,0,0,1,0,0,0,1,0,1,0,0,0,1,0,0,0,1},
                {1,0,1,1,0,1,0,1,0,1,0,1,0,1,0,1,0,1,0,1},
                {1,0,1,1,0,1,0,1,0,1,0,1,0,1,0,1,0,1,0,1},
                {1,0,1,1,0,1,0,1,0,1,0,1,0,1,0,1,0,1,0,1},
                {1,0,1,1,0,1,0,1,0,1,0,1,0,1,0,1,0,1,0,1},
                {1,0,0,0,0,0,0,1,0,0,0,0,0,1,0,1,0,1,0,1},
                {1,1,1,1,0,1,1,1,1,1,0,1,0,1,0,0,0,1,0,1},
                {1,0,0,0,0,0,0,0,0,0,0,0,0,1,0,1,1,1,0,1},
                {1,0,1,1,0,1,0,1,0,1,0,1,0,0,0,0,0,1,0,1},
                {1,0,1,1,0,1,0,1,0,1,0,1,0,1,1,1,0,1,0,1},
                {1,0,1,1,0,1,0,1,0,1,0,1,0,1,0,0,0,1,0,1},
                {1,0,1,1,0,1,0,1,0,1,0,1,0,1,0,1,0,1,0,1},
                {1,0,0,0,0,0,0,1,0,0,0,1,0,0,0,1,0,0,0,1},
                {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
        };

        for (int i = 0; i < PacManMaze.length ; i++) {
            for (int j = 0; j < PacManMaze[i].length ; j++) {
                if (PacManMaze[i][j] == 1){
                    addWallToRoom(i,j,objects,maze);
                }
            }
        }






    }

    /**
     * This is an impossible maze, used to test if the algorithm can say if it cannot find a path.
     */

    public void createImpossibleMaze( List<Item> objects, Position[][] maze) {
        int [][] Impossible = {
                {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
                {1,0,0,0,0,0,0,0,0,0,0,1,0,0,0,1,0,1,0,1},
                {1,0,1,1,1,1,1,1,1,1,1,1,1,1,0,1,0,1,0,1},
                {1,0,1,0,0,0,0,0,0,0,0,0,0,1,0,1,0,1,0,1},
                {1,0,1,0,1,1,1,1,1,1,1,1,0,1,0,1,0,1,0,1},
                {1,0,1,0,1,1,0,0,0,0,0,1,0,1,0,1,0,1,0,1},
                {1,0,1,0,1,1,1,1,1,1,0,1,0,1,0,1,0,1,0,1},
                {1,1,0,0,0,0,0,0,0,1,0,1,0,1,0,1,0,1,0,1},
                {1,1,1,1,0,1,0,1,1,1,0,1,0,1,0,0,0,1,0,1},
                {1,1,1,0,0,1,0,0,0,0,0,1,0,1,1,1,1,1,0,1},
                {1,1,1,0,1,1,1,1,1,1,1,1,0,0,0,0,0,0,0,1},
                {1,1,1,0,0,0,0,0,0,0,0,1,1,1,1,1,1,1,0,1},
                {1,1,1,1,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
                {1,1,1,0,0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
                {1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
                {1,0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
                {1,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
                {1,0,1,0,1,0,1,1,1,1,1,1,1,1,0,1,1,1,1,1},
                {1,0,1,0,1,0,1,0,0,0,0,0,0,1,0,0,0,0,0,1},
                {1,0,1,0,1,0,1,0,1,1,0,1,0,1,1,1,1,1,1,1},
                {1,0,1,0,1,0,1,0,1,0,0,1,0,1,0,0,0,0,0,1},
                {1,0,1,0,1,0,1,0,1,0,1,1,0,1,0,1,1,1,0,1},
                {1,0,1,0,1,0,1,0,1,0,1,1,0,1,0,1,0,0,0,1},
                {1,0,1,0,1,0,1,0,1,0,0,1,0,1,0,1,0,1,1,1},
                {1,0,0,0,1,0,0,0,0,0,0,1,0,0,0,1,0,0,0,1},
                {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,0,1},
                {1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
                {1,0,1,0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
                {1,0,1,0,0,0,0,0,0,1,1,1,1,1,1,1,1,0,0,1},
                {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},};

        for (int i = 0; i < Impossible.length ; i++) {
            for (int j = 0; j < Impossible[i].length ; j++) {
                if (Impossible[i][j] == 1){
                    addWallToRoom(i,j,objects,maze);
                }
            }
        }






    }


    /**
     * This is a very simple maze that is used to get an idea of how the maze works with only 2 walls
     */
    public void createSimpleMaze( List<Item> objects, Position[][] maze) {
        int [][] simpleMaze = {
                {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
                {1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
                {1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
                {1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
                {1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
                {1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
                {1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
                {1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
                {1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
                {1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
                {1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
                {1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
                {1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
                {1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
                {1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
                {1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
                {1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
                {1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
                {1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
                {1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,1},
                {1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,1},
                {1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,1},
                {1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,1},
                {1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,1},
                {1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,1},
                {1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,1},
                {1,0,0,0,0,0,0,0,1,1,1,1,1,1,1,1,1,1,0,1},
                {1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
                {1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
                {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
        };

        for (int i = 0; i < simpleMaze.length ; i++) {
            for (int j = 0; j < simpleMaze[i].length ; j++) {
                if (simpleMaze[i][j] == 1){
                    addWallToRoom(i,j,objects,maze);
                }
            }
        }






    }


    public int getWhichMaze() {
        return whichMaze;
    }

    public void setWhichMaze(int whichMaze) {
        this.whichMaze = whichMaze;
    }
}
