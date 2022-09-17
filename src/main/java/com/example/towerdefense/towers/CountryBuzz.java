package com.example.towerdefense.towers;

import com.example.towerdefense.R;
import com.example.towerdefense.util.Difficulty;

public class CountryBuzz extends Tower {

    public CountryBuzz(Difficulty difficulty) {
        super(difficulty, 20, 1500, 300);

    }

    @Override
    public int getImage() {
        return R.drawable.country_buzz;
    }

    public String toString() {
        return "Country Buzz Tower";
    }
}
