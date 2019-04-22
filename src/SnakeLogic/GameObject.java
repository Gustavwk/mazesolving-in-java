package SnakeLogic;

import javafx.scene.canvas.GraphicsContext;


public interface GameObject {


    void update();

    boolean drawObject(GraphicsContext g, double fieldWidth, double fieldHeight);
}
