package Entity;

import java.awt.*;

public class TowerPoison extends Tower {
    private int duration = 10;

    public TowerPoison(float attackPower, int range, int x, int y) {
        this.attackPower = attackPower;
        this.range = range;
        positionTile = new Point(x, y);
        positionPixel = new Point(20 + 40 * x, 20 + 40 * y);
        rangeFactor = (float) 0.7;
    }

    public void attack(Enemy[] enemy) {
        for (int i = 0; i < enemy.length; i++) {
            if (!enemy[i].dead() && (Math.sqrt(Math.pow(enemy[i].getPositionPixel().x - positionPixel.x, 2) + Math.pow(enemy[i].getPositionPixel().y - positionPixel.y, 2)) < range * rangeFactor)) {
                enemy[i].setPoison(attackPower * attackPowerFactor);
                enemy[i].setEffectDuration(duration);
            }
        }
    }

    public void draw(Graphics g) {
        g.setColor(Color.magenta);
        g.drawRect(positionPixel.x - 15, positionPixel.y - 15, 30, 30);
    }

    @Override
    public String printData() {
        return "Poison Tower\nlvl: " + level + "\npower: " + attackPower * attackPowerFactor + "\nrange: " + range * rangeFactor + "\nduration: " + duration;
    }

    @Override
    public void upgrade() {
        level++;
        attackPowerFactor++;
        duration += 2;
        rangeFactor = (float) Math.log10(level * 5);
    }

}

