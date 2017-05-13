package com.zidane.pistoleroGame.gameScreen;

import com.zidane.pistoleroGame.GameApp;
import com.zidane.pistoleroGame.mainMenu.Item;
import com.zidane.pistoleroGame.util.Consts;
import javafx.animation.ScaleTransition;
import javafx.animation.TranslateTransition;
import javafx.application.Platform;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;
import javafx.util.Pair;

import java.util.Arrays;
import java.util.List;

/**
 * Created by galax on 12/05/2017.
 */
public class GameScreen extends Pane {
    private Stopwatch stopwatch;
    private Score score;
    private HBox menuBox = new HBox(Consts.GAME_MENU_SPACING);

    private List<Pair<String, Runnable>> gameMenuData = Arrays.asList(
            new Pair<String, Runnable>("Pause", () -> {}),
            new Pair<String, Runnable>("Exit to Menu", () -> {
                GameApp.window.setScene(GameApp.menu_scene);})
    );

    public GameScreen() {
        addBackground();
        addStopwatch();
        addMenu();
        addScore();

        getChildren().add(menuBox);
        startAnimation();
    }

    private void addBackground() {
        ImageView imageView = new ImageView(new Image(GameApp.class.getResource("res/water-blue-ocean.jpg").toExternalForm()));
        imageView.setFitWidth(Consts.WIDTH);
        imageView.setFitHeight(Consts.HEIGHT);
        imageView.setPreserveRatio(true);
        imageView.setSmooth(true);
        imageView.setCache(true);
        getChildren().add(imageView);
    }

    private void addStopwatch() {
        stopwatch = new Stopwatch();
        stopwatch.stop_start();

        menuBox.getChildren().addAll(stopwatch);
    }

    private void addMenu() {
        menuBox.setTranslateX(Consts.GAME_MENU_X);
        menuBox.setTranslateY(Consts.GAME_MENU_Y);
        gameMenuData.forEach(data -> {
            Item item = new Item(data.getKey());
            item.setOnAction(data.getValue());
            item.setTranslateX(-300);

            Rectangle clip = new Rectangle(300, 30);
            clip.translateXProperty().bind(item.translateXProperty().negate());

            item.setClip(clip);

            menuBox.getChildren().addAll(item);
        });
    }

    private void startAnimation() {
        ScaleTransition st = new ScaleTransition(Duration.seconds(1));
        st.setToY(1);
        st.setOnFinished(e -> {

            for (int i = 0; i < menuBox.getChildren().size(); i++) {
                Node n = menuBox.getChildren().get(i);

                TranslateTransition tt = new TranslateTransition(Duration.seconds(1 + i * 0.15), n);
                tt.setToX(0);
                tt.setOnFinished(e2 -> n.setClip(null));
                tt.play();
            }
        });
        st.play();
    }

    private void addScore() {
        score = new Score();
        menuBox.getChildren().addAll(score);
    }
}
