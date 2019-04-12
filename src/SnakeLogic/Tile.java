package SnakeLogic;

import SnakeGUI.Position;
import javafx.geometry.Pos;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Tile extends Item implements GameObject {



    public Tile(int x, int y, Color color) {
        this.setPosition(new Position(x,y));
        this.color = color;





    }

    @Override
    public void update() {


    }

    @Override
    public boolean drawObject(GraphicsContext g, double fieldWidth, double fieldHeight) {
        g.setFill(this.getColor());
        g.fillRoundRect(this.getX() * fieldWidth, this.getY() * fieldHeight, fieldWidth, fieldHeight, 3, 3);
        return true;
    }



    public void setX(int x) {
        this.getPosition().setX(x);
    }

    public int getY() {
        return this.getPosition().getY();
    }

    public void setY(int y) {
        this.getPosition().setY(y);
    }


    public Color getColor() {
        return color;
    }

    @Override
    public String toString() {
        return  "X: " + this.getPosition().getX() + " - Y:" + this.getPosition().getY() + "Cost:" + this.getPosition().getCost();
    }

    public void setColor(Color color) {
        this.color = color;
    }
}
