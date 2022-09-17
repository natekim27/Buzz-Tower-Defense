package com.example.towerdefense.enemies;

public abstract class Enemy {
    private int speed;
    private int damage;
    private int health;
    private int money;

    /**
     * Attribute matrix for 3 Enemies
     * ----------------------------------------
     * |             Speed | Damage | Health  |
     * | Regular:     10      5       50      |
     * | Football:    3       10      100     |
     * | Prison:      20      7       40      |
     * ----------------------------------------
     */

    /**
     * 4 arg constructor
     * @param speed int representing speed
     * @param damage int representing damage
     * @param health int representing health
     * @param money int representing money player gets when enemy is defeated
     */
    public Enemy(int speed, int damage, int health, int money) {
        this.speed = speed;
        this.damage = damage;
        this.health = health;
        this.money = money;
    }

    public abstract int getImage();
    public int getSpeed() {
        return speed;
    }
    public int getHealth() {
        return health;
    }
    public int getDamage() {
        return damage;
    }
    public int getMoney() {
        return money;
    }

    public void loseHealth(int attackAmount) {
        this.health -= attackAmount;
    }

    public String toString() {
        return "Bulldog with damage: " + damage + ", health: " + health + ", and speed: "
                + speed + ".";
    }

}
