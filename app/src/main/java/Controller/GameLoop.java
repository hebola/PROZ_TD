package Controller;

import Model.*;
import View.Display;

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
    private static boolean loseCondition;

    private static Base base = new Base(26, 15);
    private static Spawn spawn = new Spawn(1, 1);
    private static Gold gold = new Gold(500);

    public static Gold getGold() {
        return gold;
    }
    public static Base getBase() {
        return base;
    }
    public static Spawn getSpawn() {
        return spawn;
    }

    private Tile[][] tiles;
    private List<Tower> towers;

    public List<Tower> getTowers() {
        return towers;
    }

    public Tile[][] getTiles() {
        return tiles;
    }

    public static boolean getLoseCondition() {
        return loseCondition;
    }

    public GameLoop() {
        tiles = new Tile[GameInit.Columns + 1][GameInit.Rows + 1];
        for (int i = 0; i < GameInit.Rows + 1; i++)
            for (int j = 0; j < GameInit.Columns + 1; j++)
                tiles[j][i] = new Tile();

        towers = new ArrayList<>();
    }

    private static Wave wave = new Wave();

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

    void run(Display display) {

        double next_game_tick = System.currentTimeMillis();
        int loops;
        loseCondition = false;

        tiles[base.getPositionTile().x][base.getPositionTile().y].setContent(base);
        tiles[spawn.getPositionTile().x][spawn.getPositionTile().y].setContent(spawn);
        try {
            GameInit.recalculateRoute(base.getPositionTile());
        } catch (Exception e) {
            e.printStackTrace();
        }

        while (!loseCondition) {
            loops = 0;

            while (System.currentTimeMillis() > next_game_tick && loops < MAX_FRAMESKIP) {

                if (wave.getSpaceTickCounter() < wave.getSpaceBetweenEnemies())
                    wave.setSpaceTickCounter(wave.getSpaceTickCounter() + 1);
                else {
                    wave.setSpaceCounter(wave.getSpaceCounter() + 1);
                    wave.setSpaceTickCounter(0);
                }
                for (int i = 0; i < min(wave.getNumOfEnemies(), wave.getSpaceCounter()); i++)
                    wave.getEnemy()[i].move(base);


                for (int i = 0; i < towers.size(); i++)
                    towers.get(i).attack(wave.getEnemy());

                next_game_tick += SKIP_TICKS;
                loops++;
            }

            interpolation = (System.currentTimeMillis() + SKIP_TICKS - next_game_tick / (double) SKIP_TICKS);
            display.repaint();
            if (base.isBaseDown())
                loseCondition = true;
        }

    }

    public void nextWave() {
        if (wave.getNumOfEnemiesAlive() == 0) {
            gold.addGold(100 + 30 * wave.getNumberOfWaves());
            wave = new Wave();
            wave.setSpaceTickCounter(0);
            wave.setSpaceCounter(0);
        }
    }
}
