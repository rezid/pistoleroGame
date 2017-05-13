package com.zidane.pistoleroGame.gameScreen;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

/**
 * Created by galax on 13/05/2017.
 */
public class Enemy extends GameObject {
    public Enemy() {
        super(new Circle(15, 15, 15, Color.RED));
    }
}
