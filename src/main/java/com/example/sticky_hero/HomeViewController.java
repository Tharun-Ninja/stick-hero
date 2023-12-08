package com.example.sticky_hero;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.io.InputStream;

public class HomeViewController implements Initializable
{
    private DeserializePoints deserializePoints = new DeserializePoints();
    private SerializePoints serializePoints = new SerializePoints();
    private AudioClip buttonClickSound = new AudioClip(getClass().getResource("/assets/click-button.mp3").toString());

    private Points points;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {

        try (InputStream audioStream = getClass().getResourceAsStream("/assets/click-button.mp3"))
        {
//            if (audioStream != null)
//            {
//                Media media = new Media(audioStream.toString());
//                buttonClickSound = new MediaPlayer(media);
//            } else
//            {
//                System.err.println("Failed to load audio file: /assets/click-button.mp3");
//            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        points = null;
        try
        {
            points = deserializePoints.deserialize("src/main/java/com/example/sticky_hero/saved.txt" );
            if (points ==null)
            {
                throw new Exception("points ==null");
            }
        }
        catch (Exception e)
        {

            points = new Points(0, 0, 0 );
            System.out.println("Invalid file found during initialize of HomeViewController. new Points(0, 0, 0 );");
            serializePoints.serialize("src/main/java/com/example/sticky_hero/saved.txt", points);
        }
    }


    @FXML
    public void loadLastSavedClicked(MouseEvent event)
    {
        buttonClickSound.play();
        serializePoints.serialize("src/main/java/com/example/sticky_hero/saved.txt", points);
        startGameScene(event);


    }
    @FXML
    public void playStartMouseClicked(MouseEvent e)
    {
        buttonClickSound.play();
        points.setCurrentScore(0);
        serializePoints.serialize("src/main/java/com/example/sticky_hero/saved.txt", points);
        startGameScene(e);
    }

    public void startGameScene(MouseEvent e) {
        Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        Scene scene = null;
        try
        {
            scene = new Scene(FXMLLoader.load(getClass().getResource("game-controller.fxml")));
        }
        catch (IOException ex)
        {
            ex.printStackTrace();
        }
        stage.setTitle("Game!");
        stage.setScene(scene);
        stage.setWidth(500);
        stage.setHeight(700);
        stage.show();
    }
}
