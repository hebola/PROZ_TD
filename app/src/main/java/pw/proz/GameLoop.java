package pw.proz;

import java.awt.*;

import static java.lang.Math.min;

public class GameLoop {

    private double interpolation = 0;
    private final int TICKS_PER_SECOND = 30;
    private final int SKIP_TICKS = 1000 / TICKS_PER_SECOND;
    private final int MAX_FRAMESKIP = 5;

    private static Wave wave = new Wave(0);

    public static Wave getWave() {
        return wave;
    }

    public static Enemy[] getEnemies() {
        return wave.getEnemy();
    }

    void run(Display display, Enemy enemy, Tower tower, Base base) {

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
                    for (int i = 0; i < min(wave.getNumOfEnemies(),spaceCounter); i++)
                        wave.getEnemy()[i].move(base);
                }

                //enemy.move(base);

                tower.attack(wave.getEnemy());

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
