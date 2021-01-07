package Entity;

import java.awt.*;

public class TowerArmor extends Tower {


    public TowerArmor(float attackPower, int range, int x, int y) {
        this.attackPower = attackPower;
        this.range = range;
        positionTile = new Point(x, y);
        positionPixel = new Point(20 + 40 * x, 20 + 40 * y);

        upgradeCost = 50;
    }

    public void attack(Enemy[] enemy) {
        for (int i = 0; i < enemy.length; i++) {
            if (!enemy[i].dead() && (Math.sqrt(Math.pow(enemy[i].getPositionPixel().x - positionPixel.x, 2) + Math.pow(enemy[i].getPositionPixel().y - positionPixel.y, 2)) < range * rangeFactor)) {
                if (enemy[i].getArmor() > 0)
                    enemy[i].setArmor(enemy[i].getArmor() - attackPower * attackPowerFactor);
                else
                    enemy[i].setHitPoints(enemy[i].getHitPoints() - attackPower * attackPowerFactor);
                break;
            }
        }
    }

    public void draw(Graphics g) {
        g.setColor(Color.green);
        g.drawRect(positionPixel.x - 15, positionPixel.y - 15, 30, 30);
    }

    @Override
    public String printData() {
        return "Armor Tower\nlvl: " + level + "\nupgrade cost: " + level * upgradeCost + "\nattack power: " + attackPower * attackPowerFactor + "\nrange: " + range * rangeFactor;
    }

    @Override
    public void upgrade() {
        level++;
        attackPowerFactor++;
        rangeFactor = (float) Math.log10(level * 10);
    }
}