package com.example.towerdefense.towers;

import com.example.towerdefense.R;
import com.example.towerdefense.util.Difficulty;

public class BoxingBuzz extends Tower {

    public BoxingBuzz(Difficulty difficulty) {
        super(difficulty, 10, 500, 150);

    }

    @Override
    public int getImage() {
        return R.drawable.boxing_buzz;
    }


    public String toString() {
        return "Boxing Buzz Tower";
    }
}
