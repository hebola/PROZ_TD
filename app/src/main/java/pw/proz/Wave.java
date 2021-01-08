package pw.proz;

import Entity.Enemy;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import static java.lang.Math.min;

public class Wave {
    private int numOfEnemies;
    private int spaceBetweenEnemies;

    private int spaceTickCounter;
    private int spaceCounter;

    private Point spawnPoint = App.getSpawn().getPositionPixel();
    private List<Enemy> enemy;

    static int numberOfWaves = 0;

    public int getSpaceBetweenEnemies() {
        return spaceBetweenEnemies;
    }

    Wave(int numOfEnemies) {
        this.numOfEnemies = numOfEnemies;
        this.spaceBetweenEnemies = 15;
        enemy = new ArrayList<>();

        for (int i = 0; i < numOfEnemies; i++)
            switch (numberOfWaves % 7) {
                case 0 -> enemy.add(new Enemy(spawnPoint.x, spawnPoint.y, 20*numberOfWaves, 10, 50));
                case 1 -> enemy.add(new Enemy(spawnPoint.x, spawnPoint.y, 10, 20*numberOfWaves, 50));
                case 2 -> enemy.add(new Enemy(spawnPoint.x, spawnPoint.y, 10, 10, 50+20*numberOfWaves));
                case 3 -> enemy.add(new Enemy(spawnPoint.x, spawnPoint.y, 20*numberOfWaves, 20*numberOfWaves, 50));
                case 4 -> enemy.add(new Enemy(spawnPoint.x, spawnPoint.y, 20*numberOfWaves, 10, 50+20*numberOfWaves));
                case 5 -> enemy.add(new Enemy(spawnPoint.x, spawnPoint.y, 10, 10*numberOfWaves, 50+20*numberOfWaves));
                case 6 -> enemy.add(new Enemy(spawnPoint.x, spawnPoint.y, 20*numberOfWaves, 20*numberOfWaves, 50+20*numberOfWaves));
            }


    }

    public int getNumOfEnemiesAlive() {
        int numOfEnemiesAlive = 0;
        for (int i = 0; i < getNumOfEnemies(); i++)
            if (!getEnemy()[i].dead())
                numOfEnemiesAlive++;

        return numOfEnemiesAlive;
    }

    public Enemy[] getEnemy() {
        Enemy[] tmp = new Enemy[numOfEnemies];
        tmp = enemy.toArray(tmp);
        return tmp;
    }

    public int getNumOfEnemies() {
        return numOfEnemies;
    }

    public int getSpaceTickCounter() {
        return spaceTickCounter;
    }

    public void setSpaceTickCounter(int spaceTickCounter) {
        this.spaceTickCounter = spaceTickCounter;
    }

    public int getSpaceCounter() {
        return spaceCounter;
    }

    public void setSpaceCounter(int spaceCounter) {
        this.spaceCounter = spaceCounter;
    }

    public static int getNumberOfWaves() {
        return numberOfWaves;
    }
}
