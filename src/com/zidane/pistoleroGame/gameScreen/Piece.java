package com.zidane.pistoleroGame.gameScreen;

/**
 * Created by galax on 13/05/2017.
 */
public class Piece {

    public int distance;
    public Direction direction;
    public Tetromino parent;
    public int x, y;

    public Piece(int distance, Direction direction) {
        this.direction = direction;
        this.distance = distance;
    }

    public void setParent(Tetromino parent) {
        this.parent = parent;
        x = parent.x + distance * direction.x;
        y = parent.y + distance * direction.y;
    }

    public Piece copy() {
        return new Piece(distance, direction);
    }
}
