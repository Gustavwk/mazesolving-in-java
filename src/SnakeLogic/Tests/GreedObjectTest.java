package SnakeLogic.Tests;

import SnakeGUI.Position;
import SnakeLogic.Goal;
import SnakeLogic.GreedObject;
import SnakeLogic.Item;
import SnakeLogic.Room;
import javafx.geometry.Pos;
import javafx.scene.paint.Color;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.LinkedList;

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
    private Goal goal;
    private GreedObject greedyObjectEmpty;
    private GreedObject greedyObjectPacMan;
    private GreedObject greedyObjectClosed;
    private GreedObject greedyObjectSimple;
    private GreedObject greedyObjectImpossible;

    @Before
    public void setUp() throws Exception {
        this.emptyMaze = room.populate(items,width,height,0);
        this.pacManMaze = room.populate(items,width,height,1);
        this.closedMaze = room.populate(items,width,height,2);
        this.simpleMaze = room.populate(items,width,height,3);
        this.impossibleMaze = room.populate(items,width,height,4);
        this.goal = new Goal(Color.GREEN, 28,18,emptyMaze,items);
        this.greedyObjectEmpty = new GreedObject(1,1,Color.RED,goal,emptyMaze);
        this.greedyObjectPacMan = new GreedObject(1,1,Color.RED,goal,simpleMaze);
        this.greedyObjectClosed = new GreedObject(1,1,Color.RED,goal,pacManMaze);
        this.greedyObjectSimple = new GreedObject(1,1,Color.RED,goal,closedMaze);
        this.greedyObjectImpossible = new GreedObject(1,1,Color.RED,goal,impossibleMaze);

    }

    @Test
    public void bestFirst() {
        System.out.println("TESTING ON : Empty Maze");
        goal.initMazeCost(emptyMaze);
        assertEquals(greedyObjectEmpty.bestFirst(goal.getPosition()),true);
        System.out.println();
    }
    @Test
    public void bestFirstReverse() {
        System.out.println("TESTING ON : Empty Maze - Reversed");
        goal.setPosition(emptyMaze[1][1]);
        goal.initMazeCost(emptyMaze);
        greedyObjectEmpty.setPosition(emptyMaze[28][18]);
        assertEquals(greedyObjectEmpty.bestFirst(goal.getPosition()),true);
        System.out.println();

    }
    @Test
    public void bestFirstPacMan() {
        System.out.println("TESTING ON : PacMan Maze");
        goal.initMazeCost(pacManMaze);
        assertEquals(greedyObjectPacMan.bestFirst(goal.getPosition()),true);
        System.out.println();

    }
    @Test
    public void bestFirstClosed() {
        System.out.println("TESTING ON : Closed Maze");
        goal.initMazeCost(closedMaze);
        assertEquals(greedyObjectClosed.bestFirst(goal.getPosition()),true);
        System.out.println();

    }
    @Test
    public void bestFirstSimple() {
        System.out.println("TESTING ON : Simple Maze");
        goal.initMazeCost(simpleMaze);
        assertEquals(greedyObjectSimple.bestFirst(goal.getPosition()),true);
        System.out.println();

    }
    @Test
    public void bestFirstImpossible() {
        System.out.println("TESTING ON : Impossible Maze");
        goal.initMazeCost(impossibleMaze);
        assertEquals(greedyObjectImpossible.bestFirst(goal.getPosition()),false);
        System.out.println();

    }

    @Test
    public void nextStep() {
        goal.initMazeCost(emptyMaze);
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