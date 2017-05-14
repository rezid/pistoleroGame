package com.zidane.pistoleroGame;

import com.zidane.pistoleroGame.endScreen.EndScreen;
import com.zidane.pistoleroGame.gameScreen.Bullet;
import com.zidane.pistoleroGame.gameScreen.GameScreen;
import com.zidane.pistoleroGame.mainMenu.MainMenu;
import com.zidane.pistoleroGame.util.Consts;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;

/**
 * Created by galax on 12/05/2017.
 */
public class GameApp extends Application{
    public static Stage window;

    public static Scene menu_scene;
    public static Scene game_scene;
    public static Scene end_scene;
    public static GameScreen gameScreen;

    @Override
    public void start(Stage window) throws Exception {
        this.window = window;
        menu_scene = new Scene(new MainMenu());
        end_scene = new Scene(new EndScreen());

        window.setTitle("Pistolero");
        window.setScene(menu_scene);
        //window.setResizable(false);
        window.sizeToScene();
        window.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
