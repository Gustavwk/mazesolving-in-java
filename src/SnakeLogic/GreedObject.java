package SnakeLogic;

import SnakeGUI.Position;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;


public class GreedObject extends Ghost implements GameObject {




    public GreedObject(int X, int Y, Color color, Goal goal, Position[][] maze, int width, int height) {
        this.setPosition(new Position(X, Y));
        this.setColor(color);
        this.setGoal(goal);
        this.setMaze(maze);
        this.setWidth(width);
        this.setHeight(height);
        System.out.println(this.getGoal().getPosition());


    }

    public boolean bestFirst(){return true;}

    @Override
    public void update() {

    }



    @Override
    public boolean drawObject(GraphicsContext g, double fieldWidth, double fieldHeight) {
        g.setFill(this.getColor());
        g.fillRoundRect(this.getPosition().getX() * fieldWidth, this.getPosition().getY() * fieldHeight, fieldWidth, fieldHeight, 3, 3);
        return true;

    }


}
