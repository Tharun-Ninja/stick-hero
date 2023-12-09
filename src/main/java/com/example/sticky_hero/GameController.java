package com.example.sticky_hero;

import javafx.animation.*;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Bounds;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Random;
import java.util.ResourceBundle;

public class GameController implements Initializable {

    @FXML
    private Text gameScore;
    private double speedForCherry;

    public boolean isMotionOfAllHappening() {
        return motionOfAllHappening;
    }

    public void setMotionOfAllHappening(boolean motionOfAllHappening) {
        this.motionOfAllHappening = motionOfAllHappening;
    }

    private boolean motionOfAllHappening = false;

    public Text getCherryScoreDisplay() {
        return cherryScoreDisplay;
    }
    public void setCherryScoreDisplay(Text cherryScoreDisplay) {
        this.cherryScoreDisplay = cherryScoreDisplay;
    }
    @FXML
    private Text cherryScoreDisplay;

    public boolean isCherryIsPresent() {
        return cherryIsPresent;
    }
    public void setCherryIsPresent(boolean cherryIsPresent) {
        this.cherryIsPresent = cherryIsPresent;
    }
    private boolean cherryIsPresent = false;

    public boolean isRotationAllowed() {
        return rotationAllowed;
    }
    public void setRotationAllowed(boolean rotationAllowed) {
        this.rotationAllowed = rotationAllowed;
    }
    private boolean rotationAllowed = false;

    public boolean isErectionAllowed() {
        return erectionAllowed;
    }
    public void setErectionAllowed(boolean erectionAllowed) {
        this.erectionAllowed = erectionAllowed;
    }
    private boolean erectionAllowed = false;

    private double randomPositionForCherryCorner;

    private double remainingLengthForPlatform = Double.MAX_VALUE;

    public double getGapLandZenith() {
        return gapLandZenith;
    }
    private final double gapLandZenith = 250;

    public Stick getCurrentStick() {
        return currentStick;
    }
    public void setCurrentStick(Stick currentStick) {
        this.currentStick = currentStick;
    }
    private Stick currentStick;

    public Stick getRedundantStick() {
        return redundantStick;
    }
    public void setRedundantStick(Stick redundantStick) {
        this.redundantStick = redundantStick;
    }
    private Stick redundantStick;

    public boolean isPressing() {
        return pressing;
    }
    public void setPressing(boolean pressing) {
        this.pressing = pressing;
    }
    private boolean pressing;


    public ArrayList<GamePlatform> getPlatforms() {
        return platforms;
    }
    public void setPlatforms(ArrayList<GamePlatform> platforms) {
        this.platforms = platforms;
    }
    private ArrayList<GamePlatform> platforms;

    public ArrayList<Cherry> getCherryList() {
        return cherryList;
    }
    public void setCherryList(ArrayList<Cherry> cherryList) {
        this.cherryList = cherryList;
    }
    private ArrayList<Cherry> cherryList;

    public GamePlatform getCurrentPlatform() {
        return currentPlatform;
    }
    public void setCurrentPlatform(GamePlatform currentPlatform) {
        this.currentPlatform = currentPlatform;
    }
    private GamePlatform currentPlatform;

    public double getCurrentGap() {
        return currentGap;
    }
    public void setCurrentGap(double currentGap) {
        this.currentGap = currentGap;
    }
    private double currentGap;

    public ImageView getImageView() {
        return imageView;
    }
    @FXML
    private ImageView imageView;

    public AnchorPane getAnchorPane() {
        return anchorPane;
    }
    @FXML
    private AnchorPane anchorPane;

    @FXML
    private Line hintLine;

    public double getSpeedForNew1() {
        return speedForNew1;
    }

    public void setSpeedForNew1(double speedForNew1) {
        this.speedForNew1 = speedForNew1;
    }

    private double speedForNew1;

    public double getRandomPositionForNew1() {
        return randomPositionForNew1;
    }

    public void setRandomPositionForNew1(double randomPositionForNew1) {
        this.randomPositionForNew1 = randomPositionForNew1;
    }

    private double randomPositionForNew1;

    public ImageView getHeroImage() {
        return heroImage;
    }

    @FXML
    private ImageView heroImage;

    @FXML

    public Hero getHero() {
        return hero;
    }
    private Hero hero;

    public int getStandardStickWidth() {
        return standardStickWidth;
    }
    private final int standardStickWidth = 2;

    private final int minWidth = 30;
    private final int maxWidth = 60;


    private Timeline motionOfAllBridgeTimeline;

    public boolean isMovingOfPlatformStopped() {
        return movingOfPlatformStopped;
    }

    public void setMovingOfPlatformStopped(boolean movingOfPlatformStopped) {
        this.movingOfPlatformStopped = movingOfPlatformStopped;
    }

    private boolean movingOfPlatformStopped = false;

    Random random = new Random();
    DeserializePoints deserializePoints = new DeserializePoints();
    SerializePoints serializePoints = new SerializePoints();

    public Points getPoints() {
        return points;
    }

    public void setPoints(Points points) {
        this.points = points;
    }

    private Points points;

    @FXML
    private Text perfectText;

    @FXML
    private Text perfectText2;

    public GameController()
    {
        System.out.println("GameController called");

        platforms = new ArrayList<>();
        initializePlatforms();
        currentStick = new Stick(this);

        motionOfAllBridgeTimeline = new Timeline();

        KeyFrame keyFrameAllComponentMotion = new KeyFrame(Duration.seconds(0.001), e -> moveAllPlatforms());
        motionOfAllBridgeTimeline.getKeyFrames().add(keyFrameAllComponentMotion);
        motionOfAllBridgeTimeline.setCycleCount(Animation.INDEFINITE);

        cherryList = new ArrayList<>();

        points = null;
        try
        {
            points = deserializePoints.deserialize("saved.txt" );
        }
        catch (IOException | ClassNotFoundException e)
        {
            points = new Points(0, 0, 0 );
            System.out.println("Invalid file. new Points(0, 0, 0 );");
        }

        points.printPoint();
        System.out.println("GameController done");

    }
    public Cherry cherryFactory(double setX)
    {
        ImageView cherryImageNew;
        Cherry toReturn;
        double random =  Math.random();
        if (random< 0.8)
        {
            cherryImageNew = new ImageView(new Image(getClass().getResource("/assets/cherry.png").toExternalForm()));
            toReturn = new Cherry(1, this, cherryImageNew);
        }
        else
        {
            cherryImageNew = new ImageView(new Image(getClass().getResource("/assets/goldenCherry.png").toExternalForm()));
            toReturn = new Cherry(5, this, cherryImageNew);
        }

        cherryImageNew.setX(setX);
        cherryImageNew.setY(getGapLandZenith()+5);
        cherryImageNew.setPickOnBounds(true);
        cherryImageNew.setFitHeight(20);
        cherryImageNew.setFitWidth(20);
        getAnchorPane().getChildren().add(cherryImageNew);
//        if (random< 0.8)
//        {
//            cherryImageNew = new ImageView(new Image(getClass().getResource("/assets/cherry.png").toExternalForm()));
//        }
//        else
//        {
//            cherryImageNew = new ImageView(new Image(getClass().getResource("/assets/goldenCherry.png").toExternalForm()));
//        }
        return toReturn;
    }


    public void addGameScore(int x)
    {
        System.out.println("--1");
        points.addCurrentScore(x);

        if( points.getCurrentScore() > points.getBestScore())
        {
            points.setBestScore( points.getCurrentScore());
        }

        gameScore.setText(String.valueOf(points.getCurrentScore()));

        serializePoints.serialize("saved.txt", points);

    }

    @FXML
    public void saveButtonClicked(ActionEvent event)
    {
        serializePoints.serialize("saved.txt", this.points);

        try
        {
            Stage stage = (Stage) heroImage.getScene().getWindow();
            Scene scene = new Scene(FXMLLoader.load(getClass().getResource("home-view.fxml")));
            stage.setTitle("Sticky Game!");
            stage.setScene(scene);
            stage.setWidth(500);
            stage.setHeight(700);
            stage.show();
        }
        catch (IOException e)
        {
            System.out.println("error on saveButtonClicked");
            System.out.println(e.getMessage());
        }

    }

    @FXML
    public void hintPressed(ActionEvent event)
    {
        hintLine.setVisible(!hintLine.isVisible());
    }



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
        System.out.println("initialize called");

        Image image1 = new Image(getClass().getResource("/assets/wall.jpg").toExternalForm());
        Image image2 = new Image(getClass().getResource("/assets/wall2.jpg").toExternalForm());


        imageView.setImage((Math.random() < 0.5) ? image1 : image2);

        anchorPane.getChildren().add(currentStick.getStickRectangle());

        anchorPane.getChildren().addAll(platforms.get(0).getBlock(), platforms.get(0).getMark());
        anchorPane.getChildren().addAll(platforms.get(1).getBlock(), platforms.get(1).getMark());
        anchorPane.getChildren().addAll(platforms.get(2).getBlock(), platforms.get(2).getMark());
        anchorPane.requestFocus();

        System.out.println("Hero not created");
        System.out.println(points);
        hero = new Hero(this, heroImage);
        System.out.println("Hero created");
        System.out.println(points);
        cherryList.add(null);

        cherryList.add(cherryFactory(600));

        heroImage.setRotationAxis(Rotate.X_AXIS);
        // https://gist.github.com/jewelsea/1436941
        double idealStickLength = currentGap + platforms.get(1).getBlock().getWidth() / 2;

        hintLine.setEndY(this.gapLandZenith - idealStickLength);
        hintLine.setStartY(this.gapLandZenith - idealStickLength);

        gameScore.setText(Integer.toString(points.getCurrentScore()));
        cherryScoreDisplay.setText(Integer.toString(points.getCherryCount()));
        System.out.println("Override initialize done");

    }

    public void startAllBridgeMotionAnimation() {
        double randomPosition = getPosition(50, 200);
        while (!((randomPosition + platforms.get(2).getBlock().getWidth() < 400))) {
            // to ensure position is such the plat2 is visible and doesn't exceed 400px
            randomPosition = getPosition(50, 200);
        }
        double speedForPlatform2 = (platforms.get(2).getBlock().getX() - randomPosition)
                / (platforms.get(1).getBlock().getX());

        this.randomPositionForNew1 = randomPosition;
        this.speedForNew1 = speedForPlatform2;

        // here we decide if cherry is present or absent
        double minWidthForCherrySpawn = 70;
        if ((this.randomPositionForNew1 - platforms.get(1).getBlock().getWidth()) > minWidthForCherrySpawn) {
            this.cherryIsPresent = true;
            this.randomPositionForCherryCorner = getCherryPosition(platforms.get(1).getBlock().getWidth(),
                    this.randomPositionForNew1);
            this.speedForCherry = (cherryList.get(1).getCherryImage().getX() - randomPositionForCherryCorner)
                    / (platforms.get(1).getBlock().getX());
        } else
        {
            this.cherryIsPresent = false;
            cherryList.get(1).getCherryImage().setVisible(false);
        }
        this.motionOfAllHappening = true;
        motionOfAllBridgeTimeline.play();
    }

    public void stopAllBridgeMotionAnimation()
    {
        this.motionOfAllHappening = false;
        motionOfAllBridgeTimeline.stop();

        // transition_place
        // spawn new stick, platform, cherry

        platforms.set(0, platforms.get(1));
        platforms.set(1, platforms.get(2));

        double platformWidth = random.nextDouble(maxWidth - minWidth) + minWidth;
        platforms.set(2, new GamePlatform(platformWidth, 600, this));

        currentPlatform = platforms.get(0);
        anchorPane.getChildren().addAll(platforms.get(2).getBlock(), platforms.get(2).getMark());
        currentGap = platforms.get(1).getBlock().getX()
                - (platforms.get(0).getBlock().getX() + platforms.get(0).getBlock().getWidth());
        double idealStickLength = currentGap + platforms.get(1).getBlock().getWidth() / 2;

        // System.out.println(ideal_stick_length);
        // System.out.println(gap_land_zenith);

        hintLine.setEndY(this.gapLandZenith - idealStickLength);
        hintLine.setStartY(this.gapLandZenith - idealStickLength);
        movingOfPlatformStopped = false;
        this.remainingLengthForPlatform = Double.MAX_VALUE;

        redundantStick = currentStick;
        currentStick = new Stick(this);
        anchorPane.getChildren().add(currentStick.getStickRectangle());
        // at this point cherry_1 has been moved from 600 to position
        cherryList.set(0, cherryList.get(1));
        cherryList.set(1, cherryFactory(600));

    }

    public void setHeroImageX(double x) {
        Platform.runLater(() -> {
            heroImage.setX(x);
        });
    }

    public void initializePlatforms()
    {
        System.out.println("initializePlatforms called");

        double platformWidth = random.nextDouble(maxWidth - minWidth) + minWidth;
        GamePlatform platform = new GamePlatform(platformWidth, 0, this);

        platforms.add(platform);
        this.setHeroImageX(platformWidth - this.getStandardStickWidth());
        // subtracted to give space for stick

        platformWidth = random.nextDouble(maxWidth - minWidth) + minWidth;
        double gap = random.nextDouble(10, 50);
        currentGap = gap;
        // "platforms.get(0).block.getWidth() + gap" is the position for 2nd one
        platform = new GamePlatform(platformWidth, platforms.get(0).getBlock().getWidth() + gap, this);
        platforms.add(platform);

        platformWidth = random.nextDouble(maxWidth - minWidth) + minWidth;
        platform = new GamePlatform(platformWidth, 600, this);
        platforms.add(platform);

        currentPlatform = platforms.get(0);
        System.out.println("initializePlatforms done");

    }


    @FXML
    public void setOnMousePressed(MouseEvent event)
    {

        if (motionOfAllHappening)
        {
            return;
        }
        pressing = true;

        if (hero.isWalking()) {
            hero.flipHeroImage();
            return;
        }



        currentStick.setPressCount(currentStick.getPressCount() + 1);
        // Run the time-consuming task in a separate thread
        if (currentStick.getPressCount() == 1)
        {
//            first sound bit played immediately
            currentStick.getStickGrowSound().play();
//            second sound play for whole timeline

            currentStick.getGrowSoundTimeline().play();
            Thread taskThread = new Thread(() ->
            {
                currentStick.startErectAnimation();

            });

            taskThread.start();
        }
    }

    @FXML
    public void setOnMouseReleased(MouseEvent event)
    {
        if (motionOfAllHappening)
        {
            return;
        }
        pressing = false;

        if (hero.isWalking()) {
            hero.flipHeroImage();
            return;
        }

        if (currentStick.getPressCount() != 0) {
            erectionAllowed = false;
            setRotationAllowed(true);
            System.out.println("pressing = false");
            System.out.println();
            double upperBound = currentGap + getPlatforms().get(1).getBlock().getWidth();
            double lowerBound = currentGap;
            hero.setWillFall(!((this.currentStick.getStickRectangle().getHeight() >= lowerBound)
                    & (this.currentStick.getStickRectangle().getHeight() <= upperBound)));
            currentStick.setMakeItFlat(true);

            currentStick.stopErectAnimation();
            currentStick.startRotationAnimation();
        }

    }

    public void moveAllPlatforms() {

        if (this.remainingLengthForPlatform > 0) {
            // move platforms
            platforms.get(0).move(-1000.0, 1);
            this.remainingLengthForPlatform = platforms.get(1).move(0.0, 1);
            platforms.get(2).move(this.randomPositionForNew1, this.speedForNew1);

            // move sticks
            currentStick.move(-1000.0, 1);
            if (redundantStick != null) {
                redundantStick.move(-1000.0, 1);
            }
            // move hero
            this.hero.setHeroImageX(this.heroImage.getX() - 1);
            // this.remaining_length_for_hero =

            // move cherry
            if (cherryList.get(0) != null) {
                cherryList.get(0).move(-1000, 1);
            }

            if (cherryList.get(1) != null) {
                // System.out.println(random_position_for_cherry_corner);
                cherryList.get(1).move(randomPositionForCherryCorner, this.speedForCherry);
            }
        }

        if (this.remainingLengthForPlatform <= 0) {
            // if hero , cherry, stick, and platform reached destination, then
            // System.out.println("remaining_length<=0");
            double rightPositionForHero = platforms.get(1).getBlock().getWidth() - this.getStandardStickWidth();
            heroImage.setX(rightPositionForHero);
            movingOfPlatformStopped = true;
            stopAllBridgeMotionAnimation();
        }
    }

    private Double getPosition(double lowerBound, double upperBound) {
        double gap = random.nextDouble(lowerBound, upperBound);
        return platforms.get(1).getBlock().getWidth() + gap;
    }

    private double getCherryPosition(double lowerBound, double upperBound) {
        double centrePosition = random.nextDouble(lowerBound + 12, upperBound - 12);
        return centrePosition - 10;
    }


    public void startPerfectScoreAnimation() {
        System.out.println("perfect");
        perfectText.setX(platforms.get(1).getMark().getX());
        perfectText.setText("+1");

        TranslateTransition transition = new TranslateTransition();
        transition.setNode(perfectText);
        transition.setFromY(240);
        transition.setFromX(platforms.get(0).getMark().getX() - (platforms.get(0).getBlock().getWidth() / 2.2));
        transition.setByY(-50);
        transition.setDuration(Duration.millis(1000));

        FadeTransition fade = new FadeTransition();
        fade.setNode(perfectText);
        fade.setFromValue(100);
        fade.setToValue(0);
        fade.setDuration(Duration.millis(1000));

        ScaleTransition scale = new ScaleTransition();
        scale.setNode(perfectText);
        scale.setFromX(1);
        scale.setFromY(1);
        scale.setByX(1.1);
        scale.setByY(1.5);
        scale.setDuration(Duration.millis(2000));

        perfectText2.setX(200);
        perfectText2.setY(100);

        FadeTransition fade2 = new FadeTransition();
        fade2.setNode(perfectText2);
        fade2.setFromValue(100);
        fade2.setToValue(0);
        fade2.setDuration(Duration.millis(1000));

        ScaleTransition scale2 = new ScaleTransition();
        scale2.setNode(perfectText2);
        scale2.setFromX(1);
        scale2.setFromY(1);
        scale2.setToX(1.15);
        scale2.setToY(1.15);
        scale2.setDuration(Duration.millis(200));
        scale2.setAutoReverse(true);
        scale2.setCycleCount(5);

        transition.play();
        fade.play();
        fade2.play();
        scale.play();
        scale2.play();

    }


    public boolean checkCollision(ImageView object1, Object object2) {
        Bounds bounds2 = null;
        if (object2.getClass() == Rectangle.class) {
            // System.out.println("(object2.getClass() == Rectangle.class)");
            Rectangle object3 = (Rectangle) object2;
            bounds2 = object3.localToScene(object3.getBoundsInLocal());
        } else if (object2.getClass() == ImageView.class) {
            // System.out.println("(object2.getClass() == ImageView.class)");
            ImageView object3 = (ImageView) object2;
            bounds2 = object3.localToScene(object3.getBoundsInLocal());
        }

        Bounds bounds1 = object1.localToScene(object1.getBoundsInLocal());
        // System.out.println("checkCollision");
        // System.out.println(bounds1.intersects(bounds2));

        return bounds1.intersects(bounds2);
    }

}
