package SnakeLogic;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 * Items in Maze
 */
public class Item implements GameObject {
    private Color color;
    private int x;
    private int y;

    public Item(Color color, int x, int y) {
        this.color = color;
        this.x = x;
        this.y = y;
    }

    @Override
    public void update() {

    }

    @Override
    public boolean drawObject(GraphicsContext g, double fieldWidth, double fieldHeight) {
        g.setFill(this.color);
        g.fillRoundRect(this.x * fieldWidth, this.y * fieldHeight, fieldWidth, fieldHeight, 3, 3);
        return true;

    }
    public Color getColor() {
        return color;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
