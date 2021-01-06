package GUI;

import java.awt.*;
import javax.swing.*;


public class Display extends JFrame{

    private Graphic graphic = new Graphic();

    public Display(){

        //this.setPreferredSize(new Dimension(1280,720));
        //this.setMinimumSize(new Dimension(1280,720));
        //this.setMaximumSize(new Dimension(1280,720));
        this.setSize(new Dimension(1296,759));//1280x720
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.add(graphic);
        //this.setBounds(100,100,1297,760);
        //this.pack();
        //this.setSize(new Dimension(1280,720));
        this.setVisible(true);

    }

    public Graphic getGraphic() {
        return graphic;
    }
}
