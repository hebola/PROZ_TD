package pw.proz;

import javax.swing.*;
import java.awt.*;


public class Display extends JFrame{

    Graphic grp = new Graphic();

    public Display(){


        //this.setPreferredSize(new Dimension(1280,720));
        //this.setMinimumSize(new Dimension(1280,720));
        //this.setMaximumSize(new Dimension(1280,720));
        this.setSize(new Dimension(1297,760));//1280x720
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.add(grp);
        //this.setBounds(100,100,1297,760);
        //this.pack();
        //this.setSize(new Dimension(1280,720));
        this.setVisible(true);

    }

}
