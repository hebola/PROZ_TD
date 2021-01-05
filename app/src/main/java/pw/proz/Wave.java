package pw.proz;

import Entity.Enemy;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Wave {
    private int numOfEnemies;
    private int numOfEnemiesAlive;
    private int spaceBetweenEnemies;
    private Point spawnPoint=App.getSpawn().getPosition();
    private List<Enemy> enemy;

    public int getSpaceBetweenEnemies() {
        return spaceBetweenEnemies;
    }

    Wave(int numOfEnemies) {
        this.numOfEnemies = numOfEnemies;
        this.numOfEnemiesAlive = numOfEnemies;
        this.spaceBetweenEnemies = 15;
        enemy = new ArrayList<>();

        for (int i = 0; i < numOfEnemies; i++)
            enemy.add(new Enemy(spawnPoint.x, spawnPoint.y));
    }

    public int getNumOfEnemiesAlive() {
        return numOfEnemiesAlive;
    }

    public Enemy[] getEnemy() {
        Enemy[] tmp =  new Enemy[numOfEnemies];
        tmp = enemy.toArray(tmp);
        return tmp;
    }

    public int getNumOfEnemies() {
        return numOfEnemies;
    }

}
