package SnakeGUI;


import SnakeLogic.*;
import javafx.animation.AnimationTimer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.text.TextFlow;

import java.util.*;

public class Controller {

    @FXML
    TextFlow textFlow;
    @FXML
    Canvas canvas;

    private double fieldHeight;
    private double fieldWidth;
    private int width = 30;
    private int height = 20;

    private float refreshRate = 300;

    private Position startingPoint = new Position(1,1);
    private Room room = new Room();

    private ArrayList<Item> items = new ArrayList<Item>();
    private LinkedList<Ghost> ghosts = new LinkedList<>();


    /**
     * Change "whichMaze" to change the maze!
     *
     * 0 -> Empty room
     * 1 -> PacMan maze
     * 2 -> Maze with dead-ends
     * 3 -> Simple maze
     * 4 -> Unsolvable/Impossible Maze
     *
     */

    int whichMaze = 2;
    private Position[][] maze = room.populate(items,width,height, whichMaze);
    private Goal goal = new Goal(Color.GREEN, 28 ,18,maze,items);
    private DFSObject dfsObject = new DFSObject(startingPoint.getX(),startingPoint.getY(), Color.RED, goal, maze);
    private GreedObject greedyObject = new GreedObject(startingPoint.getX(),startingPoint.getY(),Color.PURPLE, goal,maze);
    private BFSObject bfsObject = new BFSObject(startingPoint.getX(),startingPoint.getY(),Color.DARKORANGE, goal,maze);




    public void btnStartAction(ActionEvent event)
    {
        room.populate(items,width,height, whichMaze);
        drawCanvas();
    }

    @FXML
    public void dataDisplay(ActionEvent event){
        DataDisplay dataDisplay = new DataDisplay(ghosts);
        dataDisplay.initialiseData();

        for (String data: dataDisplay.getDataList()) {
            System.out.println(data); // der skal stå noget andet her

        }

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




    public void initialize()
    {


        goal.initMazeCost(maze);
        dfsObject.dfs(goal.getPosition());
        greedyObject.bestFirst(goal.getPosition());
        bfsObject.bfs(bfsObject.getPosition());

        ghosts.add(dfsObject);
        ghosts.add(greedyObject);
        ghosts.add(bfsObject);






        addItems();
        calculateFields();


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

        for (Ghost ghost: ghosts) {
            if (ghost.isPossible()){
                ghost.update();
            }

        }







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

        for (Ghost ghost: ghosts) {
            ghost.drawObject(g,fieldWidth,fieldHeight);
        }







    }


}

