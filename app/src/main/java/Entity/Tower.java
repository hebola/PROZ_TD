package Entity;

import java.awt.*;

public abstract class Tower extends Entity {
    protected float rangeFactor = 1;
    protected float attackPowerFactor = 1;
    protected float range;
    protected float attackPower;
    protected int level = 1;
    protected int upgradeCost;
    public static int cost=100;

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

    public void setAttackPower(float attackPower) {
        this.attackPower = attackPower;
    }

    public float getAttackPower() {
        return attackPower;
    }

    public void drawRange(Graphics g) {
        g.drawOval((int) (positionPixel.x - range * rangeFactor), (int) (positionPixel.y - range * rangeFactor), (int) (range * rangeFactor * 2), (int) (range * rangeFactor * 2));
    }

    public int getUpgradeCost() {
        return upgradeCost;
    }

    public int getLevel() {
        return level;
    }

    public abstract void upgrade();
}
