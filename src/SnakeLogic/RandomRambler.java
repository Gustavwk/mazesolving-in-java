package SnakeLogic;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.Random;
import java.util.Stack;


public class RandomRambler implements  GameObject {
    private int X;
    private int Y;
    private int preX;
    private int preY;
    private Color color;
    private int direction = 0;
    String directionString[] =  {"EAST", "NORTH", "SOUTH", "WEST"};
    Stack path = new Stack();


    public RandomRambler(int x, int y, Color color){
        this.setX(x);
        this.setY(y);
        this.setColor(color);
    }

    @Override
    public String toString() {
        return "(DIRECTION: " + directionString[direction] + ")";
    }

    public boolean moveEast(){
        this.X++;
        return true;
    }
    public boolean moveWest(){
        this.X--;
        return true;
    }
    public boolean moveNorth(){
        this.Y--;
        return true;
    }
    public boolean moveSouth(){
        this.Y++;
        return true;
    }



    @Override
    public void update() {

        setPreX(this.X);
        setPreY(this.Y);

        if (direction == 0){
            moveEast();

        } else if (direction == 1){

            moveNorth();

        }else if(direction == 2){

            moveSouth();

        } else if(direction == 3) {

            moveWest();
        }

    }
    public boolean wallCollision(ArrayList<Item> list) {
        for (Item wall: list) {
            if (((this.X == wall.getX()) && this.Y == wall.getY())){
                goBack();
                newDirection();
        }


        }
        return false;
    }
    public boolean newDirection(){
        Random random = new Random();
        setDirection(random.nextInt(4));
        return true;
    }

    public boolean goBack(){
        this.X = preX;
        this.Y = preY;
        return true;
    }


    @Override
    public boolean drawObject(GraphicsContext g, double fieldWidth, double fieldHeight) {
        g.setFill(this.getColor());
        g.fillRoundRect(this.getX() * fieldWidth, this.getY() * fieldHeight, fieldWidth, fieldHeight, 3, 3);
        return true;

    }


    public int getX() {
        return X;
    }

    public void setX(int x) {
        X = x;
    }

    public int getY() {
        return Y;
    }

    public void setY(int y) {
        Y = y;
    }

    public int getPreX() {
        return preX;
    }

    public void setPreX(int preX) {
        this.preX = preX;
    }

    public int getPreY() {
        return preY;
    }

    public void setPreY(int preY) {
        this.preY = preY;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public int getDirection() {
        return direction;
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }
}
