package SnakeLogic.Tests;

import SnakeLogic.Position;
import SnakeLogic.*;
import javafx.scene.paint.Color;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class BFSObjectTest {

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
    private BFSObject bfsObjectEmpty;
    private BFSObject bfsObjectPacMan;
    private BFSObject bfsObjectClosed;
    private BFSObject bfsObjectSimple;
    private BFSObject bfsObjectImpossible;



    @org.junit.Before
    public void setUp() {

        this.emptyMaze = room.populate(items,width,height,0);
        this.pacManMaze = room.populate(items,width,height,1);
        this.closedMaze = room.populate(items,width,height,2);
        this.simpleMaze = room.populate(items,width,height,3);
        this.impossibleMaze = room.populate(items,width,height,4);
        this.goal = new Goal(Color.GREEN,28,18,emptyMaze,items);
        this.bfsObjectEmpty = new BFSObject(1,1,Color.RED,goal,emptyMaze);
        this.bfsObjectPacMan = new BFSObject(1,1,Color.RED,goal,pacManMaze);
        this.bfsObjectClosed= new BFSObject(1,1,Color.RED,goal,closedMaze);
        this.bfsObjectSimple= new BFSObject(1,1,Color.RED,goal,simpleMaze);
        this.bfsObjectImpossible= new BFSObject(1,1,Color.RED,goal,impossibleMaze);



    }


    @org.junit.Test
    public void bfsOnEmptyMaze() {

        System.out.println("TESTING ON : Empty Maze");
        assertEquals(bfsObjectEmpty.bfs(bfsObjectEmpty.getPosition()),true);
        System.out.println();
    }
    @org.junit.Test
    public void bfsOnEmptyMazeReverse() {
        goal.setPosition(emptyMaze[28][18]);
        bfsObjectEmpty.setPosition(emptyMaze[1][1]);
        System.out.println("TESTING ON : Empty Maze Reversed");
        assertEquals(bfsObjectEmpty.bfs(bfsObjectEmpty.getPosition()),true);
        System.out.println();

    }
    @org.junit.Test
    public void bfsOnPacManMaze() {
        System.out.println("TESTING ON : PacMan Maze");
        assertEquals(bfsObjectPacMan.bfs(bfsObjectPacMan.getPosition()),true);
        System.out.println();
    }
    @org.junit.Test
    public void bfsOnClosedMaze() {
        System.out.println("TESTING ON : Closed Maze");
        assertEquals(bfsObjectClosed.bfs(bfsObjectClosed.getPosition()),true);
        System.out.println();
    }
    @org.junit.Test
    public void bfsOnSimpleMaze() {
        System.out.println("TESTING ON : Simple Maze");
        assertEquals(bfsObjectSimple.bfs(bfsObjectSimple.getPosition()),true);
        System.out.println();
    }
    @org.junit.Test
    public void bfsOnImpossibleMaze() {
        System.out.println("TESTING ON : Impossible Maze");
        assertEquals(bfsObjectImpossible.bfs(bfsObjectImpossible.getPosition()),false);
        System.out.println();
    }



}