package com.example.sticky_hero;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.Random;

public class GamePlatform implements Movable {

    public GameController getGameController() {
        return gameController;
    }
    public void setGameController(GameController gameController) {
        this.gameController = gameController;
    }
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
    public void setMark(Rectangle mark) {
        this.mark = mark;
    }
    private Rectangle mark;

    public Double getDelta() {
        return delta;
    }
    public void setDelta(Double delta) {
        this.delta = delta;
    }
    private Double delta = 5.0;


    Random rand = new Random();

    GamePlatform(double length, double position, GameController gameController)
    {
        this.gameController = gameController;
//        int length = rand.nextInt(25, 50);
        block = new Rectangle(position, gameController.getGap_land_zenith(),    length, 500);
        block.setFill(Color.web("#2191FB"));
        mark = new Rectangle(position+(length/2-delta),gameController.getGap_land_zenith(),2*delta,2);
        mark.setFill(Color.web("#FF0000"));


    }

    @Override
    public double move(double block_final_position, double move_platform_Speed)
    {
        if (!gameController.isMoving_of_platform_Stopped())
        {
            this.block.setX(this.block.getX()-move_platform_Speed);
            this.mark.setX(this.mark.getX()-move_platform_Speed);

            return this.block.getX()-block_final_position;
        }

        return Double.MAX_VALUE ;
    }


}
