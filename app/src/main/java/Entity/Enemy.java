package Entity;

import javax.swing.*;
import java.awt.*;


public class Enemy extends Entity{
    //private final int START_X = 5;
    //private final int START_Y = 3;

    int size = 24;
    private float movementSpeed = 2;
    private float hitPoints;
    private float armor;
    private float shield;
    private static int numOfEnemys = 0;
    private JPanel panel;

    public Enemy(int x, int y) {
        numOfEnemys++;
        position = new Point(20 + 40 * x, 20 + 40 * y);
        panel = new JPanel();
        hitPoints=20;
        armor=0;

    }

    public static int getNumOfEnemys() {
        return numOfEnemys;
    }

    public void move(Base toWhichBase) {
        if(!dead())
        if (toWhichBase.distanceToBase(position) > 1600)
            switch (toWhichBase.whereIsBase(position)) {
                case N -> position.y -= movementSpeed;
                case S -> position.y += movementSpeed;
                case E -> position.x += movementSpeed;
                case W -> position.x -= movementSpeed;
            }
    }

    public void draw(Graphics g) {
            g.setColor(Color.red);
            g.drawOval(position.x - size / 2, position.y - size / 2, size, size);

            g.setColor(Color.green);
            g.drawArc(position.x - 10, position.y - 10, 20, 20, 90, (int) (-18 * hitPoints));

    }


    public void attack(Base my_base) {
        if (my_base.distanceToBase(position) == 1600) {
            my_base.hitPoints--;
        }
    }

    public float getArmor() {
        return armor;
    }

    public float getMovementSpeed() {
        return movementSpeed;
    }

    public float getHitPoints() {
        return hitPoints;
    }

    public float getShield() {
        return shield;
    }

    public void setMovementSpeed(float movementSpeed) {
        this.movementSpeed = movementSpeed;
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

    public Point getPosition() {
        return position;
    }

    @Override
    public String printData() {
        return null;
    }
}
