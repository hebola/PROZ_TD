package pw.proz;

import Entity.Enemy;
import Entity.TowerArmor;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;

public class TATest {
    @Test
    public void testTowerAttackOneEnemy() {

        Enemy[] enemy = new Enemy[5];
        TowerArmor towerArmor=new TowerArmor(1,55,1,2);

        for (int i = 0; i < 5; i++)
            enemy[i]=new Enemy(1,1);

        towerArmor.attack(enemy);

        for (int i = 0; i < 5; i++)
        System.out.println(i + " " + enemy[i].getHitPoints());

    }
}




