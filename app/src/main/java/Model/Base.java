package Model;

import java.awt.*;

public class Base extends Entity {

    private int maxHitPoints;
    private int hitPoints;

    public Base(int x, int y) {
        entityType = EntityType.Base;
        maxHitPoints = 100;
        hitPoints = maxHitPoints;
        positionTile = new Point(x, y);
        positionPixel = new Point(20 + 40 * x, 20 + 40 * y);
    }

    public float distanceToBase(Point pos) {
        return (float) (Math.pow(positionPixel.x - pos.x, 2) + Math.pow(positionPixel.y - pos.y, 2));
    }

    public int getMaxHitPoints() {
        return maxHitPoints;
    }

    public int getHitPoints() {
        return hitPoints;
    }

    public boolean isBaseDown() {
        return hitPoints <= 0;
    }

    @Override
    public String toString(){
        return "Base\nhit points: " + hitPoints;
    }

    public void getHit(int power) {
        hitPoints -= power;
    }
}