package com.example.sticky_hero;

import javafx.animation.*;
import javafx.application.Platform;
import javafx.scene.image.ImageView;
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Hero {

    // private Stage stage;

    // public Hero(Stage stage) {
    // this.stage = stage;
    // }

    public int getCherry_score() {
        return cherry_score;
    }

    public void setCherry_score(int cherry_score) {
        this.cherry_score = cherry_score;
    }

    public int cherry_score;

    public int get_current_score() {
        return current_score;
    }

    public void set_current_score(int current_score) {
        this.current_score = current_score;
    }

    private int current_score = 0;

    private GameController gameController;
    private double position_X;
    private double position_Y;
    private int flip_count = 0;

    public boolean isWill_fall() {
        return will_fall;
    }

    public void setWill_fall(boolean will_fall) {
        this.will_fall = will_fall;
    }

    private boolean will_fall;

    public boolean isWalking() {
        return walking;
    }

    public void setWalking(boolean walking) {
        this.walking = walking;
    }

    private boolean walking = false;

    public ImageView getHero_image() {
        return hero_image;
    }

    public void setHero_image(ImageView hero_image) {
        this.hero_image = hero_image;
    }

    private ImageView hero_image;

    public double getHorizontal_motion_speed() {
        return horizontal_motion_speed;
    }

    public void setHorizontal_motion_speed(double horizontal_motion_speed) {
        this.horizontal_motion_speed = horizontal_motion_speed;
    }

    private double horizontal_motion_speed = 1;

    public double getVertical_motion_speed() {
        return vertical_motion_speed;
    }

    public void setVertical_motion_speed(double vertical_motion_speed) {
        this.vertical_motion_speed = vertical_motion_speed;
    }

    private double vertical_motion_speed = 5;

    public double getLength_moved_on_current_bridge() {
        return length_moved_on_current_bridge;
    }

    public void setLength_moved_on_current_bridge(double length_moved_on_current_bridge) {
        this.length_moved_on_current_bridge = length_moved_on_current_bridge;
    }

    private double length_moved_on_current_bridge = 0;

    public double getLength_fallen() {
        return length_fallen;
    }

    public void setLength_fallen(double length_fallen) {
        this.length_fallen = length_fallen;
    }

    private double length_fallen = 0;

    public Timeline getMotion_on_bridge_timeline() {
        return motion_on_bridge_timeline;
    }

    public void setMotion_on_bridge_timeline(Timeline motion_on_bridge_timeline) {
        this.motion_on_bridge_timeline = motion_on_bridge_timeline;
    }

    private Timeline motion_on_bridge_timeline;

    public Timeline getHero_Fall_Motion_timeline() {
        return hero_Fall_Motion_timeline;
    }

    public void setHero_Fall_Motion_timeline(Timeline hero_Fall_Motion_timeline) {
        this.hero_Fall_Motion_timeline = hero_Fall_Motion_timeline;
    }

    private Timeline hero_Fall_Motion_timeline;

    Hero(GameController gameController, ImageView hero_image) {
        this.gameController = gameController;
        this.hero_image = hero_image;

        motion_on_bridge_timeline = new Timeline();
        KeyFrame keyFrame_horizontal_motion = new KeyFrame(Duration.seconds(0.005), e -> move_horizontal_motion());
        motion_on_bridge_timeline.getKeyFrames().add(keyFrame_horizontal_motion);
        motion_on_bridge_timeline.setCycleCount(Animation.INDEFINITE);

        hero_Fall_Motion_timeline = new Timeline();
        KeyFrame keyFrame_vertical_motion = new KeyFrame(Duration.seconds(0.01), e -> vertical_Motion());
        hero_Fall_Motion_timeline.getKeyFrames().add(keyFrame_vertical_motion);
        hero_Fall_Motion_timeline.setCycleCount(Animation.INDEFINITE);

        // rotateTransition = new RotateTransition(Duration.seconds(0.1), hero_image);
        //// rotateTransition.setAxis(RotateTransition.Axis.Y);
        // rotateTransition.setByAngle(180);
        //
        // scaleTransition = new ScaleTransition(Duration.seconds(1), hero_image);
        // scaleTransition.setToY(-1); // Flip about the y-axis
    }

    public void flipHeroImage() {
        this.flip_count += 1;
        System.out.println("FLIP");

        if (this.flip_count % 2 == 1) {
            hero_image.setRotate(180);
            hero_image.setTranslateY(hero_image.getBoundsInLocal().getWidth() - 1);
        } else {
            hero_image.setRotate(360);
            hero_image.setTranslateY(0);
        }
    }

    private void vertical_Motion() {
        this.setHeroImageY(this.hero_image.getY() + vertical_motion_speed);
        length_fallen += vertical_motion_speed;
        // System.out.println("OKK");
        // System.out.println(length_fallen);
        // System.out.println(this.hero_image.getY());

        if (length_fallen >= 440) {
            System.out.println("length_fallen>=440");
            stopVertical_Motion_Animation();
        }

    }

    private void startVertical_Motion_Animation() {
        hero_Fall_Motion_timeline.play();
    }

    private void stopVertical_Motion_Animation() {
        hero_Fall_Motion_timeline.stop();
        System.out.println("Game Over");

        try {
            SceneController sceneController = new SceneController();
            sceneController.switchGameOver((Stage) hero_image.getScene().getWindow());
            System.out.println("done switching");
        } catch (Exception e) {
            System.out.println("error");
            System.out.println(e.getMessage());
        }
    }

    public void move_horizontal_motion() {

        if (gameController.getCherry_List().get(0) != null) {
            if (gameController.isCherry_is_present() & gameController.getHero().isWalking() & gameController
                    .checkCollision(hero_image, gameController.getCherry_List().get(0).getCherry_image())) {

                gameController.setCherry_is_present(false);
                gameController.getCherry_List().get(0).getCherry_image().setVisible(false);
                cherry_score += gameController.getCherry_List().get(1).getReward();
                gameController.getCherry_score_display().setText(String.valueOf(cherry_score));
            }
        }

        if (gameController.getHero().isWalking()
                & gameController.checkCollision(hero_image, gameController.getPlatforms().get(1).getBlock())) {
            System.out.println("Game Over");
            stopHorizontal_Motion_Animation();
            startVertical_Motion_Animation();

        }

        this.setHeroImageX(this.hero_image.getX() + horizontal_motion_speed);
        length_moved_on_current_bridge += horizontal_motion_speed;
        // System.out.println("OKK");
        // System.out.println(length_moved_on_current_bridge);
        // System.out.println(this.gameController.getCurrent_stick().getRectangle().getHeight());

        if ((will_fall) & (length_moved_on_current_bridge >= (this.gameController.getCurrent_stick()
                .getStick_rectangle().getHeight() + gameController.getStandard_stick_width() + 3))) {
            stopHorizontal_Motion_Animation();
            System.out.println("startVertical_Motion_Animation");
            Platform.runLater(this::startVertical_Motion_Animation);
            gameController.getCurrent_stick().getFalling_timeline().play();
        }

        else if ((!will_fall) & (length_moved_on_current_bridge >= gameController.getCurrent_gap()
                + gameController.getPlatforms().get(1).getBlock().getWidth())) {

            stopHorizontal_Motion_Animation();
            length_moved_on_current_bridge = 0;
            gameController.start_all_bridge_motion_Animation();
        }

    }

    public void setHeroImageX(double x) {
        hero_image.setX(x);
    }

    public void setHeroImageY(double y) {
        hero_image.setY(y);
    }

    public void stopHorizontal_Motion_Animation() {
        walking = false;
        motion_on_bridge_timeline.stop();
    }

    public void startHorizontal_Motion_Animation() {
        walking = true;
        motion_on_bridge_timeline.play();
    }

}
