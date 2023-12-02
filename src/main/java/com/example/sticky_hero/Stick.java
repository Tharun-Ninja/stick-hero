package com.example.sticky_hero;

import javafx.animation.*;
import javafx.scene.media.AudioClip;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.transform.Rotate;
import javafx.util.Duration;


public class Stick implements Movable
{
    public int getPress_count() {
        return press_count;
    }

    public void setPress_count(int press_count) {
        this.press_count = press_count;
    }

    private int press_count=0;


    public boolean isMake_it_flat() {
        return make_it_flat;
    }
    public void setMake_it_flat(boolean make_it_flat) {
        this.make_it_flat = make_it_flat;
    }
    private boolean make_it_flat = false;

    public boolean isMake_it_fall() {
        return make_it_fall;
    }
    public void setMake_it_fall(boolean make_it_fall) {
        this.make_it_fall = make_it_fall;
    }
    private boolean make_it_fall = false;


    public int getAngle_covered() {
        return angle_covered;
    }
    public void setAngle_covered(int angle_covered) {
        this.angle_covered = angle_covered;
    }
    private int angle_covered = 0;


    public int getRotation_speed() {
        return rotation_speed;
    }
    public void setRotation_speed(int rotation_speed) {
        this.rotation_speed = rotation_speed;
    }
    private int rotation_speed = 1;


    public int getStick_width() {
        return stick_width;
    }
    public void setStick_width(int stick_width) {
        this.stick_width = stick_width;
    }
    private int stick_width;


    public void setGameController(GameController gameController)
    {
        this.gameController = gameController;
    }
    public GameController getGameController() {
        return gameController;
    }
    private GameController gameController;

    public Rectangle getStick_rectangle() {
        return stick_rectangle;
    }
    public void setStick_rectangle(Rectangle stick_rectangle) {
        this.stick_rectangle = stick_rectangle;
    }
    private Rectangle stick_rectangle;

    public double getErect_speed() {
        return erect_speed;
    }
    public void setErect_speed(double erect_speed) {
        this.erect_speed = erect_speed;
    }
    private double erect_speed = 1;

    public Timeline getErecting_timeline() {
        return erecting_timeline;
    }
    public void setErecting_timeline(Timeline erecting_timeline) {
        this.erecting_timeline = erecting_timeline;
    }
    private Timeline erecting_timeline;

    public Timeline getRotating_timeline()
    {
        return rotating_timeline;
    }
    public void setRotating_timeline(Timeline rotating_timeline) {
        this.rotating_timeline = rotating_timeline;
    }
    private Timeline rotating_timeline;

    public Timeline getFalling_timeline() {
        return falling_timeline;
    }

    public void setFalling_timeline(Timeline falling_timeline) {
        this.falling_timeline = falling_timeline;
    }

    private Timeline falling_timeline;


    public TranslateTransition getTransition_erect() {
        return transition_erect;
    }
    public void setTransition_erect(TranslateTransition transition_erect) {
        this.transition_erect = transition_erect;
    }
    private TranslateTransition transition_erect;


//    public double getMove_stick_Speed() {
//        return move_stick_Speed;
//    }
//    public void setMove_stick_Speed(double move_stick_Speed) {
//        this.move_stick_Speed = move_stick_Speed;
//    }
//    private double move_stick_Speed = 1;


    public RotateTransition getRotateTransition() {
        return rotateTransition;
    }
    public void setRotateTransition(RotateTransition rotateTransition) {
        this.rotateTransition = rotateTransition;
    }
    private RotateTransition rotateTransition;

    Stick(GameController gameController)
    {
        stick_width = gameController.getStandard_stick_width();

        this.gameController = gameController;

        double x_cord = gameController.getCurrent_platform().getBlock().getX()+ gameController.getCurrent_platform().getBlock().getWidth()-stick_width;
        stick_rectangle = new Rectangle(x_cord+ (double) stick_width /2, 250.0,stick_width,0);
        stick_rectangle.setFill(Color.web("#0000FB"));

        erecting_timeline = new Timeline();
        rotating_timeline = new Timeline();
        falling_timeline = new Timeline();

        KeyFrame keyFrame_erect  = new KeyFrame(Duration.seconds(0.01), e-> erect_Stick());
        KeyFrame keyFrame_rotate = new KeyFrame(Duration.seconds(0.01), e->rotate_Stick());
        KeyFrame keyFrame_fall = new KeyFrame(Duration.seconds(0.01), e->make_stick_fall());

        erecting_timeline.getKeyFrames().add(keyFrame_erect);
        rotating_timeline.getKeyFrames().add(keyFrame_rotate);
        falling_timeline.getKeyFrames().add(keyFrame_fall);

        rotateTransition = new RotateTransition(Duration.seconds(0.5), stick_rectangle);

        erecting_timeline.setCycleCount(Animation.INDEFINITE);
        rotating_timeline.setCycleCount(Animation.INDEFINITE);
        falling_timeline.setCycleCount(Animation.INDEFINITE);
    }

    public void rotate_Stick()
    {
        if ((this.make_it_flat)&(gameController.isRotation_allowed()))
        {
            if (angle_covered >=90)
            {
                this.make_it_flat = false;
                gameController.setRotation_allowed(false);
                stopRotationAnimation();
                return;
            }
            rotate_helper();

        }
    }



    private void make_stick_fall()
    {
        System.out.println("make_stick_fall");
        if (angle_covered >=180)
        {
            System.out.println("angle_covered >=180");
            falling_timeline.stop();
            return;
        }

        rotate_helper();
    }

    private void rotate_helper() {
        double pivotY = stick_rectangle.getY() + stick_rectangle.getHeight();
        double pivotX = stick_rectangle.getX() + stick_rectangle.getWidth()/2;

        Rotate rotate = new Rotate(rotation_speed, pivotX, pivotY);
        rotate.setAxis(Rotate.Z_AXIS);

        stick_rectangle.getTransforms().add(rotate);
        rotateTransition.setNode(stick_rectangle);
        rotateTransition.play();
        this.angle_covered+=rotation_speed;
    }


    public void startErectAnimation()
    {
        gameController.setErection_allowed(true);

        erecting_timeline.play();
    }
    public void stopRotationAnimation()
    {
//        gameController.setRotation_allowed(false);
        rotating_timeline.stop();

//        if (this.press_count == 1)
//        {
            GamePlatform plat1=  gameController.getPlatforms().get(1);
            // try to make start_Perfect_Score_Animation and startHorizontal_Motion_Animation
            //        PARALLEL
            if ((stick_rectangle.getHeight() > (gameController.getCurrent_gap())+plat1.getBlock().getWidth()/2-plat1.getDelta())&((stick_rectangle.getHeight() < (gameController.getCurrent_gap())+plat1.getBlock().getWidth()/2+plat1.getDelta())))
            {
                gameController.addGameScore(1);
                this.gameController.start_Perfect_Score_Animation();
            }


            this.gameController.getHero().startHorizontal_Motion_Animation();
//        }

    }

    public void stopErectAnimation() {
        erecting_timeline.stop();
    }

    public void startRotationAnimation()
    {
        rotating_timeline.play();
    }

    AudioClip stickGrowSound = new AudioClip(getClass().getResource("/stickSound.mp3").toString());

    public void erect_Stick()
    {
//        should I put more brackets ?
        if (this.gameController.isPressing()&gameController.isErection_allowed()&(press_count==1))
        {
            this.stick_rectangle.setHeight(this.stick_rectangle.getHeight()+erect_speed);
            this.stick_rectangle.setY(this.stick_rectangle.getY()-erect_speed);
            stickGrowSound.play();
        }
    }


    @Override
    public double move(double block_final_position ,double move_stick_Speed)
    {
//        if (!gameController.isMoving_of_platform_Stopped())
        {
            this.stick_rectangle.setY(this.stick_rectangle.getY()+move_stick_Speed);
        }
        return Double.MAX_VALUE ;

    }
}
