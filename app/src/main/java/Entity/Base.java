package Entity;

import java.awt.*;

public class Base extends Entity {
    private int BASE_X;
    private int BASE_Y;
    private int maxHitPoints;
    private int hitPoints;


    public Base(int x, int y) {
        maxHitPoints = 100;
        hitPoints = maxHitPoints;
        positionTile = new Point(x, y);
        positionPixel = new Point(20 + 40 * x, 20 + 40 * y);
    }

    public float distanceToBase(Point pos) {
        return (float) (Math.pow(positionPixel.x - pos.x, 2) + Math.pow(positionPixel.y - pos.y, 2));
    }

    public void drawHitPoints(Graphics g) {
        g.setColor(Color.green);
        g.drawArc(positionPixel.x - 15, positionPixel.y - 15, 30, 30, 90, -360 * hitPoints / maxHitPoints);
    }

    public boolean isBaseDown() {
        return hitPoints == 0;
    }


    public void draw(Graphics g) {

    }

    @Override
    public String printData() {
        return "Base\nhit points: " + hitPoints;
    }

    public void getHit(int power) {
        hitPoints -= power;
    }
}