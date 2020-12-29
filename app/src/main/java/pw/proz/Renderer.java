package pw.proz;

import java.awt.*;

public class Renderer {

    Enemy enemy = App.getEnemy();
    Base base = App.getBase();
    Tower tower = App.getTower();
    Enemy[] enemies;

    public void renderEnemy(Graphics g) {
        enemies = App.getGameLoop().getEnemies();
        enemy.draw(g);
        for (int i = 0; i < enemies.length; i++)
            enemies[i].draw(g);
    }

    public void renderBase(Graphics g) {
        base.drawHitPoints(g);
    }

    public void renderTower(Graphics g) {
        tower.draw(g);
    }
}
