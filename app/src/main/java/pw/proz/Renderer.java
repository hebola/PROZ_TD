package pw.proz;

import Entity.*;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Renderer {

    Base base = App.getBase();
    List<Tower> towers = App.getGameLoop().getTowers();
    private Tile[][] tiles = App.getGameLoop().getTiles();
    Enemy[] enemies;

    public void renderEnemy(Graphics g) {
        enemies = App.getGameLoop().getEnemies();
        for (int i = 0; i < enemies.length; i++)
            enemies[i].draw(g);
    }

    public void renderBase(Graphics g) {
        base.drawHitPoints(g);
    }

    public void renderTower(Graphics g) {
        for (int i = 0; i < towers.size(); i++)
            towers.get(i).draw(g);
    }

    public void renderTileOverview(Graphics g) {
        GameLoop gameLoop = App.getGameLoop();
        Point corner = new Point(40 + 40 * App.Columns + 10, 40);
        Rectangle board = new Rectangle(corner.x, corner.y, 180, 40 * App.Rows);
        g.setColor(Color.gray);
        g.drawRect(40 + 40 * App.Columns + 10, 40, 180, 40 * App.Rows);
        g.getFont().getSize();
        g.drawString("X: " + gameLoop.getCurrentTile().x, corner.x + 5, corner.y + g.getFont().getSize() + 2);
        g.drawString("Y: " + gameLoop.getCurrentTile().y, corner.x + 40, corner.y + g.getFont().getSize() + 2);

        int y=corner.y + g.getFont().getSize()*2 + 2;
        if (tiles[gameLoop.getCurrentTile().x][gameLoop.getCurrentTile().y].getContent() != null)
            for (String line : tiles[gameLoop.getCurrentTile().x][gameLoop.getCurrentTile().y].getContent().printData().split("\n"))
                g.drawString(line, corner.x + 5, y += g.getFontMetrics().getHeight());


    }
}
