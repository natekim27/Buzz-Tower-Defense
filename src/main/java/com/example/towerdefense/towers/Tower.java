package com.example.towerdefense.towers;

import static android.os.SystemClock.uptimeMillis;

import com.example.towerdefense.enemies.Enemy;
import com.example.towerdefense.util.Difficulty;

public abstract class Tower {
    private int damage;
    private int attackSpeed; // 1000 -> 1 second
    private boolean isPlaced = false;
    private Difficulty difficulty;
    private int range;
    private int[] upgradeCosts = {50, 50, 50};

    private long lastAttack = 0;

    public Tower(Difficulty difficulty, int damage, int attackSpeed, int range) {
        this.difficulty = difficulty;
        this.damage = damage;
        this.attackSpeed = attackSpeed;
        this.range = range;
    }

    public boolean attack(Enemy enemy) {
        if (uptimeMillis() - lastAttack > attackSpeed) {
            enemy.loseHealth(this.damage);
            System.out.println("damaged by: " + damage);
            lastAttack = uptimeMillis();
            return true;
        }
        return false;
    }

    public int getRange() {
        return range;
    }

    public int getDamage() {
        return damage;
    }

    public int getAttackSpeed() {
        return attackSpeed;
    }

    public int[] getUpgradeCosts() {
        return upgradeCosts;
    }

    public void increaseUpgradeCosts(int index) {
        this.upgradeCosts[index] = (int) (this.upgradeCosts[index] * 1.5);
    }

    public abstract int getImage();

    public String toString() {
        return "Tower";
    }

    public void upgradeRange() { range *= 1.5; }

    public void upgradeDamage() {
        damage *= 1.5;
    }

    public void upgradeAttackSpeed() {
        attackSpeed *= 0.75;
    }
}
