package com.example.towerdefense.enemies;

import com.example.towerdefense.R;

public class FinalBoss extends Enemy {

    public FinalBoss() { super(0, 100, 500, 1000); }

    @Override
    public int getImage() {
        return R.drawable.final_boss;
    }

    @Override
    public String toString() {
        return "Final Boss " + super.toString();
    }
}


