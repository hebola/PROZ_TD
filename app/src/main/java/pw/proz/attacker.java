package pw.proz;

import java.awt.*;

public class attacker {
    int size=24;
    private float movement_speed=1;
    private float hit_points;
    private float armor;
    private float shield;
    private static int num_of_attackers = 0;
    Point position=new Point(20+40*8,20+40*8);

    attacker(){
        num_of_attackers++;
    }

    public static int getNum_of_attackers() {
        return num_of_attackers;
    }

    public void move(){
        position.x+=movement_speed;
    }

    public void draw(Graphics g){
        g.setColor(Color.red);
        g.drawOval(position.x-size/2, position.y-size/2, size,size);
    }

    public void atack(base my_base) {
        int dir = my_base.where_is_base(position);
        if(dir != 0) {
            my_base.hit_points--;
        }
    }
}
