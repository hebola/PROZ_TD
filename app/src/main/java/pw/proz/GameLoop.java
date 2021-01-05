package pw.proz;

import Entity.*;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import static java.lang.Math.min;

public class GameLoop {

    private double interpolation = 0;
    private final int TICKS_PER_SECOND = 30;
    private final int SKIP_TICKS = 1000 / TICKS_PER_SECOND;
    private final int MAX_FRAMESKIP = 5;
    private Point currentTile = new Point(0, 0);

    private Tile[][] tiles;
    private List<Tower> towers;

    public List<Tower> getTowers() {
        return towers;
    }

    public Tile[][] getTiles() {
        return tiles;
    }

    public GameLoop() {
        tiles = new Tile[App.Rows][App.Columns];
        for(int i=0; i<App.Rows;i++)
            for(int j=0; j<App.Columns;j++)
                tiles[i][j]=new Tile();

        towers = new ArrayList<>();
        towers.add(new TowerArmor(1, 50, 2, 2));
        tiles[2][2].setContent(towers.get(0));
        towers.add(new TowerArmor(1, 50, 2, 3));
        tiles[2][3].setContent(towers.get(1));

    }

    private static Wave wave = new Wave(0);

    public static Wave getWave() {
        return wave;
    }

    public static Enemy[] getEnemies() {
        return wave.getEnemy();
    }

    public Point getCurrentTile() {
        return currentTile;
    }

    public void setCurrentTile(Point currentTile) {
        this.currentTile = currentTile;
    }

    void run(Display display, Base base, Spawn spawn) {

        double next_game_tick = System.currentTimeMillis();
        int loops;
        int spaceTickCounter = 0;
        int spaceCounter = 0;

        while (true) {
            loops = 0;

            while (System.currentTimeMillis() > next_game_tick && loops < MAX_FRAMESKIP) {

                if (wave.getNumOfEnemiesAlive() <= 0) {
                    wave = new Wave(5);
                    spaceTickCounter = 0;
                    spaceCounter = 0;
                } else {
                    if (spaceTickCounter < wave.getSpaceBetweenEnemies())
                        spaceTickCounter++;
                    else {
                        spaceCounter++;
                        spaceTickCounter = 0;
                    }
                    for (int i = 0; i < min(wave.getNumOfEnemies(), spaceCounter); i++)
                        wave.getEnemy()[i].move(base);
                }

                //enemy.move(base);

                for (int i = 0; i < towers.size(); i++)
                    towers.get(i).attack(wave.getEnemy());

                next_game_tick += SKIP_TICKS;
                loops++;
            }

            interpolation = (System.currentTimeMillis() + SKIP_TICKS - next_game_tick / (double) SKIP_TICKS);
            //System.out.println(interpolation);
            display.repaint();
            //display.graphic.repaint();
        }
    }

}
