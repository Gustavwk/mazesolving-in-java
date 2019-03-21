package SnakeLogic;

import SnakeGUI.Position;
import SnakeLogic.GameObject;
import SnakeLogic.Item;
import javafx.geometry.Pos;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class goal implements GameObject {
    private Position position;
    private Color color;


    public goal(Color color, int X, int Y) {
        this.position = new Position(X,Y);
        this.color = color;
    }





    public void setX(int x) {
        this.position.setX(x);
    }


    public int getY() {
        return this.position.getY();
    }

    public void setY(int y) {
       this.position.setY(y);
    }


    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    @Override
    public void update() {

    }

    @Override
    public boolean drawObject(GraphicsContext g, double fieldWidth, double fieldHeight) {
        g.setFill(this.color);
        g.fillRoundRect(this.position.getX() * fieldWidth, this.position.getY() * fieldHeight, fieldWidth, fieldHeight, 3, 3);
        return true;
    }
}
