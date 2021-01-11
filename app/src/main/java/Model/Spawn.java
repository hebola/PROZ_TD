package Model;

import java.awt.*;

public class Spawn extends Entity {

    public static final int SIZE = 30;

    public Spawn(int x, int y) {
        entityType=EntityType.Spawn;
        positionTile = new Point(x, y);
        positionPixel = new Point(20 + 40 * x, 20 + 40 * y);
    }

    @Override
    public String toString(){
        return "Spawn";
    }
}
