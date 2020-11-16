package pw.proz;

import java.awt.*;


public class Attacker {
    private final int START_X = 5;
    private final int START_Y = 3;

    int size=24;
    private float movementSpeed = 1;
    private float hitPoints;
    private float armor;
    private float shield;
    private static int numOfAttackers = 0;
    Point position=new Point(20+40*START_X,20+40*START_Y);

    Attacker(){
        numOfAttackers++;
    }

    public static int getNumOfAttackers() {
        return numOfAttackers;
    }

    public void move(Base toWhichBase){
        if(toWhichBase.distanceToBase(position) > 1600)
            switch (toWhichBase.whereIsBase(position)) {
                case N -> position.y -= movementSpeed;
                case S -> position.y += movementSpeed;
                case E -> position.x += movementSpeed;
                case W -> position.x -= movementSpeed;
            }
    }

    public void draw(Graphics g){
        g.setColor(Color.red);
        g.drawOval(position.x-size/2, position.y-size/2, size,size);
    }

    public void attack(Base my_base) {
        if(my_base.distanceToBase(position) == 1600) {
            my_base.hit_points--;
        }
    }
}
