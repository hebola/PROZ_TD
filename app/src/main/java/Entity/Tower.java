package Entity;

import java.awt.*;

public abstract class Tower extends Entity{
    protected float range;
    protected float attackPower;

    protected int size;
    static int numOfTowers = 0;

    public Tower() {
        numOfTowers++;
    }

    public static int getNumOfTowers() {
        return numOfTowers;
    }

    public float getRange() {
        return range;
    }

    public void setRange(float range) {
        this.range = range;
    }

    public abstract void attack(Enemy[] enemy);

    public Point getPosition() {
        return position;
    }
}
