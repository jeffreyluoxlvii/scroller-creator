package ooga.engine.game;

import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;

public abstract class Player extends Rectangle implements Gravity {
    public static final double PLAYER_WIDTH = 10;
    public static final double PLAYER_HEIGHT = 20;
    public static final Paint PLAYER_COLOR = Color.GREEN;

    public Player() {
        super(PLAYER_WIDTH, PLAYER_HEIGHT, PLAYER_COLOR);
    }

    public abstract void jump();

    public abstract void resetJumpStrength();

    public boolean isAirborne(double floorY) {
        return (getY() < floorY);

    }
}
