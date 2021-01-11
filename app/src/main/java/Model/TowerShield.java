package Model;

import java.awt.*;

public class TowerShield extends Tower {

    public TowerShield(float attackPower, int range, int x, int y) {
        entityType=EntityType.TowerShield;
        this.attackPower = attackPower;
        this.range = range;
        positionTile = new Point(x, y);
        positionPixel = new Point(20 + 40 * x, 20 + 40 * y);

        upgradeCost = 50;
    }

    public void attack(Enemy[] enemy) {
        for (int i = 0; i < enemy.length; i++) {
            if (!enemy[i].dead() && (Math.sqrt(Math.pow(enemy[i].getPositionPixel().x - positionPixel.x, 2) + Math.pow(enemy[i].getPositionPixel().y - positionPixel.y, 2)) < range * rangeFactor)) {
                if (enemy[i].getShield() > 0)
                    enemy[i].setShield(enemy[i].getShield() - attackPower * attackPowerFactor);
                else
                    enemy[i].setHitPoints(enemy[i].getHitPoints() - attackPower * attackPowerFactor);
                break;
            }
        }
    }

    @Override
    public void upgrade() {
        level++;
        attackPowerFactor++;
        rangeFactor = (float) Math.log10(level * 10);
    }

    @Override
    public String toString(){
        return "Shield Tower\nlvl: " + level + "\nupgrade cost: " + level * upgradeCost + "\nattack power: " + attackPower * attackPowerFactor + "\nrange: " + range * rangeFactor;
    }

}