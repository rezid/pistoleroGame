package com.zidane.pistoleroGame.gameScreen;

import com.zidane.pistoleroGame.GameApp;
import com.zidane.pistoleroGame.mainMenu.Item;
import com.zidane.pistoleroGame.util.Consts;
import javafx.animation.AnimationTimer;
import javafx.animation.ScaleTransition;
import javafx.animation.TranslateTransition;
import javafx.application.Platform;
import javafx.geometry.Point2D;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.util.Duration;
import javafx.util.Pair;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by galax on 12/05/2017.
 */
public class GameScreen extends Pane {
    private List<GameObject> bullets = new ArrayList<>();
    private List<GameObject> enemies = new ArrayList<>();
    private GameObject player;

    public GameObject getPlayer() {
        return player;
    }

    private boolean isInPause = false;

    public boolean isInPause() {
        return isInPause;
    }

    public void setInPause(boolean inPause) {
        isInPause = inPause;
    }

    public Stopwatch stopwatch;
    private Pane gamePane = new Pane();
    private Score score;
    private HBox menuBox = new HBox(Consts.GAME_MENU_SPACING);

    private List<Pair<String, Runnable>> gameMenuData = Arrays.asList(
            new Pair<String, Runnable>("Pause", () -> {
                setInPause(true);
                stopwatch.stop_start();
            }),
            new Pair<String, Runnable>("Exit to Menu", () -> {
                GameApp.window.setScene(GameApp.menu_scene);
            })
    );

    public GameScreen() {
        addBackground();
        addStopwatch();
        addMenu();
        addScore();
        addGamePane();
        startAnimation();

        gamePane.setFocusTraversable(true);
        player = new Player();
        player.setVelocity(new Point2D(1, 0));

        addGameObject(player, Consts.WIDTH / 2, Consts.HEIGHT / 2);

        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                if (!isInPause)
                    onUpdate();
            }
        };
        timer.start();
    }

    private void addGamePane() {
        gamePane.setTranslateX(Consts.GAME_MENU_SPACING);
        gamePane.setTranslateY(2 * Consts.GAME_MENU_SPACING + 50);
        gamePane.setMinHeight(Consts.HEIGHT - (3 * Consts.GAME_MENU_SPACING + 120));
        gamePane.setMinWidth(Consts.WIDTH - gamePane.getTranslateX() - Consts.GAME_MENU_SPACING);


        gamePane.setStyle("-fx-background-color: white");
        getChildren().addAll(gamePane);
    }

    private void onUpdate() {
        for (GameObject bullet : bullets) {
            for (GameObject enemy : enemies) {
                if (bullet.isColliding(enemy)) {
                    score.increment();
                    bullet.setAlive(false);
                    enemy.setAlive(false);
                    gamePane.getChildren().removeAll(bullet.getView(), enemy.getView());
                }
            }
        }


        for (int i =0; i < enemies.size(); i++) {
            if (player.isColliding(enemies.get(i))) {
                setInPause(true);
                stopwatch.stop_start();
            }
            for (int j = i + 1; j < enemies.size(); j++) {
                if ( enemies.get(i).isColliding(enemies.get(j))) {
                    enemies.get(j).setAlive(false);
                    gamePane.getChildren().removeAll(enemies.get(j).getView());
                    Point2D p = enemies.get(i).getVelocity();
                    enemies.get(i).setVelocity(p.normalize().add(Math.random() * 3 - 1.5, Math.random() * 3 ));
                }
            }
        }

        bullets.removeIf(GameObject::isDead);
        enemies.removeIf(GameObject::isDead);

        bullets.forEach(GameObject::update);
        enemies.forEach(GameObject::update);

        player.update();

        if (Math.random() < 0.04) {
            Enemy e = new Enemy();
            e.setVelocity(new Point2D(0, +1));
            addEnemy(e, Math.random() * (gamePane.getWidth() - 2 * Consts.ENEMY_CIRCLE_RADIUS), 0);
        }

    }

    public void addBullet(Bullet bullet, double x, double y) {
        bullets.add(bullet);
        addGameObject(bullet, x, y);
    }

    public void addNewBullet(double x, double y) {
        Bullet bullet = new Bullet();
        bullet.setVelocity(player.getVelocity().normalize().multiply(5));
        addBullet(bullet, x, y);

    }

    private void addEnemy(Enemy enemy, double x, double y) {
        enemies.add(enemy);
        addGameObject(enemy, x, y);
    }

    private void addGameObject(GameObject object, double x, double y) {
        object.getView().setTranslateX(x);
        object.getView().setTranslateY(y);
        gamePane.getChildren().add(object.getView());
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

        stopwatch.setTranslateX(Consts.GAME_MENU_SPACING);
        stopwatch.setTranslateY(Consts.GAME_MENU_SPACING);

        getChildren().addAll(stopwatch);
    }

    private void addMenu() {
        menuBox.setTranslateX(Consts.WIDTH / 3);
        menuBox.setTranslateY(Consts.GAME_MENU_SPACING);

        Label labelP = new Label("P : Pause");
        Label labelR = new Label("R : Resume");
        Label labelQ = new Label("Q : Quite");

        labelP.setFont(Font.loadFont(GameApp.class.getResource("res/Penumbra-HalfSerif-Std_35114.ttf").toExternalForm(), 14));
        labelP.setTextFill(Color.WHITE);
        labelQ.setFont(Font.loadFont(GameApp.class.getResource("res/Penumbra-HalfSerif-Std_35114.ttf").toExternalForm(), 14));
        labelQ.setTextFill(Color.WHITE);
        labelR.setFont(Font.loadFont(GameApp.class.getResource("res/Penumbra-HalfSerif-Std_35114.ttf").toExternalForm(), 14));
        labelR.setTextFill(Color.WHITE);

        menuBox.getChildren().addAll(labelP, labelQ, labelR);

        getChildren().add(menuBox);
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
        score.setTranslateX(Consts.WIDTH - 50);
        score.setTranslateY(Consts.GAME_MENU_SPACING);
        getChildren().addAll(score);
    }
}


