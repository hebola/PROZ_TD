package Model;

import java.awt.*;

public class TowerPoison extends Tower {

    private int duration = 10;

    public TowerPoison(float attackPower, int range, int x, int y) {
        entityType=EntityType.TowerPoison;
        this.attackPower = attackPower;
        this.range = range;
        positionTile = new Point(x, y);
        positionPixel = new Point(20 + 40 * x, 20 + 40 * y);
        rangeFactor = (float) 1;
        upgradeCost = 500;
    }

    public void attack(Enemy[] enemy) {
        for (Enemy value : enemy) {
            if (!value.dead() && (Math.sqrt(Math.pow(value.getPositionPixel().x - positionPixel.x, 2) + Math.pow(value.getPositionPixel().y - positionPixel.y, 2)) < range * rangeFactor)) {
                value.setPoison(attackPower * attackPowerFactor);
                value.setEffectDuration(duration);
            }
        }
    }

    @Override
    public String toString(){
        return "Poison Tower\nlvl: " + level + "\nupgrade cost: " + level * upgradeCost + "\npower: " + attackPower * attackPowerFactor + "\nrange: " + range * rangeFactor + "\nduration: " + duration;
    }

    @Override
    public void upgrade() {
        level++;
        attackPowerFactor++;
        duration += 2;
        rangeFactor = (float) Math.log10(level * 5);
    }

}

