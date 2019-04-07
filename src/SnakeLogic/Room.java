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
        layFloor(objects, maze);
        mazeBoarders(width, height, objects, maze);
        createPacManMaze(objects,maze);


        return maze;
    }


    public boolean addWallToRoom(int x, int y, List<Item> objects, Position[][] maze) {

        Wall wall = new Wall(x, y);
        objects.add(wall);


        maze[x][y].setOccupied(true);
        maze[x][y].setEdge(true);


        return true;
    }

    public boolean addTileToRoom(int x, int y, List<Item> objects, Position[][] maze) {

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
                addTileToRoom(i, j, objects, maze);
            }
        }
        return true;
    }

    public void createPacManMaze( List<Item> objects, Position[][] maze) {

        //////////////////////////////////////////////////
        for (int i = 7; i <13 ; i++) {
            addWallToRoom(4,i,objects,maze);
        }

        for (int i = 2; i < 8 ; i++) {
            addWallToRoom(2,i,objects,maze);
        }


        for (int i = 2; i < 5 ; i++) {
            addWallToRoom(i,5,objects,maze);
        }
        //////////////////////////////////////////////////



        //////////////////////////////////////////////////
        for (int i = 2; i < 4 ; i++) {
            addWallToRoom(i,9,objects,maze);
            addWallToRoom(i,10,objects,maze);
        }

        for (int i = 7; i <13 ; i++) {
            addWallToRoom(4,i,objects,maze);
        }

        for (int i = 2; i < 8 ; i++) {
            addWallToRoom(2,i,objects,maze);
        }
        //////////////////////////////////////////////////



        //////////////////////////////////////////////////
        for (int i = 12; i < 18  ; i++) {
            addWallToRoom(2,i,objects,maze);
        }

        for (int i = 2; i < 5 ; i++) {
            addWallToRoom(i,14,objects,maze);
        }
        //////////////////////////////////////////////////



        //////////////////////////////////////////////////
        for (int i = 7; i < 13  ; i++) {
            addWallToRoom(6,i,objects,maze);
        }

        for (int i = 6; i < 9 ; i++) {
            addWallToRoom(i,9,objects,maze);
            addWallToRoom(i,10,objects,maze);
        }
        //////////////////////////////////////////////////



        //////////////////////////////////////////////////
        for (int i = 13; i < 18 ; i++) {
            addWallToRoom(i,12,objects,maze);
            if (i!=15) {
                addWallToRoom(i, 8, objects, maze);
            }
        }
        for (int i = 9; i < 12 ; i++) {
            addWallToRoom(13,i,objects,maze);
            addWallToRoom(17,i,objects,maze);

        }
        //////////////////////////////////////////////////



        //////////////////////////////////////////////////
        for (int i = 2; i < 8 ; i++) {
            addWallToRoom(27,i,objects,maze);
        }

        for (int i = 25; i < 28 ; i++) {
            addWallToRoom(i,5,objects,maze);
        }
        //////////////////////////////////////////////////




        //////////////////////////////////////////////////

        for (int i = 25; i < 28 ; i++) {
            addWallToRoom(i,9,objects,maze);
            addWallToRoom(i,10,objects,maze);

        }

        //////////////////////////////////////////////////

        //////////////////////////////////////////////////

        for (int i = 21; i < 24 ; i++) {
            addWallToRoom(i,9,objects,maze);
            addWallToRoom(i,10,objects,maze);

        }

        for (int i = 7; i < 13 ; i++) {
            addWallToRoom(25,i,objects,maze);
        }

        for (int i = 7 ; i < 13 ; i++) {
            addWallToRoom(23,i,objects,maze);
        }
        //////////////////////////////////////////////////

        //////////////////////////////////////////////////
        for (int i = 12; i < 18 ; i++) {
            addWallToRoom(27,i,objects,maze);
        }

        for (int i = 25; i < 28 ; i++) {
            addWallToRoom(i,14,objects,maze);
        }
        //////////////////////////////////////////////////

        //////////////////////////////////////////////////
        //////////////////////////////////////////////////

        //////////////////////////////////////////////////
        //////////////////////////////////////////////////

        //////////////////////////////////////////////////
        //////////////////////////////////////////////////

        //////////////////////////////////////////////////
        //////////////////////////////////////////////////

        //////////////////////////////////////////////////
        //////////////////////////////////////////////////

        //////////////////////////////////////////////////
        //////////////////////////////////////////////////

        //////////////////////////////////////////////////
        //////////////////////////////////////////////////

        //////////////////////////////////////////////////
        //////////////////////////////////////////////////

        //////////////////////////////////////////////////
        //////////////////////////////////////////////////

        //////////////////////////////////////////////////
        //////////////////////////////////////////////////

        //////////////////////////////////////////////////
        //////////////////////////////////////////////////

        //////////////////////////////////////////////////
        //////////////////////////////////////////////////

        //////////////////////////////////////////////////
        //////////////////////////////////////////////////

        //////////////////////////////////////////////////
        //////////////////////////////////////////////////

        //////////////////////////////////////////////////
        //////////////////////////////////////////////////

        //////////////////////////////////////////////////
        //////////////////////////////////////////////////

        //////////////////////////////////////////////////
        //////////////////////////////////////////////////







    }


}
