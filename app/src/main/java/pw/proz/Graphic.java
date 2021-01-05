package pw.proz;

import java.awt.*;
import javax.swing.*;


public class Graphic extends JPanel{

    private Renderer render;
    public final int Rows = 15;
    public final int Columns = 26;

    public Graphic(){
        super();
        this.setFocusable(true);

        this.addMouseListener(new Mouse());

        this.render = new Renderer();
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        this.setBackground(Color.BLACK);
        //System.out.println(this.getBounds());
        //this.setPreferredSize(new Dimension(1280,720));
        //this.setSize(new Dimension(1280,720));

        Graphics2D g2D = (Graphics2D) g;

        g2D.setColor(Color.darkGray);
        for(int i=0;i<=Columns;i++)
            g2D.drawLine(40+40*i,40,40+40*i,40+40*Rows);
        for(int i=0;i<=Rows;i++)
            g2D.drawLine(40,40+40*i,40+40*Columns,40+40*i);

        g2D.setColor(Color.red);
        g2D.drawRect(0,0,1280-1,720-1);

        render.renderBase(g);
        render.renderTower(g);
        render.renderEnemy(g);

        render.renderTileOverview(g);
    }


}
