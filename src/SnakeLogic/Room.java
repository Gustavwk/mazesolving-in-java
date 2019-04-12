package SnakeLogic;

import SnakeGUI.Position;

import javafx.scene.paint.Color;

import java.util.LinkedList;
import java.util.List;

public class Room {


    public Position[][] populate(List<Item> objects, int width, int height) {

        Position[][] maze = new Position[width][height];
        maze = initMazArray(width, height, maze);
        layFloor(objects, maze);
        mazeBoarders(width, height, objects, maze);
        createPacManMaze(objects,maze);


        return maze;
    }

    public boolean markPath(List<Position> marked, List<Item> objects, Color color){
        int howManyMarked = 0;

        for (Position pos: marked) {
            addMarkedTileToRoom(pos.getX(),pos.getY(),objects, color);
            howManyMarked++;
        }
        System.out.println(howManyMarked);

        return true;
    }


    public boolean addWallToRoom(int x, int y, List<Item> objects, Position[][] maze) {

        Wall wall = new Wall(x, y);
        objects.add(wall);


        maze[x][y].setOccupied(true);
        maze[x][y].setEdge(true);


        return true;
    }
    public boolean addMarkedTileToRoom(int x, int y, List<Item> objects, Color color) {

        Tile tile = new Tile(x, y, color);
        objects.add(tile);


        return true;
    }

    public boolean addTileToRoom(int x, int y, List<Item> objects) {

        Tile tile = new Tile(x, y, Color.BLACK);
        objects.add(tile);


        return true;
    }

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

    public Position[][] initMazArray(int width, int height, Position[][] maze) {
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                maze[i][j] = new Position(i, j);
            }


        }
        return maze;
    }

    public boolean layFloor(List objects, Position[][] maze) {
        for (int i = 0; i < maze.length; i++) {
            for (int j = 0; j < maze[i].length; j++) {
                addTileToRoom(i, j, objects);
            }
        }
        return true;
    }

    public void createPacManMaze( List<Item> objects, Position[][] maze) {
        int [][] maZeetArray = {
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

        for (int i = 0; i < maZeetArray.length ; i++) {
            for (int j = 0; j < maZeetArray[i].length ; j++) {
                if (maZeetArray[i][j] == 1){
                    addWallToRoom(i,j,objects,maze);
                }
            }
        }






    }


}
