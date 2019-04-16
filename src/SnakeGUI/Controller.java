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








    Player player = new Player(5,5, Color.WHITE);
    RandomRambler ranRam = new RandomRambler(1,1, Color.YELLOW);
    ArrayList<Item> items = new ArrayList<Item>();
    Room room = new Room();
    Position[][] maze = room.populate(items,width,height);
    Goal defaultGoal = new Goal(Color.GREEN, 15,10,maze,items);


    DFSObject dfsCrawler = new DFSObject(1,1, Color.RED,defaultGoal, maze);
    GreedObject greedGhost = new GreedObject(1,1,Color.PURPLE,defaultGoal,maze); // kan ikke finde vej på givne steder - bla hvis goal er i (1.28)
    BFSObject bfsGhost = new BFSObject(1,1,Color.GREEN,defaultGoal,maze);




    public void btnStartAction(ActionEvent event)
    {
        room.layFloor(items,maze);
        room.createPacManMaze(items,maze);
        drawCanvas();
    }

    @FXML
    public void reset(){
        dfsCrawler.setPosition(maze[1][1]);
        dfsCrawler.getGoPath().clear();
        greedGhost.setPosition(maze[1][1]);
        greedGhost.getGoPath().clear();
        takeoff = false;

    }

    @FXML
    public void go() {
        defaultGoal.initMazeCost(defaultGoal.getPosition(),maze);
        dfsCrawler.dfs(dfsCrawler.getPosition(),defaultGoal.getPosition());
        greedGhost.bestFirst(greedGhost.getPosition(),defaultGoal.getPosition());
        takeoff = true;
    }

    @FXML
    public void greedyShowPath(ActionEvent event)
    {
        room.markPath(greedGhost.getGoPath(),items,Color.DARKGREEN);
        drawCanvas();
    }
    @FXML
    public void dfsShowPath(ActionEvent event)
    {
        room.markPath(dfsCrawler.getGoPath(),items,Color.DARKRED);
        drawCanvas();
    }
    @FXML
    public void bfsShowPath(ActionEvent event)
    {
        room.markPath(bfsGhost.getGoPath(),items, Color.DARKGOLDENROD);
        drawCanvas();
    }


    /**
     * Executed when JavaFX is initialized. Used to setup the Snake game
     */

    public void initialize()
    {



        PositionTree<Position> tree = new PositionTree<>(maze);
        bfsGhost.bfs(bfsGhost.getPosition());

        tree.initTree(bfsGhost.getPosition());


        System.out.println(maze[1][1].getEast());




        room.markPath(tree.getMarked(),items,Color.RED);



















        addItems();
        calculateFields();
        //This control the start position of the player.
        getRandomPosition();

        // Start and control game loop
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



        //print2D(maze);








    }



    /**
     * Game loop - executed continously during the game
     * @param now game time in nano seconds
     */


    private void update(long now)
    {
        drawCanvas();

        if (takeoff) {

            player.update();
            ranRam.update();
            ranRam.wallCollision(items);
            dfsCrawler.update();
            defaultGoal.update();
            greedGhost.update();

        }

    }

    /**
     * Get a random position
     */

    private void getRandomPosition() {
        player.setX(random.nextInt(width));
        player.setY(random.nextInt(height));
    }

    /**
     * Calculate height and width of each field
     */
    private void calculateFields() {
        this.fieldHeight = canvas.getHeight() / this.height;
        this.fieldWidth = canvas.getWidth() / this.width;
    }

    /**
     * Draw the canvas - used in the gameloop
     */
    private void drawCanvas() {
        GraphicsContext g = canvas.getGraphicsContext2D();
        //Clear everything

        g.clearRect(0,0,width*fieldWidth ,height*fieldHeight);


        // draw items
        for (Item item : items)
        {
            item.drawObject(g,fieldWidth,fieldHeight);

        }



        // draw 'player'
        player.drawObject(g,fieldWidth,fieldHeight);
        ranRam.drawObject(g,fieldWidth,fieldHeight);
        dfsCrawler.drawObject(g,fieldWidth,fieldHeight);
        defaultGoal.drawObject(g,fieldWidth,fieldHeight);
        greedGhost.drawObject(g,fieldWidth,fieldHeight);



    }
    public static void print2D(Position[][] mat)
    {
        // Loop through all rows
        for (int i = 0; i < mat.length; i++)

            // Loop through all elements of current row
            for (int j = 0; j < mat[i].length; j++)
                if (mat[i][j].getCost() != 0){

                    System.out.println(mat[i][j] + " ");



    }}

}

