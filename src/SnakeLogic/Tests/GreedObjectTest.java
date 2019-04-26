package SnakeLogic.Tests;

import SnakeLogic.Position;
import SnakeLogic.Goal;
import SnakeLogic.GreedObject;
import SnakeLogic.Item;
import SnakeLogic.Room;
import javafx.scene.paint.Color;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class GreedObjectTest {
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
    private Goal goalPacman;
    private Goal goalSimple;
    private Goal goalClosed;
    private Goal goalImpossible;
    private GreedObject greedyObjectEmpty;
    private GreedObject greedyObjectPacMan;
    private GreedObject greedyObjectClosed;
    private GreedObject greedyObjectSimple;
    private GreedObject greedyObjectImpossible;

    /**
     * This test uses 4 different Greed Objects and 4 different mazes. The setup has to construct every one of them before the test begins.
     */
    @Before
    public void setUp() throws Exception {
        this.emptyMaze = room.populate(items,width,height,0);
        this.pacManMaze = room.populate(items,width,height,1);
        this.closedMaze = room.populate(items,width,height,2);
        this.simpleMaze = room.populate(items,width,height,3);
        this.impossibleMaze = room.populate(items,width,height,4);

        this.goalEmpty = new Goal(Color.GREEN, 28,18,emptyMaze,items);
        this.goalClosed = new Goal(Color.GREEN, 28,18,emptyMaze,items);
        this.goalImpossible = new Goal(Color.GREEN, 28,18,emptyMaze,items);
        this.goalPacman = new Goal(Color.GREEN, 28,18,emptyMaze,items);
        this.goalSimple = new Goal(Color.GREEN, 28,18,emptyMaze,items);

        this.greedyObjectEmpty = new GreedObject(1,1,Color.RED,goalEmpty,emptyMaze);
        this.greedyObjectPacMan = new GreedObject(1,1,Color.RED,goalPacman,pacManMaze);
        this.greedyObjectClosed = new GreedObject(1,1,Color.RED,goalClosed,closedMaze);
        this.greedyObjectSimple = new GreedObject(1,1,Color.RED,goalSimple,simpleMaze);
        this.greedyObjectImpossible = new GreedObject(1,1,Color.RED,goalImpossible,impossibleMaze);

    }

    /**
     * This is a test on how the Greed Object Performs in an empty room
     */
    @Test
    public void bestFirst() {
        System.out.println("TESTING ON : Empty Maze");
        goalEmpty.initMazeCost(emptyMaze);
        assertEquals(greedyObjectEmpty.bestFirst(goalEmpty.getPosition()),true);
        System.out.println();
    }

    /**
     * This is a test on how the Greed Object Performs in an empty room, but with the goal and the start positions switched
     */
    @Test
    public void bestFirstReverse() {
        System.out.println("TESTING ON : Empty Maze - Reversed");
        goalEmpty.setPosition(emptyMaze[1][1]);
        goalEmpty.initMazeCost(emptyMaze);
        greedyObjectEmpty.setPosition(emptyMaze[28][18]);
        assertEquals(greedyObjectEmpty.bestFirst(goalEmpty.getPosition()),true);
        System.out.println();

    }

    /**
     * This is a test on how the Greed Object Performs in a "Pac-Man-Like" maze
     */
    @Test
    public void bestFirstPacMan() {
        System.out.println("TESTING ON : PacMan Maze");
        goalPacman.initMazeCost(pacManMaze);
        assertEquals(greedyObjectPacMan.bestFirst(goalPacman.getPosition()),true);
        System.out.println();

    }

    /**
     * This is a test on how the Greed Object Performs in a maze with dead-ends
     */
    @Test
    public void bestFirstClosed() {
        System.out.println("TESTING ON : Closed Maze");
        goalClosed.initMazeCost(closedMaze);
        assertEquals(greedyObjectClosed.bestFirst(goalClosed.getPosition()),true);
        System.out.println();

    }

    /**
     * This is a test on how the Greed Object Performs in an Empty Room with only 2 walls
     */
    @Test
    public void bestFirstSimple() {
        System.out.println("TESTING ON : Simple Maze");
        goalSimple.initMazeCost(simpleMaze);
        assertEquals(greedyObjectSimple.bestFirst(goalSimple.getPosition()),true);
        System.out.println();

    }

    /**
     * This is a test on how the Greed Object Performs in an unsolvable / impossible maze
     */
    @Test
    public void bestFirstImpossible() {
        System.out.println("TESTING ON : Impossible Maze");
        goalImpossible.initMazeCost(impossibleMaze);
        assertEquals(greedyObjectImpossible.bestFirst(goalImpossible.getPosition()),false);
        System.out.println();

    }

    /**
     * This is a test on how to Greedy Object evaluates the "next step" on its path to the goal.
     */
    @Test
    public void nextStep() {
        goalEmpty.initMazeCost(emptyMaze);
        Position startPosition = emptyMaze[5][5];
        Position expectedPosition = emptyMaze[6][5];
        System.out.println("TESTING ON : Greedy nextStep() :" + "\nStartPosition: " + startPosition + "\nExpected Position: " + expectedPosition);
        assertEquals((greedyObjectEmpty.nextStep(startPosition,greedyObjectEmpty.getVisited())),expectedPosition);
        System.out.println();
        System.out.println("The actual result : " + greedyObjectEmpty.nextStep(startPosition,greedyObjectEmpty.getVisited()));
        System.out.println("The expected result : " + expectedPosition);
        System.out.println();
    }
}