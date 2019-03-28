package SnakeLogic;

import javafx.scene.canvas.GraphicsContext;

/**
 * Created by Ebbe Vang on 23-01-2017.
 */
public interface GameObject {


    void update();

    boolean drawObject(GraphicsContext g, double fieldWidth, double fieldHeight);
}
