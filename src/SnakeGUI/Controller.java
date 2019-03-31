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







    Player player = new Player(5,5, Color.WHITE);
    RandomRambler ranRam = new RandomRambler(1,1, Color.YELLOW);
    ArrayList<Item> items = new ArrayList<Item>();

    Room room = new Room();



    Position[][] maze = room.populate(items,width,height);
    goal defaultGoal = new goal(Color.GREEN, 15,15,maze);


    DFSObject DFS = new DFSObject(1,1, Color.RED,defaultGoal, maze, width, height);
    //DFSObject DFSTwo = new DFSObject(1,18, Color.AQUA,defaultGoal, maze, width, height);

    public void btnStartAction(ActionEvent event)
    {
        System.out.println("btn clicked");
        labelStatus.setText("test");
        getRandomPosition();
        drawCanvas();
    }

    /**
     * Executed when JavaFX is initialized. Used to setup the Snake game
     */

    public void initialize()
    {
        DFS.DFS(DFS.getPosition(),defaultGoal.getPosition());
//        defaultGoal.mazeCost(defaultGoal.getPosition());

        print2D(maze);
        //DFSTwo.DFS(DFSTwo.getPosition(),defaultGoal.getPosition());

                AddItems();

        calculateFields();
        //This control the start position of the player.
        getRandomPosition();
        //drawCanvas();

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

    private void AddItems() {
        //add walls here
        //add everything here!!






        //would be nice to add players and walls here.

    }



    /**
     * Game loop - executed continously during the game
     * @param now game time in nano seconds
     */


    private void update(long now)
    {
            player.update();
            ranRam.update();
            ranRam.wallCollision(items);
            //System.out.println(ranRam);
            DFS.update();
            //DFSTwo.update();
            defaultGoal.update();


        //getRandomPosition();
        //Crete randomRambler object
        drawCanvas();
        //System.out.println(now);

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

        // draw all fields
        g.setFill(Color.TAN);
        for (int i = 0; i < width ; i++) {
            for (int j = 0; j < height ; j++) {
                g.fillRoundRect(i*fieldWidth, j*fieldHeight, fieldWidth,fieldHeight, 5, 5);
            }
        }

        // draw items
        for (Item item : items)
        {
            item.drawObject(g,fieldWidth,fieldHeight);
        }

        // draw 'player'
        player.drawObject(g,fieldWidth,fieldHeight);
        ranRam.drawObject(g,fieldWidth,fieldHeight);
        DFS.drawObject(g,fieldWidth,fieldHeight);

       // DFSTwo.drawObject(g,fieldWidth,fieldHeight);
        defaultGoal.drawObject(g,fieldWidth,fieldHeight);



    }
    public static void print2D(Position mat[][])
    {
        // Loop through all rows
        for (int i = 0; i < mat.length; i++)

            // Loop through all elements of current row
            for (int j = 0; j < mat[i].length; j++)
                if (mat[i][j].getCost() !=0){
                System.out.println(mat[i][j] + " ");
    }}

}

