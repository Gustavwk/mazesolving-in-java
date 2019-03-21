package SnakeLogic;



import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;


public class Player implements GameObject {
    private int X;
    private int Y;
    private Color color;
    private KeyCode keyPressed = KeyCode.BACK_SPACE;


public Player(int x, int y, Color color){
    this.X = x;
    this.Y = y;
    this.color = color;
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

    @Override
    public boolean drawObject(GraphicsContext g, double fieldWidth, double fieldHeight) {
        g.setFill(this.color);
        g.fillRoundRect(this.X * fieldWidth, this.Y * fieldHeight, fieldWidth, fieldHeight, 3, 3);
        return true;

    }

    @Override
    public void update() {
        switch (keyPressed)
        {
            case S:
                this.Y++;

                break;
            case A:
                this.X--;;

                break;
            case D:
                this.X++;

                break;
            case W:
                this.Y--;

                break;
        }


    }

    public void keyPressed(KeyCode keyCode)
    {

        System.out.println("key pressed: " + keyCode);
        this.keyPressed = keyCode;
    }
}
