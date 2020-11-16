package pw.proz;

import java.awt.*;
import javax.swing.*;


public class Graphic extends JPanel{

    public Attacker atkr = new Attacker();
    public Base my_base = new Base();

    public void paint(Graphics g){
        super.paint(g);
        this.setBackground(Color.BLACK);
        //this.setPreferredSize(new Dimension(1280,720));
        //this.setSize(new Dimension(1280,720));

        Graphics2D g2D = (Graphics2D) g;

        g2D.setColor(Color.darkGray);

        for(int i=0;i<30;i++)
            for(int j=0;j<16;j++)
                g2D.drawRect(40+40*i,40+40*j,40,40);
        g2D.setColor(Color.red);
        g2D.drawRect(0,0,1280,720);
        this.atkr.draw(g);
        this.my_base.drawHitPoints(g);
    }


}
