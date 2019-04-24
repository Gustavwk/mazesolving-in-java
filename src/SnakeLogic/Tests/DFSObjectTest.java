package SnakeLogic.Tests;

import SnakeLogic.Position;
import SnakeLogic.*;
import javafx.scene.paint.Color;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class DFSObjectTest {

    private ArrayList<Item> items = new ArrayList<Item>();
    private int width = 30;
    private int height = 20;
    private Room room = new Room();
    private Position[][] emptyMaze;
    private Position[][] simpleMaze;
    private Position[][] pacManMaze;
    private Position[][] closedMaze;
    private Position[][] impossibleMaze;
    private Goal goal;
    private DFSObject dfsObjectEmpty;
    private DFSObject dfsObjectPacMan;
    private DFSObject dfsObjectClosed;
    private DFSObject ddsObjectSimple;
    private DFSObject dfsObjectImpossible;

    @Before
    public void setUp() throws Exception {

        this.emptyMaze = room.populate(items,width,height,0);
        this.pacManMaze = room.populate(items,width,height,1);
        this.closedMaze = room.populate(items,width,height,2);
        this.simpleMaze = room.populate(items,width,height,3);
        this.impossibleMaze = room.populate(items,width,height,4);
        this.goal = new Goal(Color.GREEN, 28,18,emptyMaze,items);
        this.dfsObjectEmpty = new DFSObject(1,1,Color.RED,goal,emptyMaze);
        this.dfsObjectPacMan = new DFSObject(1,1,Color.RED,goal,pacManMaze);
        this.dfsObjectClosed = new DFSObject(1,1,Color.RED,goal,closedMaze);
        this.ddsObjectSimple = new DFSObject(1,1,Color.RED,goal,simpleMaze);
        this.dfsObjectImpossible = new DFSObject(1,1,Color.RED,goal,impossibleMaze);


    }
    @Test
    public void dfs() {
        System.out.println("TESTING ON : Empty Maze");
        assertEquals(dfsObjectEmpty.dfs(goal.getPosition()),true);
        System.out.println();
    }
    @Test
    public void dfsReverse() {
        System.out.println("TESTING ON : Empty Maze");
        dfsObjectEmpty.setPosition(emptyMaze[28][18]);
        goal.setPosition(emptyMaze[1][1]);
        assertEquals(dfsObjectEmpty.dfs(goal.getPosition()),true);
        System.out.println();
    }
    @Test
    public void dfsPacMan(){

        System.out.println("TESTING ON : PacMan Maze");
        assertEquals(dfsObjectPacMan.dfs(goal.getPosition()),true);
        System.out.println();
    }
    @Test
    public void dfsClosedMaze(){


        System.out.println("TESTING ON : Closed Maze");
        assertEquals(dfsObjectClosed.dfs(goal.getPosition()),true);
        System.out.println();
    }
    @Test
    public void dfsSimpleMaze(){


        System.out.println("TESTING ON : Simple Maze");
        assertEquals(ddsObjectSimple.dfs(goal.getPosition()),true);
        System.out.println();
    }
    @Test
    public void dfsImpossibleMaze(){

        System.out.println("TESTING ON : Impossible Maze");
        assertEquals(dfsObjectImpossible.dfs(goal.getPosition()),false);
        System.out.println();
    }

}