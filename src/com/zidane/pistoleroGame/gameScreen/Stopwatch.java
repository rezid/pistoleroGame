package com.zidane.pistoleroGame.gameScreen;

import com.zidane.pistoleroGame.GameApp;
import com.zidane.pistoleroGame.util.Consts;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class Stopwatch extends Pane {
    private Label l;
    private enum StopWatchStatus {
        STOPPED, RUNNING, PAUSE
    };
    private StopwatchWorker stopwatchWorker;
    private StopWatchStatus currentStatus = StopWatchStatus.STOPPED;

    public Stopwatch() {
        Font f = Font.loadFont(GameApp.class.getResource("res/digital-7_mono.ttf").toExternalForm(), Consts.SCORE_STOPWATCH_TEXT_SIZE);
        l = new Label("00:00:00.000");
        l.setFont(f);

        l.setTextFill(Color.WHITE);

        this.getChildren().add(l);
    }

    public void stop_start() {
        if (currentStatus==StopWatchStatus.STOPPED){
            currentStatus = StopWatchStatus.RUNNING;
            stopwatchWorker = new StopwatchWorker();
            Thread t = new Thread(stopwatchWorker);
            l.textProperty().bind(stopwatchWorker.messageProperty());
            t.setDaemon(true);
            t.start();
            return;
        }

        if (currentStatus==StopWatchStatus.RUNNING) {
            stopwatchWorker.stop();
            stopwatchWorker = null;
            currentStatus = StopWatchStatus.STOPPED;
        }
    }

    public void reset() {
        l.textProperty().unbind();
        l.setText("00:00:00.000");
        if (currentStatus==StopWatchStatus.RUNNING) {
            stopwatchWorker.stop();
            stopwatchWorker = null;
            currentStatus = StopWatchStatus.STOPPED;
        }
    }
}