package com.example.sticky_hero;

import javafx.animation.*;
import javafx.application.Platform;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Hero {






    private GameController gameController;
    private int flipCount = 0;

    public boolean isWillFall() {
        return willFall;
    }
    public void setWillFall(boolean willFall) {
        this.willFall = willFall;
    }
    private boolean willFall;

    public boolean isWalking() {
        return walking;
    }
    public void setWalking(boolean walking) {
        this.walking = walking;
    }
    private boolean walking = false;

    public ImageView getHeroImage() {
        return heroImage;
    }
    public void setHeroImage(ImageView heroImage) {
        this.heroImage = heroImage;
    }
    private ImageView heroImage;

    public double getHorizontalMotionSpeed() {
        return horizontalMotionSpeed;
    }
    public void setHorizontalMotionSpeed(double horizontalMotionSpeed) {
        this.horizontalMotionSpeed = horizontalMotionSpeed;
    }
    private double horizontalMotionSpeed = 1;

    public double getVerticalMotionSpeed() {
        return verticalMotionSpeed;
    }
    public void setVerticalMotionSpeed(double verticalMotionSpeed) {
        this.verticalMotionSpeed = verticalMotionSpeed;
    }
    private double verticalMotionSpeed = 5;

    public double getLengthMovedOnCurrentBridge() {
        return lengthMovedOnCurrentBridge;
    }
    public void setLengthMovedOnCurrentBridge(double lengthMovedOnCurrentBridge) {
        this.lengthMovedOnCurrentBridge = lengthMovedOnCurrentBridge;
    }
    private double lengthMovedOnCurrentBridge = 0;

    public double getLengthFallen() {
        return lengthFallen;
    }
    public void setLengthFallen(double lengthFallen) {
        this.lengthFallen = lengthFallen;
    }
    private double lengthFallen = 0;

    public Timeline getMotionOnBridgeTimeline() {
        return motionOnBridgeTimeline;
    }
    public void setMotionOnBridgeTimeline(Timeline motionOnBridgeTimeline) {
        this.motionOnBridgeTimeline = motionOnBridgeTimeline;
    }
    private Timeline motionOnBridgeTimeline;

    public Timeline getHeroFallMotionTimeline() {
        return heroFallMotionTimeline;
    }
    public void setHeroFallMotionTimeline(Timeline heroFallMotionTimeline) {
        this.heroFallMotionTimeline = heroFallMotionTimeline;
    }
    private Timeline heroFallMotionTimeline;


    public Hero(GameController gameController, ImageView heroImage)
    {


        this.gameController = gameController;
        this.heroImage = heroImage;

        motionOnBridgeTimeline = new Timeline();
        KeyFrame keyFrameHorizontalMotion = new KeyFrame(Duration.seconds(0.005), e -> moveHorizontalMotion());
        motionOnBridgeTimeline.getKeyFrames().add(keyFrameHorizontalMotion);
        motionOnBridgeTimeline.setCycleCount(Animation.INDEFINITE);

        heroFallMotionTimeline = new Timeline();
        KeyFrame keyFrameVerticalMotion = new KeyFrame(Duration.seconds(0.01), e -> verticalMotion());
        heroFallMotionTimeline.getKeyFrames().add(keyFrameVerticalMotion);
        heroFallMotionTimeline.setCycleCount(Animation.INDEFINITE);
    }



    public void flipHeroImage() {
        this.flipCount += 1;
        System.out.println("FLIP");

        if (this.flipCount % 2 == 1) {
            heroImage.setRotate(180);
            heroImage.setTranslateY(heroImage.getBoundsInLocal().getWidth() - 1);
        } else {
            heroImage.setRotate(360);
            heroImage.setTranslateY(0);
        }
    }

    public void verticalMotion() {
        this.setHeroImageY(this.heroImage.getY() + verticalMotionSpeed);
        lengthFallen += verticalMotionSpeed;
        // System.out.println("OKK");
        // System.out.println(length_fallen);
        // System.out.println(this.hero_image.getY());

        if (lengthFallen >= 440) {
            System.out.println("length_fallen>=440");
            stopVerticalMotionAnimation();
        }

    }

    public  void startVerticalMotionAnimation() {
        heroFallMotionTimeline.play();
    }


    public  void stopVerticalMotionAnimation()
    {

        heroFallMotionTimeline.stop();
        System.out.println("Game Over");

        try
        {
            SceneController sceneController = new SceneController();
            sceneController.switchGameOver((Stage) heroImage.getScene().getWindow());
            System.out.println("done switching");
        }
        catch (Exception e) {
            System.out.println("error");
            System.out.println(e.getMessage());
        }
    }

    public void moveHorizontalMotion()
   {


        if (gameController.getCherryList().get(0) != null)
        {
            if (gameController.isCherryIsPresent() & gameController.getHero().isWalking() & gameController
                    .checkCollision(heroImage, gameController.getCherryList().get(0).getCherryImage())) {

                gameController.setCherryIsPresent(false);
                gameController.getCherryList().get(0).getCherryImage().setVisible(false);

                int newCherryCount =  gameController.getPoints().getCherryCount()+ gameController.getCherryList().get(1).getReward();
                gameController.getPoints().setCherryCount(  newCherryCount );

                gameController.serializePoints.serialize("src/main/java/com/example/sticky_hero/saved.txt", gameController.getPoints());

                gameController.getCherryScoreDisplay().setText(String.valueOf(gameController.getPoints().getCherryCount()));
            }
        }


        if (gameController.getHero().isWalking()
                & gameController.checkCollision(heroImage, gameController.getPlatforms().get(1).getBlock()))
        {
            System.out.println("Game Over");
            stopHorizontalMotionAnimation();
            startVerticalMotionAnimation();

        }

        this.setHeroImageX(this.heroImage.getX() + horizontalMotionSpeed);
        lengthMovedOnCurrentBridge += horizontalMotionSpeed;
        // System.out.println("OKK");
        // System.out.println(length_moved_on_current_bridge);
        // System.out.println(this.gameController.getCurrent_stick().getRectangle().getHeight());

        if ((willFall) & (lengthMovedOnCurrentBridge >= (this.gameController.getCurrentStick()
                .getStickRectangle().getHeight() + gameController.getStandardStickWidth() + 3))) {
            stopHorizontalMotionAnimation();
            System.out.println("startVertical_Motion_Animation");
            Platform.runLater(this::startVerticalMotionAnimation);
            gameController.getCurrentStick().getFallingTimeline().play();
        }

        else if ((!willFall) & (lengthMovedOnCurrentBridge >= gameController.getCurrentGap()
                + gameController.getPlatforms().get(1).getBlock().getWidth())) {

            gameController.addGameScore(1);
            stopHorizontalMotionAnimation();
            lengthMovedOnCurrentBridge = 0;
            gameController.startAllBridgeMotionAnimation();
        }

    }

    public void setHeroImageX(double x) {
        heroImage.setX(x);
    }

    public void setHeroImageY(double y) {
        heroImage.setY(y);
    }

    public void stopHorizontalMotionAnimation() {
        walking = false;
        motionOnBridgeTimeline.stop();
    }

    public void startHorizontalMotionAnimation() {
        walking = true;
        motionOnBridgeTimeline.play();
    }

}
