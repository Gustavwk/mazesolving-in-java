package SnakeLogic;

import SnakeGUI.Position;
import SnakeLogic.GameObject;
import SnakeLogic.Wall;
import com.sun.deploy.util.BlackList;
import javafx.scene.paint.Color;

import java.util.List;

public class Room
{
    //TileTree tilesInRoom = new TileTree();


public Position[][] populate(List<Item> objects, int width, int height){

    Position[][] room = new Position[width][height];

    for (int i = 0; i < width ; i++) {
        for (int j = 0; j < height ; j++) {
            room[i][j] = new Position(i,j);
        }


    }

    for (int i = 0; i < width ; i++) {
        for (int j = 0; j < height; j ++) {

            if(i == 0 || i == width-1) {

                Wall wall = new Wall(i, j, Color.BLACK);
                objects.add(wall);
                room[i][j].setOccupied(true);


            }

            if(j == 0 || j == height-1) {

                Wall wall = new Wall(i, j, Color.BLACK);
                objects.add(wall);
                room[i][j].setOccupied(true);
            }
        }
    }
    //This is a good comment to goood to be true actually

    for (int i = 0; i < height/2 ; i++) {

        Wall wall = new Wall(20,i,Color.BLACK);
        Wall wall2 = new Wall(10,i,Color.BLACK);
        objects.add(wall);
        objects.add(wall2);
        room[20][i].setOccupied(true);
        room[10][i].setOccupied(true);


    }
    return room;
}
}
