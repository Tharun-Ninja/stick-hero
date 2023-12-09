package com.example.sticky_hero;

import javafx.scene.image.ImageView;

import java.util.Random;

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
    Random random = new Random();
    public double getCherryPosition(double lowerBound, double upperBound) {
        double centrePosition = random.nextDouble(lowerBound + 12, upperBound - 12);
        return centrePosition - 10;
    }

    public int getReward() {
        return reward;
    }
    private final int reward;

    Cherry(String type, GameController gameController , ImageView cherryImage )
    {
        if(type.equalsIgnoreCase("golden")){
            this.reward = 5;
        }
        else{
            this.reward = 1;
        }
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
