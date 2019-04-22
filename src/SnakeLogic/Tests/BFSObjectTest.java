package SnakeLogic.Tests;

import SnakeGUI.Position;
import SnakeLogic.*;
import javafx.scene.paint.Color;
import org.junit.Assert;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class BFSObjectTest {

    private ArrayList<Item> items = new ArrayList<Item>();
    private int width = 30;
    private int height = 20;
    private Room room = new Room();
    private Position[][] maze = room.populate(items,width,height);
    private Goal goal = new Goal(Color.GREEN, 1 ,28,maze,items);
    private BFSObject bfsObject = new BFSObject(1,1, Color.YELLOW,goal,maze);



    @org.junit.Before
    public void setUp() throws Exception {

        maze = room.populate(items,width,height);
        goal.initMazeCost(maze);
    }

    @org.junit.After
    public void tearDown() throws Exception {
    }

    @org.junit.Test
    public void mapToRoot() {
    }

    @org.junit.Test
    public void bfsOnPacManMaze() {

        assertEquals(bfsObject.bfs(bfsObject.getPosition()),true);
    }


}