package SnakeLogic;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Tile extends Item implements GameObject {
    private int X;
    private int Y;
    private Color color;

    public Tile(int x, int y,Color color) {
        super(color, x, y);
        this.setX(x);
        this.setY(y);
        this.setColor(color);

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
        X = x;
    }

    public int getY() {
        return Y;
    }

    public void setY(int y) {
        Y = y;
    }

    public Color getColor() {
        return color;
    }

    @Override
    public String toString() {
        return  "X: " + this.X + " - Y:" + this.Y;
    }

    public void setColor(Color color) {
        this.color = color;
    }
}
