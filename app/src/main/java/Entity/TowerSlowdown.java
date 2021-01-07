package Entity;

import pw.proz.App;

import java.awt.*;

public class TowerSlowdown extends Tower {


    public TowerSlowdown(float attackPower, int range, int x, int y) {
        this.attackPower = attackPower;
        this.range = range;
        positionTile = new Point(x, y);
        positionPixel = new Point(20 + 40 * x, 20 + 40 * y);
        upgradeCost = 60;
    }

    public void attack(Enemy[] enemy) {
        for (int i = 0; i < enemy.length; i++) {
            if (!enemy[i].dead() && (Math.sqrt(Math.pow(enemy[i].getPositionPixel().x - positionPixel.x, 2) + Math.pow(enemy[i].getPositionPixel().y - positionPixel.y, 2)) < range * rangeFactor)) {
                enemy[i].setMovementSpeedFactor((float) Math.pow(19. / 20, attackPower * attackPowerFactor));
            }
        }
    }

    public void draw(Graphics g) {
        g.setColor(Color.cyan);
        g.drawRect(positionPixel.x - 15, positionPixel.y - 15, 30, 30);
    }

    @Override
    public String printData() {
        return "Slowdown Tower\nlvl: " + level+ "\nupgrade cost: " + level * upgradeCost  + "\nslowdown factor: " + (float) Math.pow(19. / 20, attackPower * attackPowerFactor) + "\nrange: " + range * rangeFactor;
    }

    @Override
    public void upgrade() {
        if(App.getMyGold().subtractGold(level*upgradeCost))
        level++;
        attackPowerFactor += 0.1;
        rangeFactor = (float) Math.log10(level * 10);
    }
}
