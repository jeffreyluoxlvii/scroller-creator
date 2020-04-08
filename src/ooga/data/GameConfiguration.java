package ooga.data;

import ooga.engine.dinosaur.Cactus;
import ooga.engine.game.Scrolling;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

/**
 * Basic file reading for creating CSV-defined game objects to be used in a GameWorld (?)
 */
public class GameConfiguration {

    private List<Scrolling> scrollers;
    private int length;

    public GameConfiguration(Path path) throws IOException {

        scrollers = new ArrayList<>();
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

        int countCol = 0;
        int totalRow = lines.size();
        int countRow = 0;
        int totalCols = 0;
        int xCoord;
        int yCoef;
        int val;

        for(int i = 0; i<lines.size(); i++){
            array = lines.get(i).split(",");
            if(array.length == 0) break;
            totalCols = array.length;
            for(String element: array){
                if(element.isEmpty()) break;
                val = Integer.parseInt(element);
                xCoord = (countCol/totalCols) * length;
                yCoef = countRow/totalRow;

                //TODO Decide between an approach below: setting locations with ratio or coordinate?
                if(val == 1){
                    Cactus cac = new Cactus();
                    cac.setX(xCoord);
                    //cac.setY(cac.getY()*yCoef);
                    scrollers.add(cac);
                }
                //TODO Implement other classes if that's ok design

                countCol++;
            }
            countRow++;
        }

    }

    //TODO is this allowed idk
    public List<Scrolling> getScrollers() {
        return scrollers;
    }
}