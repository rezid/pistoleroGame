package com.zidane.pistoleroGame.gameScreen;

import javafx.geometry.Point2D;
import javafx.scene.Node;

/**
 * Created by galax on 13/05/2017.
 */
public class GameObject {
    private Node view;
    private Point2D velocity = new Point2D(0, 0); //speed
    private boolean alive = true;

    public GameObject(Node view) {
        this.view = view;
    }

    public double getRotate() {
        return view.getRotate();
    }

    public void rotateRight() {
        view.setRotate(view.getRotate() + 5);
    }

    public void rotateLeft() {
        view.setRotate(view.getRotate() - 5);
    }

    public void update() {
        view.setTranslateX(view.getTranslateX() + velocity.getX());
        view.setTranslateY(view.getTranslateY() + velocity.getY());
    }

    public boolean isAlive() {
        return alive;
    }

    public boolean isDead() {
        return !alive;
    }

    public void setAlive(boolean alive) {
        this.alive = alive;
    }

    public Point2D getVelocity() {
        return velocity;
    }

    public void setVelocity(Point2D velocity) {
        this.velocity = velocity;
    }

    public Node getView() {
        return view;
    }

    public boolean isColliding(GameObject other) {
        return getView().getBoundsInParent().intersects(other.getView().getBoundsInParent());
    }
}
