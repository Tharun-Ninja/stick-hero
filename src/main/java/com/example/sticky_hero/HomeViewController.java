package com.example.sticky_hero;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.AudioClip;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class HomeViewController implements Initializable
{
    private DeserializePoints deserializePoints = new DeserializePoints();
    private SerializePoints serializePoints = new SerializePoints();
    private AudioClip buttonClickSound = new AudioClip(getClass().getResource("/click-button.mp3").toString());

    private Points points;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
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
