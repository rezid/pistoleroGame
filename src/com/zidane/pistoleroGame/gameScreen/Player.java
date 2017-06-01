package com.zidane.pistoleroGame.gameScreen;

import javafx.application.Platform;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;

/**
 * Created by galax on 13/05/2017.
 */
public class Player extends GameObject{
    public Player() {
        super(new Polygon( 10.0, 0.0, -10.0, -10.0, -10.0, 10.0 ));
    }
}
