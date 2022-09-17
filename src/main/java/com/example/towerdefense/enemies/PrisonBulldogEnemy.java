package com.example.towerdefense.enemies;

import com.example.towerdefense.R;

public class PrisonBulldogEnemy extends Enemy {

    public PrisonBulldogEnemy() {
        super(20, 7, 40, 15);
    }

    @Override
    public int getImage() {
        return R.drawable.prison_bulldog;
    }

    @Override
    public String toString() {
        return "Prison " + super.toString();
    }
}