package Entity;

import java.awt.*;

public class TowerArmor extends Tower {

    public TowerArmor(float attackPower, int range, int x, int y) {
        this.attackPower = attackPower;
        this.range = range;
        position = new Point(20 + 40 * x, 20 + 40 * y);
    }

    public void attack(Enemy[] enemy) {
        for (int i = 0; i < enemy.length; i++) {
            if (!enemy[i].dead() && (Math.sqrt(Math.pow(enemy[i].getPosition().x - position.x, 2) + Math.pow(enemy[i].getPosition().y - position.y, 2)) < range)) {
                if (enemy[i].getArmor() > 0)
                    enemy[i].setArmor(enemy[i].getArmor() - attackPower);
                else
                    enemy[i].setHitPoints(enemy[i].getHitPoints() - attackPower);
                break;
            }
        }
    }

    public void draw(Graphics g) {
        g.setColor(Color.green);
        g.drawRect(position.x - 15, position.y - 15, 30, 30);

        g.drawOval((int) (position.x - range), (int) (position.y - range), (int) (range * 2), (int) (range * 2));
    }

}