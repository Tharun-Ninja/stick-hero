package com.example.sticky_hero;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.AudioClip;
import javafx.stage.Stage;

import java.io.IOException;

public class SceneController {
    AudioClip buttonClickSound = new AudioClip(getClass().getResource("/click-button.mp3").toString());

    public void play_start_mouse_clicked(MouseEvent e) throws IOException {
        buttonClickSound.play();
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
