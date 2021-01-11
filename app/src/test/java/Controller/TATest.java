package Controller;

import Model.Enemy;
import Model.TowerArmor;
import org.junit.Test;

import static org.junit.Assert.*;

public class TATest {
    @Test
    public void testTowerAttackOneEnemy() {

        Enemy[] enemy = new Enemy[5];
        TowerArmor towerArmor = new TowerArmor(1, 55, 1, 2);

        for (int i = 0; i < 5; i++)
            enemy[i] = new Enemy(20 + 40 * 1, 20 + 40 * 1, 0, 0, 10);

        towerArmor.attack(enemy);

        for (int i = 0; i < 5; i++)
            if (i == 0)
                assertEquals(9, (long) enemy[i].getHitPoints());
            else
                assertEquals(10, (long) enemy[i].getHitPoints());


    }
}




