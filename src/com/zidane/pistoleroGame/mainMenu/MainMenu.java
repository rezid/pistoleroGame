package com.zidane.pistoleroGame.mainMenu;

import com.zidane.pistoleroGame.GameApp;
import com.zidane.pistoleroGame.gameScreen.GameScreen;
import com.zidane.pistoleroGame.util.Consts;
import javafx.animation.ScaleTransition;
import javafx.animation.TranslateTransition;
import javafx.application.Platform;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;
import javafx.util.Pair;

import java.util.Arrays;
import java.util.List;

/**
 * Created by galax on 12/05/2017.
 */
public class MainMenu extends Pane {

    private List<Pair<String, Runnable>> menuData = Arrays.asList(
            new Pair<String, Runnable>("Start", () -> {
                GameApp.game_scene = new Scene(new GameScreen());
                GameApp.window.setScene(GameApp.game_scene);}),
            new Pair<String, Runnable>("Guide", () -> {}),
            new Pair<String, Runnable>("Game Options", () -> {}),
            new Pair<String, Runnable>("Score", () -> {GameApp.window.setScene(GameApp.end_scene);}),
            new Pair<String, Runnable>("Exit to Desktop", Platform::exit)
    );


    private VBox menuBox = new VBox(-5);
    private Line line;

    public MainMenu() {
        addBackground();
        addTitle();

        double lineX = Consts.WIDTH / 2 - 100;
        double lineY = Consts.HEIGHT / 3 + 50;

        addLine(lineX, lineY);
        addMenu(lineX + 5, lineY + 5);

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

    private void addTitle() {
        Title title = new Title("PISTOLERO");
        title.setTranslateX(Consts.WIDTH / 2 - title.getTitleWidth() / 2);
        title.setTranslateY(Consts.HEIGHT / 3);
        getChildren().add(title);
    }

    private void addLine(double x, double y) {
        line = new Line(x, y, x, y + Consts.TITLE_LINE_LENGTH);
        line.setStrokeWidth(3);
        line.setStroke(Color.color(1, 1, 1, 0.75));
        line.setEffect(new DropShadow(5, Color.BLACK));
        line.setScaleY(0);

        getChildren().add(line);
    }

    private void startAnimation() {
        ScaleTransition st = new ScaleTransition(Duration.seconds(1), line);
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

    private void addMenu(double x, double y) {
        menuBox.setTranslateX(x);
        menuBox.setTranslateY(y);
        menuData.forEach(data -> {
            Item item = new Item(data.getKey());
            item.setOnAction(data.getValue());
            item.setTranslateX(-300);

            Rectangle clip = new Rectangle(300, 30);
            clip.translateXProperty().bind(item.translateXProperty().negate());

            item.setClip(clip);

            menuBox.getChildren().addAll(item);
        });

        getChildren().add(menuBox);
    }

}
