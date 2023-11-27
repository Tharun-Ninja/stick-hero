package com.example.sticky_hero;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class StickApplication extends javafx.application.Application {
    @Override
    public void start(Stage stage) throws IOException {
//        System.out.println((char)-1);
        FXMLLoader fxmlLoader = new FXMLLoader(StickApplication.class.getResource("game-controller.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.setWidth(500);
        stage.setHeight(700);
        stage.show();


    }
//500*700
    public static void main(String[] args)
    {
        launch();
    }
}