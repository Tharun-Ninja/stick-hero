package com.example.sticky_hero;

import javafx.animation.*;
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

    public Timeline getMain_timeline() {
        return main_timeline;
    }
    public void setMain_timeline(Timeline main_timeline) {
        this.main_timeline = main_timeline;
    }
    private Timeline main_timeline;

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

        main_timeline = new Timeline();

        KeyFrame keyFrame_erect = new KeyFrame(Duration.seconds(0.01)   , e-> erect_Stick());
        KeyFrame keyFrame_rotate = new KeyFrame(Duration.seconds(0.01), e->rotate_Stick());

        main_timeline.getKeyFrames().add(keyFrame_erect);
        main_timeline.getKeyFrames().add(keyFrame_rotate);

        rotateTransition = new RotateTransition(Duration.seconds(0.5), stick_rectangle);

        main_timeline.setCycleCount(Animation.INDEFINITE);

    }

    public void startErectAnimation()
    {
        gameController.setRotation_allowed(true);
        gameController.setErection_allowed(true);

        main_timeline.play();
    }
    public void stopRotationAnimation()
    {
        main_timeline.stop();

//        if (this.press_count == 1)
//        {
            GamePlatform plat1=  gameController.getPlatforms().get(1);
            // try to make start_Perfect_Score_Animation and startHorizontal_Motion_Animation
            //        PARALLEL
            if ((stick_rectangle.getHeight() > (gameController.getCurrent_gap())+plat1.getBlock().getWidth()/2-plat1.getDelta())&((stick_rectangle.getHeight() < (gameController.getCurrent_gap())+plat1.getBlock().getWidth()/2+plat1.getDelta())))
            {
                this.gameController.start_Perfect_Score_Animation();
            }

            this.gameController.getHero().startHorizontal_Motion_Animation();
//        }

    }

    public void stopErectAnimation() {
        main_timeline.stop();
    }

    public void startRotationAnimation()
    {
        main_timeline.play();
    }


    public void erect_Stick()
    {
//        setErection_rotation_allowed

//        should I put more brackets ?
        if (this.gameController.isPressing()&gameController.isRotation_allowed()&(press_count==1))
        {
            this.stick_rectangle.setHeight(this.stick_rectangle.getHeight()+erect_speed);
            this.stick_rectangle.setY(this.stick_rectangle.getY()-erect_speed);
        }
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
            double pivotY = stick_rectangle.getY() + stick_rectangle.getHeight();
            double pivotX = stick_rectangle.getX() + stick_rectangle.getWidth()/2;

            Rotate rotate = new Rotate(rotation_speed, pivotX, pivotY);
            rotate.setAxis(Rotate.Z_AXIS);

            stick_rectangle.getTransforms().add(rotate);
            rotateTransition.setNode(stick_rectangle);
            rotateTransition.play();
            this.angle_covered+=rotation_speed;
//            System.out.printf("%f-%f-%f-%f-%d\n", rectangle.getX(), rectangle.getY(), rectangle.getWidth(), rectangle.getHeight(), angle_covered);

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
