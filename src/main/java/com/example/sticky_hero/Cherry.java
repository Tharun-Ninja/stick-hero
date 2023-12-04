package com.example.sticky_hero;

import javafx.scene.image.ImageView;

public class Cherry implements Movable
{


    public void setGameController(GameController gameController) {
        this.gameController = gameController;
    }
    public GameController getGameController() {
        return gameController;
    }
    private GameController gameController;

    public ImageView getCherryImage() {
        return cherryImage;
    }
    public void setCherryImage(ImageView cherryImage) {
        this.cherryImage = cherryImage;
    }
    private ImageView cherryImage;



    public int getReward() {
        return reward;
    }
    private int reward;

    Cherry(int reward, GameController gameController , ImageView cherryImage )
    {
        this.reward = reward;
        this.gameController = gameController;
        this.cherryImage = cherryImage;
    }



    @Override
    public double move(double blockFinalPosition ,double movePlatformSpeed)
    {
//        System.out.println(this.cherry_image.isVisible());
//        System.out.printf("%f - %f\n", block_final_position,  this.cherry_image.getX());
        if (blockFinalPosition < this.cherryImage.getX())
        {
            this.cherryImage.setX(this.cherryImage.getX()- movePlatformSpeed);
        }
        return Double.MAX_VALUE ;

    }
}
