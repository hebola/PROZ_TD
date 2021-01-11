package View;

import Model.*;
import Controller.GameInit;
import Controller.GameLoop;
import Model.Tile;
import Model.Wave;

import java.awt.*;
import java.util.List;

public class Renderer {

    private Base base;
    private Spawn spawn;
    private List<Tower> towers;
    private Tile[][] tiles;
    private Enemy[] enemies;

    public Renderer(){
        base = GameLoop.getBase();
        spawn = GameLoop.getSpawn();
        towers = GameInit.getGameLoop().getTowers();
        tiles = GameInit.getGameLoop().getTiles();
    }

    public void renderEnemy(Graphics g) {
        enemies = GameInit.getGameLoop().getEnemies();
        for (int i = 0; i < enemies.length; i++)
            if (!enemies[i].dead()) {
                g.setColor(Color.pink);
                g.fillOval(enemies[i].getPositionPixel().x - Enemy.SIZE / 2, enemies[i].getPositionPixel().y - Enemy.SIZE / 2, Enemy.SIZE, Enemy.SIZE);

                g.setColor(Color.green);
                g.fillRect(enemies[i].getPositionPixel().x - Enemy.SIZE / 2, enemies[i].getPositionPixel().y + 4, (int) (Enemy.SIZE * Math.max(enemies[i].getHitPoints(), 0) / enemies[i].getMaxHitPoints()), 2);
                g.setColor(Color.red);
                g.fillRect(enemies[i].getPositionPixel().x - Enemy.SIZE / 2, enemies[i].getPositionPixel().y + 7, (int) (Enemy.SIZE * Math.max(enemies[i].getArmor(), 0) / enemies[i].getMaxArmor()), 2);
                g.setColor(Color.blue);
                g.fillRect(enemies[i].getPositionPixel().x - Enemy.SIZE / 2, enemies[i].getPositionPixel().y + 10, (int) (Enemy.SIZE * Math.max(enemies[i].getShield(), 0) / enemies[i].getMaxShield()), 2);
            }
    }

    public void renderBase(Graphics g) {
        g.setColor(Color.green);
        g.drawArc(base.getPositionPixel().x - 15, base.getPositionPixel().y - 15, 30, 30, 90, -360 * base.getHitPoints() / base.getMaxHitPoints());
    }

    public void renderTower(Graphics g) {
        for (int i = 0; i < towers.size(); i++) {
            switch (towers.get(i).getEntityType()) {
                case TowerArmor -> g.setColor(Color.green);
                case TowerShield -> g.setColor(Color.blue);
                case TowerPoison -> g.setColor(Color.magenta);
                case TowerSlowdown -> g.setColor(Color.cyan);
            }
            g.drawRect(towers.get(i).getPositionPixel().x - 15, towers.get(i).getPositionPixel().y - 15, 30, 30);
        }
    }

    public void renderTileOverview(Graphics g) {
        GameLoop gameLoop = GameInit.getGameLoop();
        Point currentTile = gameLoop.getCurrentTile();
        Point corner = new Point(40 + 40 * GameInit.Columns + 10, 40);

        g.setColor(Color.red);

        if (currentTile.x != 0 || currentTile.y != 0) {
            if (tiles[currentTile.x][currentTile.y].getContent() == null) {
                g.drawString("buy:", corner.x + 36, corner.y + 120 + 20);
                g.drawString("AntiArmor Twr", corner.x + 5, corner.y + 120 + 50);
                g.drawString("AntiShield Twr", corner.x + 5, corner.y + 120 + 80);
                g.drawString("Poison Tower", corner.x + 5, corner.y + 120 + 110);
                g.drawString("Slowing Twr", corner.x + 5, corner.y + 120 + 140);
                g.drawRect(corner.x, corner.y + 120 + 30, 90, 30);
                g.drawRect(corner.x, corner.y + 120 + 60, 90, 30);
                g.drawRect(corner.x, corner.y + 120 + 90, 90, 30);
                g.drawRect(corner.x, corner.y + 120 + 120, 90, 30);
                g.drawRect(corner.x, corner.y + 120, 90, 30);

            } else if ( tiles[currentTile.x][currentTile.y].getContent().getEntityType() != EntityType.Base &&
                        tiles[currentTile.x][currentTile.y].getContent().getEntityType() != EntityType.Spawn) {
                g.drawString("upgrade", corner.x + 23, corner.y + 120 + 20);
                g.drawString("sell", corner.x + 36 + 90, corner.y + 120 + 20);
                ((Tower) tiles[currentTile.x][currentTile.y].getContent()).drawRange(g);
                g.drawRect(corner.x, corner.y + 120, 90, 30);
                g.drawRect(corner.x + 90, corner.y + 120, 90, 30);
            }
        }

        g.drawRect(corner.x, corner.y + 300, 180, 30);
        g.drawString("next wave", corner.x + 64, corner.y + 300 + 20);

        g.setColor(Color.gray);
        g.drawRect(40 + 40 * GameInit.Columns + 10, 40, 180, 40 * GameInit.Rows);
        g.getFont().getSize();
        g.drawString("X: " + currentTile.x, corner.x + 5, corner.y + g.getFont().getSize() + 2);
        g.drawString("Y: " + currentTile.y, corner.x + 40, corner.y + g.getFont().getSize() + 2);

        int y = corner.y + g.getFont().getSize() * 2 - 4;
        if (tiles[currentTile.x][currentTile.y].getContent() != null)
            for (String line : tiles[currentTile.x][currentTile.y].getContent().toString().split("\n"))
                g.drawString(line, corner.x + 5, y += g.getFontMetrics().getHeight());

        g.drawString("wave: " + gameLoop.getWave().getNumberOfWaves(), corner.x + 5, corner.y + 320 + 30);
        g.drawString("gold: " + GameLoop.getGold().getAmount(), corner.x + 5, corner.y + 320 + 50);
    }

    public void renderSpawn(Graphics g) {
        g.setColor(Color.blue);
        g.fillOval(spawn.getPositionPixel().x - Spawn.SIZE / 2, spawn.getPositionPixel().y - Spawn.SIZE / 2, Spawn.SIZE, Spawn.SIZE);
    }

    public void renderGameOverScreen(Graphics g) {
        g.setColor(Color.darkGray);
        g.fillRect(100, 100, 920, 480);

        g.setColor(Color.black);
        g.setFont(new Font("TimesRoman", Font.PLAIN, 120));
        g.drawString("GAME OVER", 170, 350);

        g.setFont(new Font("TimesRoman", Font.PLAIN, 25));
        g.drawString("you survived " + (Wave.getNumberOfWaves() - 1) + " waves!", 170, 390);

    }
}
