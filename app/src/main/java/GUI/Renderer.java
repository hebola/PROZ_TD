package GUI;

import Entity.*;
import pw.proz.App;
import pw.proz.GameLoop;
import pw.proz.Tile;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Renderer {

    Base base = App.getBase();
    Spawn spawn = App.getSpawn();
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
        Point currentTile = gameLoop.getCurrentTile();
        Point corner = new Point(40 + 40 * App.Columns + 10, 40);
        Rectangle board = new Rectangle(corner.x, corner.y, 180, 40 * App.Rows);
        Rectangle actionBox = new Rectangle(corner.x, corner.y + 120, 180, 30);
        Rectangle buyingBox = new Rectangle(corner.x, corner.y + 150, 90, 120);
        Rectangle nextWaveBox = new Rectangle(corner.x, corner.y + 300, 180, 30);

        g.setColor(Color.red);

        if (currentTile.x != 0 || currentTile.y != 0) {
            if (tiles[currentTile.x][currentTile.y].getContent() == null) {
                g.drawString("buy:", corner.x + 36, corner.y + 120 + 20);
                g.drawString("Armor Tower", corner.x + 5, corner.y + 120 + 50);
                g.drawString("Shield Tower", corner.x + 5, corner.y + 120 + 80);
                g.drawString("Poison Tower", corner.x + 5, corner.y + 120 + 110);
                g.drawString("Slowing Tow.", corner.x + 5, corner.y + 120 + 140);
                g.drawRect(corner.x, corner.y + 120 + 30, 90, 30);
                g.drawRect(corner.x, corner.y + 120 + 60, 90, 30);
                g.drawRect(corner.x, corner.y + 120 + 90, 90, 30);
                g.drawRect(corner.x, corner.y + 120 + 120, 90, 30);
                g.drawRect(corner.x, corner.y + 120, 90, 30);

            } else if (tiles[currentTile.x][currentTile.y].getContent().getClass().getTypeName() != "Entity.Base" && tiles[currentTile.x][currentTile.y].getContent().getClass().getTypeName() != "Entity.Spawn") {
                g.drawString("upgrade", corner.x + 23, corner.y + 120 + 20);
                g.drawString("sell", corner.x + 36 + 90, corner.y + 120 + 20);
                ((Tower) tiles[currentTile.x][currentTile.y].getContent()).drawRange(g);
                g.drawRect(corner.x, corner.y + 120, 90, 30);
                g.drawRect(corner.x + 90, corner.y + 120, 90, 30);
            }
        }

        g.drawRect(corner.x, corner.y + 300, 180, 30);


        g.setColor(Color.gray);
        g.drawRect(40 + 40 * App.Columns + 10, 40, 180, 40 * App.Rows);
        g.getFont().getSize();
        g.drawString("X: " + currentTile.x, corner.x + 5, corner.y + g.getFont().getSize() + 2);
        g.drawString("Y: " + currentTile.y, corner.x + 40, corner.y + g.getFont().getSize() + 2);

        int y = corner.y + g.getFont().getSize() * 2 + 2;
        if (tiles[currentTile.x][currentTile.y].getContent() != null)
            for (String line : tiles[currentTile.x][currentTile.y].getContent().printData().split("\n"))
                g.drawString(line, corner.x + 5, y += g.getFontMetrics().getHeight());
    }

    public void renderSpawn(Graphics g) {
        spawn.draw(g);
    }
}
