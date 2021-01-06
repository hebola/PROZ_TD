package Entity;

import java.awt.*;

public class Spawn extends Entity{

    int size = 30;
    public Spawn(int x, int y){
        positionTile = new Point(x, y);
        positionPixel = new Point(20 + 40 * x, 20 + 40 * y);
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(Color.blue);
        g.fillOval(positionPixel.x - size / 2, positionPixel.y - size / 2, size, size);
    }

    @Override
    public String printData() {
        return "Spawn";
    }
}
