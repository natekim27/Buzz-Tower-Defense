package com.M5;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import android.graphics.drawable.GradientDrawable;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.runner.AndroidJUnitRunner;

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

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class M5Tests extends AndroidJUnitRunner {
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
    public void checkEnemyLoseHealth() {
        Enemy bulldog = new BulldogEnemy();
        Enemy prisonDog = new PrisonBulldogEnemy();
        Enemy footballDog = new FootballBulldogEnemy();
        Tower tower = new BoxingBuzz(Difficulty.EASY);
        bulldog.loseHealth(tower.getDamage());
        prisonDog.loseHealth(tower.getDamage());
        footballDog.loseHealth(tower.getDamage());

        assertTrue(bulldog.getHealth() == 40);
        assertTrue(prisonDog.getHealth() == 30);
        assertTrue(footballDog.getHealth() == 90);
    }

    @Test
    public void checkTowerAttack() {
        Enemy bulldog1 = new BulldogEnemy();
        Enemy bulldog2 = new BulldogEnemy();
        Enemy bulldog3 = new BulldogEnemy();
        Tower countryBuzzTower = new CountryBuzz(Difficulty.EASY);
        Tower boxingBuzzTower = new BoxingBuzz(Difficulty.EASY);
        Tower wizardBuzzTower = new WizardBuzz(Difficulty.EASY);
        countryBuzzTower.attack(bulldog1);
        boxingBuzzTower.attack(bulldog2);
        wizardBuzzTower.attack(bulldog3);

        assertTrue(bulldog1.getHealth() == 30);
        assertTrue(bulldog2.getHealth() == 40);
        assertTrue(bulldog3.getHealth() == 35);
    }

    @Test
    public void checkTowerRanges() {
        Tower tower1 = new CountryBuzz(Difficulty.EASY);
        Tower tower2 = new BoxingBuzz(Difficulty.EASY);
        Tower tower3 = new WizardBuzz(Difficulty.EASY);
        assertTrue(tower1.getRange() != tower2.getRange() &&
                tower2.getRange() != tower3.getRange() &&
                tower3.getRange() != tower2.getRange());
    }

    @Test
    public void checkEnemyGameplay() {
        Enemy enemy1 = new BulldogEnemy();
        Enemy enemy2 = new PrisonBulldogEnemy();
        Enemy enemy3 = new FootballBulldogEnemy();
        assertTrue(enemy1.getDamage() != enemy2.getDamage() &&
                enemy1.getHealth() != enemy2.getHealth() &&
                enemy2.getDamage() != enemy3.getDamage() &&
                enemy2.getHealth() != enemy3.getHealth() &&
                enemy1.getDamage() != enemy3.getDamage() &&
                enemy1.getHealth() != enemy3.getHealth());
    }

    @Test
    public void enemiesGiveDifferentMoney() {
        Enemy enemy1 = new BulldogEnemy();
        Enemy enemy2 = new PrisonBulldogEnemy();
        Enemy enemy3 = new FootballBulldogEnemy();

        game.setEnemy(enemy1, 0);
        game.setEnemy(enemy2, 1);
        game.setEnemy(enemy3, 2);

        int money = game.getMoney();

        game.removeEnemy(enemy1, true);
        int money1 = game.getMoney() - money;
        game.removeEnemy(enemy2, true);
        int money2 = game.getMoney() - money1;
        game.removeEnemy(enemy3, true);
        int money3 = game.getMoney() - money2;

        assertTrue(money1 != money2 && money1 != money3 && money2 != money3);
    }

    @Test
    public void enemiesDisappear() {
        Enemy enemy1 = new BulldogEnemy();
        game.setEnemy(enemy1, 0);
        game.removeEnemy(enemy1, true);

        assertTrue(null == game.getEnemy(0));
    }

    @Test
    public void checkStartLevel() {
        Game game1 = new Game("Easy");
        game1.setLevel(1);
        Enemy[] level1 = game1.startLevel();

        Game game2 = new Game("Easy");
        game2.setLevel(3);
        Enemy[] level2 = game2.startLevel();

        assertTrue(level2.length > level1.length);
    }

    @Test
    public void checkLevelOverMoneyGain() {
        int prevMoney = game.getMoney();
        game.levelOverIsTrue();
        int expectedMoney = prevMoney + game.getLevel() * 50;
        int moneyAfter = game.getMoney();

        assertTrue(expectedMoney == moneyAfter);
    }

    @Test
    public void drawableCircleTest() {
        GradientDrawable shape = DrawableHelper.buildCircle();
        assertNotNull(shape.getColor().toString());
        assertEquals(shape.getShape(), GradientDrawable.OVAL);
    }

    @Test
    public void testTowerPurchaseDuringCombat() {
        assertNotNull(game.purchase(1));
        game.startLevel();
        assertNotNull(game.purchase(1));
    }

}
