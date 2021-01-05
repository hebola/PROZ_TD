package Entity;

import java.awt.*;

public abstract class Entity {
    protected Point position;
    public abstract void draw(Graphics g);

    public Point getPosition() {
        return position;
    }

    public abstract String printData();
}
