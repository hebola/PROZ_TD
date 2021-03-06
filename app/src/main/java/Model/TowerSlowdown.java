package Model;

import Controller.GameLoop;

import java.awt.*;

public class TowerSlowdown extends Tower {

    public TowerSlowdown(float attackPower, int range, int x, int y) {
        entityType=EntityType.TowerSlowdown;
        this.attackPower = attackPower;
        this.range = range;
        positionTile = new Point(x, y);
        positionPixel = new Point(20 + 40 * x, 20 + 40 * y);
        upgradeCost = 60;
    }

    public void attack(Enemy[] enemy) {
        for (Enemy value : enemy) {
            if (!value.dead() && (Math.sqrt(Math.pow(value.getPositionPixel().x - positionPixel.x, 2) + Math.pow(value.getPositionPixel().y - positionPixel.y, 2)) < range * rangeFactor)) {
                value.setMovementSpeedFactor((float) Math.pow(9. / 10, attackPower * attackPowerFactor));
            }
        }
    }

    @Override
    public String toString(){
        return "Slowdown Tower\nlvl: " + level + "\nupgrade cost: " + level * upgradeCost + "\nslowdown factor: " + (float) Math.pow(19. / 20, attackPower * attackPowerFactor) + "\nrange: " + range * rangeFactor;
    }

    @Override
    public void upgrade() {
        if (GameLoop.getGold().subtractGold(level * upgradeCost))
            level++;
        attackPowerFactor += 0.5;
        rangeFactor = (float) Math.log10(level * 10);
    }
}
