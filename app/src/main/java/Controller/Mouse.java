package Controller;

import Model.*;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Mouse implements MouseListener {
    private final int COLUMNS = GameInit.COLUMNS;
    private final int ROWS = GameInit.ROWS;
    private final Point CORNER = new Point(40 + 40 * COLUMNS + 10, 40);
    private final Rectangle BOARD = new Rectangle(40, 40, 40 * COLUMNS, 40 * ROWS);
    private final Rectangle ACTION_BOX = new Rectangle(CORNER.x, CORNER.y + 120, 180, 30);
    private final Rectangle BUYING_BOX = new Rectangle(CORNER.x, CORNER.y + 150, 90, 120);
    private final Rectangle NEXT_WAVE_BOX = new Rectangle(CORNER.x, CORNER.y + 300, 180, 30);
    private GameLoop gameLoop;

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

        gameLoop = GameInit.getGameLoop();

        Point tile;

        if (BOARD.contains(mouseX, mouseY)) {
            tile = new Point((mouseX) / 40, (mouseY) / 40);
            if (gameLoop.getCurrentTile().equals(tile))
                gameLoop.setCurrentTile(new Point(0, 0));
            else
                gameLoop.setCurrentTile(tile);

        } else if (!gameLoop.getCurrentTile().equals(new Point(0, 0))) {
            tile = gameLoop.getCurrentTile();
            if (ACTION_BOX.contains(mouseX, mouseY)) {
                if (gameLoop.getTiles()[tile.x][tile.y].getContent() != null)
                    if (gameLoop.getTiles()[tile.x][tile.y].getContent().getEntityType() != EntityType.Base &&
                            gameLoop.getTiles()[tile.x][tile.y].getContent().getEntityType() != EntityType.Spawn)
                        if (mouseX < CORNER.x + 90) /* upgrade tower */ {
                            Tower tower;
                            tower = (Tower) gameLoop.getTiles()[tile.x][tile.y].getContent();
                            if (GameLoop.getGold().subtractGold(tower.getUpgradeCost() * tower.getLevel()))
                                tower.upgrade();
                        } else {
                            Tower tower = (Tower) gameLoop.getTiles()[tile.x][tile.y].getContent();
                            GameLoop.getGold().addGold(((tower.getLevel() - 1) * tower.getUpgradeCost()) / 2 + Tower.getCost());
                            gameLoop.getTowers().remove(gameLoop.getTiles()[tile.x][tile.y].getContent());
                            gameLoop.getTiles()[tile.x][tile.y].setContent(null);
                        }
            } else if (BUYING_BOX.contains(mouseX, mouseY)) {
                if (gameLoop.getTiles()[tile.x][tile.y].getContent() == null) {
                    try {
                        if (mouseY < CORNER.y + 150 + 30) {
                            if (GameLoop.getGold().subtractGold(TowerArmor.getCost()))
                                gameLoop.getTiles()[tile.x][tile.y].setContent(new TowerArmor(1, 50, tile.x, tile.y));
                        } else if (mouseY < CORNER.y + 150 + 60) {
                            if (GameLoop.getGold().subtractGold(TowerShield.getCost()))
                                gameLoop.getTiles()[tile.x][tile.y].setContent(new TowerShield(1, 50, tile.x, tile.y));
                        } else if (mouseY < CORNER.y + 150 + 90) {
                            if (GameLoop.getGold().subtractGold(TowerPoison.getCost()))
                                gameLoop.getTiles()[tile.x][tile.y].setContent(new TowerPoison(1, 50, tile.x, tile.y));
                        } else {
                            if (GameLoop.getGold().subtractGold(TowerSlowdown.getCost()))
                                gameLoop.getTiles()[tile.x][tile.y].setContent(new TowerSlowdown(1, 50, tile.x, tile.y));
                        }

                        if (gameLoop.getTiles()[tile.x][tile.y].getContent() != null)
                            gameLoop.getTowers().add((Tower) gameLoop.getTiles()[tile.x][tile.y].getContent());

                        GameInit.recalculateRoute(GameLoop.getBase().getPositionTile());
                    } catch (IllegalStateException ex) {
                        gameLoop.getTowers().remove(gameLoop.getTiles()[tile.x][tile.y].getContent());
                        gameLoop.getTiles()[tile.x][tile.y].setContent(null);
                        GameLoop.getGold().addGold(100);
                    }
                }
            } else if (!NEXT_WAVE_BOX.contains(mouseX, mouseY))
                gameLoop.setCurrentTile(new Point(0, 0));
        }

        if (NEXT_WAVE_BOX.contains(mouseX, mouseY)) {
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