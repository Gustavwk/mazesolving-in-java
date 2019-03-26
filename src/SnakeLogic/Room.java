package SnakeLogic;

import SnakeGUI.Position;
import SnakeLogic.GameObject;
import SnakeLogic.Wall;

import javafx.geometry.Pos;
import javafx.scene.paint.Color;

import java.util.List;

public class Room {


    public Position[][] populate(List<Item> objects, int width, int height) {

        Position[][] maze = new Position[width][height];
        maze = initMazArray(width, height, maze);
        mazeBoarders(width, height, objects, maze);



        for (int i = 0; i < height / 2; i++) {

            addWallToRoom(20, i, objects, maze);
            addWallToRoom(10, i, objects, maze);
        }
        return maze;
    }

    public boolean addWallToRoom(int x, int y, List<Item> objects, Position[][] maze) {

        Wall wall = new Wall(x, y, Color.BLACK);
        objects.add(wall);
        maze[x][y].setOccupied(true);
        maze[x][y].setEdge(true);


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
}
