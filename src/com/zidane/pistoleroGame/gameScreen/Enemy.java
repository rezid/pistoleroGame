package com.zidane.pistoleroGame.gameScreen;

import com.zidane.pistoleroGame.util.Consts;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

/**
 * Created by galax on 13/05/2017.
 */
public class Enemy extends GameObject {
    public Enemy() {
        super(new Circle(Consts.ENEMY_CIRCLE_RADIUS, Consts.ENEMY_CIRCLE_RADIUS, Consts.ENEMY_CIRCLE_RADIUS, Color.RED));
    }
}
