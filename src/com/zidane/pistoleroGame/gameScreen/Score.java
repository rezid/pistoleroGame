package com.zidane.pistoleroGame.gameScreen;

import com.zidane.pistoleroGame.GameApp;
import com.zidane.pistoleroGame.util.Consts;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

/**
 * Created by galax on 12/05/2017.
 */
public class Score extends Pane {
    private Label l;

    public Score() {
        Font f = Font.loadFont(GameApp.class.getResource("res/digital-7_mono.ttf").toExternalForm(), Consts.SCORE_STOPWATCH_TEXT_SIZE);
        l = new Label("0");
        l.setFont(f);

        l.setTextFill(Color.WHITE);

        this.getChildren().add(l);

    }

    public void increment() {
        int score = Integer.parseInt(l.getText());
        l.setText(Integer.toString(score + 1));
    }
}
