package com.example.sticky_hero;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Bounds;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.scene.transform.Rotate;
import javafx.util.Duration;

import java.util.ArrayList;
import java.net.URL;
import java.util.Objects;
import java.util.Random;
import java.util.ResourceBundle;

public class GameController implements Initializable {
    public double getSpeed_for_cherry() {
        return speed_for_cherry;
    }
    public void setSpeed_for_cherry(double speed_for_cherry) {
        this.speed_for_cherry = speed_for_cherry;
    }
    private double speed_for_cherry;


    public Text getCherry_score_display() {
        return cherry_score_display;
    }
    public void setCherry_score_display(Text cherry_score_display) {
        this.cherry_score_display = cherry_score_display;
    }
    @FXML
    private Text cherry_score_display;


    public boolean isCherry_is_present() {
        return cherry_is_present;
    }
    public void setCherry_is_present(boolean cherry_is_present) {
        this.cherry_is_present = cherry_is_present;
    }
    private boolean cherry_is_present = false;


    public boolean isRotation_allowed() {
        return rotation_allowed;
    }
    public void setRotation_allowed(boolean rotation_allowed) {
        this.rotation_allowed = rotation_allowed;
    }
    private boolean rotation_allowed = false;


    public boolean isErection_allowed() {
        return erection_allowed;
    }
    public void setErection_allowed(boolean erection_allowed) {
        this.erection_allowed = erection_allowed;
    }
    private boolean erection_allowed = false;


    private double random_position_for_cherry_corner;
    private double remaining_length_for_platform = Double.MAX_VALUE;

    public double getGap_land_zenith() {
        return gap_land_zenith;
    }
    private final double gap_land_zenith = 250;

    public Stick getCurrent_stick() {
        return current_stick;
    }
    public void setCurrent_stick(Stick current_stick) {
        this.current_stick = current_stick;
    }
    private Stick current_stick;

    public Stick getRedundant_stick() {
        return redundant_stick;
    }
    public void setRedundant_stick(Stick redundant_stick) {
        this.redundant_stick = redundant_stick;
    }
    private Stick redundant_stick;

    public boolean isPressing() {
        return pressing;
    }
    public void setPressing(boolean pressing) {
        this.pressing = pressing;
    }
    private boolean pressing;



    public int getHighest_score() {
        return highest_score;
    }
    public void setHighest_score(int highest_score) {
        this.highest_score = highest_score;
    }
    private int highest_score  = 0;

    public ArrayList<GamePlatform> getPlatforms() {
        return platforms;
    }
    public void setPlatforms(ArrayList<GamePlatform> platforms) {
        this.platforms = platforms;
    }
    private ArrayList<GamePlatform> platforms;

    public ArrayList<Cherry> getCherry_List() {
        return cherry_List;
    }
    public void setCherry_List(ArrayList<Cherry> cherry_List) {
        this.cherry_List = cherry_List;
    }
    private ArrayList<Cherry> cherry_List;



    public GamePlatform getCurrent_platform() {
        return current_platform;
    }
    public void setCurrent_platform(GamePlatform current_platform) {
        this.current_platform = current_platform;
    }
    private GamePlatform current_platform;


    public double getCurrent_gap() {
        return current_gap;
    }
    public void setCurrent_gap(double current_gap) {
        this.current_gap = current_gap;
    }
    private double current_gap;

    public ImageView getImage_view() {
        return image_view;
    }
    @FXML
    private ImageView image_view;

    public AnchorPane getAnchorPane() {
        return anchorPane;
    }
    @FXML
    private AnchorPane anchorPane;
    @FXML
    private Line hint_line;

    public double getSpeed_for_new_1() {
        return speed_for_new_1;
    }
    public void setSpeed_for_new_1(double speed_for_new_1) {
        this.speed_for_new_1 = speed_for_new_1;
    }
    private double speed_for_new_1;



    public double getRandom_position_for_new_1() {
        return random_position_for_new_1;
    }
    public void setRandom_position_for_new_1(double random_position_for_new_1) {
        this.random_position_for_new_1 = random_position_for_new_1;
    }
    private double random_position_for_new_1;

    public ImageView getHero_image() {
        return hero_image;
    }
    @FXML
    private ImageView hero_image;

    @FXML
    private ImageView cherry_image;



    public Hero getHero() {
        return hero;
    }
    public void setHero(Hero hero) {
        this.hero = hero;
    }
    private Hero hero;

    public int getStandard_stick_width() {
        return standard_stick_width;
    }

    private final int standard_stick_width = 2;

    private final int minWidth = 30;
    private final int maxWidth = 60;

    public Timeline getMotion_of_all_bridge_timeline() {
        return motion_of_all_bridge_timeline;
    }
    public void setMotion_of_all_bridge_timeline(Timeline motion_of_all_bridge_timeline) {
        this.motion_of_all_bridge_timeline = motion_of_all_bridge_timeline;
    }
    private Timeline motion_of_all_bridge_timeline;


    public boolean isMoving_of_platform_Stopped() {
        return moving_of_platform_Stopped;
    }
    public void setMoving_of_platform_Stopped(boolean moving_of_platform_Stopped) {
        this.moving_of_platform_Stopped = moving_of_platform_Stopped;
    }
    private boolean moving_of_platform_Stopped = false;


    Random random = new Random();

    public GameController()
    {
        hero = new Hero(this, hero_image);

        platforms = new ArrayList<>();
        initializePlatforms();
        current_stick = new Stick(this);
        System.out.println("GameController done");


        motion_of_all_bridge_timeline = new Timeline();
        KeyFrame keyFrame_all_component_motion = new KeyFrame(Duration.seconds(0.01)   , e-> move_All_Platforms());
        motion_of_all_bridge_timeline.getKeyFrames().add(keyFrame_all_component_motion);
        motion_of_all_bridge_timeline.setCycleCount(Animation.INDEFINITE);



        cherry_List = new ArrayList<>();




        System.out.println("new ArrayList<>(2)");
        System.out.println(cherry_List);
    }

    public void start_all_bridge_motion_Animation()
    {
        double random_position = get_position(50, 200);
        while (!((random_position+platforms.get(2).getBlock().getWidth()<400)))
        {
//            to ensure position is such the plat2 is visible and doesn't exceed 400px
            random_position = get_position(50, 200);
        }
        double speed_for_platform_2 = (platforms.get(2).getBlock().getX()-random_position)/(platforms.get(1).getBlock().getX());

        this.random_position_for_new_1 = random_position;
        this.speed_for_new_1 = speed_for_platform_2;



//        here we decide if cherry is present or absent
        if ((this.random_position_for_new_1 - platforms.get(1).getBlock().getWidth())>60)
        {
            this.cherry_is_present = true;
            this.random_position_for_cherry_corner = get_cherry_position(platforms.get(1).getBlock().getWidth(), this.random_position_for_new_1);
            this.speed_for_cherry = (cherry_List.get(1).getCherry_image().getX() - random_position_for_cherry_corner)/(platforms.get(1).getBlock().getX());
        }
        else
        {
            this.cherry_is_present = false;
            cherry_List.get(1).getCherry_image().setVisible(false);
        }

        motion_of_all_bridge_timeline.play();
    }


    public void stop_all_bridge_motion_Animation()
    {
        motion_of_all_bridge_timeline.stop();
//transition_place
//        spawn new stick, platform, cherry

        platforms.set(0, platforms.get(1));
        platforms.set(1, platforms.get(2));

        double platformWidth = random.nextDouble(maxWidth - minWidth) + minWidth;
        platforms.set(2, new GamePlatform(  platformWidth, 600, this));

        current_platform = platforms.get(0);
        anchorPane.getChildren().addAll(platforms.get(2).getBlock(), platforms.get(2).getMark() );
        current_gap = platforms.get(1).getBlock().getX()-(platforms.get(0).getBlock().getX()  +  platforms.get(0).getBlock().getWidth());
        double ideal_stick_length = current_gap+platforms.get(1).getBlock().getWidth()/2;

//        System.out.println(ideal_stick_length);
//        System.out.println(gap_land_zenith);


        hint_line.setEndY(this.gap_land_zenith-ideal_stick_length);
        hint_line.setStartY(this.gap_land_zenith-ideal_stick_length);
        moving_of_platform_Stopped = false;
        this.remaining_length_for_platform = Double.MAX_VALUE;

        redundant_stick = current_stick;
        current_stick = new Stick(this);
        anchorPane.getChildren().add(current_stick.getStick_rectangle());
//at this point cherry_1 has been moved from 600 to position
        cherry_List.set(0,cherry_List.get(1) );
        cherry_List.set(1, new Cherry(1, this, 600));




    }

    public void setHeroImageX(double x)
{
    Platform.runLater(() -> {
        hero_image.setX(x);
    });
}

public void initializePlatforms()
{


    double platformWidth = random.nextDouble(maxWidth - minWidth) + minWidth;
    GamePlatform platform = new GamePlatform(  platformWidth, 0, this);

    platforms.add(platform);
    this.setHeroImageX(platformWidth-this.getStandard_stick_width());
//    subtracted to give space for stick





    platformWidth = random.nextDouble(maxWidth - minWidth) + minWidth;
    double gap = random.nextDouble(10, 50);
    current_gap = gap;
//    "platforms.get(0).block.getWidth() + gap" is the position for 2nd one
    platform = new GamePlatform(  platformWidth, platforms.get(0).getBlock().getWidth() + gap, this);
    platforms.add(platform);


    platformWidth = random.nextDouble(maxWidth - minWidth) + minWidth;
    platform = new GamePlatform(  platformWidth, 600, this);
    platforms.add(platform);

    current_platform = platforms.get(0);
    System.out.println("initializePlatforms done");

}



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
        anchorPane.getChildren().add(current_stick.getStick_rectangle());

        anchorPane.getChildren().addAll(platforms.get(0).getBlock(), platforms.get(0).getMark() );
        anchorPane.getChildren().addAll(platforms.get(1).getBlock(), platforms.get(1).getMark() );
        anchorPane.getChildren().addAll(platforms.get(2).getBlock(), platforms.get(2).getMark() );
        anchorPane.requestFocus();


        System.out.println("Override initialize done");
        hero = new Hero(this, hero_image);

//



        cherry_List.add(null);

        cherry_List.add(new Cherry(1, this, 600));

        hero_image.setRotationAxis(Rotate.X_AXIS);
//        https://gist.github.com/jewelsea/1436941

    }



    @FXML
    void keyPressed(KeyEvent event)
    {
        System.out.println("keyPressed");
        hero.flipHeroImage();
    }
    @FXML
    void setOnMousePressed(MouseEvent event)
    {
        if (hero.isWalking())
        {
            hero.flipHeroImage();
            return;
        }
        System.out.println("pressing = true");
        current_stick.setPress_count(current_stick.getPress_count()+1);
        // Run the time-consuming task in a separate thread
        if (current_stick.getPress_count() == 1)
        {
            Thread taskThread = new Thread(() ->
            {
                pressing = true;
                current_stick.startErectAnimation();

            });

            taskThread.start();
        }
    }

    @FXML
    void setOnMouseReleased(MouseEvent event)
    {
        pressing = false;
        erection_allowed = false;

        if (hero.isWalking())
        {
            hero.flipHeroImage();
            return;
        }
        Platform.runLater(() -> {


            System.out.println("pressing = false");
            double upper_bound = current_gap+ getPlatforms().get(1).getBlock().getWidth();
            double lower_bound = current_gap;
            hero.setWill_fall(!((this.current_stick.getStick_rectangle().getHeight() >= lower_bound) & (this.current_stick.getStick_rectangle().getHeight() <= upper_bound)));


//            System.out.println(current_gap);
//            System.out.println(getPlatforms().get(1).getBlock().getWidth());
//            System.out.println(upper_bound);
//            System.out.println(this.current_stick.getRectangle().getHeight());
//            System.out.println(hero.isWill_fall());

//            current_stick.stopErectAnimation();
            current_stick.setMake_it_flat(true) ;
//            current_stick.startRotationAnimation();
        });
    }


    public void move_All_Platforms()
    {

        if (this.remaining_length_for_platform >0)
        {
//        move platforms
            platforms.get(0).move(-1000.0, 1);
            this.remaining_length_for_platform = platforms.get(1).move(0.0, 1);
            platforms.get(2).move(this.random_position_for_new_1, this.speed_for_new_1);

//        move sticks
            current_stick.move(-1000.0, 1);
            if (redundant_stick != null) {
                redundant_stick.move(-1000.0, 1);
            }
//        move hero
            this.hero.setHeroImageX(this.hero_image.getX() - 1);
//            this.remaining_length_for_hero =

//        move cherry
            if (cherry_List.get(0) != null) {
                cherry_List.get(0).move(-1000, 1);
            }

            if (cherry_List.get(1) != null) {
//            System.out.println(random_position_for_cherry_corner);
                cherry_List.get(1).move(random_position_for_cherry_corner, this.speed_for_cherry);
            }
        }

        if (this.remaining_length_for_platform <=0)
        {
//            if hero , cherry, stick, and platform reached destination, then
//            System.out.println("remaining_length<=0");
            double right_position_for_hero = platforms.get(1).getBlock().getWidth() - this.getStandard_stick_width();
            hero_image.setX(right_position_for_hero);
            moving_of_platform_Stopped  = true;
            stop_all_bridge_motion_Animation();
        }
    }

    private Double get_position(double lower_bound, double upper_bound)
    {
        double gap = random.nextDouble(lower_bound, upper_bound);
        return platforms.get(1).getBlock().getWidth() + gap;
    }

    private double get_cherry_position(double lower_bound, double upper_bound)
    {
        double centre_position = random.nextDouble(lower_bound+12, upper_bound-12);
        return   centre_position-10 ;
    }

    public void start_Perfect_Score_Animation()
    {
        System.out.println("PERFECT");
    }


    public void stop_Perfect_Score_Animation()
    {

    }

    boolean checkCollision(ImageView object1, Object object2)
    {
        Bounds bounds2 = null;
        if (object2.getClass() == Rectangle.class)
        {
//            System.out.println("(object2.getClass() == Rectangle.class)");
            Rectangle object3 = (Rectangle) object2;
            bounds2 = object3.localToScene(object3.getBoundsInLocal());
        }
        else if (object2.getClass() == ImageView.class)
        {
//            System.out.println("(object2.getClass() == ImageView.class)");
            ImageView object3 = (ImageView) object2;
            bounds2 = object3.localToScene(object3.getBoundsInLocal());
        }

        Bounds bounds1 = object1.localToScene(object1.getBoundsInLocal());
//        System.out.println("checkCollision");
//        System.out.println(bounds1.intersects(bounds2));

        return bounds1.intersects(bounds2);
    }

}
