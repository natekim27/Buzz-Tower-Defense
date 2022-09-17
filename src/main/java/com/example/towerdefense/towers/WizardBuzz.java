package com.example.towerdefense.towers;

import com.example.towerdefense.R;
import com.example.towerdefense.util.Difficulty;

public class WizardBuzz extends Tower {

    public WizardBuzz(Difficulty difficulty) {
        super(difficulty, 15, 750, 250);

    }

    @Override
    public int getImage() {
        return R.drawable.wizard_buzz;
    }

    public String toString() {
        return "Wizard Buzz Tower";
    }
}
