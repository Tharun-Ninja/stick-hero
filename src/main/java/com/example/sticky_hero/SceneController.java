package com.example.sticky_hero;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.AudioClip;
import javafx.stage.Stage;
import javafx.scene.text.Text;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class SceneController implements Initializable
{
    private final int cherriesToRevive = 5;

    private DeserializePoints deserializePoints = new DeserializePoints();
    private SerializePoints serializePoints = new SerializePoints();

    private Points points;
    @FXML
    private Text bestScoreScreen;

    @FXML
    private Text currentScoreScreen;

    @FXML
    private Text cherryScoreDisplayGameOver;

    @FXML
    private Text notEnoughCherries;

    private AudioClip buttonClickSound = new AudioClip(getClass().getResource("/assets/click-button.mp3").toString());

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
        try
        {
            this.points =  deserializePoints.deserialize("saved.txt");
        }
        catch (IOException | ClassNotFoundException e)
        {
            points = new Points(0, 0, 0 );
            System.out.println("Invalid file found during initialize of SceneController. new Points(0, 0, 0 );");
            serializePoints.serialize("saved.txt", points);

        }

//        System.out.println(Integer.toString(points.getCurrent_score()));
//        System.out.println(Integer.toString(points.getBest_score()));
        cherryScoreDisplayGameOver.setText(Integer.toString(points.getCherryCount()));
        currentScoreScreen.setText(Integer.toString(points.getCurrentScore()));
        bestScoreScreen.setText(Integer.toString(points.getBestScore()));

    }


    @FXML
    public void reviveClicked(MouseEvent e) throws IOException {
        buttonClickSound.play();

        if (points.getCherryCount()>= cherriesToRevive)
        {
            points.setCherryCount(points.getCherryCount()- cherriesToRevive);
            serializePoints.serialize("saved.txt", points);
            startGameScene(e);
        }

        else
        {
            notEnoughCherries.setVisible(true);
        }
    }




    public  void startGameScene(MouseEvent e) throws IOException {
        Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        Scene scene = new Scene(FXMLLoader.load(getClass().getResource("game-controller.fxml")));
        stage.setTitle("Game!");
        stage.setScene(scene);
        stage.setWidth(500);
        stage.setHeight(700);
        stage.show();
    }

    public void switchGameOver(Stage stage) throws IOException {

        Scene scene = new Scene(FXMLLoader.load(getClass().getResource("game-over-view.fxml")));
        stage.setTitle("GameOver!");
        stage.setScene(scene);
        stage.setWidth(500);
        stage.setHeight(700);
        stage.show();
    }


    public void playStartMouseClicked(MouseEvent e) throws IOException {
        buttonClickSound.play();
        if (points!=null)
        {
            points.setCurrentScore(0);
        }
        else
        {
            points = new Points(0,0,0);
        }
        serializePoints.serialize("saved.txt", points);
        startGameScene(e);
    }
    public void switchHomeScreen(MouseEvent e) throws IOException {
        buttonClickSound.play();
        Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        Scene scene = new Scene(FXMLLoader.load(getClass().getResource("home-view.fxml")));
        stage.setTitle("Game!");
        stage.setScene(scene);
        stage.setWidth(500);
        stage.setHeight(700);
        stage.show();
    }

    public void quitGame(MouseEvent e) throws IOException {
        buttonClickSound.play();
        Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        stage.close();
    }

}
