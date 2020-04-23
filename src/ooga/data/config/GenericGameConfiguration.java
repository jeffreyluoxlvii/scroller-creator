package ooga.data.config;

import ooga.data.LevelFileException;
import ooga.engine.game.Coin;
import ooga.engine.game.Enemy;
import ooga.engine.game.Powerup;
import ooga.engine.game.Scrolling;
import ooga.engine.generic.GameRules;
import ooga.engine.generic.GenericEnemy;
import ooga.engine.generic.GenericGameWorld;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class GenericGameConfiguration extends GameConfiguration {
    private List<Scrolling> scrollers;
    private List<Enemy> allEnemies;
    private List<Powerup> allPU;
    private int length;
    private GameRules rules;

    public GenericGameConfiguration(Path path) throws LevelFileException {
        scrollers = new ArrayList<>();
        allEnemies = new ArrayList<>();
        allPU = new ArrayList<>();
        List<String> lines = null;
        rules = new GameRules();

        try {
            lines = Files.readAllLines(path);
        } catch (IOException e){
            throw new LevelFileException(e);
        }

        String[] array;
        length = Integer.parseInt(lines.get(0));
        lines.remove(0);

        parseCSV(lines);
    }

    @Override
    public void makeCoin(double xCoef, double yCoef) {
        if(rules.ALLOW_COINS) {
            Coin pu = new Coin(xCoef * length, yCoef * rules.SCREEN_HEIGHT);
            pu.setYPos(rules.FLOOR_HEIGHT);
            scrollers.add(pu);
            allPU.add(pu);
        }
    }

    @Override
    public void makeEnemy(double xCoef, double yCoef){
        GenericEnemy c = new GenericEnemy();
        c.setStandardY();
        c.setXPos(xCoef*length);
        scrollers.add(c);
        allEnemies.add(c);
    }

    public List<Scrolling> getScrollers() {
        return scrollers;
    }

    public List<Enemy> getEnemies() {
        return allEnemies;
    }

    public List<Powerup> getPowerups() {
        return allPU;
    }
}