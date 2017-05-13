package com.zidane.pistoleroGame.mainMenu;

import com.zidane.pistoleroGame.GameApp;
import com.zidane.pistoleroGame.util.Consts;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;


/**
 * Created by galax on 12/05/2017.
 */
public class Title extends Pane {

    private Text text;

    public Title(String name) {
        String spread = "";
        for (char c : name.toCharArray()) {
            spread += c + " ";
        }

        text = new Text(spread);
        text.setFont(Font.loadFont(GameApp.class.getResource("res/Penumbra-HalfSerif-Std_35114.ttf").toExternalForm(), Consts.TITLE_TEXT_SIZE));
        text.setFill(Color.WHITE);
        text.setEffect(new DropShadow(Consts.TITLE_SHADOW_RADIUS, Color.BLACK));

        getChildren().addAll(text);
    }

    public double getTitleWidth() {
        return text.getLayoutBounds().getWidth();
    }

    public double getTitleHeight() {
        return text.getLayoutBounds().getHeight();
    }
}
