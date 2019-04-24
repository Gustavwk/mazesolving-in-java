package SnakeLogic;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Wall extends Item implements GameObject {
    private Color color = Color.BLUE;

    public Wall(int x, int y) {
        this.setPosition(new Position(x,y));


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
        return  "X: " + this.getPosition().getX() + " - Y:" + this.getPosition().getY();
    }

    public void setColor(Color color) {
        this.color = color;
    }
}
