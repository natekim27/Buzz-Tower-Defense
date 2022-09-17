package com.example.towerdefense;

import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.example.towerdefense.enemies.BulldogEnemy;
import com.example.towerdefense.enemies.Enemy;
import com.example.towerdefense.enemies.FinalBoss;
import com.example.towerdefense.enemies.FootballBulldogEnemy;
import com.example.towerdefense.enemies.PrisonBulldogEnemy;
import com.example.towerdefense.towers.BoxingBuzz;
import com.example.towerdefense.towers.CountryBuzz;
import com.example.towerdefense.util.Difficulty;
import com.example.towerdefense.towers.Tower;
import com.example.towerdefense.towers.WizardBuzz;
import com.example.towerdefense.util.Placement;

import java.util.ArrayList;
import java.util.Random;


public class Game {
    private int[] towerCosts;

    private Difficulty difficulty = Difficulty.HARD;
    private int money = 0;
    private int moneySpent = 0;
    private int health = 0;
    private int startingHealth = 0;
    private int level = 1;
    private int numLevels = 3;
    private boolean gameOver = false;
    private Placement currentTower = null;

    private ArrayList<Tower> towers = new ArrayList<>(7);
    private Placement[] placements = new Placement[7];
    private int towersPlaced = 0;

    private Enemy[] enemies;
    private int enemiesKilled = 0;
    private GameListener listener;


    public Game(String difficulty) {
        switch (difficulty) {
        case "Easy":
            money = 500;
            health = 100;
            startingHealth = 100;
            this.difficulty = Difficulty.EASY;
            break;
        case "Medium":
            money = 350;
            health = 70;
            startingHealth = 70;
            this.difficulty = Difficulty.MEDIUM;
            break;
        default:
            money = 150;
            health = 40;
            startingHealth = 40;
            break;
        }
    }


    public void setListener(GameListener listener) {
        this.listener = listener;
    }

    public int getMoney() {
        return money;
    }
    public int getMoneySpent() {return moneySpent;}
    public void setMoney(int newMoney) {
        money = newMoney;
    }

    public int getLevel() {
        return level;
    }
    public int getNumLevels() {
        return numLevels;
    }

    public void setLevel(int levelNum) {
        level = levelNum;
    }

    public boolean getGameOver() {
        return gameOver;
    }
    public void setGameOver(boolean game) {
        gameOver = game;
    }

    public int getHealth() {
        return health;
    }
    public int getStartingHealth() {return startingHealth;}
    public void setHealth(int healthInput) {
        health = healthInput;
    }

    public int getEnemiesKilled() {
        return enemiesKilled;
    }

    public Difficulty getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(Difficulty difficulty) {
        this.difficulty = difficulty;
    }

    public Placement[] getPlacements() {
        return placements;
    }

    public Placement getCurrentTower() {
        return currentTower;
    }

    public int[] getTowerCosts() {
        if (towerCosts == null) {
            towerCosts = new int[]{(int) (money * 0.3), (int) (money * 0.5), (int) (money * 0.8)};
        }
        return towerCosts;
    }

    public Enemy getEnemy(int spot) {
        return enemies[spot];
    }
    public void setEnemy(Enemy enemy, int spot) {
        if (enemies == null) {
            enemies = new Enemy[1];
        }
        enemies[spot] = enemy;
    }

    public Tower purchase(int option) {
        if (getTowerCosts()[option - 1] > money) {
            return null;
        }

        Tower tower;
        switch (option) {
        case 1:
            tower = new BoxingBuzz(difficulty);
            break;
        case 2: tower = new WizardBuzz(difficulty);
            break;
        default:
            tower = new CountryBuzz(difficulty);
            break;
        }

        money -= getTowerCosts()[option - 1];
        towers.add(tower);
        return tower;
    }

    public String placeTower(int spot, float x, float y, ImageView patch) {
        if (placements[spot - 1] == null && towersPlaced < towers.size()) {
            placements[spot - 1] = new Placement(x, y, towers.get(towersPlaced), patch);
            towersPlaced++;
            currentTower = placements[spot - 1];
            return "Tower placed";
        } else if (placements[spot - 1] != null) {
            currentTower = placements[spot - 1];
            return "You already have a tower placed there!";
        } else if (gameOver) {
            currentTower = null;
            return "Game is over!";
        } else {
            currentTower = null;
            return "You don't have a tower to place!";
        }
    }

    public Enemy[] startLevel() {
        return startLevel(-1);
    }

    public Enemy[] startLevel(int num) {
        int levelMultiplier = (int) (6 + level * 2.5);

        // allows manual override of number of enemies
        if (num >= 0) {
            levelMultiplier = num;
        }

        // final level, boss only
        if (level == numLevels) {
            enemies = new Enemy[]{new FinalBoss()};
        } else {
            Random rand = new Random();
            enemies = new Enemy[levelMultiplier];

            // currently, enemies for a level are built randomly with the following chances
            // 50% regular, 30% prison, 20% football
            for (int i = 0; i < levelMultiplier; i++) {
                int val = rand.nextInt(10) + 1;

                if (val <= 5) {
                    enemies[i] = new BulldogEnemy();
                } else if (val <= 8) {
                    enemies[i] = new PrisonBulldogEnemy();
                } else {
                    enemies[i] = new FootballBulldogEnemy();
                }
            }
        }
        return enemies;
    }

    public boolean damageMonument(int attack) {
        health -= attack;
        listener.updateValues();

        if (health <= 0 && !gameOver) {
            gameOver = true;
            listener.gameOver();
        }

        return gameOver;
    }

    public void removeEnemy(Enemy enemy, boolean killed) {
        boolean levelOver = true;

        for (int i = 0; i < this.enemies.length; i++) {
            if (enemy.equals(this.enemies[i])) {
                if (killed) {
                    money += enemy.getMoney();
                    listener.updateValues();
                    enemiesKilled++;

                    if (enemies.length == 1) {
                        gameOver = true;
                        listener.winScreen();
                    }
                }
                this.enemies[i] = null;
            } else if (this.enemies[i] != null) {
                levelOver = false;
            }
        }

        if (levelOver) {
            levelOverIsTrue();
        }
    }

    public void levelOverIsTrue() {
        money += level * 50;
        level++;

        listener.levelOver();
    }


    public void checkEnemyStatus(Enemy enemy, View enemyView) {
        // determine center of enemy (not including health bar)
        float x = enemyView.getX() + 40;
        float y = enemyView.getY() + 120;

        for (Placement placement : placements) {

            if (isWithinProximity(placement, x, y)) {
                if (placement.getTower().attack(enemy)) {
                    if (enemy.getHealth() <= 0) {
                        removeEnemy(enemy, true);
                        enemyView.setVisibility(View.GONE);
                    } else {
                        // decrease health bar
                        ProgressBar healthBar = enemyView.findViewById(R.id.enemyHealthBar);
                        healthBar.setProgress(healthBar.getProgress()
                                - placement.getTower().getDamage());
                    }
                }
            }
        }
    }

    private boolean isWithinProximity(Placement placement, float x, float y) {
        return placement != null && Math.pow(x - placement.getX(), 2) + Math.pow(y
                - placement.getY(), 2) <= Math.pow(placement.getTower().getRange(), 2);
    }

    public String upgrade(String upgrade, Tower tower) {
        String message;
        int[] costs = tower.getUpgradeCosts();
        if (upgrade.equals("damage")) {
            if (money >= costs[0]) {
                money -= costs[0];
                moneySpent += costs[0];

                int old = tower.getDamage();
                tower.upgradeDamage();
                message = "damage from " + old + " to " + tower.getDamage();
                tower.increaseUpgradeCosts(0);
            } else {
                return "Insufficient funds to upgrade damage";
            }
        } else if (upgrade.equals("attack speed")) {
            if (money >= costs[1]) {
                money -= costs[1];
                moneySpent += costs[1];

                int old = tower.getAttackSpeed();
                tower.upgradeAttackSpeed();
                message = "attack cool down from " + old + " to " + tower.getAttackSpeed() + " milliseconds";
                tower.increaseUpgradeCosts(1);
            } else {
                return "Insufficient funds to upgrade attack speed";
            }
        } else {
            if (money >= costs[2]) {
                money -= costs[2];
                moneySpent += costs[2];

                int old = tower.getRange();
                tower.upgradeRange();
                message = "range from " + old + " to " + tower.getRange();
                tower.increaseUpgradeCosts(2);
            } else {
                return "Insufficient funds to upgrade range";
            }
        }
        return "Upgraded " + tower + "'s " + message;
    }

    public String upgrade(ImageView upgrade) {
        if (currentTower != null) {
            if (upgrade.getId() == R.id.upgradeDamage) {
                return upgrade("damage", currentTower.getTower());
            } else if (upgrade.getId() == R.id.upgradeAttackSpeed) {
                return upgrade("attack speed", currentTower.getTower());
            } else {
                return upgrade("range", currentTower.getTower());
            }
        }
        return "Select a tower before trying to upgrade";
    }

    // listener interface for sending messages from logic layer to UI
    public interface GameListener {
        public void updateValues();
        public void levelOver();
        public void gameOver();
        public void winScreen();
    }

}
