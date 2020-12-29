package pw.proz;

import java.awt.*;

public abstract class Tower {
    protected float range;
    protected float attackPower;

    protected int size;
    protected Point position;
    static int numOfTowers = 0;

    Tower() {
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

    public abstract void draw(Graphics g);

    public abstract void attack(Enemy[] enemy);

    public Point getPosition() {
        return position;
    }
}
