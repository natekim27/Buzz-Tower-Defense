package com.M4;

import static org.junit.Assert.assertEquals;

import android.graphics.Path;
import android.graphics.RectF;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.runner.AndroidJUnitRunner;

import com.example.towerdefense.EnemyPath;
import com.example.towerdefense.Game;
import com.example.towerdefense.enemies.BulldogEnemy;
import com.example.towerdefense.enemies.Enemy;
import com.example.towerdefense.enemies.FootballBulldogEnemy;
import com.example.towerdefense.enemies.PrisonBulldogEnemy;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Random;


@RunWith(AndroidJUnit4.class)
public class M4Tests extends AndroidJUnitRunner {
    Game game;

    @Before
    public void initialize() {
        game = new Game("Easy");
        game.setListener(new Game.GameListener() {
            @Override
            public void updateValues() {}

            @Override
            public void levelOver() {}

            @Override
            public void gameOver() {}

            @Override
            public void winScreen() {}
        });
    }

    @Test
    public void returnsVarietyOfPaths() {
        boolean[] paths = new boolean[3];
        RectF rectA = new RectF();
        RectF rectB = new RectF();
        EnemyPath.buildPathA().computeBounds(rectA, true);
        EnemyPath.buildPathB().computeBounds(rectB, true);

        for (int i=0; i < 20; i++) {
            Path path = EnemyPath.makePath();
            RectF rect = new RectF();
            path.computeBounds(rect, true);

            if (rect.bottom == rectA.bottom) {
                paths[0] = true;
            } else if (rect.bottom == rectB.bottom) {
                paths[1] = true;
            } else {
                paths[2] = true;
            }
        }

        assertTrue(paths[0]);
        assertTrue(paths[1]);
        assertTrue(paths[2]);

    }

    @Test
    public void gameOverFlagSetCorrectly() {
        assertFalse(game.getGameOver());
        game.damageMonument(50);
        assertFalse(game.getGameOver());

        game.damageMonument(49);
        assertFalse(game.getGameOver());

        game.damageMonument(1);
        assertTrue(game.getGameOver());

        game.damageMonument(1);
        assertTrue(game.getGameOver());
    }

    @Test
    public void enemyHasCorrectAttributes() {
        Enemy regular = new BulldogEnemy();
        assertEquals(regular.getSpeed(), 10);
        assertEquals(regular.getDamage(), 5);
        assertEquals(regular.getHealth(), 50);

        Enemy football = new FootballBulldogEnemy();
        assertEquals(football.getSpeed(), 3);
        assertEquals(football.getDamage(), 10);
        assertEquals(football.getHealth(), 100);

        Enemy prison = new PrisonBulldogEnemy();
        assertEquals(prison.getSpeed(), 20);
        assertEquals(prison.getDamage(), 7);
        assertEquals(prison.getHealth(), 40);
    }

    @Test
    public void enemyHasCorrectString() {
        Enemy regular = new BulldogEnemy();
        assertEquals(regular.toString(),
                "Regular Bulldog with damage: " + regular.getDamage() + ", health: " +
                        regular.getHealth() + ", and speed: " + regular.getSpeed() + ".");

        Enemy football = new FootballBulldogEnemy();
        assertEquals(football.toString(),
                "Football Bulldog with damage: " + football.getDamage() + ", health: " +
                        football.getHealth() + ", and speed: " + football.getSpeed() + ".");


        Enemy prison = new PrisonBulldogEnemy();
        assertEquals(prison.toString(),
                "Prison Bulldog with damage: " + prison.getDamage() + ", health: " +
                        prison.getHealth() + ", and speed: " + prison.getSpeed() + ".");
    }


    public void startLevelReturnsParameterEnemy() {
        Enemy[] enemies = game.startLevel(10);
        assertEquals(10, enemies.length);

        enemies = game.startLevel(0);
        assertEquals(0, enemies.length);
    }

    @Test
    public void startLevelReturnsLevelMultiplierEnemy() {
        Enemy[] enemies = game.startLevel();
        assertEquals(8, enemies.length);

        enemies = game.startLevel();
        assertEquals(11, enemies.length);

        enemies = game.startLevel();
        assertEquals(13, enemies.length);

        enemies = game.startLevel();
        assertEquals(16, enemies.length);
    }
    @Test
    public void cantPlaceTowerAfterGameOver() {
        game.setGameOver(true);
        String str = game.placeTower(1, 0, 0, null);
        assertEquals("Game is over!", str);
    }

    @Test
    public void enemiesDamageMonument() {
        game.setHealth(50);
        Enemy regular = new BulldogEnemy();
        game.damageMonument(regular.getDamage());
        assertEquals(45, game.getHealth());
    }

    @Test
    public void testDamageMonumentEndsGame() {
        game.setHealth(50);
        assertFalse(game.damageMonument(45));
        assertTrue(game.damageMonument(5));
    }

    @Test
    public void damageMonument(){
        game.setHealth(100);
        Random r = new Random();
        int low = 0;
        int high = 99;
        int result = r.nextInt(high-low) + low;
        game.damageMonument(result);
        assertEquals(100 - result, game.getHealth());
    }

    @Test
    public void removeEnemy() {
        int oldMoney = game.getMoney();
        Enemy[] enemies = game.startLevel();
        int enemyLength = enemies.length;
        for (int i = 0; i < enemyLength; i++) {
            game.removeEnemy(enemies[i], false);
        }
        assertEquals(game.getLevel() * 50, game.getMoney() - oldMoney);
    }
}
