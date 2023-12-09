package com.example.sticky_hero;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class CherryFactory {

    private final GameController gameController;
    public Cherry makeCherry(double setX){

        ImageView cherryImageNew;
        Cherry toReturn;
        double random =  Math.random();
        if (random< 0.8)
        {
            cherryImageNew = new ImageView(new Image(getClass().getResource("/assets/cherry.png").toExternalForm()));
            toReturn = new Cherry("normal", gameController, cherryImageNew);
        }
        else
        {
            cherryImageNew = new ImageView(new Image(getClass().getResource("/assets/goldenCherry.png").toExternalForm()));
            toReturn = new Cherry("golden", gameController, cherryImageNew);
        }

        cherryImageNew.setX(setX);
        cherryImageNew.setY(gameController.getGapLandZenith()+5);
        cherryImageNew.setPickOnBounds(true);
        cherryImageNew.setFitHeight(20);
        cherryImageNew.setFitWidth(20);
        gameController.getAnchorPane().getChildren().add(cherryImageNew);

        return toReturn;
    }

    public CherryFactory(GameController g){
        this.gameController = g;
    }
}
