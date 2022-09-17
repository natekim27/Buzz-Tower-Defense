package com.example.towerdefense.enemies;

import com.example.towerdefense.R;

public class BulldogEnemy extends Enemy {

    public BulldogEnemy() {
        super(10, 5, 50, 10);
    }

    @Override
    public int getImage() {
        return R.drawable.bulldog;
    }

    @Override
    public String toString() {
        return "Regular " + super.toString();
    }
}

