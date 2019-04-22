package SnakeGUI;


import SnakeLogic.*;
import javafx.animation.AnimationTimer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;

import java.util.*;

public class Controller {

    @FXML
    Label labelStatus;
    @FXML
    Canvas canvas;

    private double fieldHeight;
    private double fieldWidth;
    private int width = 30;
    private int height = 20;
    private Random random = new Random();
    private int gameLoopDelay = 500;
    private float refreshRate = 300;
    private KeyCode keyPressed = KeyCode.BACK_SPACE;
    private boolean takeoff = false;
    private Position startingPoint = new Position(1,1);
    private Player player = new Player(5,5, Color.WHITE);
    private RandomRambler ranRam = new RandomRambler(1,1, Color.YELLOW);
    private ArrayList<Item> items = new ArrayList<Item>();
    private Room room = new Room();
    private Position[][] maze = room.populate(items,width,height);
    private Goal goal = new Goal(Color.GREEN, 28 ,18,maze,items);
    private DFSObject dfsObject = new DFSObject(startingPoint.getX(),startingPoint.getY(), Color.RED, goal, maze);
    private GreedObject greedyObject = new GreedObject(startingPoint.getX(),startingPoint.getY(),Color.PURPLE, goal,maze);
    private BFSObject bfsObject = new BFSObject(startingPoint.getX(),startingPoint.getY(),Color.GREEN, goal,maze);


    public void btnStartAction(ActionEvent event)
    {
        room.layFloor(items,maze);
        room.createPacManMaze(items,maze);
        drawCanvas();
    }


    @FXML
    public void greedyShowPath(ActionEvent event)
    {
        room.markPath(greedyObject.getGoPath(),items,Color.DARKVIOLET);
        drawCanvas();
    }
    @FXML
    public void dfsShowPath(ActionEvent event)
    {
        room.markPath(dfsObject.getGoPath(),items,Color.DARKRED);
        drawCanvas();
    }
    @FXML
    public void bfsShowPath(ActionEvent event)
    {

        room.markPath(bfsObject.getTree().getMarked(),items,Color.DARKGOLDENROD);

        room.markPath(bfsObject.getGoPath(),items, Color.YELLOW);
        drawCanvas();
    }


    /**
     * Executed when JavaFX is initialized. Used to setup the Snake game
     */

    public void initialize()
    {

        goal.initMazeCost(maze);
        bfsObject.bfs(bfsObject.getPosition());
        dfsObject.dfs(goal.getPosition());
        greedyObject.bestFirst(goal.getPosition());
        takeoff = true;

        addItems();
        calculateFields();
        getRandomPosition();

        new AnimationTimer(){
            long lastUpdate;
            public void handle (long now)
            {
                if (now > lastUpdate + refreshRate * 1000000)
                {
                    lastUpdate = now;
                    update(now);
                }             }
        }.start();

    }

    private void addItems() {

    }

    private void update(long now)
    {
        drawCanvas();

        if (takeoff) {
            player.update();
            ranRam.update();
            ranRam.wallCollision(items);
            dfsObject.update();
            goal.update();
            greedyObject.update();
            bfsObject.update();
        }

    }


    private void getRandomPosition() {
        player.setX(random.nextInt(width));
        player.setY(random.nextInt(height));
    }

    private void calculateFields() {
        this.fieldHeight = canvas.getHeight() / this.height;
        this.fieldWidth = canvas.getWidth() / this.width;
    }

    private void drawCanvas() {
        GraphicsContext g = canvas.getGraphicsContext2D();
        g.clearRect(0,0,width*fieldWidth ,height*fieldHeight);

        for (Item item : items) {
            item.drawObject(g,fieldWidth,fieldHeight);

        }

        player.drawObject(g,fieldWidth,fieldHeight);
        ranRam.drawObject(g,fieldWidth,fieldHeight);
        dfsObject.drawObject(g,fieldWidth,fieldHeight);
        goal.drawObject(g,fieldWidth,fieldHeight);
        greedyObject.drawObject(g,fieldWidth,fieldHeight);



    }


    public Player getPlayer() {
        return player;
    }
}

