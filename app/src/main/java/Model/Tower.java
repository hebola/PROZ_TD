package Model;

public abstract class Tower extends Entity {
    protected float rangeFactor = 1;
    protected float attackPowerFactor = 1;
    protected float range;
    protected float attackPower;
    protected int level = 1;
    protected int upgradeCost;
    protected static int cost = 100;
    static int numOfTowers = 0;

    public Tower() {
        numOfTowers++;
    }
    public abstract void attack(Enemy[] enemy);
    public static int getCost() {
        return cost;
    }
    public int getUpgradeCost() {
        return upgradeCost;
    }
    public int getLevel() {
        return level;
    }
    public float getRangeFactor() {
        return rangeFactor;
    }
    public float getRange() {
        return range;
    }
    public abstract void upgrade();
}
