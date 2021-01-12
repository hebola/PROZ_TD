package View;

import java.awt.*;
import javax.swing.*;


public class Display extends JFrame {

    private Graphic graphic;

    public Display() {
        graphic = new Graphic();
        this.setSize(new Dimension(1296, 759));//1280x720
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.add(graphic);
        this.setVisible(true);
    }
}
