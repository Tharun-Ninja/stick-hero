package com.example.sticky_hero;

import javafx.animation.*;
import javafx.application.Platform;
import javafx.scene.media.AudioClip;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.transform.Rotate;
import javafx.util.Duration;


public class Stick implements Movable
{


    public int getPressCount() {
        return pressCount;
    }
    public void setPressCount(int pressCount) {
        this.pressCount = pressCount;
    }
    private int pressCount =0;


    public boolean isMakeItFlat() {
        return makeItFlat;
    }
    public void setMakeItFlat(boolean makeItFlat) {
        this.makeItFlat = makeItFlat;
    }
    private boolean makeItFlat = false;

    public boolean isMakeItFall() {
        return makeItFall;
    }
    public void setMakeItFall(boolean makeItFall) {
        this.makeItFall = makeItFall;
    }
    private boolean makeItFall = false;


    public int getAngleCovered() {
        return angleCovered;
    }
    public void setAngleCovered(int angleCovered) {
        this.angleCovered = angleCovered;
    }
    private int angleCovered = 0;


    public int getRotationSpeed() {
        return rotationSpeed;
    }
    public void setRotationSpeed(int rotationSpeed) {
        this.rotationSpeed = rotationSpeed;
    }
    private int rotationSpeed = 1;


    public int getStickWidth() {
        return stickWidth;
    }
    public void setStickWidth(int stickWidth) {
        this.stickWidth = stickWidth;
    }
    private int stickWidth;


    public void setGameController(GameController gameController)
    {
        this.gameController = gameController;
    }
    public GameController getGameController() {
        return gameController;
    }
    private GameController gameController;

    public Rectangle getStickRectangle() {
        return stickRectangle;
    }
    public void setStickRectangle(Rectangle stickRectangle) {
        this.stickRectangle = stickRectangle;
    }
    private Rectangle stickRectangle;

    public double getErectSpeed() {
        return erectSpeed;
    }
    public void setErectSpeed(double erectSpeed) {
        this.erectSpeed = erectSpeed;
    }
    private double erectSpeed = 1;

    public Timeline getErectingTimeline() {
        return erectingTimeline;
    }
    public void setErectingTimeline(Timeline erectingTimeline) {
        this.erectingTimeline = erectingTimeline;
    }
    private Timeline erectingTimeline;

    public Timeline getRotatingTimeline()
    {
        return rotatingTimeline;
    }
    public void setRotatingTimeline(Timeline rotatingTimeline) {
        this.rotatingTimeline = rotatingTimeline;
    }
    private Timeline rotatingTimeline;

    public Timeline getFallingTimeline() {
        return fallingTimeline;
    }
    public void setFallingTimeline(Timeline fallingTimeline) {
        this.fallingTimeline = fallingTimeline;
    }
    private Timeline fallingTimeline;

    public Timeline getGrowSoundTimeline() {
        return growSoundTimeline;
    }
    public void setGrowSoundTimeline(Timeline growSoundTimeline) {
        this.growSoundTimeline = growSoundTimeline;
    }
    private Timeline growSoundTimeline;


    public AudioClip getStickGrowSound()
    {
        return stickGrowSound;
    }


    public RotateTransition getRotateTransition() {
        return rotateTransition;
    }
    public void setRotateTransition(RotateTransition rotateTransition) {
        this.rotateTransition = rotateTransition;
    }
    private RotateTransition rotateTransition;

    private AudioClip stickGrowSound = new AudioClip(getClass().getResource("/assets/stickSound.mp3").toString());

    public Stick(GameController gameController)
    {
        stickWidth = gameController.getStandardStickWidth();

        this.gameController = gameController;

        double xCord = gameController.getCurrentPlatform().getBlock().getX()+ gameController.getCurrentPlatform().getBlock().getWidth()- stickWidth;
        stickRectangle = new Rectangle(xCord+ (double) stickWidth /2, 250.0, stickWidth,0);
        stickRectangle.setFill(Color.web("#0000FB"));

        erectingTimeline = new Timeline();
        rotatingTimeline = new Timeline();
        fallingTimeline = new Timeline();
        growSoundTimeline = new Timeline();

        KeyFrame keyFrameErect  = new KeyFrame(Duration.seconds(0.01), e-> erectStick());
        KeyFrame keyFrameRotate = new KeyFrame(Duration.seconds(0.01), e-> rotateStick());
        KeyFrame keyFrameFall = new KeyFrame(Duration.seconds(0.01), e-> makeStickFall());
        KeyFrame keyFrameGrowSound = new KeyFrame(Duration.seconds(0.1), e-> stickGrowSound.play());

        erectingTimeline.getKeyFrames().add(keyFrameErect);
        growSoundTimeline.getKeyFrames().add(keyFrameGrowSound);

        rotatingTimeline.getKeyFrames().add(keyFrameRotate);
        fallingTimeline.getKeyFrames().add(keyFrameFall);

        rotateTransition = new RotateTransition(Duration.seconds(0.5), stickRectangle);

        erectingTimeline.setCycleCount(Animation.INDEFINITE);
        rotatingTimeline.setCycleCount(Animation.INDEFINITE);
        fallingTimeline.setCycleCount(Animation.INDEFINITE);
        growSoundTimeline.setCycleCount(Animation.INDEFINITE);
    }

    public void rotateStick()
    {
        if ((this.makeItFlat)&(gameController.isRotationAllowed()))
        {
            if (angleCovered >=90)
            {
                this.makeItFlat = false;
                gameController.setRotationAllowed(false);
                stopRotationAnimation();
                return;
            }
            rotateHelper();

        }
    }



    public  void makeStickFall()
    {
//        System.out.println("make_stick_fall");
        if (angleCovered >=180)
        {
            System.out.println("angle_covered >=180");
            fallingTimeline.stop();
            return;
        }

        rotateHelper();
    }

    public  void rotateHelper()
    {
        double pivotY = stickRectangle.getY() + stickRectangle.getHeight();
        double pivotX = stickRectangle.getX() + stickRectangle.getWidth()/2;

        Rotate rotate = new Rotate(rotationSpeed, pivotX, pivotY);
        rotate.setAxis(Rotate.Z_AXIS);

        stickRectangle.getTransforms().add(rotate);
        rotateTransition.setNode(stickRectangle);
        rotateTransition.play();
        this.angleCovered += rotationSpeed;
    }


    public void startErectAnimation()
    {
        gameController.setErectionAllowed(true);

        erectingTimeline.play();

//        Platform.runLater(() ->
//        {
//
//        });
    }
    public void stopRotationAnimation()
    {
//        gameController.setRotation_allowed(false);
        rotatingTimeline.stop();

//        if (this.press_count == 1)
//        {
            GamePlatform plat1=  gameController.getPlatforms().get(1);
            // try to make start_Perfect_Score_Animation and startHorizontal_Motion_Animation
            //        PARALLEL
            if ((stickRectangle.getHeight() > (gameController.getCurrentGap())+plat1.getBlock().getWidth()/2-plat1.getDelta())&((stickRectangle.getHeight() < (gameController.getCurrentGap())+plat1.getBlock().getWidth()/2+plat1.getDelta())))
            {
                gameController.addGameScore(1);
                this.gameController.startPerfectScoreAnimation();
            }

            this.gameController.getHero().startHorizontalMotionAnimation();
//        }

    }

    public void stopErectAnimation() {
        erectingTimeline.stop();
        growSoundTimeline.stop();
    }

    public void startRotationAnimation()
    {
        rotatingTimeline.play();
    }

    public void erectStick()
    {
//        should I put more brackets ?
        if (this.gameController.isPressing()&gameController.isErectionAllowed()&(pressCount ==1))
        {
            this.stickRectangle.setHeight(this.stickRectangle.getHeight()+ erectSpeed);
            this.stickRectangle.setY(this.stickRectangle.getY()- erectSpeed);
        }
    }


    @Override
    public double move(double blockFinalPosition, double movePlatformSpeed)
    {
//        if (!gameController.isMoving_of_platform_Stopped())
        {
            this.stickRectangle.setY(this.stickRectangle.getY()+ movePlatformSpeed);
        }
        return Double.MAX_VALUE ;

    }
}
