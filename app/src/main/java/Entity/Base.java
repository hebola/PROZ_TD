package Entity;

import pw.proz.Direction;

import java.awt.*;

public class Base extends Entity{
    private int BASE_X;
    private int BASE_Y;
    public int hitPoints = 20;

    public Base(int x, int y){
        BASE_X = x;
        BASE_Y = y;
        position = new Point(20 + 40 * BASE_X, 20 + 40 * BASE_Y);
    }

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
        g.drawArc(position.x - 15, position.y - 15, 30,30,90, - 18 * hitPoints);
    }

    public boolean isBaseDown(){
        return hitPoints == 0;
    }

    public int getBASE_X(){
        return BASE_X;
    }

    public int getBASE_Y(){
        return BASE_Y;
    }

    public void draw(Graphics g) {

    }

    @Override
    public String printData() {
        return null;
    }
}