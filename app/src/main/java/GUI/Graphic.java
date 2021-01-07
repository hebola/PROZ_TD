package GUI;

import pw.proz.App;
import pw.proz.GameLoop;
import pw.proz.Mouse;

import java.awt.*;
import javax.swing.*;


public class Graphic extends JPanel{

    private Renderer render;


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
        for(int i = 0; i<= App.Columns; i++)
            g2D.drawLine(40+40*i,40,40+40*i,40+40*App.Rows);
        for(int i=0;i<=App.Rows;i++)
            g2D.drawLine(40,40+40*i,40+40*App.Columns,40+40*i);

        g2D.setColor(Color.red);
        g2D.drawRect(0,0,1280-1,720-1);

        render.renderBase(g);
        render.renderTower(g);
        render.renderEnemy(g);
        render.renderSpawn(g);
        render.renderTileOverview(g);
        if(GameLoop.getLoseCondition())
            render.renderGameOverScreen(g);

    }


}
