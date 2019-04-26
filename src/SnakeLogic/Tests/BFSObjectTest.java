package SnakeLogic.Tests;

import SnakeLogic.Position;
import SnakeLogic.*;
import javafx.scene.paint.Color;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

/**
 * This is a testing class for the BFS algorithm. It can test the algorithm on a number of mazes and give back relevant stats
 */
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
    private Goal goalEmpty;
    private Goal goalPacMan;
    private Goal goalClosed;
    private Goal goalSimple;
    private Goal goalImpossible;
    private BFSObject bfsObjectEmpty;
    private BFSObject bfsObjectPacMan;
    private BFSObject bfsObjectClosed;
    private BFSObject bfsObjectSimple;
    private BFSObject bfsObjectImpossible;
    private Position goal = new Position(28,18);
    private Position start = new Position(1,1);


    /**
     * This test uses 4 different BFS Objects and 4 different mazes. The setup has to construct every one of them before the test begins.
     */
    @org.junit.Before
    public void setUp() {

        this.emptyMaze = room.populate(items,width,height,0);
        this.pacManMaze = room.populate(items,width,height,1);
        this.closedMaze = room.populate(items,width,height,2);
        this.simpleMaze = room.populate(items,width,height,3);
        this.impossibleMaze = room.populate(items,width,height,4);

        this.goalEmpty = new Goal(Color.GREEN,goal.getX(),goal.getY(),emptyMaze,items);
        this.goalPacMan = new Goal(Color.GREEN,goal.getX(),goal.getY(),pacManMaze,items);
        this.goalClosed = new Goal(Color.GREEN,goal.getX(),goal.getY(),closedMaze,items);
        this.goalSimple = new Goal(Color.GREEN,goal.getX(),goal.getY(),simpleMaze,items);
        this.goalImpossible = new Goal(Color.GREEN,goal.getX(),goal.getY(),impossibleMaze,items);

        this.bfsObjectEmpty = new BFSObject(start.getX(),start.getY(),Color.RED,goalEmpty,emptyMaze);
        this.bfsObjectPacMan = new BFSObject(start.getX(),start.getY(),Color.RED,goalPacMan,pacManMaze);
        this.bfsObjectClosed= new BFSObject(start.getX(),start.getY(),Color.RED,goalClosed,closedMaze);
        this.bfsObjectSimple= new BFSObject(start.getX(),start.getY(),Color.RED,goalSimple,simpleMaze);
        this.bfsObjectImpossible= new BFSObject(start.getX(),start.getY(),Color.RED,goalImpossible,impossibleMaze);



    }


    /**
     * Testing the BFS Object in an Empty room
     */
    @org.junit.Test
    public void bfsOnEmptyMaze() {


        System.out.println("TESTING ON : Empty Maze");
        assertEquals(bfsObjectEmpty.bfs(bfsObjectEmpty.getPosition()),true);

        System.out.println();
    }

    /**
     * Same as above, but with goal and start switched
     */
    @org.junit.Test
    public void bfsOnEmptyMazeReverse() {
        goalEmpty.setPosition(emptyMaze[28][18]);
        bfsObjectEmpty.setPosition(emptyMaze[1][1]);
        System.out.println("TESTING ON : Empty Maze Reversed");
        assertEquals(bfsObjectEmpty.bfs(bfsObjectEmpty.getPosition()),true);
        System.out.println();

    }

    /**
     * Tests the BFS Object on a Pac-Mac-like maze
     */
    @org.junit.Test
    public void bfsOnPacManMaze() {
        System.out.println("TESTING ON : PacMan Maze");
        assertEquals(bfsObjectPacMan.bfs(bfsObjectPacMan.getPosition()),true);
        System.out.println();
    }

    /**
     * This is a test om how the BFS Object performs in a maze with dead-ends
     */
    @org.junit.Test
    public void bfsOnClosedMaze() {
        System.out.println("TESTING ON : Closed Maze");
        assertEquals(bfsObjectClosed.bfs(bfsObjectClosed.getPosition()),true);
        System.out.println();
    }

    /**
     * This is a test on how the BFS Object performs on a Empty room with just 2 walls - therefor the name "Simple Maze".
     */
    @org.junit.Test
    public void bfsOnSimpleMaze() {
        System.out.println("TESTING ON : Simple Maze");
        assertEquals(bfsObjectSimple.bfs(bfsObjectSimple.getPosition()),true);
        System.out.println();
    }

    /**
     * This is a test on how to BFS Object performs on an Unsolvable/Impossible Maze
     */
    @org.junit.Test
    public void bfsOnImpossibleMaze() {
        System.out.println("TESTING ON : Impossible Maze");
        assertEquals(bfsObjectImpossible.bfs(bfsObjectImpossible.getPosition()),false);
        System.out.println();
    }



}