package SnakeLogic;

import SnakeGUI.Position;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;


public abstract class Item implements GameObject {
    protected Color color;
    protected Position position;


    @Override
    public void update() {

    }

    @Override
    public boolean drawObject(GraphicsContext g, double fieldWidth, double fieldHeight) {
        g.setFill(this.color);
        g.fillRoundRect(this.position.getX() * fieldWidth, this.position.getY() * fieldHeight, fieldWidth, fieldHeight, 3, 3);
        return true;

    }
    public Color getColor() {
        return color;
    }

    public int getX() {
        return this.position.getX();
    }

    public int getY() {
        return this.position.getX();
    }
}
