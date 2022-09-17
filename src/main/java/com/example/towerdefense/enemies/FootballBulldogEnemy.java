package com.example.towerdefense.enemies;

import com.example.towerdefense.R;

public class FootballBulldogEnemy extends Enemy {

    public FootballBulldogEnemy() {
        super(3, 10, 100, 25);
    }

    @Override
    public int getImage() {
        return R.drawable.football_bulldog;
    }

    @Override
    public String toString() {
        return "Football " + super.toString();
    }
}

