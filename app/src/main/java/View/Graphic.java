package View;

import Controller.GameInit;
import Controller.GameLoop;
import Controller.Mouse;

import java.awt.*;
import javax.swing.*;


public class Graphic extends JPanel {

    private Renderer render;

    public Graphic() {
        super();
        this.setFocusable(true);

        this.addMouseListener(new Mouse());

        this.render = new Renderer();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        this.setBackground(Color.BLACK);

        Graphics2D g2D = (Graphics2D) g;

        g2D.setColor(Color.darkGray);
        for (int i = 0; i <= GameInit.COLUMNS; i++)
            g2D.drawLine(40 + 40 * i, 40, 40 + 40 * i, 40 + 40 * GameInit.ROWS);
        for (int i = 0; i <= GameInit.ROWS; i++)
            g2D.drawLine(40, 40 + 40 * i, 40 + 40 * GameInit.COLUMNS, 40 + 40 * i);

        g2D.setColor(Color.red);
        g2D.drawRect(0, 0, 1280 - 1, 720 - 1);

        render.renderBase(g);
        render.renderTower(g);
        render.renderEnemy(g);
        render.renderSpawn(g);
        render.renderTileOverview(g);
        if (GameLoop.getLoseCondition())
            render.renderGameOverScreen(g);
    }
}
