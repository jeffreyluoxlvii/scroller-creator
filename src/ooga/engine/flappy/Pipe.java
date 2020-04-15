package ooga.engine.flappy;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import ooga.engine.game.Enemy;
import ooga.engine.game.Player;

public class Pipe extends Enemy {
    public static final int SPEED = 9;
    private DoubleProperty x = new SimpleDoubleProperty();
    private DoubleProperty y = new SimpleDoubleProperty();
    private int lowerHalfY = 245;
    private int upperHalfY = 135;
    private int xOffset = 100;
    private int playerOffset = 40;

    public Pipe(double x, double y) {
        super();
        this.x.setValue(x);
        this.y.setValue(y);
    }

    @Override
    public void scroll() {
        setXPos(getXPos() - SPEED);
    }

    @Override
    public void setXPos(double x) {
        this.x.setValue(x);
    }

    @Override
    public void setYPos(double y){
        this.y.setValue(y);
    }

    @Override
    public double getXPos(){ return x.getValue(); }

    @Override
    public double getYPos(){ return y.getValue(); }

    @Override
    public DoubleProperty getXProperty(){
        return x;
    }

    @Override
    public boolean collide(Player player) {
        return inXBounds(player) && inYBounds(player);
    }

    private boolean inXBounds(Player player) {
        return player.getXPos() > this.getXPos() - playerOffset && player.getXPos() < this.getXPos() + xOffset - playerOffset;
    }

    private boolean inYBounds(Player player) {
        return player.getYPos() < upperHalfY || player.getYPos() > lowerHalfY;
    }

    @Override
    public DoubleProperty getYProperty(){
        return y;
    }
}