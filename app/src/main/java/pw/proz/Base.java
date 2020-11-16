package pw.proz;

import java.awt.*;

public class Base {
    private final int BASE_X = 15;
    private final int BASE_Y = 8;
    private Point position = new Point(20 + 40 * BASE_X, 20 + 40 * BASE_Y);
    public int hit_points = 20;

    public Direction whereIsBase(Point pos) {
        if(position.y < pos.y)
            return Direction.N;
        else if(position.x > pos.x)
            return Direction.E;
        else if(position.y > pos.y)
            return Direction.S;
        else if(position.x < pos.x)
            return Direction.W;
        else
            return Direction.AP;
    }

    public float distanceToBase(Point pos){
        return (float) (Math.pow(position.x - pos.x, 2) + Math.pow(position.y - pos.y, 2));
    }

    public void drawHitPoints(Graphics g) {
        g.setColor(Color.green);
        g.drawArc(position.x - 15, position.y - 15, 30,30,90, - 18 * hit_points);
    }

    public boolean isBaseDown(){
        return hit_points == 0;
    }
}