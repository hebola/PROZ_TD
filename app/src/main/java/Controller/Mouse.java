package Controller;

import Model.*;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Mouse implements MouseListener {

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        int mouseX = e.getX();
        int mouseY = e.getY();
        int Columns = GameInit.Columns;
        int Rows = GameInit.Rows;
        GameLoop gameLoop = GameInit.getGameLoop();

        Point corner = new Point(40 + 40 * Columns + 10, 40);
        Rectangle board = new Rectangle(40, 40, 40 * Columns, 40 * Rows);
        Rectangle actionBox = new Rectangle(corner.x, corner.y + 120, 180, 30);
        Rectangle buyingBox = new Rectangle(corner.x, corner.y + 150, 90, 120);
        Rectangle nextWaveBox = new Rectangle(corner.x, corner.y + 300, 180, 30);
        Point tile = new Point(0, 0);

        if (board.contains(mouseX, mouseY)) {
            tile = new Point((mouseX) / 40, (mouseY) / 40);
            if (gameLoop.getCurrentTile().equals(tile))
                gameLoop.setCurrentTile(new Point(0, 0));
            else
                gameLoop.setCurrentTile(tile);

        } else if (!gameLoop.getCurrentTile().equals(new Point(0, 0))) {
            tile = gameLoop.getCurrentTile();
            if (actionBox.contains(mouseX, mouseY)) {
                if (gameLoop.getTiles()[tile.x][tile.y].getContent() != null)
                    if (    gameLoop.getTiles()[tile.x][tile.y].getContent().getEntityType() != EntityType.Base &&
                            gameLoop.getTiles()[tile.x][tile.y].getContent().getEntityType() != EntityType.Spawn)
                        if (mouseX < corner.x + 90) /* upgrade tower */ {
                            Tower tower;
                            tower = (Tower) gameLoop.getTiles()[tile.x][tile.y].getContent();
                            if (GameLoop.getGold().subtractGold(tower.getUpgradeCost() * tower.getLevel()))
                                tower.upgrade();
                        } else {
                            Tower tower = (Tower) gameLoop.getTiles()[tile.x][tile.y].getContent();
                            GameLoop.getGold().addGold(((tower.getLevel() - 1) * tower.getUpgradeCost()) / 2 + tower.cost);
                            gameLoop.getTowers().remove(gameLoop.getTiles()[tile.x][tile.y].getContent());
                            gameLoop.getTiles()[tile.x][tile.y].setContent(null);
                        }
            } else if (buyingBox.contains(mouseX, mouseY)) {
                if (gameLoop.getTiles()[tile.x][tile.y].getContent() == null) {
                    try {
                        if (mouseY < corner.y + 150 + 30) {
                            if (GameLoop.getGold().subtractGold(TowerArmor.cost))
                                gameLoop.getTiles()[tile.x][tile.y].setContent(new TowerArmor(1, 50, tile.x, tile.y));
                        } else if (mouseY < corner.y + 150 + 60) {
                            if (GameLoop.getGold().subtractGold(TowerShield.cost))
                                gameLoop.getTiles()[tile.x][tile.y].setContent(new TowerShield(1, 50, tile.x, tile.y));
                        } else if (mouseY < corner.y + 150 + 90) {
                            if (GameLoop.getGold().subtractGold(TowerPoison.cost))
                                gameLoop.getTiles()[tile.x][tile.y].setContent(new TowerPoison(1, 50, tile.x, tile.y));
                        } else {
                            if (GameLoop.getGold().subtractGold(TowerSlowdown.cost))
                                gameLoop.getTiles()[tile.x][tile.y].setContent(new TowerSlowdown(1, 50, tile.x, tile.y));
                        }

                        if (gameLoop.getTiles()[tile.x][tile.y].getContent() != null)
                            gameLoop.getTowers().add((Tower) gameLoop.getTiles()[tile.x][tile.y].getContent());

                        GameInit.recalculateRoute(GameLoop.getBase().getPositionTile());
                    } catch (Exception ex) {
                        gameLoop.getTowers().remove(gameLoop.getTiles()[tile.x][tile.y].getContent());
                        gameLoop.getTiles()[tile.x][tile.y].setContent(null);
                    }
                }
            } else if (nextWaveBox.contains(mouseX, mouseY)) ;
            else gameLoop.setCurrentTile(new Point(0, 0));
        }

        if (nextWaveBox.contains(mouseX, mouseY)) {
            gameLoop.nextWave();
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }
}