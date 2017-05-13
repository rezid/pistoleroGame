package com.zidane.pistoleroGame.gameScreen;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

/**
 * Created by galax on 13/05/2017.
 */
public class Bullet extends GameObject{
    public Bullet() {
        super(new Circle(5, 5, 5, Color.BROWN));
    }
}
