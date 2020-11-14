package pw.proz;

import java.awt.*;

public class base {
    Point position=new Point(540,20+40*8);
    public int hit_points=20;

    public int where_is_base(Point pos) {
        if(position.x==pos.x && position.y==pos.y-40)
            return 1;
        else if(position.x==pos.x+40 && position.y==pos.y)
            return 2;
        else if(position.x==pos.x && position.y==pos.y+40)
            return 3;
        else if(position.x==pos.x-40 && position.y==pos.y)
            return 4;
        else return 0;
    }

    public void draw_hit_points(Graphics g) {
        g.setColor(Color.green);
        g.drawArc(position.x-15, position.y-15, 30,30,90,-18*hit_points);
    }

    public boolean is_Base_down(){
        return hit_points==0?true:false;
    }
}
