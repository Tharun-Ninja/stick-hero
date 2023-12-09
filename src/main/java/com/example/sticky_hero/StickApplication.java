package com.example.sticky_hero;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

public class StickApplication extends javafx.application.Application {
    @Override
    public void start(Stage stage) throws IOException
    {
        FXMLLoader fxmlLoader = new FXMLLoader(StickApplication.class.getResource("home-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);

        stage.setTitle("Home!");
        stage.setScene(scene);
        stage.setWidth(500);
        stage.setHeight(700);
        stage.show();
    }

    public void launchCall() {
        launch();
    }

}