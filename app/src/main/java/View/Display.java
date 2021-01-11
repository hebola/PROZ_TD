package View;

import java.awt.*;
import javax.swing.*;


public class Display extends JFrame {

    private Graphic graphic = new Graphic();

    public Display() {
        this.setSize(new Dimension(1296, 759));//1280x720
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.add(graphic);
        this.setVisible(true);
    }

    public Graphic getGraphic() {
        return graphic;
    }
}
