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



    public ImageView getCherry_image() {
        return cherry_image;
    }
    public void setCherry_image(ImageView cherry_image) {
        this.cherry_image = cherry_image;
    }
    private ImageView cherry_image;



    public int getReward() {
        return reward;
    }
    public void setReward(int reward) {
        this.reward = reward;
    }
    private int reward;





    Cherry(int reward, GameController gameController , double setX)
    {
        this.reward = reward;
        this.gameController = gameController;

        ImageView cherry_image_new = new ImageView("file:C:\\Users\\hp\\IdeaProjects\\Sticky Hero\\src\\main\\java\\com\\example\\sticky_hero\\assets\\cherry.png");
        cherry_image_new.setX(setX);
        cherry_image_new.setY(gameController.getGap_land_zenith()+5);
        cherry_image_new.setPickOnBounds(true);
        cherry_image_new.setFitHeight(20);
        cherry_image_new.setFitWidth(20);
        gameController.getAnchorPane().getChildren().add(cherry_image_new);

        this.cherry_image = cherry_image_new;

    }



    @Override
    public double move(double block_final_position ,double move_cherry_Speed )
    {
//        System.out.println(this.cherry_image.isVisible());
//        System.out.printf("%f - %f\n", block_final_position,  this.cherry_image.getX());
        if (block_final_position < this.cherry_image.getX())
        {
            this.cherry_image.setX(this.cherry_image.getX()-move_cherry_Speed);
        }
        return Double.MAX_VALUE ;

    }
}
