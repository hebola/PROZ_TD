package pw.proz;

import Entity.Base;
import Entity.Enemy;
import Entity.Tower;

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

    public void renderTileOverview(Graphics g) {
        GameLoop gameLoop = App.getGameLoop();
        Point corner = new Point(40+40*App.getMyDisplay().graphic.Columns + 10,40);
        Rectangle board = new Rectangle(corner.x,corner.y,180,40*App.getMyDisplay().graphic.Rows);
        g.setColor(Color.gray);
        g.drawRect(40+40*App.getMyDisplay().graphic.Columns + 10,40,180,40*App.getMyDisplay().graphic.Rows);
        g.getFont().getSize();
        g.drawString("X: " + gameLoop.getCurrentTile().x, corner.x+5,corner.y+g.getFont().getSize() + 2 );
        g.drawString("Y: " + gameLoop.getCurrentTile().y, corner.x+40,corner.y+g.getFont().getSize() + 2 );

    }
}
