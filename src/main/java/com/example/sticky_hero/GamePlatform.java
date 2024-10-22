package com.example.sticky_hero;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.Random;

public class GamePlatform implements Movable {
    private GameController gameController;

    public Rectangle getBlock() {
        return block;
    }
    public void setBlock(Rectangle block) {
        this.block = block;
    }
    private Rectangle block;

    public Rectangle getMark() {
        return mark;
    }
    private Rectangle mark;

    public Double getDelta() {
        return delta;
    }
    private Double delta = 5.0;



    public GamePlatform(double length, double position, GameController gameController)
    {
        this.gameController = gameController;
//        int length = rand.nextInt(25, 50);
        block = new Rectangle(position, gameController.getGapLandZenith(),    length, 500);
        block.setFill(Color.web("#2191FB"));
        mark = new Rectangle(position+(length/2-delta),gameController.getGapLandZenith(),2*delta,2);
        mark.setFill(Color.web("#FF0000"));

    }

    @Override
    public double move(double blockFinalPosition, double movePlatformSpeed)
    {
        if (!gameController.isMovingOfPlatformStopped())
        {
            this.block.setX(this.block.getX()-movePlatformSpeed);
            this.mark.setX(this.mark.getX()-movePlatformSpeed);

            return this.block.getX()-blockFinalPosition;
        }

        return Double.MAX_VALUE ;
    }


}
