package Entity;

import pw.proz.App;
import pw.proz.Direction;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Point2D;


public class Enemy extends Entity {

    int size = 24;
    private float movementSpeed;
    private float maxHitPoints;
    private float hitPoints;
    private float maxArmor;
    private float armor;
    private float maxShield;
    private float shield;
    private Point2D.Float exactPosition;
    private Direction direction;

    private float movementSpeedFactor;
    private float poison;
    private int effectDuration;
    private static int numOfEnemies = 0;

    public Enemy(int x, int y, float armor, float shield, float maxHitPoints) {
        numOfEnemies++;
        positionPixel = new Point(x, y);
        positionTile = pixelToTile();
        exactPosition = new Point2D.Float(positionPixel.x, positionPixel.y);
        movementSpeed = 4;
        this.maxHitPoints = maxHitPoints;
        hitPoints = maxHitPoints;
        this.maxShield = shield;
        this.maxArmor = armor;
        this.armor = armor;
        this.shield = shield;
        movementSpeedFactor = 1;
        poison = 0;
        effectDuration = 0;
        direction = Direction.N;
    }

    public static int getNumOfEnemies() {
        return numOfEnemies;
    }

    public void move(Base toWhichBase) {
        int[][] route = App.getRoute();
        if ((direction == Direction.E && Math.abs((exactPosition.x + 20) % 40 - (exactPosition.x + movementSpeed * movementSpeedFactor + 20) % 40) > 20) ||
                (direction == Direction.W && Math.abs((exactPosition.x + 20) % 40 - (exactPosition.x - movementSpeed * movementSpeedFactor + 20) % 40) > 20) ||
                (direction == Direction.N && Math.abs((exactPosition.y + 20) % 40 - (exactPosition.y - movementSpeed * movementSpeedFactor + 20) % 40) > 20) ||
                (direction == Direction.S && Math.abs((exactPosition.y + 20) % 40 - (exactPosition.y + movementSpeed * movementSpeedFactor + 20) % 40) > 20)) {

            positionTile = pixelToTile();
            int min = App.min(route[positionTile.x + 1][positionTile.y], App.min(route[positionTile.x - 1][positionTile.y], App.min(route[positionTile.x][positionTile.y + 1], route[positionTile.x][positionTile.y - 1])));

            if (route[positionTile.x + 1][positionTile.y] == min)
                direction = Direction.E;
            else if (route[positionTile.x - 1][positionTile.y] == min)
                direction = Direction.W;
            else if (route[positionTile.x][positionTile.y + 1] == min)
                direction = Direction.S;
            else direction = Direction.N;

        }
        if (!dead()) {
            if (toWhichBase.distanceToBase(positionPixel) > 1600) {
                switch (direction) {
                    case N -> exactPosition.y -= (movementSpeed * movementSpeedFactor);
                    case S -> exactPosition.y += (movementSpeed * movementSpeedFactor);
                    case E -> exactPosition.x += (movementSpeed * movementSpeedFactor);
                    case W -> exactPosition.x -= (movementSpeed * movementSpeedFactor);
                }
                positionPixel.x = (int) exactPosition.x;
                positionPixel.y = (int) exactPosition.y;
                movementSpeedFactor = 1;
            } else toWhichBase.getHit(1);

            if (effectDuration > 0) {
                effectDuration--;
                hitPoints -= poison;
            }
        }
    }

    public void draw(Graphics g) {
        if (!dead()) {
            g.setColor(Color.pink);
            g.fillOval(positionPixel.x - size / 2, positionPixel.y - size / 2, size, size);

            g.setColor(Color.green);
            g.fillRect(positionPixel.x - size / 2, positionPixel.y + 4, (int) (size * Math.max(hitPoints, 0) / maxHitPoints), 2);
            g.setColor(Color.red);
            g.fillRect(positionPixel.x - size / 2, positionPixel.y + 7, (int) (size * Math.max(armor, 0) / maxArmor), 2);
            g.setColor(Color.blue);
            g.fillRect(positionPixel.x - size / 2, positionPixel.y + 10, (int) (size * Math.max(shield, 0) / maxShield), 2);

        }
    }


    public float getArmor() {
        return armor;
    }


    public float getHitPoints() {
        return hitPoints;
    }

    public float getShield() {
        return shield;
    }

    public void setHitPoints(float hitPoints) {
        this.hitPoints = hitPoints;
    }

    public void setArmor(float armor) {
        this.armor = armor;
    }

    public void setShield(float shield) {
        this.shield = shield;
    }

    public boolean dead() {
        return hitPoints <= 0;
    }

    public void setMovementSpeedFactor(float movementSpeedFactor) {
        this.movementSpeedFactor = movementSpeedFactor;
    }

    public void setPoison(float poison) {
        this.poison = poison;
    }

    public void setEffectDuration(int effectDuration) {
        this.effectDuration = effectDuration;
    }

    @Override
    public String printData() {
        return null;
    }
}
