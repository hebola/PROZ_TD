package Entity;

import java.awt.*;

public abstract class Entity {
    protected Point positionPixel;
    protected Point positionTile;

    public abstract void draw(Graphics g);

    public Point getPositionPixel() {
        return positionPixel;
    }

    public Point getPositionTile() {
        return positionTile;
    }

    public Point pixelToTile() {
        return new Point(positionPixel.x/40, positionPixel.y/40);
    }

    public abstract String printData();
}
