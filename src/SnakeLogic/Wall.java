package SnakeLogic;

import SnakeGUI.Position;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Wall extends Item implements GameObject {
    private Color color;

    public Wall(int x, int y, Color color) {
        this.position = new Position(x,y);
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

    @Override
    public String toString() {
        return  "X: " + this.position.getX() + " - Y:" + this.position.getY();
    }

    public void setColor(Color color) {
        this.color = color;
    }
}
