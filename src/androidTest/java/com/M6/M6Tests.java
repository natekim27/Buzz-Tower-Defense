package com.M6;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import android.graphics.drawable.GradientDrawable;
import android.widget.ImageView;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.runner.AndroidJUnitRunner;

import com.example.towerdefense.enemies.FinalBoss;
import com.example.towerdefense.towers.CountryBuzz;
import com.example.towerdefense.towers.Tower;
import com.example.towerdefense.enemies.BulldogEnemy;
import com.example.towerdefense.enemies.Enemy;
import com.example.towerdefense.enemies.FootballBulldogEnemy;
import com.example.towerdefense.enemies.PrisonBulldogEnemy;
import com.example.towerdefense.Game;
import com.example.towerdefense.towers.BoxingBuzz;
import com.example.towerdefense.towers.WizardBuzz;
import com.example.towerdefense.util.Difficulty;
import com.example.towerdefense.util.DrawableHelper;
import com.example.towerdefense.util.Placement;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.stream.IntStream;

@RunWith(AndroidJUnit4.class)
public class M6Tests extends AndroidJUnitRunner {
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
    public void checkFinalBoss() {
        Enemy finalBoss = new FinalBoss();
        assertEquals(500, finalBoss.getHealth());
        assertEquals(0, finalBoss.getSpeed());
        assertEquals(100, finalBoss.getDamage());
        assertEquals(1000, finalBoss.getMoney());
    }

    @Test
    public void upgradeReturnMessage() {
        Tower newTower = new WizardBuzz(game.getDifficulty());
        int oldRange = newTower.getRange();
        newTower.upgradeRange();
        int newRange = newTower.getRange();
        assertEquals(oldRange * 1.5, newRange, 0.0);
    }

    @Test
    public void upgradeDamage() {
        Tower countryBuzz = new CountryBuzz(game.getDifficulty());
        int originalDamage = countryBuzz.getDamage();
        countryBuzz.upgradeDamage();
        int upgradedDamage = countryBuzz.getDamage();
        assertEquals(originalDamage * 1.5, upgradedDamage, 0.0);
    }

    @Test
    public void upgradeAttackSpeed() {
        Tower countryBuzz = new CountryBuzz(game.getDifficulty());
        int originalAttackSpeed = countryBuzz.getAttackSpeed();
        countryBuzz.upgradeAttackSpeed();
        int upgradedAttackSpeed = countryBuzz.getAttackSpeed();
        assertEquals(originalAttackSpeed * .75, upgradedAttackSpeed, 0.0);
    }

    @Test
    public void finalBossAppearsOnLastLevel() {
        while (game.getLevel() < game.getNumLevels()) {
            game.startLevel();
            game.levelOverIsTrue();
        }
        Enemy[] enemies = game.startLevel();
        assertEquals(1, enemies.length);
        assertEquals(FinalBoss.class, enemies[0].getClass());
    }

    @Test
    public void preventsUpgradesWithInsufficientMoney() {
        game.setMoney(49);
        assertEquals("Insufficient funds to upgrade damage",
                game.upgrade("damage", new BoxingBuzz(game.getDifficulty())));
        assertEquals("Insufficient funds to upgrade attack speed",
                game.upgrade("attack speed", new BoxingBuzz(game.getDifficulty())));
        assertEquals("Insufficient funds to upgrade range",
                game.upgrade("range", new BoxingBuzz(game.getDifficulty())));

        game.setMoney(50);
        assertNotEquals("Insufficient funds to upgrade range",
                game.upgrade("range", new BoxingBuzz(game.getDifficulty())));
    }

    @Test
    public void killFinalBossWinsGame() {
        initialize();

        Enemy enemy = new FinalBoss();
        game.setEnemy(enemy, 0);
        game.removeEnemy(enemy, true);

        assertTrue(game.getGameOver());
    }

    @Test
    public void statisticsCheck() {
        initialize();

        game.setMoney(50);
        game.upgrade("range", new BoxingBuzz(game.getDifficulty()));
        assertEquals(50, game.getMoneySpent());

        game.setHealth(100);
        game.damageMonument(10);
        assertEquals(10, game.getStartingHealth() - game.getHealth());

        Enemy enemy = new FinalBoss();
        game.setEnemy(enemy, 0);
        game.removeEnemy(enemy, true);
        assertEquals(1, game.getEnemiesKilled());
    }

    @Test
    public void reduceGameMoney() {
        game.setMoney(1000);
        Tower baseTower = new CountryBuzz(game.getDifficulty());
        int[] costs = baseTower.getUpgradeCosts();
        int moneyCost = IntStream.of(costs).sum();
        game.upgrade("damage", baseTower);
        game.upgrade("attack speed", baseTower);
        game.upgrade("", baseTower);
        costs = baseTower.getUpgradeCosts();
        moneyCost += IntStream.of(costs).sum();
        game.upgrade("damage", baseTower);
        game.upgrade("attack speed", baseTower);
        game.upgrade("", baseTower);
        assertEquals(moneyCost, 1000 - game.getMoney());
    }

    @Test
    public void initialUpgradeCosts() {
        Tower baseTower = new CountryBuzz(game.getDifficulty());
        int[] costs = baseTower.getUpgradeCosts();
        assertTrue(costs[0] == 50 && costs[1] == 50 && costs[2] == 50);
    }
}
