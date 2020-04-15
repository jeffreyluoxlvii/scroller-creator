package ooga.data.config;

import ooga.engine.game.Coin;
import ooga.engine.game.Enemy;
import ooga.engine.game.Powerup;
import ooga.engine.game.Scrolling;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class JetpackGameConfiguration extends GameConfiguration {
    private List<Scrolling> scrollers;
    private List<Enemy> allEnemies;
    private List<Powerup> allPU;
    private int length;

    public JetpackGameConfiguration(Path path) throws IOException {
        scrollers = new ArrayList<>();
        allEnemies = new ArrayList<>();
        allPU = new ArrayList<>();
        List<String> lines = null;
        //TODO implement custom exceptions
        try {
            lines = Files.readAllLines(path);
        } catch (IOException e){
            throw new IOException("your level configuration file couldn't be read", e);
        }
        String[] array;
        length = Integer.parseInt(lines.get(0));
        lines.remove(0);
        parseCSV(lines);
    }

    @Override
    public void makeCoin(double xCoef) {
        Coin pu = new Coin();
        pu.setX(xCoef*length);
        scrollers.add(pu);
        allPU.add(pu);
    }

    @Override
    public void makeEnemy(double xCoef){

    }

    //TODO is this allowed idk
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
