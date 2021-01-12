package Controller;

import Model.*;
import View.Display;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

import static java.lang.Math.min;

public class GameLoop {

    private double interpolation;
    private final int TICKS_PER_SECOND;
    private final int SKIP_TICKS;
    private final int MAX_FRAMESKIP;
    private Point currentTile;
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
        interpolation = 0;
        TICKS_PER_SECOND = 30;
        SKIP_TICKS = 1000 / TICKS_PER_SECOND;
        MAX_FRAMESKIP = 5;
        currentTile = new Point(0, 0);

        tiles = new Tile[GameInit.COLUMNS + 1][GameInit.ROWS + 1];
        for (int i = 0; i < GameInit.ROWS + 1; i++)
            for (int j = 0; j < GameInit.COLUMNS + 1; j++)
                tiles[j][i] = new Tile();

        towers = new ArrayList<>();
    }

    private static Wave wave = new Wave();
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
        } catch (IllegalStateException e) {
            //will never happen, unless you separate base from spawn, what can't happen before game is started
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
                IntStream.range(0, min(wave.getNumOfEnemies(), wave.getSpaceCounter())).forEach(i -> wave.getEnemy()[i].move(base));


                for (Tower tower : towers) tower.attack(wave.getEnemy());

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
            gold.addGold(100 + 30 * Wave.getNumberOfWaves());
            wave = new Wave();
            wave.setSpaceTickCounter(0);
            wave.setSpaceCounter(0);
        }
    }
}
