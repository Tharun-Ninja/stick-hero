package com.example.sticky_hero;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.media.AudioClip;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.application.Application;
import java.io.IOException;

public class StickApplication extends  Application {
    private static AudioClip buttonClickSound;

    @Override
    public void start(Stage stage) throws IOException
    {
        stage.initStyle(StageStyle.UNDECORATED);
        FXMLLoader fxmlLoader = new FXMLLoader(StickApplication.class.getResource("home-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);

        stage.setTitle("Home!");
        stage.setScene(scene);
        stage.setWidth(500);
        stage.setHeight(700);
        stage.show();

        AudioClip bgMusic = new AudioClip(getClass().getResource("/assets/arcade.mp3").toString());
        bgMusic.play(0.2);

        buttonClickSound = new AudioClip(getClass().getResource("/assets/click-button.mp3").toString());

    }

    public void launchCall() {
        launch();
    }


    public static AudioClip getButtonClickSound(){
        return buttonClickSound;
    }

}