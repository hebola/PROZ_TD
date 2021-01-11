package Model;

import java.awt.*;

public abstract class Entity {
    protected Point positionPixel;
    protected Point positionTile;
    protected EntityType entityType;

    public Point getPositionPixel() {
        return positionPixel;
    }

    public Point getPositionTile() {
        return positionTile;
    }

    public Point pixelToTile() {
        return new Point(positionPixel.x / 40, positionPixel.y / 40);
    }

    public EntityType getEntityType() {
        return entityType;
    }
}
