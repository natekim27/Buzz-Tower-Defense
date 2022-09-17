package com.example.towerdefense.util;

import android.widget.ImageView;

import com.example.towerdefense.towers.Tower;

public class Placement {
    private float x;
    private float y;
    private Tower tower;
    private ImageView proxCircle;

    public Placement(float x, float y, Tower tower, ImageView proxCircle) {
        this.x = x;
        this.y = y;
        this.tower = tower;
        this.proxCircle = proxCircle;
        // TODO separate generate and display functions of proxCircle
    }

    public Tower getTower() {
        return tower;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }
}
