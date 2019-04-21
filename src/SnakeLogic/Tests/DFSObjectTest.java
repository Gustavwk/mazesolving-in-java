package SnakeLogic.Tests;

import SnakeGUI.Position;
import SnakeLogic.*;
import javafx.scene.paint.Color;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class DFSObjectTest {
    private ArrayList<Item> items = new ArrayList<Item>();
    private int width = 30;
    private int height = 20;
    private Room room = new Room();
    private Position[][] maze;
    private Goal goal;
    private DFSObject dfsObject;

    @org.junit.Before
    public void setUp() throws Exception {

        this.maze = room.populate(items,width,height);
        this.goal = new Goal(Color.GREEN, 1 ,28,maze,items);
        this.dfsObject = new DFSObject(1,1,Color.RED,goal,maze);

    }

    @Test
    public void dfs() {
        assertEquals(dfsObject.dfs(goal.getPosition()),true);

    }
}